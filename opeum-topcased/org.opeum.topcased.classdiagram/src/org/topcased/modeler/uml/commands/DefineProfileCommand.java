/***********************************************************************
 * Copyright (c) 2005, 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Mathieu Garcia (Anyware Technologies) - initial API and implementation
 *    Jacques Lescot (Anyware Technologies) - Bug #904 : you can fully define a profile in the editor. DefineProfileDialog will be removed
 **********************************************************************/
package org.topcased.modeler.uml.commands;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Profile;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * TODO completly refactor and redesign this class Class that create a command in order to update profile settings <br>
 * 
 * Creation : 31 may 2005 Updated : 19 June 2008
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 */
public class DefineProfileCommand extends Command
{
    private static final String UML2_UML_PACKAGE_2_0_NS_URI = UMLPlugin.UML_URI; //$NON-NLS-1$

    /** Current profile */
    private Profile profile;

    /** The defined profile */
    private EPackage definedProfile;

    /**
     * Create a command for updating parameters on a given profile
     * 
     * @param prof the profile
     */
    public DefineProfileCommand(Profile prof)
    {
        profile = prof;
    }

    /**
     * Set the values
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute()
    {
        definedProfile = profile.define();
    }

    /**
     * set the old values
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo()
    {
        // Remove the definedProfile in the corresponding Annotation
        UML2Util.getEAnnotation(profile, UML2_UML_PACKAGE_2_0_NS_URI, false).getContents().remove(definedProfile);
    }

    /**
     * Set the new values
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo()
    {
        // Add the definedProfile in the corresponding Annotation. Should not be added twice (case when it is already
        // done in the execute() method)
        if (!UML2Util.getEAnnotation(profile, UML2_UML_PACKAGE_2_0_NS_URI, false).getContents().contains(definedProfile))
        {
            UML2Util.getEAnnotation(profile, UML2_UML_PACKAGE_2_0_NS_URI, false).getContents().add(0, definedProfile);
        }
    }
}
