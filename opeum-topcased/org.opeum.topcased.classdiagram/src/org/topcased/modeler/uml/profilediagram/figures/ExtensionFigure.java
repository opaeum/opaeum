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

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.topcased.modeler.figures.EdgeObjectEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IGraphEdgeFigure;

/**
 * @generated
 */
public class ExtensionFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
{

    /**
     * @generated
     */
    private IEdgeObjectFigure requiredFieldEdgeObject;

    /**
     * @generated
     */
    private Locator requiredFieldLocator;

    /**
     * The constructor
     * 
     * @generated
     */
    public ExtensionFigure()
    {
        super();

        requiredFieldEdgeObject = new EdgeObjectEditableLabel(this);
        requiredFieldLocator = new ConnectionEndpointLocator(this, false);
        add(requiredFieldEdgeObject, requiredFieldLocator);
        setLineStyle(SWT.LINE_SOLID);
    }

    /**
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getRequiredFieldEdgeObjectFigure()
    {
        return requiredFieldEdgeObject;
    }

    /**
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == requiredFieldEdgeObject)
        {
            return requiredFieldLocator;
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