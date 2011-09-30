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

package org.topcased.modeler.uml.usecasediagram.figures;

import org.topcased.modeler.uml.figure.UMLDiagramFigure;

/**
 * The figure to display a Use Case Diagram. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NOT
 */
public class UseCaseDiagramFigure extends UMLDiagramFigure
{
    /**
     * Default constructor
     * 
     */
    public UseCaseDiagramFigure()
    {
        super();
    }

    /**
     * @see org.topcased.modeler.uml.figure.UMLDiagramFigure#getPrefixText()
     */
    protected String getPrefixText()
    {
        return "package ";
    }

}
