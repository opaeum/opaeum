/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Thomas Szadel (Atos Origin)
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.topcased.modeler.uml.classdiagram.edit.ClassFromAssociationClassEditPart;

/**
 * An edit policy to select and move {@link org.topcased.modeler.uml.classdiagram.figures.AssociationClassFigure}
 * relative to their owning edge. <br>
 * Creation : 26 jun. 2008
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class AssociationClassMoveEditPolicy extends SelectionHandlesEditPolicy implements PropertyChangeListener
{

    @Override
    protected List createSelectionHandles()
    {
        return new ArrayList<Handle>();
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent arg0)
    {
        if (getHost().getSelected() != EditPart.SELECTED_NONE)
        {
            addSelectionHandles();
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#activate()
     */
    public void activate()
    {
        super.activate();
        ((ClassFromAssociationClassEditPart) getHost()).getFigure().addPropertyChangeListener(this);
    }

    /**
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#deactivate()
     */
    public void deactivate()
    {
        ((ClassFromAssociationClassEditPart) getHost()).getFigure().removePropertyChangeListener(this);
        super.deactivate();
    }

}
