/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.figures;

import org.topcased.modeler.uml.figure.UMLDiagramFigure;

/**
 * The figure to display a Class Diagram. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NOT
 */
public class ClassDiagramFigure extends UMLDiagramFigure
{
    /**
     * Default constructor
     * 
     */
    public ClassDiagramFigure()
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
