/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.figures;

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.Label;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InstanceSpecificationFigure extends org.topcased.draw2d.figures.ClassFigure
{
    private Figure header;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InstanceSpecificationFigure()
    {
        super();
    }

    /**
     * The label is a composed label and is underlined
     * 
     * @see org.topcased.draw2d.figures.ClassFigure#drawFigure()
     */
    protected void drawFigure()
    {
        ToolbarLayout layout = new ToolbarLayout();
        setLayoutManager(layout);

        setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
        setOpaque(true);

        setLabel(new ComposedLabel(new Label(), new ComposedLabel(null, new EditableLabel(), new Label(), true), null, false));
        // getLabel().setBorder(new OneLineBorder(1, PositionConstants.BOTTOM));
        // //to underline the label

        header = new Figure();
        header.setLayoutManager(new GridLayout());
        header.add(getLabel(), new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL));

        add(header);

        setContentPane(new Figure());
        getContentPane().setLayoutManager(new ToolbarLayout());
        add(getContentPane());

        //
        // ToolbarLayout layout = new ToolbarLayout();
        // setLayoutManager(layout);
        //
        // setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(1)));
        // setOpaque(true);
        //
        // setLabel(new ComposedLabel(new Label(), new EditableLabel(), new
        // Label(), false));
        // add(getLabel());
        //
        // setContentPane(new Figure());
        // getContentPane().setLayoutManager(new ToolbarLayout());
        // add(getContentPane());
    }

}