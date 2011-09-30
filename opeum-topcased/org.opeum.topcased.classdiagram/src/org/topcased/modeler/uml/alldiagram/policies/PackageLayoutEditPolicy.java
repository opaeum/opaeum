/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
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
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;

/**
 * @generated
 */
public class PackageLayoutEditPolicy extends ModelerLayoutEditPolicy
{

    /**
     * Default contructor.
     * 
     * @generated
     */
    public PackageLayoutEditPolicy()
    {
        super();
    }

    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject)
     * @generated
     */
    protected boolean isValid(EObject child, EObject parent)
    {
        if (( child instanceof Type || child instanceof PackageableElement || child instanceof Comment)
                && !(child instanceof EnumerationLiteral))
        {
            return true;
        }
        return false;
    }
}
