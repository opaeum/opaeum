/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tristan FAURE (ATOS ORIGIN INTEGRATION) tristan.faure@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.topcased.modeler.editor.Modeler;

/**
 * This class manages read only for an action using eobjects 
 * @author tfaure
 *
 */
public abstract class UMLEObjectAction extends UMLAction
{

    public UMLEObjectAction(String text, Modeler ed)
    {
        super(text, ed);
    }

    /**
     * This method returns the eobjects selected in the action
     * this eobject is used to calculate if action is enabled or not
     * @return the eobject
     */
    public abstract EObject getEObject();

    @Override
    public boolean isEnabled()
    {
        boolean isReadOnly = false;
        if (getEditor() != null && getEObject() != null)
        {
            EditingDomain domain = getEditor().getEditingDomain();
            if (domain != null)
            {
                isReadOnly = domain.isReadOnly(getEObject().eResource());
            }
        }
        return super.isEnabled() && !isReadOnly;
    }

}
