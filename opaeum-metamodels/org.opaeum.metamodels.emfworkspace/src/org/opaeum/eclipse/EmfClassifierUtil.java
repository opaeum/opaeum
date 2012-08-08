package org.opaeum.eclipse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class EmfClassifierUtil{
	public static CodeGenerationStrategy getCodeGenerationStrategy(NamedElement c){
		EList<Stereotype> appliedStereotypes = c.getAppliedStereotypes();
		for(Stereotype st:appliedStereotypes){
			CodeGenerationStrategy codeGenerationStrategy = null;
			if(st.getAttribute(TagNames.MAPPED_IMPLEMENTATION_TYPE, null) != null
					|| st.getAttribute(TagNames.MAPPED_IMPLEMENTATION_PACKAGE, null) != null){
				codeGenerationStrategy = CodeGenerationStrategy.NO_CODE;
			}
			if(st.getAttribute(TagNames.CODE_GENERATION_STRATEGY, null) != null){
				EnumerationLiteral l = (EnumerationLiteral) c.getValue(st, TagNames.CODE_GENERATION_STRATEGY);
				codeGenerationStrategy = Enum.valueOf(CodeGenerationStrategy.class, l.getName().toUpperCase());
			}
			if(codeGenerationStrategy != null){
				return codeGenerationStrategy;
			}
		}
		return CodeGenerationStrategy.ALL;
	}
	public static boolean hasMappedImplementationType(Classifier classifier){
		String mit = getMappedImplementationType(classifier);
		return mit != null && mit.trim().length() > 0;
	}
	public static boolean conformsTo(CollectionType from,CollectionType to){
		if(from.getKind() == CollectionKind.COLLECTION_LITERAL || from.getKind() == to.getKind()){
			return from.getElementType().conformsTo(to.getElementType());
		}else{
			return false;
		}
	}
	public static String getMappedImplementationType(Classifier classifier){
		return (String) getTagValue(classifier, TagNames.MAPPED_IMPLEMENTATION_TYPE);
	}
	private static Object getTagValue(Classifier dt,String mappedImplementationType){
		EList<Stereotype> appliedStereotypes = dt.getAppliedStereotypes();
		for(Stereotype st:appliedStereotypes){
			if(st.getDefinition().getEStructuralFeature(mappedImplementationType) != null){
				return dt.getValue(st, mappedImplementationType);
			}
		}
		return null;
	}
	public static boolean hasStrategy(DataType dt,java.lang.Class<? extends ISimpleTypeStrategy> strat){
		AbstractStrategyFactory stf = getStrategyFactory(dt);
		if(stf != null){
			return stf.hasStrategy(strat);
		}else if(dt.getGenerals().size() >= 1 && dt.getGenerals().get(0) instanceof DataType){
			return hasStrategy((DataType) dt.getGenerals().get(0), strat);
		}else{
			return false;
		}
	}
	public static <T extends ISimpleTypeStrategy>T getStrategy(DataType dt,java.lang.Class<T> strat){
		AbstractStrategyFactory stf = getStrategyFactory(dt);
		if(stf != null){
			return stf.getStrategy(strat);
		}else if(dt.getGenerals().size() >= 1 && dt.getGenerals().get(0) instanceof DataType){
			return getStrategy((DataType) dt.getGenerals().get(0), strat);
		}else{
			return null;
		}
	}
	public static AbstractStrategyFactory getStrategyFactory(DataType dt){
		String tagName = "strategyFactory";
		Object value = getTagValue(dt, tagName);
		AbstractStrategyFactory stf = null;
		if(value != null){
			stf = (AbstractStrategyFactory) IntrospectionUtil.newInstance((String) value);
		}
		return stf;
	}
	private static Object getTagValue(DataType dt,String tagName){
		Stereotype st = null;
		if(dt instanceof PrimitiveType){
			st = StereotypesHelper.getStereotype(dt, StereotypeNames.PRIMITIVE_TYPE);
		}else{
			st = StereotypesHelper.getStereotype(dt, StereotypeNames.VALUE_TYPE);
		}
		Object value = null;
		if(st != null){
			value = dt.getValue(st, tagName);
		}
		return value;
	}
	public static Classifier findCommonSuperType(Classifier from,Classifier to){
		Classifier result = null;
		if(from.conformsTo(to)){
			result = to;
		}else if(to.conformsTo(from)){
			result = from;
		}
		if(result == null){
			for(Generalization supr:from.getGeneralizations()){
				result = findCommonSuperType(supr.getGeneral(), to);
				if(result != null){
					break;
				}
			}
		}
		return result;
	}
	public static boolean conformsTo(Classifier from,Classifier to){
		if(from.equals(to)){
			return true;
		}else if(from.allParents().contains(to)){
			return true;
		}else if(from instanceof BehavioredClassifier){
			for(InterfaceRealization i:((BehavioredClassifier) from).getInterfaceRealizations()){
				if(i.getContract().equals(to) || i.getContract().allParents().contains(to)){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean comformsToLibraryType(Type type,String string){
		if(type.getName() != null && type.getName().equalsIgnoreCase(string)){
			return true;
		}else if(type instanceof Classifier){
			for(Classifier g:((Classifier) type).getGenerals()){
				if(comformsToLibraryType(g, string)){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isSimpleType(Type type){
		return type instanceof PrimitiveType
				|| (type.eClass().equals(UMLPackage.eINSTANCE.getDataType()) || StereotypesHelper.hasStereotype(type, StereotypeNames.VALUE_TYPE));
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public static Collection<BehavioredClassifier> getConcreteEntityImplementationsOf(Interface baseType,Collection<Package> models){
		SortedSet<Classifier> results = new TreeSet<Classifier>(new DefaultOpaeumComparator());
		addConcreteSubclasses(results, baseType, models, true);
		results.remove(baseType);
		return (Collection) results;
	}
	public static Collection<Classifier> getAllSubClassifiers(Classifier baseType,Collection<Package> models){
		Set<Classifier> results = new HashSet<Classifier>();
		addConcreteSubclasses(results, baseType, models, false);
		results.remove(baseType);
		return results;
	}
	public static Collection<Classifier> getAllConcreteSubClassifiers(Classifier baseType,Collection<Package> models){
		Set<Classifier> results = new HashSet<Classifier>();
		addConcreteSubclasses(results, baseType, models, true);
		results.remove(baseType);
		return results;
	}
	private static void addConcreteSubclasses(Set<Classifier> results,Classifier baseType,Collection<Package> models,boolean concreteOnly){
		if(models.contains(EmfElementFinder.getRootObject(baseType))){
			if(!(baseType.isAbstract() && concreteOnly)){
				if(StereotypesHelper.hasStereotype(baseType, StereotypeNames.HELPER) == false){
					results.add(baseType);
				}
			}
			if(baseType instanceof Interface){
				for(Classifier ic:getImplementingClassifiers(((Interface) baseType))){
					addConcreteSubclasses(results, ic, models, concreteOnly);
				}
			}
			for(Classifier c:getSubClasses(baseType)){
				addConcreteSubclasses(results, (Classifier) c, models, concreteOnly);
			}
		}
	}
	public static Collection<Classifier> getSubClasses(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		Collection<Setting> refs = ECrossReferenceAdapter.getCrossReferenceAdapter(c.eResource().getResourceSet())
				.getNonNavigableInverseReferences(c);
		for(Setting setting:refs){
			if(setting.getEObject() instanceof Generalization
					&& setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getGeneralization_General())){
				result.add(((Generalization) setting.getEObject()).getSpecific());
			}
		}
		return result;
	}
	public static Collection<BehavioredClassifier> getImplementingClassifiers(Interface i){
		Set<BehavioredClassifier> result = new HashSet<BehavioredClassifier>();
		Collection<Setting> refs = ECrossReferenceAdapter.getCrossReferenceAdapter(i.eResource().getResourceSet())
				.getNonNavigableInverseReferences(i);
		for(Setting setting:refs){
			if(setting.getEObject() instanceof InterfaceRealization
					&& setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getInterfaceRealization_Contract())){
				result.add(((InterfaceRealization) setting.getEObject()).getImplementingClassifier());
			}
		}
		return result;
	}
	public static boolean isHelper(Type type){
		return StereotypesHelper.hasKeyword(type, StereotypeNames.HELPER);
	}
	public static boolean isSchema(Classifier ns){
		return Boolean.TRUE.equals(getTagValue(ns, TagNames.IS_SCHEMA));
	}
	public static boolean isStructuredDataType(Type type){
		return type.eClass().equals(UMLPackage.eINSTANCE.getDataType()) && !isSimpleType(type);
	}
	public static boolean isNotification(Signal s){
		// TODO Auto-generated method stub
		return StereotypesHelper.hasStereotype(s, StereotypeNames.NOTIFICATION);
	}
	public static boolean isCompositionParticipant(Type umlOwner){
		if(umlOwner instanceof Component || umlOwner instanceof Class || umlOwner instanceof MessageType || umlOwner instanceof Actor){
			return true;
		}else if(umlOwner instanceof Interface){
			return !isHelper(umlOwner);
		}else if(umlOwner instanceof Association){
			return EmfAssociationUtil.isClass((Association) umlOwner);
		}
		return false;
	}
	public static boolean isPersistent(Type type){
		if(!isComplexStructure(type)){
			return false;
		}else if(type instanceof Behavior){
			return EmfBehaviorUtil.isProcess((Behavior) type);
		}else if(type instanceof IEmulatedElement){
			Element element = ((IEmulatedElement) type).getOriginalElement();
			if(element instanceof Operation){
				return EmfBehaviorUtil.isLongRunning((Operation)element);
			}else if(element instanceof StructuredActivityNode){
				StructuredActivityNode n = (StructuredActivityNode) element;
				Activity a= EmfActivityUtil.getContainingActivity(n);
				return EmfBehaviorUtil.isProcess(a);
			}else if(element instanceof OpaqueAction){
				return EmfActionUtil.isEmbeddedTask((OpaqueAction) element);
			}else{
				return false;
			}
		}else {
			return type instanceof Class || type instanceof Actor ||  EmfClassifierUtil.isBusinessCollaboration(type) ;
		}
	}
	public static boolean isComplexStructure(Type type){
		if(type instanceof Class || type instanceof Actor || type instanceof Collaboration
				|| type instanceof MessageType){
			return true;
		}
		return false;
	}
	public static boolean isFact(Type type){
		for(Property property:EmfElementFinder.getPropertiesInScope((Classifier) type)){
			if(EmfPropertyUtil.isMeasure(property)){
				return true;
			}
		}
		return false;
	}
	public static boolean isBusinessRole(Type type){
		return type.eClass().equals(UMLPackage.eINSTANCE.getClass_()) && StereotypesHelper.hasStereotype(type, StereotypeNames.BUSINESS_ROLE);
	}
	public static boolean isBusinessComponent(Classifier umlOwner){
		return umlOwner.eClass().equals(UMLPackage.eINSTANCE.getComponent())
				&& StereotypesHelper.hasStereotype(umlOwner, StereotypeNames.BUSINESS_COMPONENT);
	}
	public static boolean isBusinessCollaboration(Type type){
		return type.eClass().equals(UMLPackage.eINSTANCE.getCollaboration())
				&& StereotypesHelper.hasStereotype(type, StereotypeNames.BUSINESS_COLLABORATION);
	}
	public static Class getAdminRole(Component c){
		Stereotype st = StereotypesHelper.getStereotype(c, StereotypeNames.BUSINESS_COMPONENT);
		if(st != null){
			return (Class) c.getValue(st, "adminRole");
		}
		return null;
	}
	public static Collection<Property> getPrimaryKeyProperties(Class class1){
		Collection<Property> result = new HashSet<Property>();
		for(Property property:class1.getOwnedAttributes()){
			if(EmfPropertyUtil.isMarkedAsPrimaryKey(property)){
				result.add(property);
			}
		}
		return result;
	}
	public static EnumerationLiteral getPowerTypeLiteral(Generalization generalization,Enumeration type){
		for(GeneralizationSet gs:generalization.getGeneralizationSets()){
			if(gs.getPowertype() == type){
				for(EnumerationLiteral l:type.getOwnedLiterals()){
					if(generalization.getSpecific().getName().equalsIgnoreCase(l.getName())){
						return l;
					}
				}
			}
		}
		return null;
	}
	public static boolean isPowerTypeInstanceOn(Class entity,Enumeration type){
		EList<Generalization> generalizations = entity.getGeneralizations();
		for(Generalization generalization:generalizations){
			for(GeneralizationSet gs:generalization.getGeneralizationSets()){
				if(gs.getPowertype() == type){
					return true;
				}
			}
		}
		return false;
	}
	public static String getMetaClass(NamedElement c){
		if(c instanceof Activity){
			if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINES_PROCESS)){
				return StereotypeNames.BUSINES_PROCESS;
			}else{
				return StereotypeNames.METHOD;
			}
		}else if(c instanceof Class){
			if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_DOCUMENT)){
				return StereotypeNames.BUSINESS_DOCUMENT;
			}else if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_ROLE)){
				return StereotypeNames.BUSINESS_ROLE;
			}else if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_COMPONENT)){
				return StereotypeNames.BUSINESS_COMPONENT;
			}else if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_WORKER)){
				return StereotypeNames.BUSINESS_WORKER;
			}else{
				return StereotypeNames.ENTITY;
			}
		}else if(c instanceof Collaboration && StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_COLLABORATION)){
			return StereotypeNames.BUSINESS_COLLABORATION;
		}else if(c instanceof Enumeration){
			Enumeration en = (Enumeration) c;
			if(en.getPowertypeExtents().size() > 0){
				return StereotypeNames.POWER_TYPE;
			}
		}
		return c.eClass().getName();
	}
}
