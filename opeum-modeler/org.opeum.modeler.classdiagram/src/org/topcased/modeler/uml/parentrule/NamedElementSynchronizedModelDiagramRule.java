/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Hemery (Atos Origin) vincent.hemery@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/

package org.topcased.modeler.uml.parentrule;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.topcased.modeler.extensions.SynchronizedModelDiagramRule;

/**
 * The Class NamedElementSynchronizedModelDiagramRule provides the name for NamedElement objects.
 */
public class NamedElementSynchronizedModelDiagramRule extends SynchronizedModelDiagramRule
{

    /**
     * @see org.topcased.modeler.extensions.SynchronizedModelDiagramRule#getEObjectName(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    @Override
    public String getEObjectName(EObject pObject, boolean pLongName)
    {
        String lClassName = pObject.eClass().getName();
        if (pObject instanceof NamedElement)
        {
            String lObjectName = ((NamedElement) pObject).getQualifiedName();
            if (lObjectName == null || !pLongName)
            {
                lObjectName = ((NamedElement) pObject).getName();
            }
            return lClassName + " " + lObjectName;
        }
        else
        {
            return super.getEObjectName(pObject, pLongName);
        }
    }

}
