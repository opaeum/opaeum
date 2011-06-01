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
package org.topcased.modeler.uml.internal.properties.sections.classifier;

import org.eclipse.jface.viewers.IFilter;
import org.topcased.modeler.uml.classdiagram.annotation.UseElementsVisibility;

/**
 * Filter for the section ClassifierElementsVisibilityPropertySection.
 */
public class ClassifierElementsVisibilitySectionFilter implements IFilter
{

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object toTest)
    {
        return toTest != null && toTest.getClass().isAnnotationPresent(UseElementsVisibility.class);
    }

}
