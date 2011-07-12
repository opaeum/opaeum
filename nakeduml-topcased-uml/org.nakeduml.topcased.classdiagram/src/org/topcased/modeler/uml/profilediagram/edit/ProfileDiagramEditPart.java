/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;
import org.topcased.modeler.uml.profilediagram.figures.ProfileDiagramFigure;
import org.topcased.modeler.uml.profilediagram.policies.ProfileDiagramLayoutEditPolicy;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProfileDiagramEditPart extends DiagramEditPart
{

    /**
     * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the root model element
     * @generated
     */
    public ProfileDiagramEditPart(Diagram model)
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
        return new ProfileDiagramLayoutEditPolicy();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
     * @generated
     */
    protected IFigure createBodyFigure()
    {
        return new ProfileDiagramFigure();
    }
}