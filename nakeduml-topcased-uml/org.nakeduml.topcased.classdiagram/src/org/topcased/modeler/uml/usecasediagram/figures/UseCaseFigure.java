/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalev�e (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IOvalAnchorableFigure;
import org.topcased.draw2d.figures.BorderedLabel;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.layout.CenterLayout;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseFigure extends BorderedLabel implements IOvalAnchorableFigure
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public UseCaseFigure()
    {
        setBorder(new MarginBorder(5));
        CenterLayout layout = new CenterLayout();
        setLayoutManager(layout);

        setOpaque(true);

        EditableLabel nameLbl = new EditableLabel(true);
        nameLbl.setTextJustification(PositionConstants.CENTER);
        setLabel(new ComposedLabel(new Label(), nameLbl, new Label(), false));
        add(getLabel());
    }

    /**
     * @see org.topcased.draw2d.figures.BorderedLabel#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics)
    {
        Rectangle r = Rectangle.SINGLETON;
        r.setBounds(getBounds());
        r.width--;
        r.height--;
        r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);
        graphics.drawOval(r);
    }

    /**
     * The figure is a filled ellipse
     * 
     * @see org.topcased.draw2d.figures.BorderedLabel#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected void fillShape(Graphics graphics)
    {
        graphics.fillOval(getBounds());
    }

    /**
     *  
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IOvalAnchorableFigure#getOvalBounds()
     */
    public Rectangle getOvalBounds()
    {
        Rectangle r = new Rectangle();

        r.setBounds(getBounds());
        r.width--;
        r.height--;
        r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);

        return r;
    }

}