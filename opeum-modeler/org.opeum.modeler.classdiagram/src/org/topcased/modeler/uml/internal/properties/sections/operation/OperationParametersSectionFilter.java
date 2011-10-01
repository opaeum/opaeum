/*****************************************************************************
 * 
 * Copyright (c) 2009 Atos Origin
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
package org.topcased.modeler.uml.internal.properties.sections.operation;

import org.eclipse.uml2.uml.Operation;
import org.topcased.modeler.editor.properties.filters.PropertySectionFilter;

/**
 * Add a filter to the section OperationParameter in order third party tools to allow disabling this section.
 *
 */
public class OperationParametersSectionFilter extends PropertySectionFilter
{
    private static final String SECTION_ID = "org.topcased.modeler.uml.internal.properties.sections.operation.OperationParametersSection";  //$NON-NLS-1$ 

    @Override
    protected String getSectionId()
    {
        return SECTION_ID;
    }

    @Override
    protected boolean isValidObject(Object toTest)
    {
        return toTest instanceof Operation;
    }

}
