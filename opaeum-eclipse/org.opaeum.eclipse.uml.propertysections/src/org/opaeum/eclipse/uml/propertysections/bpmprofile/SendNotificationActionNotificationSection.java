package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.SendSignalActionSignalSection;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class SendNotificationActionNotificationSection extends SendSignalActionSignalSection{
	@Override
	protected String getLabelText(){
		return "Notification Type";
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> reac = typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getSignal());
		for(EObject r:reac){
			if(StereotypesHelper.hasStereotype((Element) r, StereotypeNames.NOTIFICATION)){
				choices.add(r);
			}
		}
		return choices.toArray();
	}
}
