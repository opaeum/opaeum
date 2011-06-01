/*******************************************************************************
 * Copyright (c) 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.preferences;

import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;

/**
 * This class represents a preference page that is contributed to the Preferences or Property dialog. This page is used
 * to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated
 */
public class PrimitiveTypePreferencePage extends ClassifierPreferencePage
{

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeBackgroundColor()
     */
    @Override
    protected String getNodeBackgroundColor()
    {
        return ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_BACKGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeFont()
     */
    @Override
    protected String getNodeFont()
    {
        return ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeForegroundColor()
     */
    @Override
    protected String getNodeForegroundColor()
    {
        return ClassDiagramPreferenceConstants.PRIMITIVETYPE_DEFAULT_FOREGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.uml.classdiagram.preferences.ClassifierPreferencePage#getPreferencePrefix()
     */
    @Override
    protected String getPreferencePrefix()
    {
        return ClassElementsVisibilityConstants.PRIMITIVE_TYPE_PREFERENCE_PREFIX;
    }
}
