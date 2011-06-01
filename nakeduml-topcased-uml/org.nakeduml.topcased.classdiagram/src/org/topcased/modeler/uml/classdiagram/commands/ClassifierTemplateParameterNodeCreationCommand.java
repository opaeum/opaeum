/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 * 
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: eperico (atos origin) emilien.perico@atosorigin.com - Initial
 * API and implementation
 * 
 *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLFactory;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.commands.IDialogCommand;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.classdiagram.dialogs.ParameteredElementCreateDialog;
import org.topcased.modeler.utils.LabelHelper;

/**
 * command creation for a TemplateParameter.
 * 
 * @author eperico
 */
public class ClassifierTemplateParameterNodeCreationCommand extends CreateGraphNodeCommand implements IDialogCommand
{

    /** The parameterableElement to affect */
    private ParameterableElement parameterableElement;

    /** The name of the parameterableElement to create */
    private String parameterableElementName;

    /** Indicates if the new parameterableElement has to be created */
    private boolean createParameterableElement;

    /**
     * The Constructor.
     * 
     * @param domain the domain
     * @param newObject the new object
     * @param newParent the new parent
     * @param newContainerParent the new container parent
     * @param location the location
     * @param dimension the dimension
     * @param attach the attach
     * @param featuresList the features list
     * @param needModelUpdate the need model update
     */
    @SuppressWarnings("unchecked")
    public ClassifierTemplateParameterNodeCreationCommand(EditDomain domain, GraphNode newObject, GraphNode newParent,
            EObject newContainerParent, Point location, Dimension dimension, int attach, List featuresList,
            boolean needModelUpdate)
    {
        super(domain, newObject, newParent, newContainerParent, location, dimension, attach, featuresList,
                needModelUpdate);
    }

    /**
     * Open dialog.
     * 
     * @return true, if open dialog
     * 
     * @see org.topcased.modeler.commands.IDialogCommand#openDialog()
     */
    public boolean openDialog()
    {
        Shell currentShell = UMLPlugin.getActiveWorkbenchShell();
        ParameteredElementCreateDialog dialog = new ParameteredElementCreateDialog((Element) getChildEObject(),
                (Element) getParentEObject(), currentShell);
        if (dialog.open() == Window.OK)
        {
            createParameterableElement = dialog.isCreateType();
            // create parameterableElement if needed
            if (createParameterableElement)
            {
                // creates a model object 'Class' and adds it to the template
                // parameter.
                parameterableElementName = dialog.getParameterableElementName();

                // FIXME parametered element should be created with a command to manage undo/redo
                TemplateParameter templateParameter = (TemplateParameter) getChildEObject();
                Class newClass = UMLFactory.eINSTANCE.createClass();
                newClass.setName(parameterableElementName);
                templateParameter.setOwnedParameteredElement(newClass);
                newClass.setTemplateParameter(templateParameter);

                parameterableElement = newClass;
            }
            else
            {
                // selected parameterableElement or null if is new
                parameterableElement = dialog.getParameterableElement();
            }
            // initializes the name given for the ParameterableElement
            String curName = LabelHelper.INSTANCE.getName(getEditDomain(), getChildEObject());
            if (curName == null || "".equals(curName))
            {
                LabelHelper.INSTANCE.initName(getEditDomain(), getParentEObject(), getChildEObject());
            }
            ((ClassifierTemplateParameter) getChildEObject()).setParameteredElement(parameterableElement);
            return true;
        }
        return false;
    }

    /**
     * Redo model.
     * 
     * @see org.topcased.modeler.commands.CreateGraphNodeCommand#redoModel()
     */
    protected void redoModel()
    {
        super.redoModel();
    }

    /**
     * Undo model.
     * 
     * @see org.topcased.modeler.commands.CreateGraphNodeCommand#undoModel()
     */
    protected void undoModel()
    {
        super.undoModel();
        if (createParameterableElement)
        {
            ((ClassifierTemplateParameter) getChildEObject()).setParameteredElement(null);
        }
        ((TemplateSignature) getParentEObject()).getOwnedParameters().remove(getChildEObject());
    }

}
