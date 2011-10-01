/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Thibault Landre (Atos Origin) <a href="mailto:thibault.landre@atosorigin.com">Thibault Landre</a> - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.profilediagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * The Class ProfileDiagramTopcasedPreferenceInitializer.
 */
public class ProfileDiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer
{

    /** 
     * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
     */
    public HashMap<String, String> getDefaultPreference()
    {
        HashMap<String, String> defaultProfilePreference = new HashMap<String, String>();

        // Initialize the default value of the STEREOTYPE_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.STEREOTYPE_DEFAULT_BACKGROUND_COLOR, "229,229,229");

        // Initialize the default value of the STEREOTYPE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.STEREOTYPE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the STEREOTYPE_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.STEREOTYPE_DEFAULT_FONT, StringConverter.asFontData("Sans-bold-10").toString());

        // Initialize the default value of the PROPERTY_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.PROPERTY_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the PROPERTY_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.PROPERTY_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PROPERTY_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.PROPERTY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER, "TreeRouter");

        // Initialize the default value of the ELEMENTIMPORT_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.ELEMENTIMPORT_DEFAULT_BACKGROUND_COLOR, "200,230,255");

        // Initialize the default value of the ELEMENTIMPORT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.ELEMENTIMPORT_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ELEMENTIMPORT_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.ELEMENTIMPORT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the EXTENSION_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the EXTENSION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the EXTENSION_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        defaultProfilePreference.put(ProfileDiagramPreferenceConstants.ASK_DEFINE_PROFILE_ON_SAVING, "false");

        return defaultProfilePreference;
    }

}
