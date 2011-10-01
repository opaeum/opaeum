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
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DependencyFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
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
    private IEdgeObjectFigure stereotypeEdgeObject;

    /**
     * @generated
     */
    private Locator stereotypeLocator;

    /**
     * @generated NOT
     */
    private IEdgeObjectFigure middleNameEdgeObject;

    /**
     * @generated NOT
     */
    private Locator middleNameLocator;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public DependencyFigure()
    {
        super();

        stereotypeEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        stereotypeLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) stereotypeEdgeObject);
        add(stereotypeEdgeObject, stereotypeLocator);

        middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        middleNameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) middleNameEdgeObject);
        add(middleNameEdgeObject, middleNameLocator);

        setLineStyle(SWT.LINE_CUSTOM);
        setLineDash(new int[] {LINE_ON, LINE_OFF});
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getStereotypeEdgeObjectFigure()
    {
        return stereotypeEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated NOT
     */
    public IEdgeObjectFigure getNameEdgeObjectFigure()
    {
        return middleNameEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated NOT
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == stereotypeEdgeObject)
        {
            return stereotypeLocator;
        }
        if (edgeObjectfigure == middleNameEdgeObject)
        {
            return middleNameLocator;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.handles.HandleBounds#getHandleBounds()
     * @generated
     */
    public Rectangle getHandleBounds()
    {
        return getPoints().getBounds();
    }
}