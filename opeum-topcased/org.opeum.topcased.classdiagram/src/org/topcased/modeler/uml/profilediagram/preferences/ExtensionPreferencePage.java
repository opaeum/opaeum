/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.profilediagram.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.topcased.modeler.preferences.AbstractEdgePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.profilediagram.ProfileEdgeObjectConstants;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated
 */
public class ExtensionPreferencePage extends AbstractEdgePreferencePage
{
    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeFont()
     * @generated
     */
    protected String getEdgeFont()
    {
        return ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeForegroundColor()
     * @generated
     */
    protected String getEdgeForegroundColor()
    {
        return ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FOREGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeRouter()
     * @generated
     */
    protected String getEdgeRouter()
    {
        return ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_ROUTER;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getHiddenElements()
     * @generated
     */
    protected List<String> getHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (!ps.getBoolean(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultHiddenElements()
     * @generated
     */
    protected List<String> getDefaultHiddenElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (!ps.getDefaultBoolean(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getVisibleElements()
     * @generated
     */
    protected List<String> getVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (ps.getBoolean(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultVisibleElements()
     * @generated
     */
    protected List<String> getDefaultVisibleElements()
    {
        List<String> choiceOfValues = new ArrayList<String>();
        IPreferenceStore ps = getPreferenceStore();
        if (ps.getDefaultBoolean(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY))
        {
            choiceOfValues.add(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID);
        }
        return choiceOfValues;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#storeEdgeObjectVisibility(java.util.List)
     * @generated
     */
    protected void storeEdgeObjectVisibility(List<String> visibleElement)
    {
        IPreferenceStore ps = getPreferenceStore();
        if (visibleElement.contains(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID))
        {
            ps.setValue(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY, true);
        }
        else
        {
            ps.setValue(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY, false);
        }
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     * @generated
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

}