package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.opaeum.topcased.uml.editor.OpaeumQualifiedNameLabelProvider;
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
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getType());
		if(((TypedElement) getEObject()).getModel() != null){
			types = UmlMetaTypeRemover.removeAll(types);
		}
		Iterator<EObject> iterator = types.iterator();
		while(iterator.hasNext()){
			EObject eObject = (EObject) iterator.next();
			boolean accept = true;
			if(StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.BUSINESS_ROLE_CONTAINMENT)){
				accept = eObject instanceof org.eclipse.uml2.uml.Class && StereotypesHelper.hasStereotype((Element) eObject, StereotypeNames.BUSINESS_ROLE);
			}else if(StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.PARTICIPANT_REFERENCE)){
				accept = eObject instanceof org.eclipse.uml2.uml.Class
						&& (StereotypesHelper.hasStereotype((Element) eObject, StereotypeNames.BUSINESS_ROLE) || StereotypesHelper.hasStereotype((Element) eObject,
								StereotypeNames.BUSINESS_COMPONENT));
			}else if(StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.DIMENSION)){
				accept = eObject instanceof org.eclipse.uml2.uml.Class || eObject instanceof Enumeration;
			}else if(StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.FACT)){
				if(eObject instanceof org.eclipse.uml2.uml.PrimitiveType){
					accept = true;
				}else if(eObject instanceof DataType && !(eObject instanceof Enumeration)){
					DataType dt = (DataType) eObject;
					if(dt.getAssociations().size() > 0){
						accept = false;
					}else{
						for(Property p:dt.getAllAttributes()){
							if(!p.isDerived()){
								accept = false;
							}
						}
					}
				}else{
					accept = false;
				}
			}else if(StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.BUSINESS_GATEWAY)){
				accept = (eObject instanceof org.eclipse.uml2.uml.Interface && StereotypesHelper.hasStereotype((Element) eObject, StereotypeNames.BUSINESS_SERVICE))
						|| (eObject instanceof Class && StereotypesHelper.hasStereotype((Element) eObject, StereotypeNames.BUSINESS_ROLE));
			}
			if(!accept){
				iterator.remove();
			}
		}
		choices.addAll(types);
		return choices.toArray();
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	protected Object getFeatureValue(){
		return ((TypedElement) getEObject()).getType();
	}
}