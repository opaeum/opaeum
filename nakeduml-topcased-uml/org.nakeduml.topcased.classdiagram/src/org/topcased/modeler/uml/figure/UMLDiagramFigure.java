/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalevée (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/

package org.topcased.modeler.uml.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.layout.BorderAttachedLayout;
import org.eclipse.draw2d.GridData;
import org.topcased.modeler.figures.DiagramFigure;

/**
 * The figure for the UML2 diagrams
 * 
 * @author <a href="mailto:nicolas.lalevee@anyware-tech.com">Nicolas Lalevée</a>
 * 
 */
public class UMLDiagramFigure extends DiagramFigure
{
    private static final int HEADER_CORNER = 7;

    /**
     * Constructor
     */
    public UMLDiagramFigure()
    {
        super();

        setOpaque(true);
        setBorder(new LineBorder());
    }

    /**
     * Create the contents
     * 
     * @see org.topcased.draw2d.figures.ContainerFigure#createContents()
     */
    public void createContents()
    {
        setLayoutManager(createLayout());

        setLabel(createLabel());
        add(getLabel());

        setContentPane(createContainer());
        add(getContentPane(), new GridData(GridData.FILL_BOTH));
    }

    /**
     * The label is 'act' followed by the name
     * 
     * @see org.topcased.draw2d.figures.ContainerFigure#createLabel()
     */
    protected ILabel createLabel()
    {
        Label prefix = new Label(getPrefixText());
        prefix.setFont(JFaceResources.getFontRegistry().getBold(""));
        ComposedLabel label = new ComposedLabel(prefix, new EditableLabel(), null, true);
        label.setBorder(new UMLHeaderBorder());
        return label;
    }

    /**
     * Simple BorderAttached layouted figure
     * 
     * @see org.topcased.draw2d.figures.ContainerFigure#createContainer()
     */
    protected IFigure createContainer()
    {
        IFigure container = new Figure();
        container.setLayoutManager(new BorderAttachedLayout());
        return container;
    }

    /**
     * Do special painting for the header
     * 
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics graphics)
    {
        super.paintFigure(graphics);

        Rectangle headerBounds = getLabel().getBounds();

        graphics.drawLine(headerBounds.getBottomLeft(), headerBounds.getBottomRight());
        graphics.drawLine(headerBounds.getBottomRight(), headerBounds.getBottomRight().getTranslated(HEADER_CORNER, -HEADER_CORNER * 2));
        graphics.drawLine(headerBounds.getBottomRight().getTranslated(HEADER_CORNER, -HEADER_CORNER * 2), headerBounds.getTopRight().getTranslated(HEADER_CORNER, 0));
    }

    /**
     * Subclasses should override this to return the prefix that should be used in the header
     * 
     * @return String
     */
    protected String getPrefixText()
    {
        return "prefix ";
    }

    /**
     * The border common to all the UML Diagrams
     */
    public static class UMLHeaderBorder extends AbstractBorder
    {
        private static final Insets INSETS = new Insets(5, 5, 8, 5);

        /**
         * The Constructor
         */
        public UMLHeaderBorder()
        {
            // Do nothing
        }

        /**
         * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
         */
        public Insets getInsets(IFigure figure)
        {
            return INSETS;
        }

        /**
         * Draw the border
         * 
         * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure, org.eclipse.draw2d.Graphics,
         *      org.eclipse.draw2d.geometry.Insets)
         */
        public void paint(IFigure figure, Graphics graphics, Insets insets)
        {
            // Do nothing
        }
    }
}
