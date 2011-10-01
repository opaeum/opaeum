/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;

/**
 * Action that allows user to unapply profiles
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class UnapplyProfileAction extends UMLEObjectAction
{

    private Package thePackage;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param p the target package
     */
    public UnapplyProfileAction(Modeler ed, org.eclipse.uml2.uml.Package p)
    {
        super("Unapply Profile", ed);
        this.thePackage = p;
    }

    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        ((CommandStack) getEditor().getAdapter(CommandStack.class)).execute(createActionCommand(thePackage));
    }

    
    
    @Override
    public EObject getEObject()
    {
        return thePackage;
    }

    /**
     * Create the generation command
     * 
     * @param p the UML package
     * @return the command that creates the primitive types
     */
    protected Command createActionCommand(final org.eclipse.uml2.uml.Package p)
    {
        List<Profile> choiceOfValues = new ArrayList<Profile>(p.getAppliedProfiles());

        Collections.sort(choiceOfValues, new TextComparator<Profile>());

        String label = "Choose the profile to unapply";

        final FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), getLabelProvider(), p, UMLPackage.Literals.PROFILE, Collections.EMPTY_LIST, label,
                choiceOfValues);
        dialog.open();

        if (dialog.getReturnCode() == Window.OK)
        {

            return new RecordingChangeCommand(p.eResource().getResourceSet())
            {
                /**
                 * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
                 */
                protected void doExecute()
                {
                    for (Iterator< ? > profiles = dialog.getResult().iterator(); profiles.hasNext();)
                    {
                        p.unapplyProfile((Profile) profiles.next());
                    }
                }
            };
        }

        return UnexecutableCommand.INSTANCE;
    }
}
