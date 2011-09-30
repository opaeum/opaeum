/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.Locator;
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
public class IncludeFigure extends PolylineConnectionEx implements IGraphEdgeFigure
{
    /**
     * @generated NOT
     */
    private static final int LINE_ON = 10;

    /**
     * @generated NOT
     */
    private static final int LINE_OFF = 5;

    /**
     * @generated
     */
    private IEdgeObjectFigure nameEdgeObject;

    /**
     * @generated
     */
    private Locator nameLocator;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public IncludeFigure()
    {
        super();

        nameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        nameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) nameEdgeObject);
        add(nameEdgeObject, nameLocator);

        setLineStyle(SWT.LINE_CUSTOM);
        setLineDash(new int[] {LINE_ON, LINE_OFF});
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getNameEdgeObjectFigure()
    {
        return nameEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {

        if (edgeObjectfigure == nameEdgeObject)
        {
            return nameLocator;
        }

        return null;
    }

}