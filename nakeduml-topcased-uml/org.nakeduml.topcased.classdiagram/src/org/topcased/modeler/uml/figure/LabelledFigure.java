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
 *  Benoit MAGGI (Atos Origin) benoit.maggi@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/

package org.topcased.modeler.uml.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.eclipse.draw2d.GridData;

/**
 * The Class LabbelledFigure.
 */
public class LabelledFigure extends Figure implements ILabelFigure
{

    /** The label. */
    private ILabel label;

    /** The body figure. */
    private IFigure bodyFigure;

    /**
     * Instantiates a new labbelled figure.
     * 
     * @param pBody the body
     */
    public LabelledFigure(IFigure pBody)
    {

        bodyFigure = pBody;
        // this(PositionConstants.BOTTOM);
        createContents(PositionConstants.BOTTOM);
    }

    /**
     * Creates the contents of the figure : by default, it creates a layout manager, a label and a body.
     * 
     * @param labelPosition the label position (from {@link PositionConstants})
     */
    protected void createContents(int labelPosition)
    {
        ToolbarLayout gridLayout = new ToolbarLayout();
        gridLayout.setStretchMinorAxis(false);
        gridLayout.setSpacing(5);
        gridLayout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
        setLayoutManager(gridLayout);

        // Create the label and the body figures
        label = createLabel();
        bodyFigure = createBodyFigure();
        setLayoutManager(gridLayout);
        add(bodyFigure, new GridData(getDefaultBodyFigureStyle(labelPosition)));
        add(label, new GridData(getDefaultLabelStyle(labelPosition)));
    }

    /**
     * Define the GridData style to be used for the body figure. This method should be overridden when a other style is
     * necessary.
     * 
     * @param labelPosition the label position (from {@link PositionConstants})
     * 
     * @return a GridData style
     */
    protected int getDefaultBodyFigureStyle(int labelPosition)
    {
        return GridData.FILL_BOTH;
    }

    /**
     * Define the GridData style to be used for the Label. This method should be overridden when a other style is
     * necessary.
     * 
     * @param labelPosition the label position (from {@link PositionConstants})
     * 
     * @return a GridData style
     */
    protected int getDefaultLabelStyle(int labelPosition)
    {
        switch (labelPosition)
        {
            case PositionConstants.LEFT:
            case PositionConstants.RIGHT:
                return GridData.VERTICAL_ALIGN_CENTER;
            case PositionConstants.TOP:
            case PositionConstants.BOTTOM:
                return GridData.HORIZONTAL_ALIGN_CENTER;
            default:
                break;
        }
        return GridData.CENTER;
    }

    /**
     * Creates the label of the figure.<br>
     * <b>Subclasses can override this method to customize the appearance of the label (for example you can return a
     * {@link ComposedLabel} instead)</b>
     * 
     * @return the label of the figure
     */
    protected ILabel createLabel()
    {
        EditableLabel lbl = new EditableLabel();
        lbl.setAlignment(PositionConstants.LEFT);
        lbl.setBorder(new MarginBorder(5));
        return lbl;
    }

    /**
     * Create the figure displayed beside the label. It is a RectangleFigure by default.<br>
     * <b>Subclasses can override this method to customize the appearance of the body</b>
     * 
     * @return the figure
     */
    protected IFigure createBodyFigure()
    {
        if (bodyFigure != null)
        {
            return bodyFigure;
        }
        else
        {
            return new RectangleFigure();
        }
    }

    /**
     * Gets the label.
     * 
     * @return the label
     * 
     * @see org.topcased.draw2d.figures.ILabelFigure#getLabel()
     */
    public ILabel getLabel()
    {
        return label;
    }

    /**
     * Return the body figure.
     * 
     * @return IFigure the body figure
     */
    public IFigure getBodyFigure()
    {
        return bodyFigure;
    }

}
