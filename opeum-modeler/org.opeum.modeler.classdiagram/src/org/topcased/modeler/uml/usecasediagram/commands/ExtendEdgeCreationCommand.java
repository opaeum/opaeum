/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.commands;

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.commands.IDialogCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.usecasediagram.dialogs.ExtendEditDialog;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

/**
 * Extend edge creation command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ExtendEdgeCreationCommand extends CreateTypedEdgeCommand implements IDialogCommand
{

    private Extend newExtendObject = null;

    private ExtensionPoint extensionPoint = null;

    private String condition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public ExtendEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
    {
        this(domain, newObj, src, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @param needModelUpdate set it to true if the model need to be updated
     * @generated NOT
     */
    public ExtendEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
        newExtendObject = (Extend) Utils.getElement(getEdge());
    }

    /**
     * Open the dialog to edit the extend edge
     * 
     * @see org.topcased.modeler.commands.IDialogCommand#openDialog()
     */
    public boolean openDialog()
    {
        UseCase targetUseCase = (UseCase) Utils.getElement(getTarget());

        ExtendEditDialog extendDlg = new ExtendEditDialog(newExtendObject, targetUseCase, getEditDomain(), UMLPlugin.getActiveWorkbenchShell());

        if (extendDlg.open() == Window.OK)
        {
            extensionPoint = extendDlg.getExtensionPoint();
            condition = extendDlg.getCondition();
            return true;
        }

        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected void redoModel()
    {
        UseCase targetUseCase = (UseCase) Utils.getElement(getTarget());
        UseCase sourceUseCase = (UseCase) Utils.getElement(getSource());

        if (condition == null || condition.length() != 0)
        {
            Constraint constraint = UMLFactory.eINSTANCE.createConstraint();
            OpaqueExpression opaqueExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
            opaqueExpression.getBodies().add(condition);
            constraint.setSpecification(opaqueExpression);
            constraint.getConstrainedElements().add(newExtendObject);
            newExtendObject.setCondition(constraint);
        }

        // Add it to the source use case
        sourceUseCase.getExtends().add(newExtendObject);

        // init the name of the Extend object
        String curName = LabelHelper.INSTANCE.getName(getEditDomain(), newExtendObject);
        if (curName == null || curName.length() == 0)
        {
            LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), newExtendObject);
        }

        // Indicate to the extend object the extended use case and the extension
        // point to used
        newExtendObject.getExtensionLocations().add(extensionPoint);
        newExtendObject.setExtendedCase(targetUseCase);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected void undoModel()
    {
        UseCase targetUseCase = (UseCase) Utils.getElement(getTarget());
        UseCase sourceUseCase = (UseCase) Utils.getElement(getSource());

        // Remove extend object
        sourceUseCase.getExtends().remove(newExtendObject);

        // Remove extension point
        targetUseCase.getExtensionPoints().remove(extensionPoint);
    }

}