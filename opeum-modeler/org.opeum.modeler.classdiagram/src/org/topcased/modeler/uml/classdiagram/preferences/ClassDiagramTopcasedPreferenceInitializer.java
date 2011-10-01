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
package org.topcased.modeler.uml.classdiagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;

/**
 * The Class ProfileDiagramTopcasedPreferenceInitializer.
 */
public class ClassDiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer
{

    /**
     * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
     */
    public HashMap<String, String> getDefaultPreference()
    {
        HashMap<String, String> defaultProfilePreference = new HashMap<String, String>();

        // Initialize the default value of the CLASS_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CLASS_DEFAULT_BACKGROUND_COLOR, "200,230,255");

        // Initialize the default value of the CLASS_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CLASS_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the CLASS_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CLASS_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the INTERFACE_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACE_DEFAULT_BACKGROUND_COLOR, "255,200,255");

        // Initialize the default value of the INTERFACE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the INTERFACE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the DATATYPE_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DATATYPE_DEFAULT_BACKGROUND_COLOR, "200,230,255");

        // Initialize the default value of the DATATYPE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DATATYPE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the DATATYPE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DATATYPE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the OPERATION_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the OPERATION_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the OPERATION_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the PROPERTY_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the PROPERTY_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PROPERTY_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PROPERTY_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the INSTANCESPECIFICATION_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_BACKGROUND_COLOR, "255,180,180");

        // Initialize the default value of the INSTANCESPECIFICATION_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the INSTANCESPECIFICATION_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INSTANCESPECIFICATION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the ASSOCIATION_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");

        // Fix #1780
        // Initialize the default value of the ASSOCIATION_SRCPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATION_TARGETPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // EndFix #1780

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER, "TreeRouter");

        // Initialize the default value of the GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the INTERFACEREALIZATION_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the INTERFACEREALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the INTERFACEREALIZATION_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");

        // Initialize the default value of the SLOT_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SLOT_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the SLOT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SLOT_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the SLOT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SLOT_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ENUMERATION_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATION_DEFAULT_BACKGROUND_COLOR, "229,229,229");

        // Initialize the default value of the ENUMERATION_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATION_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ENUMERATION_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATION_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_BACKGROUND_COLOR, "255,255,255");

        // Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the PRIMITIVETYPE_DEFAULT_BACKGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_BACKGROUND_COLOR, "200,230,255");

        // Initialize the default value of the PRIMITIVETYPE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

        // Initialize the default value of the PRIMITIVETYPE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ASSOCIATIONCLASS_EDGE_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_FONT, StringConverter.asFontData("Sans-regular-10").toString());

        // Initialize the default value of the ASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR, "160,32,240");

        // Initialize the default value of the ASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

        // Initialize the default value of the ASSOCIATIONCLASS_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATIONCLASS_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATIONCLASS_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATIONCLASS_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the ASSOCIATIONCLASS_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
        // Initialize the default value of the TEMPLATEBINDING_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.TEMPLATEBINDING_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
        // Initialize the default value of the TEMPLATEBINDING_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.TEMPLATEBINDING_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		// Initialize the default value of the SIGNAL_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the SIGNAL_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the SIGNAL_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the RECEPTION_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the RECEPTION_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the RECEPTION_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());
        
        // Initialize the default value of the EXECUTIONEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.EXECUTIONEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the EXECUTIONEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.EXECUTIONEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the EXECUTIONEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.EXECUTIONEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the CREATIONEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CREATIONEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the CREATIONEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CREATIONEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the CREATIONEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CREATIONEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the DESTRUCTIONEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DESTRUCTIONEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the DESTRUCTIONEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DESTRUCTIONEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the DESTRUCTIONEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.DESTRUCTIONEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the SENDOPERATIONEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDOPERATIONEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the SENDOPERATIONEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDOPERATIONEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the SENDOPERATIONEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDOPERATIONEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the SENDSIGNALEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDSIGNALEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the SENDSIGNALEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDSIGNALEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the SENDSIGNALEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SENDSIGNALEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the RECEIVEOPERATIONEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVEOPERATIONEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the RECEIVEOPERATIONEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVEOPERATIONEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the RECEIVEOPERATIONEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVEOPERATIONEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the RECEIVESIGNALEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVESIGNALEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the RECEIVESIGNALEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVESIGNALEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the RECEIVESIGNALEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.RECEIVESIGNALEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the CALLEVENTSIGNAL_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CALLEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the CALLEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CALLEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the CALLEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CALLEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the ANYRECEIVEEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ANYRECEIVEEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the ANYRECEIVEEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ANYRECEIVEEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the ANYRECEIVEEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.ANYRECEIVEEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the CHANGEEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CHANGEEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the CHANGEEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CHANGEEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the CHANGEEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.CHANGEEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the SIGNALEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNALEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the SIGNALEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNALEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the SIGNALEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.SIGNALEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());

		// Initialize the default value of the TIMEEVENT_DEFAULT_BACKGROUND_COLOR property 
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.TIMEEVENT_DEFAULT_BACKGROUND_COLOR,"198,234,199");

		// Initialize the default value of the TIMEEVENT_DEFAULT_FOREGROUND_COLOR property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.TIMEEVENT_DEFAULT_FOREGROUND_COLOR,"0,0,0");

		// Initialize the default value of the TIMEEVENT_DEFAULT_FONT property
        defaultProfilePreference.put(ClassDiagramPreferenceConstants.TIMEEVENT_DEFAULT_FONT,StringConverter.asFontData("Sans-regular-10").toString());


        
        // The default visibility of class elements
        String[] prefixes = {ClassElementsVisibilityConstants.CLASS_PREFERENCE_PREFIX, ClassElementsVisibilityConstants.DATATYPE_PREFERENCE_PREFIX,
                ClassElementsVisibilityConstants.INTERFACE_PREFERENCE_PREFIX, ClassElementsVisibilityConstants.PRIMITIVE_TYPE_PREFERENCE_PREFIX};
        for (String prefix : prefixes)
        {
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE_DEFAULT, "true");
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETER_TYPE_DEFAULT, "true");
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETERS_DEFAULT, "true");
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_RETURN_TYPE_DEFAULT, "true");
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_PROPERTY_DEFAULT_VALUE_DEFAULT, "true");
            defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_PROPERTY_TYPE_DEFAULT, "true");
        }

        return defaultProfilePreference;
    }

}
