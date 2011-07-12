/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/

package org.topcased.modeler.uml.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.uml2.uml.Model;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;

/**
 * Action to generate the UML Primitive types in the selected UML model
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class GenerateUMLPrimitiveTypesAction extends Action
{

    private Model model;

    private Modeler editor;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param m the UML model where the type are generated
     */
    public GenerateUMLPrimitiveTypesAction(Modeler ed, Model m)
    {
        super("UML");
        this.editor = ed;
        this.model = m;
    }

    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        ((CommandStack) editor.getAdapter(CommandStack.class)).execute(createActionCommand(model));
    }

    /**
     * Create the generation command
     * 
     * @param m the UML model
     * @return the command that creates the primitive types
     */
    protected Command createActionCommand(final Model m)
    {

        return new RecordingChangeCommand(m.eResource())
        {
            /**
             * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
             */
            protected void doExecute()
            {
                m.createOwnedPrimitiveType("Boolean"); //$NON-NLS-1$
                m.createOwnedPrimitiveType("Integer"); //$NON-NLS-1$
                m.createOwnedPrimitiveType("String"); //$NON-NLS-1$
                m.createOwnedPrimitiveType("UnlimitedNatural"); //$NON-NLS-1$
            }
        };
    }
}
