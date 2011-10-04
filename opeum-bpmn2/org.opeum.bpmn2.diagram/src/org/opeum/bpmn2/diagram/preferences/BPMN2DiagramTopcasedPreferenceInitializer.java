/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.diagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of BPMN2 diagram
 * 
 * @generated
 */
public class BPMN2DiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer{
	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String,String> getDefaultPreference(){
		HashMap<String,String> defaultBPMN2Preference = new HashMap<String,String>();
		// Initialize the default value of the SEQUENCEFLOW_EDGE_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the SEQUENCEFLOW_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the SEQUENCEFLOW_EDGE_DEFAULT_ROUTER property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		// Initialize the default value of the USERTASK_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_BACKGROUND_COLOR, "127,127,127");
		// Initialize the default value of the USERTASK_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the USERTASK_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the BOUNDARYEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_BACKGROUND_COLOR, "0,0,255");
		// Initialize the default value of the BOUNDARYEVENT_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the BOUNDARYEVENT_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the MESSAGEEVENTDEFINITION_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.MESSAGEEVENTDEFINITION_DEFAULT_BACKGROUND_COLOR, "255,255,255");
		// Initialize the default value of the MESSAGEEVENTDEFINITION_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.MESSAGEEVENTDEFINITION_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the MESSAGEEVENTDEFINITION_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.MESSAGEEVENTDEFINITION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		return defaultBPMN2Preference;
	}
}
