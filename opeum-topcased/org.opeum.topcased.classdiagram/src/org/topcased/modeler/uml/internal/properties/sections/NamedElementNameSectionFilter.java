/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (Atos Origin) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pin;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

/**
 * The Class NamedElementNameSectionFilter. This filter is added in order to have a specific behavior for the pin
 */
public class NamedElementNameSectionFilter implements IFilter
{

    /**
     * True when toTest is NamedElement AND NOT a Pin
     */
    public boolean select(Object toTest)
    {
        Object element = toTest;
        if (toTest instanceof EMFGraphNodeEditPart)
        {
            element = ((EMFGraphNodeEditPart) toTest).getEObject();
        }
        else if (toTest instanceof EMFGraphEdgeEditPart)
        {
            element = ((EMFGraphEdgeEditPart) toTest).getEObject();
        }
        if (element instanceof NamedElement && !(element instanceof Pin))
        {
            return true;
        }

        return false;
    }
}
