/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

/**
 * The section for the name property of the first end Property of an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public class FirstEndRoleSection extends AbstractRoleSection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractRoleSection#getProperty(org.eclipse.uml2.uml.Association)
     */
    protected Property getProperty(Association association)
    {
        if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0)
        {
            return association.getMemberEnds().get(0);
        }
        return null;
    }
}