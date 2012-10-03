package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.emulated.AbstractEmulatedProperty;
import org.opaeum.eclipse.emulated.AssociationClassToEnd;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.eclipse.emulated.InverseArtificialProperty;
import org.opaeum.eclipse.emulated.NonInverseArtificialProperty;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmfPropertyUtil{
	public static boolean isDerived(Property p){
		return p.isDerived() || p.isDerivedUnion();
	}
	public static Set<Property> getUniquenessConstraints(Classifier c){
		Set<Property> result = new TreeSet<Property>(new ElementComparator());
		for(Property property:getDirectlyImplementedAttributes(c)){
			if(!property.isDerived() && !property.isDerivedUnion() && property.getOtherEnd() != null
					&& property.getOtherEnd().getQualifiers().size() > 0 && property.getOtherEnd().getUpper() == 1){
				result.add(property);
			}
		}
		return result;
	}
	public static Set<Property> getDirectlyImplementedAttributes(Classifier c){
		// NB remember that there might be properties specified by this class' interfaces that have already been implemented by a superclass
		Set<String> inheritedConcretePropertyNames = new TreeSet<String>();
		for(Generalization g:c.getGeneralizations()){
			for(Property p:getDirectlyImplementedAttributes(g.getGeneral())){
				inheritedConcretePropertyNames.add(p.getName());
			}
		}
		Set<Property> results = new TreeSet<Property>(new ElementComparator());
		List<Property> effectiveAttributes = getEffectiveAttributes(c);
		for(Property p:effectiveAttributes){
			if(EmfPropertyUtil.getOwningClassifier(p) == c || !inheritedConcretePropertyNames.contains(p.getName())){
				for(Property rp:p.getRedefinedProperties()){
					if(!rp.getName().equals(p.getName())){
						// We need to redeclare the redefined properties to ensure that their accessors are available in the subclass for redefinition
						results.add(rp);
					}
				}
				for(Property rp:p.getSubsettedProperties()){
					if(!rp.getName().equals(p.getName()) && !inheritedConcretePropertyNames.contains(rp.getName())){
						// We need to redeclare the subsetted properties to ensure that their getters are available in the subclass for subsetting
						results.add(rp);
					}
				}
				results.add(p);
			}
		}
		return results;
	}
	private static List<Property> getEffectiveAttributes(Classifier c){
		return EmfElementFinder.getPropertiesInScope(c);
	}
	public static boolean isDimension(TypedElement te){
		return fullfillsRoleInCube(te, "DIMENSION");
	}
	public static boolean isMeasure(TypedElement te){
		return fullfillsRoleInCube(te, "MEASURE");
	}
	private static boolean fullfillsRoleInCube(TypedElement te,String role){
		if(EmfPropertyUtil.isMany(te)){
			return false;
		}
		for(Stereotype st:te.getAppliedStereotypes()){
			Property roleInCube = st.getAttribute("roleInCube", null);
			if(roleInCube != null){
				EnumerationLiteral l = (EnumerationLiteral) te.getValue(st, "roleInCube");
				return l.getName().toUpperCase().equals(role);
			}
		}
		return false;
	}
	public static Property getEndToComposite(Classifier c){
		Property result = getImmediateEndToComposite(c);
		if(result == null){
			Iterator<Classifier> classes = c.getGenerals().iterator();
			while(classes.hasNext() && result == null){
				result = getImmediateEndToComposite(classes.next());
			}
			if(result == null && c instanceof BehavioredClassifier){
				Iterator<Interface> interfaces = ((BehavioredClassifier) c).getImplementedInterfaces().iterator();
				while(interfaces.hasNext() && result == null){
					result = getImmediateEndToComposite(interfaces.next());
				}
			}
		}
		return result;
	}
	public static CollectionKind getCollectionKind(MultiplicityElement exp){
		if(exp == null){
			return null;
		}
		if(exp.isMultivalued()){
			if(exp.isOrdered()){
				if(exp.isUnique()){
					return CollectionKind.ORDERED_SET_LITERAL;
				}else{
					return CollectionKind.SEQUENCE_LITERAL;
				}
			}else{
				if(exp.isUnique()){
					return CollectionKind.SET_LITERAL;
				}else{
					return CollectionKind.BAG_LITERAL;
				}
			}
		}
		return null;
	}
	private static Property getImmediateEndToComposite(Classifier c){
		Property result = null;
		for(Association association:c.getAssociations()){
			for(Property property:association.getMemberEnds()){
				if(property.getType() == c && property.isComposite() && property.getOtherEnd().isNavigable()){
					result = property.getOtherEnd();
					break;
				}
			}
		}
		if(result == null){
			for(Property p:c.getAttributes()){
				if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
					result = p;
					break;
				}
			}
		}
		return result;
	}
	public static boolean isMany(TypedElement typedElement){
		MultiplicityElement me = (MultiplicityElement) typedElement;
		if(typedElement instanceof Property){
			if(((Property) typedElement).getQualifiers().size() > 0){
				return true;
			}
		}
		return me.isMultivalued();
	}
	public static boolean isComposite(TypedElement t){
		if(t instanceof Property){
			Property p = (Property) t;
			return p.isComposite();
		}
		return false;
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public static Collection<TypedElement> getOwnedTypedElements(Element e){
		if(e instanceof Class){
			Class class1 = (Class) e;
			if(class1 != null){
				return new ArrayList<TypedElement>((Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(class1));
			}
		}else if(e instanceof State){
			State class1 = (State) e;
			if(class1 != null){
				return new ArrayList<TypedElement>(EmfStateMachineUtil.getStateMachine(class1).getAllAttributes());
			}
		}else if(e instanceof Operation){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>(operation.getOwnedParameters());
			}
		}else if(e instanceof Operation){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>(operation.getOwnedParameters());
			}
		}else if(e instanceof OpaqueAction){
			OpaqueAction action = (OpaqueAction) e;
			if(action != null){
				Collection<Pin> result = new TreeSet<Pin>(new ElementComparator());
				result.addAll(action.getInputs());
				result.addAll(action.getOutputs());
			}
		}
		return Collections.emptySet();
	}
	public static boolean isRequired(MultiplicityElement otherEnd){
		if(otherEnd.getLower() == 1){
			return true;
		}else if(otherEnd instanceof Property){
			Property p = (Property) otherEnd;
			return isQualifier(p) || (p.getOtherEnd() != null && p.getOtherEnd().getQualifiers().size() > 0);
		}
		return false;
	}
	public static boolean isQualifier(Property p){
		Classifier c = (Classifier) EmfElementFinder.getContainer(p);
		List<Property> propertiesInScope = EmfElementFinder.getPropertiesInScope(c);
		for(Property property:propertiesInScope){
			if(property.getOtherEnd() != null){
				for(Property q:property.getOtherEnd().getQualifiers()){
					if(p.getName().equals(q.getName())){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isInverse(Property f){
		if(f.getOtherEnd() == null || !f.getOtherEnd().isNavigable()){
			return false;
		}else{
			if(f instanceof EndToAssociationClass){
				return true;
			}else if(f instanceof AssociationClassToEnd){
				return false;
			}else if(f instanceof NonInverseArtificialProperty){
				return false;
			}else if(f instanceof InverseArtificialProperty){
				return true;
			}else if(isMany(f)){
				if(isMany(f.getOtherEnd())){
					return f.getAssociation().getMemberEnds().indexOf(f) == 1;
				}else{
					return true;
				}
			}else{
				if(isMany(f.getOtherEnd())){
					return false;
				}else{
					return f.getAssociation().getMemberEnds().indexOf(f) == 1;
				}
			}
		}
	}
	public static Classifier getOwningClassifier(Property p){
		if(p instanceof AbstractEmulatedProperty){
			return (Classifier) p.getOwner();
		}
		return (Classifier) EmfElementFinder.getContainer(p);
	}
	public static boolean isMarkedAsPrimaryKey(Property property){
		for(Stereotype st:property.getAppliedStereotypes()){
			if(st.getMember("isPrimaryKey") != null){
				return Boolean.TRUE.equals(property.getValue(st, "isPrimaryKey"));
			}
		}
		return false;
	}
	public static boolean isDiscriminator(Property attr){
		if(attr.getType() instanceof Enumeration){
			Enumeration en = (Enumeration) attr.getType();
			return en.getPowertypeExtents().size() > 0;
		}
		return false;
	}
	public static Collection<Property> getPropertiesQualified(Property property){
		if(property.getOtherEnd() != null){
			Type owner = property.getOtherEnd().getType();
			Classifier otherClass = (Classifier) property.getType();
			Collection<Property> result = new TreeSet<Property>(new ElementComparator());
			for(Association association:otherClass.getAssociations()){
				EList<Property> memberEnds = association.getMemberEnds();
				for(Property potentialQualifiedProperty:memberEnds){
					if(potentialQualifiedProperty.getType().equals(owner)){
						for(Property q:potentialQualifiedProperty.getQualifiers()){
							if(q.getName().equals(property.getName())){
								result.add(potentialQualifiedProperty);
								break;
							}
						}
					}
				}
			}
			return result;
		}
		return Collections.emptySet();
	}
	public static Property getNameProperty(Classifier toClass){
		NamedElement member = toClass.getMember("name");
		if(member instanceof Property){
			return (Property) member;
		}else{
			for(Stereotype stereotype:toClass.getAppliedStereotypes()){
				if(stereotype.getMember("nameProperty") != null){
					return (Property) toClass.getValue(stereotype, "nameProperty");
				}
			}
		}
		return null;
	}
	public static boolean isOneToOne(Property p){
		if(p.getOtherEnd() == null){
			return false;
		}else{
			return !(isMany(p) || isMany(p.getOtherEnd()));
		}
	}
	public static Property getBackingPropertyForQualifier(Property q){
		Classifier type = (Classifier) q.getAssociationEnd().getType();
		return (Property) type.getMember(q.getName());
	}
	public static Collection<Property> getSubsettingProperties(Property p){
		Collection<Setting> ref = ECrossReferenceAdapter.getCrossReferenceAdapter(p).getNonNavigableInverseReferences(p);
		Collection<Property> result = new HashSet<Property>();
		for(Setting setting:ref){
			if(setting.getEObject() instanceof Property
					&& setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getProperty_SubsettedProperty())){
				result.add((Property) setting.getEObject());
			}
		}
		return result;
	}
	public static boolean isEndToComposite(TypedElement property){
		return  property instanceof Property && ((Property) property).isNavigable() && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite();
	}
}
