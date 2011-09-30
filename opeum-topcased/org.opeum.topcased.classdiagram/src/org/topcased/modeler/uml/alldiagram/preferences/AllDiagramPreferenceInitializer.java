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
package org.topcased.modeler.uml.alldiagram.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.editor.UMLEditor;


/**
 * Initialize preferences to all UML diagrams.
 */
public class AllDiagramPreferenceInitializer extends AbstractTopcasedPreferenceInitializer
{

    /**
     * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences()
    {
        super.initializeDefaultPreferences();
        
        Preferences ps = getPreferences();
            
		// Initialize the default value of the COMMENT_DEFAULT_BACKGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENT_DEFAULT_BACKGROUND_COLOR, "255,195,0");

        // Initialize the default value of the COMMENT_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENT_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the COMMENT_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the COMMENTLINK_EDGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the COMMENTLINK_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_FOREGROUND_COLOR, "255,195,0");

        // Initialize the default value of the COMMENTLINK_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the CONSTRAINT_DEFAULT_BACKGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_BACKGROUND_COLOR, "155,230,130");

        // Initialize the default value of the CONSTRAINT_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the CONSTRAINT_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the CONSTRAINTLINK_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.CONSTRAINTLINK_EDGE_DEFAULT_FOREGROUND_COLOR, "155,230,130");

        // Initialize the default value of the CONSTRAINTLINK_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.CONSTRAINTLINK_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the DEPENDENCY_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, true);

        // Initialize the default value of the DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.DEPENDENCY_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY, false);

        // Initialize the default value of the USAGE_EDGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the USAGE_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the USAGE_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the USAGE_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.USAGE_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, true);

        // Initialize the default value of the USAGE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.USAGE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, true);

        // Initialize the default value of the STEREOTYPE_DEFAULT_DISPLAY property
        ps.setDefault(AllDiagramPreferenceConstants.STEREOTYPE_DEFAULT_DISPLAY, "chevron");
        
        // Initialize the default value of the PACKAGE_DEFAULT_BACKGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_BACKGROUND_COLOR, "244,164,96");

        // Initialize the default value of the PACKAGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PACKAGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());
        

        // Initialize the default value of the PACKAGEIMPORT_EDGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the PACKAGEIMPORT_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PACKAGEIMPORT_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEIMPORT_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the PACKAGEIMPORT_VISIBILITY_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEIMPORT_VISIBILITY_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the PACKAGEMERGE_EDGE_DEFAULT_FONT property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the PACKAGEMERGE_EDGE_DEFAULT_FOREGROUND_COLOR property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PACKAGEMERGE_EDGE_DEFAULT_ROUTER property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the PACKAGEMERGE_MERGE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        ps.setDefault(AllDiagramPreferenceConstants.PACKAGEMERGE_MERGE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        
        ps.setDefault(AllDiagramPreferenceConstants.LOAD_CUSTOM_RESOLVE_ALL, "false");
        
        ps.setDefault(AllDiagramPreferenceConstants.LOAD_DEFER_IDREF, "true");
        
        ps.setDefault(AllDiagramPreferenceConstants.LOAD_DEFER_ATTACHMENT, "false");
        
        ps.setDefault(AllDiagramPreferenceConstants.LOAD_NOTIFY, "false");
        
        // Initialize the default value of the STEREOTYPE_AS_A_NOTE to false
        ps.setDefault(AllDiagramPreferenceConstants.STEREOTYPE_AS_A_NOTE, false);

    }
    
    /**
     * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getPreferences()
     */
    @Override
    protected Preferences getPreferences()
    {
        return UMLPlugin.getDefault().getPluginPreferences();
    }
    
    /**
     * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
     */
    protected String getEditorId()
    {
        return UMLEditor.EDITOR_ID;
    }
}
