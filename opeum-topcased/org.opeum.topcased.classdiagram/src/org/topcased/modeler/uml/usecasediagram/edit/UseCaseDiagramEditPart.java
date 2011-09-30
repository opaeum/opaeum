/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalev�e (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.uml2.uml.NamedElement;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;
import org.topcased.modeler.uml.usecasediagram.figures.UseCaseDiagramFigure;
import org.topcased.modeler.uml.usecasediagram.policies.UseCaseDiagramLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseDiagramEditPart extends DiagramEditPart
{

    /**
     * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the root model element
     * @generated
     */
    public UseCaseDiagramEditPart(Diagram model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#getLayoutEditPolicy()
     * @generated
     */
    protected EditPolicy getLayoutEditPolicy()
    {
        return new UseCaseDiagramLayoutEditPolicy();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
     * @generated
     */
    protected IFigure createBodyFigure()
    {
        return new UseCaseDiagramFigure();
    }

    /**
     * @see org.topcased.modeler.edit.DiagramEditPart#getHeaderDiagram()
     */
    protected String getHeaderDiagram()
    {
        return ((NamedElement) getEObject()).getName();
    }

}