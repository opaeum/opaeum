/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.figures;

import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PackageFigure extends org.topcased.draw2d.figures.PackageFigure
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PackageFigure()
    {
        super();
    }

    /**
     * The label is a composed label
     * 
     * @see org.topcased.draw2d.figures.PackageFigure#createLabel()
     */
    protected ILabel createLabel()
    {
        return new ComposedLabel(new Label(), new EditableLabel(), new Label(), false);
    }
}