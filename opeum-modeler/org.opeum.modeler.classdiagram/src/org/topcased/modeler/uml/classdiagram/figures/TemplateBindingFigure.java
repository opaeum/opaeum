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
 * @generated
 */
public class TemplateBindingFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
{

    /**
     * @generated
     */
    private IEdgeObjectFigure middleNameEdgeObject;

    /**
     * @generated
     */
    private Locator middleNameLocator;

    /**
     * @generated
     */
    private IEdgeObjectFigure stereotypeEdgeObject;

    /**
     * @generated
     */
    private Locator stereotypeLocator;

    /**
     * The constructor
     *
     * @generated
     */
    public TemplateBindingFigure()
    {
        super();

        middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        middleNameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) middleNameEdgeObject);
        add(middleNameEdgeObject, middleNameLocator);

        stereotypeEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        stereotypeLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) stereotypeEdgeObject);
        add(stereotypeEdgeObject, stereotypeLocator);
        setLineStyle(SWT.LINE_DASH);
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
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getStereotypeEdgeObjectFigure()
    {
        return stereotypeEdgeObject;
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
        if (edgeObjectfigure == stereotypeEdgeObject)
        {
            return stereotypeLocator;
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