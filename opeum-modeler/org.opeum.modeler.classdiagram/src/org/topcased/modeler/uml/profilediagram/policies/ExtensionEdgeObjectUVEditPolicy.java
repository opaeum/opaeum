/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.profilediagram.policies;

import org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.profilediagram.figures.ExtensionFigure;

/**
 * An edit policy to select and move the name and the cardinality label relative to the EReference connection.
 * 
 * @generated
 */
public class ExtensionEdgeObjectUVEditPolicy extends EdgeObjectUVEditPolicy
{
    /**
     * @see org.topcased.modeler.edit.policies.EdgeObjectUVEditPolicy#isEnd(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    protected boolean isEnd(IEdgeObjectFigure figure)
    {
        ExtensionFigure extensionFigure = (ExtensionFigure) getHostFigure();

        if (figure == extensionFigure.getRequiredFieldEdgeObjectFigure())
        {
            return false;
        }
        return true;
    }

}
