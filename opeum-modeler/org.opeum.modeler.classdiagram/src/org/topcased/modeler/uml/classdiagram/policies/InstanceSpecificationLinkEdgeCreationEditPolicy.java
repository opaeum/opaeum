/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.commands.ReconnectGraphEdgeCommand;
import org.topcased.modeler.commands.ReconnectNodeToNodeCommand;
import org.topcased.modeler.commands.ReconnectSourceToTargetCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.commands.InstanceSpecificationLinkEdgeCreationCommand;
import org.topcased.modeler.uml.classdiagram.edit.InstanceSpecificationLinkEditPart;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * InstanceSpecificationLink edge creation <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class InstanceSpecificationLinkEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain,
     *      org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    @Override
    protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source)
    {
        return new InstanceSpecificationLinkEdgeCreationCommand(domain, edge, source);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
     * @generated
     */
    @Override
    protected boolean checkEdge(GraphEdge edge)
    {
        return Utils.getElement(edge) instanceof InstanceSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    @Override
    protected boolean checkSource(GraphElement source)
    {
        EObject object = Utils.getElement(source);
        return object instanceof org.eclipse.uml2.uml.InstanceSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    @Override
    protected boolean checkTargetForSource(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof InstanceSpecification && targetObject instanceof InstanceSpecification)
        {
            return !sourceObject.equals(targetObject);
        }
        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
     * @generated
     */
    @Override
    protected boolean checkCommand(Command command)
    {
        return command instanceof InstanceSpecificationLinkEdgeCreationCommand;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    @Override
    protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.InstanceSpecification && targetObject instanceof org.eclipse.uml2.uml.InstanceSpecification)
        {
            return new SourceTargetData(false, false, SourceTargetData.DIAGRAM, null, null, null, null, null, "annotatedElement", null, null);
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     * @generated
     */
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request)
    {
        ConnectionEditPart connection = request.getConnectionEditPart();
        if (connection instanceof InstanceSpecificationLinkEditPart)
        {
            final InstanceSpecificationLinkEditPart edit = (InstanceSpecificationLinkEditPart) connection;

            final GraphElement newSource = (GraphElement) getHost().getModel();

            if (checkSource(newSource))
            {
                final GraphElement graphElt = (GraphElement) edit.getTarget().getModel();
                ReconnectGraphEdgeCommand command = new ReconnectSourceToTargetCommand(edit, "annotatedElement", ReconnectNodeToNodeCommand.SOURCE)
                {
                    @Override
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
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request)
    {
        ConnectionEditPart connection = request.getConnectionEditPart();
        if (connection instanceof InstanceSpecificationLinkEditPart)
        {
            final InstanceSpecificationLinkEditPart edit = (InstanceSpecificationLinkEditPart) connection;

            final GraphElement newTarget = (GraphElement) getHost().getModel();
            final GraphElement source = (GraphElement) edit.getSource().getModel();

            if (checkTargetForSource(source, newTarget))
            {
                final GraphElement graphElt = source;
                ReconnectGraphEdgeCommand command = new ReconnectSourceToTargetCommand(edit, "annotatedElement", ReconnectNodeToNodeCommand.TARGET)
                {
                    @Override
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