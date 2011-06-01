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
package org.topcased.modeler.uml.alldiagram.figures;

import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;
import org.topcased.modeler.figures.IGraphEdgeFigure;

/**
 * 
 * @generated
 */
public class UsageFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
{
    /**
     * @generated
     */
    private static final int LINE_ON = 8;

    /**
     * @generated
     */
    private static final int LINE_OFF = 7;

    /**
     * @generated
     */
    private IEdgeObjectFigure middleNameEdgeObject;

    /**
     * @generated
     */
    private Locator middleNameLocator;

    /**
     * The constructor
     * 
     * @generated
     */
    public UsageFigure()
    {
        super();

        middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        middleNameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) middleNameEdgeObject);
        add(middleNameEdgeObject, middleNameLocator);

        setLineStyle(SWT.LINE_CUSTOM);
        setLineDash(new int[] {LINE_ON, LINE_OFF});
    }

    /**
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getMiddleNameEdgeObjectFigure()
    {
        return middleNameEdgeObject;
    }

    /**
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == middleNameEdgeObject)
        {
            return middleNameLocator;
        }
        return null;
    }

    /**
     * @see org.eclipse.gef.handles.HandleBounds#getHandleBounds()
     * @generated
     */
    public Rectangle getHandleBounds()
    {
        return getPoints().getBounds();
    }
}