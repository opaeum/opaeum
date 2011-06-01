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
package org.topcased.modeler.uml.profilediagram.figures;

import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

/**
 * @generated
 */
public class ElementImportFigure extends org.topcased.draw2d.figures.BorderedLabel
{
    /**
     * Constructor
     * 
     * @generated
     */
    public ElementImportFigure()
    {
        super();
    }
    
    /**
     * @see org.topcased.draw2d.figures.BorderedLabel#createLabel()
     */
    protected ILabel createLabel()
    {
        return new ComposedLabel(new Label("<<metaclass>>"), new Label(), null, false);
    }

}