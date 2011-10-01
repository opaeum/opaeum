/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import org.eclipse.gef.EditDomain;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;

/**
 * InterfaceRealization edge creation command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InterfaceRealizationEdgeCreationCommand extends CreateTypedEdgeCommand
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public InterfaceRealizationEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
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
     * @generated
     */
    public InterfaceRealizationEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void redoModel()
    {
        // TODO add specific code if super method is not sufficient
        super.redoModel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void undoModel()
    {
        // TODO add specific code if super method is not sufficient
        super.undoModel();
    }

}