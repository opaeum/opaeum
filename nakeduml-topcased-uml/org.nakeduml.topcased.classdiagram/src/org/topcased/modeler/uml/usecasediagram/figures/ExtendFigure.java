/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectEditableLabel;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;
import org.topcased.modeler.figures.IGraphEdgeFigure;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ExtendFigure extends PolylineConnectionEx implements IGraphEdgeFigure
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
     * @generated
     */
    private IEdgeObjectFigure extensionEdgeObject;

    /**
     * @generated
     */
    private Locator extensionLocator;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExtendFigure()
    {
        super();

        nameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        nameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) nameEdgeObject);
        add(nameEdgeObject, nameLocator);
        extensionEdgeObject = new EdgeObjectEditableLabel(this);
        extensionLocator = new ConnectionEndpointLocator(this, false);
        add(extensionEdgeObject, extensionLocator);

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
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getExtensionEdgeObjectFigure()
    {
        return extensionEdgeObject;
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
        if (edgeObjectfigure == extensionEdgeObject)
        {
            return extensionLocator;
        }

        return null;
    }

}