/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
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
public class AssociationFigure extends PolylineConnectionEx implements IGraphEdgeFigure, HandleBounds
{

    /**
     * @generated
     */
    private IEdgeObjectFigure srcCountEdgeObject;

    /**
     * @generated
     */
    private Locator srcCountLocator;

    /**
     * @generated
     */
    private IEdgeObjectFigure targetCountEdgeObject;

    /**
     * @generated
     */
    private Locator targetCountLocator;

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
    private IEdgeObjectFigure stereotypeEdgeObject;

    /**
     * @generated
     */
    private Locator stereotypeLocator;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociationFigure()
    {
        super();

        srcCountEdgeObject = new EdgeObjectEditableLabel(this);
        srcCountLocator = new ConnectionEndpointLocator(this, false);
        add(srcCountEdgeObject, srcCountLocator);

        targetCountEdgeObject = new EdgeObjectEditableLabel(this);
        targetCountLocator = new ConnectionEndpointLocator(this, true);
        add(targetCountEdgeObject, targetCountLocator);

        nameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        nameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) nameEdgeObject);
        add(nameEdgeObject, nameLocator);

        stereotypeEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        stereotypeLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) stereotypeEdgeObject);
        add(stereotypeEdgeObject, stereotypeLocator);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == srcCountEdgeObject)
        {
            return srcCountLocator;
        }

        if (edgeObjectfigure == targetCountEdgeObject)
        {
            return targetCountLocator;
        }

        if (edgeObjectfigure == nameEdgeObject)
        {
            return nameLocator;
        }

        if (edgeObjectfigure == stereotypeEdgeObject)
        {
            return stereotypeLocator;
        }

        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getSrcCountEdgeObjectFigure()
    {
        return srcCountEdgeObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getTargetCountEdgeObjectFigure()
    {
        return targetCountEdgeObject;
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
    public IEdgeObjectFigure getStereotypeEdgeObjectFigure()
    {
        return stereotypeEdgeObject;
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