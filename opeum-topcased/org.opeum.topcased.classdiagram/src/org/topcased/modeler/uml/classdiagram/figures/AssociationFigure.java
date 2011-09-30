/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectEditableLabel;
import org.topcased.modeler.figures.EdgeObjectLabel;
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

    private IEdgeObjectFigure srcNameEdgeObject;

    private Locator srcCountLocator;

    private IEdgeObjectFigure targetNameEdgeObject;

    private Locator srcNameLocator;

    private IEdgeObjectFigure srcCountEdgeObject;

    private Locator targetCountLocator;

    private IEdgeObjectFigure middleNameEdgeObject;

    private Locator targetNameLocator;

    private IEdgeObjectFigure targetCountEdgeObject;

    private Locator middleNameLocator;

    private IEdgeObjectFigure stereotypeEdgeObject;

    private Locator stereotypeLocator;

    //Fix #1780
    private IEdgeObjectFigure srcPropertiesEdgeObject;

    private RelativeLocator srcPropertiesLocator;
    
    private IEdgeObjectFigure targetPropertiesEdgeObject;

    private Locator targetPropertiesLocator;
    //EndFix #1780
    
    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociationFigure()
    {
        super();

        srcNameEdgeObject = new EdgeObjectEditableLabel(this);
        srcNameLocator = new ConnectionEndpointLocator(this, false);
        add(srcNameEdgeObject, srcNameLocator);

        //Fix #1780
        srcPropertiesEdgeObject = new EdgeObjectLabel(this);
        srcPropertiesLocator = new RelativeLocator(srcNameEdgeObject, 0.5, 1.5);
        add(srcPropertiesEdgeObject, srcPropertiesLocator);
        //EndFix #1780
        
        srcCountEdgeObject = new EdgeObjectEditableLabel(this);
        srcCountLocator = new ConnectionEndpointLocator(this, false);
        add(srcCountEdgeObject, srcCountLocator);

        targetNameEdgeObject = new EdgeObjectEditableLabel(this);
        targetNameLocator = new ConnectionEndpointLocator(this, true);
        add(targetNameEdgeObject, targetNameLocator);

        //Fix #1780
        targetPropertiesEdgeObject = new EdgeObjectLabel(this);
        targetPropertiesLocator = new RelativeLocator(targetNameEdgeObject, 0.5, 1.5);
        add(targetPropertiesEdgeObject, targetPropertiesLocator);
        //EndFix #1780
        
        targetCountEdgeObject = new EdgeObjectEditableLabel(this);
        targetCountLocator = new ConnectionEndpointLocator(this, true);
        add(targetCountEdgeObject, targetCountLocator);
        
        middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        middleNameLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) middleNameEdgeObject);
        add(middleNameEdgeObject, middleNameLocator);

        stereotypeEdgeObject = new EdgeObjectOffsetEditableLabel(this);
        stereotypeLocator = new EdgeObjectOffsetLocator((IEdgeObjectOffsetFigure) stereotypeEdgeObject);
        add(stereotypeEdgeObject, stereotypeLocator);
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

    //Fix #1780
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getSrcPropertiesEdgeObjectFigure()
    {
        return srcPropertiesEdgeObject;
    }
    //EndFix #1780
    
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
    public IEdgeObjectFigure getTargetNameEdgeObjectFigure()
    {
        return targetNameEdgeObject;
    }

    //Fix #1780
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the object figure
     * @generated
     */
    public IEdgeObjectFigure getTargetPropertiesEdgeObjectFigure()
    {
        return targetPropertiesEdgeObject;
    }
    //EndFix #1780
    
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
    public IEdgeObjectFigure getMiddleNameEdgeObjectFigure()
    {
        return middleNameEdgeObject;
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
     * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
     * @generated
     */
    public Locator getLocator(IEdgeObjectFigure edgeObjectfigure)
    {
        if (edgeObjectfigure == srcNameEdgeObject)
        {
            return srcNameLocator;
        }
        if (edgeObjectfigure == srcCountEdgeObject)
        {
            return srcCountLocator;
        }
        if (edgeObjectfigure == targetNameEdgeObject)
        {
            return targetNameLocator;
        }
        if (edgeObjectfigure == targetCountEdgeObject)
        {
            return targetCountLocator;
        }
        if (edgeObjectfigure == middleNameEdgeObject)
        {
            return middleNameLocator;
        }
        if (edgeObjectfigure == stereotypeEdgeObject)
        {
            return stereotypeLocator;
        }
        //Fix #1780
        if (edgeObjectfigure == srcPropertiesEdgeObject)
        {
            return srcPropertiesLocator;
        }
        if (edgeObjectfigure == targetPropertiesEdgeObject)
        {
            return targetPropertiesLocator;
        }
        //EndFix #1780
        
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