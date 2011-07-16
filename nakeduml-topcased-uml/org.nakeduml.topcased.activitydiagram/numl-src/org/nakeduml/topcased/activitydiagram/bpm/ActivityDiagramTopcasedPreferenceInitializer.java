package org.nakeduml.topcased.activitydiagram.bpm;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;
import org.topcased.modeler.uml.activitydiagram.preferences.ActivityDiagramPreferenceConstants;

public class ActivityDiagramTopcasedPreferenceInitializer extends org.topcased.modeler.uml.activitydiagram.preferences.ActivityDiagramTopcasedPreferenceInitializer  implements ITopcasedPreferenceInitializer{
	public HashMap<String,String> getDefaultPreference(){
		HashMap<String,String> defaultActivityPreference = super.getDefaultPreference();
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.EXCEPTIONHANDLER_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.EXCEPTIONHANDLER_EXCEPTIONTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.EXCEPTIONHANDLER_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CONTROLFLOW_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CONTROLFLOW_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "ralse");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CONTROLFLOW_GUARD_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CONTROLFLOW_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CONTROLFLOW_WEIGHT_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.OBJECTFLOW_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.OBJECTFLOW_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.OBJECTFLOW_GUARD_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.OBJECTFLOW_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.OBJECTFLOW_WEIGHT_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLBEHAVIORACTION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLBEHAVIORACTION_DEFAULT_AUTOMATIC_PINS_CREATION, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLBEHAVIORACTION_DEFAULT_LABEL, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLBEHAVIORACTION_DEFAULT_SHOW_ARGUMENT_TYPE, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLBEHAVIORACTION_DEFAULT_SHOW_PARAMETERS, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLOPERATIONACTION_DEFAULT_AUTOMATIC_PINS_CREATION, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.CALLOPERATIONACTION_DEFAULT_SHOW_ARGUMENT_TYPE, "false");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.SUCCESSORCLAUSELINK_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultActivityPreference.put(ActivityDiagramPreferenceConstants.SUCCESSORCLAUSELINK_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());
		return defaultActivityPreference;
	}
}
