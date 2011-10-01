/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.commands.BehavioredClassifierRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.policies.InterfaceRealizationEdgeCreationEditPolicy;

/**
 * The BehavioredClassifier object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class BehavioredClassifierEditPart extends ClassifierEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public BehavioredClassifierEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ClassEditPolicyConstants.INTERFACEREALIZATION_EDITPOLICY, new InterfaceRealizationEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new BehavioredClassifierRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

    }

}