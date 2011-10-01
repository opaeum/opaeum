/*****************************************************************************
 * Copyright (c) 2008; Atos Origin.
 *
 * Contributors:
 *  Frédéric Barraillé; [(Atos Origin)] [frederic.barraille@atosorigin.com]
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.uml.alldiagram.commands.ConstraintLinkEdgeCreationCommand;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * ConstraintLink edge creation
 * 
 * @generated
 */
public class ConstraintLinkEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy
{
    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain,
     *      org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source)
    {
        return new ConstraintLinkEdgeCreationCommand(domain, edge, source);
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
     * @generated
     */
    protected boolean checkEdge(GraphEdge edge)
    {
        if (edge.getSemanticModel() instanceof SimpleSemanticModelElement)
        {
            return AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo());
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkSource(GraphElement source)
    {
        EObject object = Utils.getElement(source);
        if (object instanceof org.eclipse.uml2.uml.Constraint)
        {
            return true;
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkTargetForSource(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.Constraint && targetObject instanceof org.eclipse.uml2.uml.Element)
        {
            if (!sourceObject.equals(targetObject))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
     * @generated
     */
    protected boolean checkCommand(Command command)
    {
        return command instanceof ConstraintLinkEdgeCreationCommand;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.Constraint && targetObject instanceof org.eclipse.uml2.uml.Element)
        {
            return new SourceTargetData(false, false, SourceTargetData.NONE, null, null, null, null, null, "constrainedElement", null, null);
        }
        return null;
    }

}
