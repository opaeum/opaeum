/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.preferences;

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
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_BACKGROUND_COLOR, "165,144,61");
		// Initialize the default value of the USERTASK_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the USERTASK_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.USERTASK_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the BOUNDARYEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_BACKGROUND_COLOR, "134,129,165");
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
		// Initialize the default value of the INTERMEDIATECATCHEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATECATCHEVENT_DEFAULT_BACKGROUND_COLOR, "134,129,165");
		// Initialize the default value of the INTERMEDIATECATCHEVENT_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATECATCHEVENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the INTERMEDIATECATCHEVENT_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATECATCHEVENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the STARTEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.STARTEVENT_DEFAULT_BACKGROUND_COLOR, "134,129,165");
		// Initialize the default value of the STARTEVENT_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.STARTEVENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the STARTEVENT_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.STARTEVENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the ENDEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.ENDEVENT_DEFAULT_BACKGROUND_COLOR, "134,129,165");
		// Initialize the default value of the ENDEVENT_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.ENDEVENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the ENDEVENT_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.ENDEVENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the INTERMEDIATETHROWEVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATETHROWEVENT_DEFAULT_BACKGROUND_COLOR, "134,129,165");
		// Initialize the default value of the INTERMEDIATETHROWEVENT_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATETHROWEVENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the INTERMEDIATETHROWEVENT_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INTERMEDIATETHROWEVENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the SIGNALEVENTDEFINITION_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SIGNALEVENTDEFINITION_DEFAULT_BACKGROUND_COLOR, "255,255,255");
		// Initialize the default value of the SIGNALEVENTDEFINITION_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SIGNALEVENTDEFINITION_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the SIGNALEVENTDEFINITION_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.SIGNALEVENTDEFINITION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the INCLUSIVEGATEWAY_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INCLUSIVEGATEWAY_DEFAULT_BACKGROUND_COLOR, "126,51,51");
		// Initialize the default value of the INCLUSIVEGATEWAY_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INCLUSIVEGATEWAY_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the INCLUSIVEGATEWAY_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.INCLUSIVEGATEWAY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the EXCLUSIVEGATEWAY_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EXCLUSIVEGATEWAY_DEFAULT_BACKGROUND_COLOR, "126,51,51");
		// Initialize the default value of the EXCLUSIVEGATEWAY_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EXCLUSIVEGATEWAY_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the EXCLUSIVEGATEWAY_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EXCLUSIVEGATEWAY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the PARALLELGATEWAY_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.PARALLELGATEWAY_DEFAULT_BACKGROUND_COLOR, "126,51,51");
		// Initialize the default value of the PARALLELGATEWAY_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.PARALLELGATEWAY_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the PARALLELGATEWAY_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.PARALLELGATEWAY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the EVENTBASEDGATEWAY_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EVENTBASEDGATEWAY_DEFAULT_BACKGROUND_COLOR, "126,51,51");
		// Initialize the default value of the EVENTBASEDGATEWAY_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EVENTBASEDGATEWAY_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the EVENTBASEDGATEWAY_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.EVENTBASEDGATEWAY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		// Initialize the default value of the COMPLEXGATEWAY_DEFAULT_BACKGROUND_COLOR property 
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.COMPLEXGATEWAY_DEFAULT_BACKGROUND_COLOR, "126,51,51");
		// Initialize the default value of the COMPLEXGATEWAY_DEFAULT_FOREGROUND_COLOR property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.COMPLEXGATEWAY_DEFAULT_FOREGROUND_COLOR, "0,0,0");
		// Initialize the default value of the COMPLEXGATEWAY_DEFAULT_FONT property
		defaultBPMN2Preference.put(BPMN2DiagramPreferenceConstants.COMPLEXGATEWAY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-8").toString());
		return defaultBPMN2Preference;
	}
}
