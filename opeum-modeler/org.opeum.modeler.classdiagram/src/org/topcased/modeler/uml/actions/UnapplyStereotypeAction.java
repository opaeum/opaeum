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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.commands.UnapplyProfileCommand;
import org.topcased.modeler.utils.Utils;

/**
 * Action that allows user to apply stereotypes
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class UnapplyStereotypeAction extends UMLEObjectAction
{

    private Element element;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param e the target Element
     */
    public UnapplyStereotypeAction(Modeler ed, Element e)
    {
        super("Unapply Stereotype", ed);
        this.element = e;
    }

    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        Command cmd = createActionCommand(element);
        if (cmd != null && cmd.canExecute())
        {
            ((CommandStack) getEditor().getAdapter(CommandStack.class)).execute(cmd);
            refreshModeler();
        }
    }
    
    

    @Override
    public EObject getEObject()
    {
        return element;
    }

    private void refreshModeler()
    {
        List<GraphElement> graphElts = Utils.getGraphElements(getEditor().getActiveDiagram(), element, true);
        for (GraphElement graphElt : graphElts)
        {
            Object editPart = ((GraphicalViewer) getEditor().getAdapter(GraphicalViewer.class)).getEditPartRegistry().get(graphElt);
            if (editPart instanceof EditPart)
            {
                ((EditPart) editPart).refresh();
            }
        }
    }

    /**
     * Create the generation command
     * 
     * @param e the UML Element
     * @return the command that apply the stereotypes
     */
    protected Command createActionCommand(final Element e)
    {
        List<Stereotype> choiceOfValues = new ArrayList<Stereotype>();

        for (Stereotype stereotype : e.getAppliedStereotypes())
        {
            if (!e.isStereotypeRequired(stereotype))
            {
                choiceOfValues.add(stereotype);
            }
        }

        Collections.sort(choiceOfValues, new TextComparator<Stereotype>());

        String label = "Choose the stereotypes to unapply";

        final FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), getLabelProvider(), e, UMLPackage.Literals.ELEMENT, Collections.EMPTY_LIST, label,
                choiceOfValues);
        dialog.open();

        if (dialog.getReturnCode() == Window.OK)
        {
            return new UnapplyProfileCommand(e,(List<Stereotype>) dialog.getResult(), null);
        }

        return UnexecutableCommand.INSTANCE;
    }
}
