package org.opaeum.reverse.popup.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

public class UmlGeneratorFromJpa extends AbstractUmlGenerator{
	@Override
	public Collection<Element> generateUml(Map<ITypeBinding,AbstractTypeDeclaration> selection,Package library,IProgressMonitor m)
			throws Exception{
		m.beginTask("Importing JPA Entities", selection.size());
		if(library instanceof Model){
			// TODO fix dependency on opaeum specifics
			ProfileApplier.applyProfile((Model) library, StereotypeNames.OPAEUM_STANDARD_PROFILE);
			factory = new ClassifierFactory(library);
			for(Entry<ITypeBinding,AbstractTypeDeclaration> t:selection.entrySet()){
				if(!m.isCanceled() && !t.getKey().isAnnotation()
						&& Annotations.getAnnotation("javax.persistence.Entity", t.getKey().getAnnotations()) != null){
					// Only do annotations in profiles
					Classifier cls = factory.getClassifierFor(t.getKey());
					Stereotyper.stereotype(cls, t.getKey().getAnnotations(), factory);
					populateAttributes(library, cls, t);
					populateOperations(library, cls, t);
				}
				m.worked(1);
			}
		}
		m.done();
		Collection<Element> result = new HashSet<Element>();
		for(Classifier c:factory.getNewClassifiers()){
			result.add(c);
			result.addAll(c.getAssociations());
		}
		return result;
	}
	private Association createAssociation(Classifier cls,PropertyDescriptor pd){
		Classifier baseType = factory.getClassifierFor(pd.getBaseType());
		Association assoc = null;
		if(pd.getOtherEnd() != null && pd.getOtherEnd().isComposite()){
			assoc = (Association) cls.getNearestPackage().getOwnedType(
					baseType.getName() + "Has" + NameConverter.capitalize(pd.getOtherEnd().getName()), true, UMLPackage.eINSTANCE.getAssociation(),
					true);
		}
		if(assoc == null && (pd.isComposite() || pd.isMany() || pd.getOtherEnd() == null)){
			assoc = (Association) cls.getNearestPackage().getOwnedType(cls.getName() + "Has" + NameConverter.capitalize(pd.getName()), true,
					UMLPackage.eINSTANCE.getAssociation(), true);
		}
		if(assoc == null){
			assoc = (Association) cls.getNearestPackage().getOwnedType(
					baseType.getName() + "Has" + NameConverter.capitalize(pd.getOtherEnd().getName()), true, UMLPackage.eINSTANCE.getAssociation(),
					true);
		}
		return assoc;
	}
	protected Property findProperty(Classifier classifier,PropertyDescriptor pd){
		Property r = findProperty(pd, classifier.getAttributes());
		if(r == null){
			outer:for(Association a:classifier.getAssociations()){
				for(Property m:a.getMemberEnds()){
					if(m.getOtherEnd() != null && m.getOtherEnd().getType().equals(classifier) && m.getName().equals(pd.getName())){
						r = m;
						break outer;
					}
				}
			}
		}
		return r;
	}
	@Override
	protected Property createAttribute(Classifier cls,PropertyDescriptor pd){
		Classifier baseType = factory.getClassifierFor(pd.getBaseType());
		if(baseType instanceof PrimitiveType
				|| Annotations.getAnnotation("javax.persistence.Entity", pd.getBaseType().getAnnotations()) == null){
			Property attr = super.createAttribute(cls, pd);
			setLower(pd, attr);
			Stereotyper.stereotype(attr, pd.getField().getAnnotations(), factory);
			return attr;
		}else{
			Association assoc = createAssociation(cls, pd);
			Property otherEnd = createEnd(baseType,assoc, pd.getOtherEnd(), cls);
			Stereotyper.stereotype(otherEnd, pd.getField().getAnnotations(), factory);
			Property thisEnd = createEnd(cls,assoc, pd, baseType);
			Stereotyper.stereotype(thisEnd, pd.getField().getAnnotations(), factory);
			return thisEnd;
		}
	}
	public Property createEnd(Classifier owner, Association assoc,PropertyDescriptor end,Classifier baseType){
		Property otherEnd;
		if(end == null){
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}
			otherEnd = (Property) assoc.getOwnedEnd(NameConverter.decapitalize(baseType.getName()), baseType, true,
					UMLPackage.eINSTANCE.getProperty(), true);
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}
		}else if(end.isComposite()){
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}
			otherEnd = super.createAttribute(owner, end);
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}

			assoc.getMemberEnds().add(otherEnd);
			otherEnd.setAggregation(AggregationKind.COMPOSITE_LITERAL);
			otherEnd.setIsNavigable(true);
		}else{
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}
			otherEnd = (Property) assoc.getNavigableOwnedEnd(end.getName(), baseType, true, UMLPackage.eINSTANCE.getProperty(), true);
			if(assoc.getMemberEnds().size()>2){
				System.out.println();
			}
		}
		if(end == null || end.isMany()){
			otherEnd.setUpper(-1);
			otherEnd.setLower(0);
		}else{
			setLower(end, otherEnd);
		}
		return otherEnd;
	}
	public void setLower(PropertyDescriptor end,Property otherEnd){
		boolean required = isRequired(end);
		if(required){
			otherEnd.setLower(1);
		}else{
			otherEnd.setLower(0);
		}
	}
	private boolean isRequired(PropertyDescriptor end){
		boolean required=false;
		IAnnotationBinding manyToOne = getJpaAnnotation(end, "ManyToOne", "OneToOne", "Basic");
		if(manyToOne != null){
			IMemberValuePairBinding optional = Annotations.getAnnotationAttribute(manyToOne, "optional");
			if(optional != null && Boolean.FALSE.equals(optional.getValue())){
				required=true;
			}
		}
		return required;
	}
	private IAnnotationBinding getJpaAnnotation(PropertyDescriptor pd, String ... names){
		IAnnotationBinding result =null;
		for(String string:names){
			result=Annotations.getAnnotation("javax.persistence."+string, pd.getField().getAnnotations());
			if(result!=null){
				break;
			}
		}
		return result;
	}
}
