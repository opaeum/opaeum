/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram.policies;

import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProfileDiagramLayoutEditPolicy extends ModelerLayoutEditPolicy
{
    /**
     * Default contructor.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ProfileDiagramLayoutEditPolicy()
    {
        super();
    }

    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isExternalObjectAllowed(org.topcased.modeler.di.model.GraphNode,
     *      org.topcased.modeler.di.model.GraphNode)
     */
    protected boolean isExternalObjectAllowed(GraphNode parent, GraphNode child)
    {
        return true;
    }

}