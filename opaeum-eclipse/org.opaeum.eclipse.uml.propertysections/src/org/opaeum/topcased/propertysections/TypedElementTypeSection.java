package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class TypedElementTypeSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTypedElement_Type();
	}
	protected String getLabelText(){
		return "Type:";
	}
	protected Object[] getComboFeatureValues(){
		EObject element = getEObject();
		return getValidTypes(element);
	}
	public static Object[] getValidTypes(EObject element){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(element);
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(element, UMLPackage.eINSTANCE.getClassifier());
		if(((TypedElement) element).getModel() != null){
			types = UmlMetaTypeRemover.removeAll(types);
		}
		Iterator<EObject> iterator = types.iterator();
		EObject root = element;
		while(root.eContainer() != null){
			root = root.eContainer();
		}
		while(iterator.hasNext()){
			EObject eObject = (EObject) iterator.next();
			boolean accept = true;
			if(eObject instanceof UseCase || eObject instanceof Collaboration
					|| (eObject instanceof Association && !(eObject instanceof AssociationClass))){
				accept = false;
			}else{
				TypedElement element2 = (TypedElement) element;
				Classifier clss = (Classifier) eObject;
				if(eObject instanceof Activity){
					accept = StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINES_PROCESS);
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.BUSINESS_ROLE_CONTAINMENT)){
					accept = eObject instanceof org.eclipse.uml2.uml.Class
							&& StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE);
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.PARTICIPANT_REFERENCE)){
					accept = eObject instanceof org.eclipse.uml2.uml.Class
							&& (StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE) || StereotypesHelper.hasStereotype(
									clss, StereotypeNames.BUSINESS_COMPONENT));
				}else if(StereotypesHelper.hasStereotype(element2, StereotypeNames.BUSINESS_GATEWAY)){
					accept = (eObject instanceof org.eclipse.uml2.uml.Interface && StereotypesHelper.hasStereotype(clss,
							StereotypeNames.BUSINESS_SERVICE))
							|| (eObject instanceof Class && StereotypesHelper.hasStereotype(clss, StereotypeNames.BUSINESS_ROLE));
				}
			}
			if(root instanceof Model){
				accept = accept && !(eObject instanceof Stereotype);
			}
			if(!accept){
				iterator.remove();
			}
		}
		choices.addAll(types);
		return choices.toArray();
	}
	protected Object getFeatureValue(){
		return ((TypedElement) getEObject()).getType();
	}
}