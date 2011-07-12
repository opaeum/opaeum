/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevee (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.GraphicWithLabelFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.figures.ManFigure;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ActorFigure extends GraphicWithLabelFigure
{

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ActorFigure()
    {
        super(PositionConstants.BOTTOM);
    }

    /**
     * The label is a composed label
     * 
     * @see org.topcased.draw2d.figures.GraphicWithLabelFigure#createLabel()
     */
    protected ILabel createLabel()
    {
        return new ComposedLabel(new Label(), new EditableLabel(), new Label(), false);
    }

    /**
     * @see org.topcased.draw2d.figures.GraphicWithLabelFigure#createBodyFigure()
     */
    protected IFigure createBodyFigure()
    {
        return new ManFigure();
    }

}