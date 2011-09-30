/*******************************************************************************
 * Copyright (c) 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.preferences;

import java.util.ArrayList;
import java.util.List;

import org.topcased.modeler.preferences.AbstractEdgePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated
 */
public class InterfaceRealizationPreferencePage extends AbstractEdgePreferencePage
{

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeFont()
     */
    protected String getEdgeFont()
    {
        return ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeForegroundColor()
     */
    protected String getEdgeForegroundColor()
    {
        return ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeRouter()
     */
    protected String getEdgeRouter()
    {
        return ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_ROUTER;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getHiddenElements()
     */
    protected List<String> getHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        if (!getPreferenceStore().getBoolean(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultHiddenElements()
     */
    protected List<String> getDefaultHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        if (!getPreferenceStore().getDefaultBoolean(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getVisibleElements()
     */
    protected List<String> getVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        if (getPreferenceStore().getBoolean(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultVisibleElements()
     */
    protected List<String> getDefaultVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        if (getPreferenceStore().getDefaultBoolean(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#storeEdgeObjectVisibility(java.util.List)
     */
    protected void storeEdgeObjectVisibility(List<String> visibleElement)
    {
        if (visibleElement.contains(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID))
        {
            getPreferenceStore().setValue(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            getPreferenceStore().setValue(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

}
