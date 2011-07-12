/***********************************************************************
 * Copyright (c) 2005, 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies) - initial API and implementation
 *   Jacques Lescot (Anyware Technologies) - initial API and implementation
 *   Maxime Nauleau (Atos Origin) - Fix #792
 *   Jacques Lescot (Anyware Technologies) - Remove unecessary fix in this method. This is managed at the generic level
 **********************************************************************/
package org.topcased.modeler.uml.usecasediagram.policies;

import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseDiagramLayoutEditPolicy extends ModelerLayoutEditPolicy
{
    /**
     * Default contructor. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UseCaseDiagramLayoutEditPolicy()
    {
        super();
    }

    /**
     * Allow drag and drop
     * 
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isExternalObjectAllowed(org.topcased.modeler.di.model.GraphNode,
     *      org.topcased.modeler.di.model.GraphNode)
     */
    protected boolean isExternalObjectAllowed(GraphNode parent, GraphNode child)
    {
        return true;
    }
}