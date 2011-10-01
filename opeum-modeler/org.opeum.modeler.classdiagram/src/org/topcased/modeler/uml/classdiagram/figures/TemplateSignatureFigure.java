/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  eperico (atos origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

/**
 * @generated
 */
public class TemplateSignatureFigure extends Shape implements IContainerFigure
{    
    /** The content pane. */
    private IFigure contentPane;
    
    /**
     * Constructor
     *
     * @generated
     */
    public TemplateSignatureFigure()
    {
        LayoutManager layout = new StackLayout();
        setLayoutManager(layout);

        contentPane = new Figure();
        contentPane.setOpaque(false);
        
        ToolbarLayout toolbarLayout = new ToolbarLayout();
        toolbarLayout.setSpacing(5);
        toolbarLayout.setVertical(false);
        contentPane.setLayoutManager(toolbarLayout);
        contentPane.setBorder(new MarginBorder(4));
        add(contentPane);
    }
    
    /**
     * @see org.topcased.draw2d.figures.BorderedLabel#createLabel()
     */
    protected ILabel createLabel()
    {
        return new ComposedLabel(new Label(), new EditableLabel(), new Label(), false);
    }
    
    /**
     * @see org.topcased.draw2d.figures.BorderedLabel#fillShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void fillShape(Graphics graphics)
    {
        setLineStyle(Graphics.LINE_DASH);
        graphics.fillRectangle(getBounds().getExpanded(-1, -1));
    }
    
    /**
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void outlineShape(Graphics graphics)
    {
        Rectangle f = Rectangle.SINGLETON;
        Rectangle r = getBounds();
        f.x = r.x + lineWidth / 2;
        f.y = r.y + lineWidth / 2;
        f.width = r.width - lineWidth;
        f.height = r.height - lineWidth;
        graphics.drawRectangle(f);
    }

    /**
     * @see org.topcased.draw2d.figures.IContainerFigure#getContentPane()
     */
    public IFigure getContentPane()
    {
        return contentPane;
    }

}