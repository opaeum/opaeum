/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.RedefinableTemplateSignature;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ClassDiagramLayoutEditPolicy extends ModelerLayoutEditPolicy
{
    /**
     * Default contructor.<br>
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ClassDiagramLayoutEditPolicy()
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

    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject)
     * @custom
     */
    protected boolean isValid(EObject child, EObject parent)
    {
        // Fix bug [#610]
        if (child instanceof EnumerationLiteral || child instanceof RedefinableTemplateSignature)
        {
            return false;
        }

        return super.isValid(child, parent);
    }
}