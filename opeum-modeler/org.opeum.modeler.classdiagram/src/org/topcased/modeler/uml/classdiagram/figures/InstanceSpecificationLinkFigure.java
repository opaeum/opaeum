/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.topcased.modeler.figures.EdgeObjectEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IGraphEdgeFigure;

/**
 * Figure if the instance secification link.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class InstanceSpecificationLinkFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
{

    private final IEdgeObjectFigure srcNameEdgeObject;

    private final Locator srcNameLocator;

    private final IEdgeObjectFigure targetNameEdgeObject;

    private final Locator targetNameLocator;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InstanceSpecificationLinkFigure()
    {
        super();

        srcNameEdgeObject = new EdgeObjectEditableLabel(this);
        srcNameLocator = new ConnectionEndpointLocator(this, false);
        add(srcNameEdgeObject, srcNameLocator);

        targetNameEdgeObject = new EdgeObjectEditableLabel(this);
        targetNameLocator = new ConnectionEndpointLocator(this, true);
        add(targetNameEdgeObject, targetNameLocator);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getSrcNameEdgeObjectFigure()
    {
        return srcNameEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getTargetNameEdgeObjectFigure()
    {
        return targetNameEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == srcNameEdgeObject)
        {
            return srcNameLocator;
        }
        if (edgeObjectfigure == targetNameEdgeObject)
        {
            return targetNameLocator;
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