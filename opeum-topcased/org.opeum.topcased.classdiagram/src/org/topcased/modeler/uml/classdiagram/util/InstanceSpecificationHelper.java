/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.classdiagram.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.uml.UMLTools;

/**
 * The Class object controller
 */
public final class InstanceSpecificationHelper
{

    /**
     * No default constructor, it's a utility class
     * 
     */
    private InstanceSpecificationHelper()
    {
        // no default constructor
    }

    /**
     * Look for available instance specification objects in a given container that match a given type
     * 
     * @param container container where looking for
     * @param type class type to filter
     * @return an array of found instance names
     */
    public static String[] getAvailableInstanceNames(Package container, Type type)
    {
        Collection<InstanceSpecification> validInstances = getAvailableInstances(container, type);
        String[] values = new String[validInstances.size()];
        int i = 0;
        for (Iterator<InstanceSpecification> it = validInstances.iterator(); it.hasNext();)
        {
            values[i++] = UMLTools.getCompleteName(it.next());
        }
        return values;
    }

    /**
     * Look for available instance specification objects in a given container that match a given type
     * 
     * @param container container where looking for
     * @param type class type to filter
     * @return a collection of found instance
     */
    public static Collection<InstanceSpecification> getAvailableInstances(Package container, Type type)
    {
        List<InstanceSpecification> instances = UMLTools.getAllObjects(container, InstanceSpecification.class);
        List<InstanceSpecification> validInstances = new ArrayList<InstanceSpecification>();
        for (InstanceSpecification inst : instances)
        {
            if (inst.getClassifiers().size() != 0 && inst.getClassifiers().get(0).equals(type))
            {
                validInstances.add(inst);
            }
        }
        return validInstances;
    }

    /**
     * Return boolean values
     * 
     * @return an array of strings
     */
    public static String[] getBooleanValues()
    {
        return new String[] {"true", "false"};
    }
}
