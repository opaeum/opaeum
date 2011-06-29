/*****************************************************************************
 * 
 * BoundaryBoxPreferencePage.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Maxime Nauleau (Atos Origin) maxime.nauleau@atosorigin.com - initial API and implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/

package org.topcased.modeler.uml.usecasediagram.preferences;

import org.topcased.modeler.preferences.AbstractNodePreferencePage;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * This class represents a preference page that is contributed to the Preferences or Property dialog. This page is used
 * to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 */
public class BoundaryBoxPreferencePage extends AbstractNodePreferencePage
{
    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeBackgroundColor()
     * @generated
     */
    protected String getNodeBackgroundColor()
    {
        return UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_BACKGROUND_COLOR;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeFont()
     * @generated
     */
    protected String getNodeFont()
    {
        return UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FONT;
    }

    /**
     * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeForegroundColor()
     * @generated
     */
    protected String getNodeForegroundColor()
    {
        return UseCaseDiagramPreferenceConstants.BOUNDARY_BOX_DEFAULT_FOREGROUND_COLOR;
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