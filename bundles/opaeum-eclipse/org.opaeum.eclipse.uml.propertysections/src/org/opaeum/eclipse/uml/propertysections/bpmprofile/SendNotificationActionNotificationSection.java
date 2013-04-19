package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.SendSignalActionSignalSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;



public class SendNotificationActionNotificationSection extends SendSignalActionSignalSection{
	@Override
	public String getLabelText(){
		return "Notification Type";
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<Element> reac = OpaeumEclipseContext.getReachableObjectsOfStereotype(getEObject(), StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.NOTIFICATION);
		choices.addAll(reac);
		return choices.toArray();
	}
}
