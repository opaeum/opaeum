/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.alldiagram.commands.NamedElementRestoreConnectionCommand;
import org.topcased.modeler.uml.alldiagram.policies.DependencyEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.UsageEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;

/**
 * The NamedElement object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class NamedElementEditPart extends ElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public NamedElementEditPart(GraphNode obj)
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

        installEditPolicy(ClassEditPolicyConstants.DEPENDENCY_EDITPOLICY, new DependencyEdgeCreationEditPolicy());

        installEditPolicy(ClassEditPolicyConstants.USAGE_EDITPOLICY, new UsageEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new NamedElementRestoreConnectionCommand(getHost());
            }
        });
    }

}