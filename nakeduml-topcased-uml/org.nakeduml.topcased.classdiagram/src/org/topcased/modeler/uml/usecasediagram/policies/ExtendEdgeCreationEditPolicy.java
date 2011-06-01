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

package org.topcased.modeler.uml.usecasediagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.uml2.uml.Extend;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.commands.ReconnectEdgeToTargetCommand;
import org.topcased.modeler.commands.ReconnectGraphEdgeCommand;
import org.topcased.modeler.commands.ReconnectSourceToEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.usecasediagram.commands.ExtendEdgeCreationCommand;
import org.topcased.modeler.uml.usecasediagram.edit.ExtendEditPart;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * Extend edge creation <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ExtendEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain,
     *      org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source)
    {
        return new ExtendEdgeCreationCommand(domain, edge, source);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
     * @generated
     */
    protected boolean checkEdge(GraphEdge edge)
    {
        return Utils.getElement(edge) instanceof Extend;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkSource(GraphElement source)
    {
        EObject object = Utils.getElement(source);
        if (object instanceof org.eclipse.uml2.uml.UseCase)
        {
            return true;
        }
        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkTargetForSource(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.UseCase && targetObject instanceof org.eclipse.uml2.uml.UseCase)
        {
            if (!sourceObject.equals(targetObject))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
     * @generated
     */
    protected boolean checkCommand(Command command)
    {
        return command instanceof ExtendEdgeCreationCommand;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.UseCase && targetObject instanceof org.eclipse.uml2.uml.UseCase)
        {
            return new SourceTargetData(false, false, SourceTargetData.SOURCE, "org.eclipse.uml2.uml.UseCase", "extend", null, "extendedCase", null, null, null, null);
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     * @generated
     */
    protected Command getReconnectSourceCommand(ReconnectRequest request)
    {
        ConnectionEditPart connection = request.getConnectionEditPart();
        if (connection instanceof ExtendEditPart)
        {
            final ExtendEditPart edit = (ExtendEditPart) connection;

            final GraphElement newSource = (GraphElement) getHost().getModel();

            if (checkSource(newSource))
            {
                final GraphElement graphElt = (GraphElement) edit.getModel();
                ReconnectGraphEdgeCommand command = new ReconnectSourceToEdgeCommand(edit, "extend")
                {
                    protected EObject getObjectToUpdate()
                    {
                        return Utils.getElement(graphElt);
                    }
                };
                command.setNewElement(newSource);
                return command;
            }
        }

        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     * 
     * @generated
     */
    protected Command getReconnectTargetCommand(ReconnectRequest request)
    {
        ConnectionEditPart connection = request.getConnectionEditPart();
        if (connection instanceof ExtendEditPart)
        {
            final ExtendEditPart edit = (ExtendEditPart) connection;

            final GraphElement newTarget = (GraphElement) getHost().getModel();
            final GraphElement source = (GraphElement) edit.getSource().getModel();

            if (checkTargetForSource(source, newTarget))
            {
                final GraphElement graphElt = (GraphElement) edit.getModel();
                ReconnectGraphEdgeCommand command = new ReconnectEdgeToTargetCommand(edit, "extendedCase")
                {
                    protected EObject getObjectToUpdate()
                    {
                        return Utils.getElement(graphElt);
                    }
                };
                command.setNewElement(newTarget);
                return command;
            }
        }

        return null;
    }
}