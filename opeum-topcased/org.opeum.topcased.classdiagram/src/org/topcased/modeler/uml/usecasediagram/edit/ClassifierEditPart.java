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

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.alldiagram.edit.NamedElementEditPart;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.ConstraintLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.usecasediagram.UseCaseEditPolicyConstants;
import org.topcased.modeler.uml.usecasediagram.commands.ClassifierRestoreConnectionCommand;
import org.topcased.modeler.uml.usecasediagram.policies.AssociationEdgeCreationEditPolicy;
import org.topcased.modeler.uml.usecasediagram.policies.GeneralizationEdgeCreationEditPolicy;

/**
 * The Classifier object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class ClassifierEditPart extends NamedElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public ClassifierEditPart(GraphNode obj)
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

        installEditPolicy(UseCaseEditPolicyConstants.ASSOCIATION_EDITPOLICY, new AssociationEdgeCreationEditPolicy());

        installEditPolicy(UseCaseEditPolicyConstants.GENERALIZATION_EDITPOLICY, new GeneralizationEdgeCreationEditPolicy());

        installEditPolicy(UseCaseEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());

        installEditPolicy(UseCaseEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY, new ConstraintLinkEdgeCreationEditPolicy());
        
        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new ClassifierRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }

}