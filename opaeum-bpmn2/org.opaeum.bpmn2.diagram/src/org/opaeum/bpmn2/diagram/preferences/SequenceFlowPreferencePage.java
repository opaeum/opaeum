/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.opaeum.bpmn2.modeleditor.Bpmn2Plugin;
import org.topcased.modeler.preferences.AbstractEdgePreferencePage;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @generated
 */
public class SequenceFlowPreferencePage extends AbstractEdgePreferencePage{
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeFont()
	 * @generated
	 */
	protected String getEdgeFont(){
		return BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FONT;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeForegroundColor()
	 * @generated
	 */
	protected String getEdgeForegroundColor(){
		return BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FOREGROUND_COLOR;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeRouter()
	 * @generated
	 */
	protected String getEdgeRouter(){
		return BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_ROUTER;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getHiddenElements()
	 * @generated
	 */
	protected List<String> getHiddenElements(){
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultHiddenElements()
	 * @generated
	 */
	protected List<String> getDefaultHiddenElements(){
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getVisibleElements()
	 * @generated
	 */
	protected List<String> getVisibleElements(){
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultVisibleElements()
	 * @generated
	 */
	protected List<String> getDefaultVisibleElements(){
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#storeEdgeObjectVisibility(java.util.List)
	 * @generated
	 */
	protected void storeEdgeObjectVisibility(List visibleElement){
		IPreferenceStore ps = getPreferenceStore();
	}
	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 * @generated
	 */
	protected String getBundleId(){
		return Bpmn2Plugin.getId();
	}
}
