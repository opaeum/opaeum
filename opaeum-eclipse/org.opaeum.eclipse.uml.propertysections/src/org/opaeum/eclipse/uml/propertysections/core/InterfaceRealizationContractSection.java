package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class InterfaceRealizationContractSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getInterfaceRealization_Contract();
	}
	protected String getLabelText(){
		return "Classifier specialized:";
	}
	protected Object[] getComboFeatureValues(){
		
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getType());

		final Iterator<EObject> iterator = types.iterator();
		while(iterator.hasNext()){
			Classifier eObject = (Classifier) iterator.next();
			if(!(eObject instanceof Interface)){
				iterator.remove();
			}
		}
		choices.addAll(UmlMetaTypeRemover.removeAll(types));

		return choices.toArray();
	}
	protected Object getFeatureValue(){
		return ((InterfaceRealization)getEObject()).getContract();
	}
}