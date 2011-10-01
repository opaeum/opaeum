/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.uml2.uml.NamedElement;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;
import org.topcased.modeler.uml.classdiagram.figures.ClassDiagramFigure;
import org.topcased.modeler.uml.classdiagram.policies.ClassDiagramLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ClassDiagramEditPart extends DiagramEditPart
{

    /**
     * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the root model element
     * @generated
     */
    public ClassDiagramEditPart(Diagram model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#getLayoutEditPolicy()
     * @generated
     */
    @Override
    protected EditPolicy getLayoutEditPolicy()
    {
        return new ClassDiagramLayoutEditPolicy();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
     * @generated
     */
    @Override
    protected IFigure createBodyFigure()
    {
        return new ClassDiagramFigure();
    }

    /**
     * The diagram header is the name of the package
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#getHeaderDiagram()
     */
    @Override
    protected String getHeaderDiagram()
    {
        return ((NamedElement) getEObject()).getName();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#removeChildVisual(org.eclipse.gef.EditPart)
     */
    @Override
    protected void removeChildVisual(EditPart childEditPart)
    {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (child instanceof org.topcased.draw2d.figures.ClassFigure)
        {
            for (Object obj : childEditPart.getChildren())
            {
                if (obj instanceof TemplateSignatureEditPart)
                {
                    child.getParent().remove(((TemplateSignatureEditPart) obj).getFigure());
                }
            }
        }
        super.removeChildVisual(childEditPart);
    }
}