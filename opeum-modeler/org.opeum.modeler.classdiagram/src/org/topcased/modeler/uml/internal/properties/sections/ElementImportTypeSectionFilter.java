/*****************************************************************************
 * 
 * ElementImportTypeSectionFilter.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com - Initial API and implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.jface.viewers.IFilter;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.editor.UMLEditor;

/**
 * Filter for the section ElementImportTypeSection (to use only with UML Editor).
 */
public class ElementImportTypeSectionFilter implements IFilter
{

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object toTest)
    {
        if (UMLPlugin.getActivePage() != null && UMLPlugin.getActivePage().getActiveEditor() instanceof UMLEditor)
        {
            if (toTest instanceof org.topcased.modeler.uml.profilediagram.edit.ElementImportEditPart)
            {
                return true;
            }
        }
        return false;
    }

}
