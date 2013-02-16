package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class TypedElementTypeSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTypedElement_Type();
	}
	public String getLabelText(){
		return "Type";
	}
	protected Object[] getComboFeatureValues(){
		EObject element = getEObject();
		return getValidTypes(element);
	}
	public static Object[] getValidTypes(EObject element){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = getValidTypeCollection(element);
		choices.addAll(types);
		return choices.toArray();
	}
	public static Collection<EObject> getValidTypeCollection(EObject element){
		EObject eContainer=element.eContainer();
		Collection<EObject> types=new TreeSet<EObject>(new Comparator<EObject>(){

			@Override
			public int compare(EObject o1,EObject o2){
				return ((org.eclipse.uml2.uml.Type)o1).getQualifiedName().compareTo(((org.eclipse.uml2.uml.Type)o2).getQualifiedName());
			}
		});
		while(eContainer!=null){
			if(eContainer instanceof Package){
				types.addAll(((Package) eContainer).getOwnedTypes());
			}else if(eContainer instanceof Class){
				types.addAll(((Class) eContainer).getNestedClassifiers());
				if(eContainer instanceof BehavioredClassifier){
					types.addAll(((BehavioredClassifier) eContainer).getOwnedBehaviors());
				}
			}
			if(eContainer instanceof Namespace){
				for(Package package1:((Namespace) eContainer).getImportedPackages()){
					types.addAll(package1.getOwnedTypes());
				}
				for(PackageableElement pe:((Namespace) eContainer).getImportedMembers()){
					if(pe instanceof Classifier){
						types.add(pe);
					}
				}
			}
			eContainer=eContainer.eContainer();
		}
		if(((TypedElement) element).getModel() != null){
			types = UmlMetaTypeRemover.removeAssocations(types);
		}
		Iterator<EObject> iterator = types.iterator();
		while(iterator.hasNext()){
			EObject eObject = (EObject) iterator.next();
			boolean accept = true;
			if(eObject instanceof UseCase || eObject instanceof Collaboration || (eObject instanceof Association && !(eObject instanceof AssociationClass))){
				accept = false;
			}else{
				TypedElement element2 = (TypedElement) element;
				Classifier clss = (Classifier) eObject;
				if(eObject instanceof Activity){
					accept = StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINES_PROCESS);
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.BUSINESS_ROLE_CONTAINMENT)){
					accept = eObject instanceof org.eclipse.uml2.uml.Class && StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE);
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.PARTICIPANT_REFERENCE)){
					accept = eObject instanceof org.eclipse.uml2.uml.Class
							&& (StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE) || StereotypesHelper.hasStereotype(clss,
									StereotypeNames.BUSINESS_COMPONENT));
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.BUSINESS_GATEWAY)){
					accept = (eObject instanceof org.eclipse.uml2.uml.Interface && StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_SERVICE))
							|| (eObject instanceof Class && StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE));
				}
			}
			if(EcoreUtil.getRootContainer(element) instanceof Model){
				accept = accept && !(eObject instanceof Stereotype);
			}
			if(!accept){
				iterator.remove();
			}
		}
		return types;
	}
	protected Object getFeatureValue(){
		return ((TypedElement) getEObject()).getType();
	}
}