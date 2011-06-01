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
 * The section for the isDerivedUnion property of the first end Property of an Association Object.
 * 
 * Creation 26 févr. 07
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class FirstEndIsDerivedUnionSection extends AbstractIsDerivedUnionSection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractIsDerivedUnionSection#getProperty(org.eclipse.uml2.uml.Association)
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