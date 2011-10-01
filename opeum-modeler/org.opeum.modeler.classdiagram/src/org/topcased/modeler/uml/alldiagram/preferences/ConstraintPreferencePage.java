/***********************************************************************
 * Copyright (c) 2008 Atos Origin
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atos Origin - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.alldiagram.preferences;

import org.topcased.modeler.preferences.AbstractNodePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * This class represents a preference page that is contributed to the Preferences or Property dialog. This page is used
 * to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @generated
 */
public class ConstraintPreferencePage extends AbstractNodePreferencePage
{
    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeBackgroundColor()
     */
    protected String getNodeBackgroundColor()
    {
        return AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_BACKGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeFont()
     */
    protected String getNodeFont()
    {
        return AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeForegroundColor()
     */
    protected String getNodeForegroundColor()
    {
        return AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FOREGROUND_COLOR;
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

}
