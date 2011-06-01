/***********************************************************************
 * Copyright (c) 2006, 2008 Anyware Technologies
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.Profile;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.commands.DefineProfileCommand;

/**
 * Action that allows user to define profile
 * 
 * Creation 25 August 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class DefineProfileAction extends UMLEObjectAction
{
    private Profile profile;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param prof The profile that should be defined
     */
    public DefineProfileAction(Modeler ed, org.eclipse.uml2.uml.Profile prof)
    {
        super("Define Profile", ed);
        this.profile = prof;
    }

    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        ((CommandStack) getEditor().getAdapter(CommandStack.class)).execute(createActionCommand(profile));
    }
    
    

    @Override
    public EObject getEObject()
    {
        return profile;
    }

    /**
     * Create the generation command
     * 
     * @param p the UML profile
     * @return the command that update the profile properties
     */
    protected Command createActionCommand(org.eclipse.uml2.uml.Profile p)
    {
        Command defineProfilCommand = null;
        if (p != null)
        {
            if (p.getQualifiedName() != null && !"".equals(p.getQualifiedName()))
            {
                defineProfilCommand = new DefineProfileCommand(profile);
            }
            else
            {
                MessageDialog.openWarning(getEditor().getSite().getShell(), "Error",
                        "Unable to define profile.\nCheck that all parent objects of the profile and the profile itself have their name set.");
            }
        }
        return defineProfilCommand;
    }

}
