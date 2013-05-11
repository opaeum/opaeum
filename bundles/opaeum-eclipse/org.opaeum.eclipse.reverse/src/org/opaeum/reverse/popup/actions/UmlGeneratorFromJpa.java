package org.opaeum.reverse.popup.actions;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
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
	public void generateUml(Map<ITypeBinding,AbstractTypeDeclaration> selection,Package library,IProgressMonitor m) throws Exception{
		m.beginTask("Importing JPA Entities", selection.size());
		if(library instanceof Model){
			//TODO fix dependency on opaeum specifics
			ProfileApplier.applyProfile((Model) library, StereotypeNames.OPAEUM_STANDARD_PROFILE);
			factory = new ClassifierFactory(library);
			for(Entry<ITypeBinding,AbstractTypeDeclaration> t:selection.entrySet()){
				if(!m.isCanceled() && !t.getKey().isAnnotation() && Annotations.getAnnotationAttribute("javax.persistence.Entity", t.getKey().getAnnotations())!=null){
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
	}
	private Association createAssociation(Classifier cls, PropertyDescriptor pd){
		Classifier baseType = factory.getClassifierFor(pd.getBaseType());
		Association assoc = null;
		if(pd.getOtherEnd() != null && pd.getOtherEnd().isComposite()){
			assoc = (Association) cls.getNearestPackage().getOwnedType(baseType.getName() + "Has" + NameConverter.capitalize(pd.getOtherEnd().getName()), true,
					UMLPackage.eINSTANCE.getAssociation(), true);
		}
		if(assoc==null && (pd.isComposite() || pd.isMany() || pd.getOtherEnd()==null)){
			assoc = (Association) cls.getNearestPackage().getOwnedType(cls.getName() + "Has" + NameConverter.capitalize(pd.getName()), true,
					UMLPackage.eINSTANCE.getAssociation(), true);
		}
		if(assoc==null){
			assoc = (Association) cls.getNearestPackage().getOwnedType(baseType.getName() + "Has" + NameConverter.capitalize(pd.getOtherEnd().getName()), true,
					UMLPackage.eINSTANCE.getAssociation(), true);
		}
		return assoc;
	}
	protected Property findProperty(Classifier classifier,PropertyDescriptor pd){
		Property r = findProperty(pd, classifier.getAttributes());
		if(r==null){
			outer:for(Association a:classifier.getAssociations()){
				for(Property m:a.getMemberEnds()){
					if(m.getOtherEnd()!=null && m.getOtherEnd().getType().equals(classifier)){
						r= m;
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
		if(baseType instanceof PrimitiveType || Annotations.getAnnotationAttribute("javax.persistence.Entity", pd.getBaseType().getAnnotations())==null){
			Property attr = super.createAttribute(cls, pd);
			Stereotyper.stereotype(attr, pd.getField().getAnnotations(),factory);
			return attr;
		}else{
			Association assoc=createAssociation(cls, pd);
			Property otherEnd = createEnd(assoc, pd.getOtherEnd(), cls);
			Stereotyper.stereotype(otherEnd, pd.getField().getAnnotations(),factory);
			Property thisEnd = createEnd(assoc, pd, baseType);
			Stereotyper.stereotype(thisEnd, pd.getField().getAnnotations(),factory);
			return thisEnd;
		}
	}
	public Property createEnd(Association assoc,PropertyDescriptor end,Classifier baseType){
		Property otherEnd;
		if(end == null){
			otherEnd = (Property)assoc.getOwnedEnd(NameConverter.decapitalize(baseType.getName()),baseType,true,UMLPackage.eINSTANCE.getProperty(), true);
		}else if(end.isComposite()){
			otherEnd = super.createAttribute(baseType, end);
			assoc.getMemberEnds().add(otherEnd);
		}else{
			otherEnd = (Property)assoc.getOwnedEnd(end.getName(),baseType,true,UMLPackage.eINSTANCE.getProperty(), true);
		}
		return otherEnd;
	}
}
