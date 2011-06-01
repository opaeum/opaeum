/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.MoveEdgeObjectUVCommand;
import org.topcased.modeler.di.model.EdgeObjectUV;
import org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy;
import org.topcased.modeler.edit.requests.MoveEdgeObjectRequest;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.classdiagram.figures.InstanceSpecificationLinkFigure;

/**
 * An edit policy to select and move the name relative to the EReference connection .<br>
 * 
 *@author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class InstanceSpecificationLinkEdgeObjectUVEditPolicy extends EdgeObjectUVEditPolicy
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy#isEnd(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    @Override
    protected boolean isEnd(IEdgeObjectFigure figure)
    {
        InstanceSpecificationLinkFigure instanceFigure = (InstanceSpecificationLinkFigure) getHostFigure();

        if (figure == instanceFigure.getSrcNameEdgeObjectFigure())
        {
            return false;
        }
        if (figure == instanceFigure.getTargetNameEdgeObjectFigure())
        {
            return true;
        }

        return true;
    }

    /**
     * @see org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy#getMoveEdgeObjectCommand(org.topcased.modeler.edit.requests.MoveEdgeObjectRequest)
     */
    @Override
    protected Command getMoveEdgeObjectCommand(MoveEdgeObjectRequest request)
    {
        EdgeObjectUV edgeObject = (EdgeObjectUV) request.getEdgeObject();
        // Fix #1780
        // No movement allowed for Edge Objects representing source and target properties
        if (ClassEdgeObjectConstants.SRCPROPERTIES_EDGE_OBJECT_ID.equals(edgeObject.getId()) || ClassEdgeObjectConstants.TARGETPROPERTIES_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return null;
        }
        // EndFix #1780
        int[] uv = computeUVDistance(request.getMoveDelta(), edgeObject, request.getLocation());
        return new MoveEdgeObjectUVCommand(edgeObject, uv[0], uv[1]);
    }

}
