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
package org.topcased.modeler.uml.usecasediagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * The Class UseCaseDiagramTopcasedPreferenceInitializer.
 */
public class UseCaseDiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer
{

    /**
     * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
     */
    public HashMap<String, String> getDefaultPreference()
    {
        HashMap<String, String> defaultUseCasePreference = new HashMap<String, String>();

        // Initialize the default value of the ACTOR_DEFAULT_BACKGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the ACTOR_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ACTOR_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the USECASE_DEFAULT_BACKGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the USECASE_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the USECASE_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.USECASE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the EXTEND_EDGE_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the EXTEND_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the EXTEND_EDGE_DEFAULT_ROUTER property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.EXTEND_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the EXTEND_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.EXTEND_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the EXTEND_EXTENSION_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.EXTEND_EXTENSION_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the INCLUDE_EDGE_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the INCLUDE_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the INCLUDE_EDGE_DEFAULT_ROUTER property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the INCLUDE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.INCLUDE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ASSOCIATION_USECASE_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ASSOCIATION_USECASE_EDGE_DEFAULT_ROUTER property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
        // Initialize the default value of the ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
        // Initialize the default value of the ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_ROUTER property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Fix #792
        // Initialize the default value of the BOUNDARY_BOX_DEFAULT_BACKGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the BOUNDARY_BOX_DEFAULT_FOREGROUND_COLOR property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the BOUNDARY_BOX_DEFAULT_FONT property
        defaultUseCasePreference.put(UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());
        // EndFix #792

        return defaultUseCasePreference;
    }

}
