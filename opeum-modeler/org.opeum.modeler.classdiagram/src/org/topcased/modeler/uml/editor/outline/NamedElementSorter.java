/*****************************************************************************
 * Copyright (c) 2010 ATOS ORIGIN INTEGRATION.
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
package org.topcased.modeler.uml.editor.outline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.topcased.modeler.internal.ordering.IOrderAlgorithm;

/**
 * Sort UML named element by name
 * @author tfaure
 *
 */
public class NamedElementSorter implements IOrderAlgorithm
{

    public List<EObject> sortChildren(EObject eobject)
    {
        List<EObject> result = new ArrayList<EObject>(eobject.eContents());
        result.addAll(eobject.eContents());
        Collections.sort(result,new Comparator<EObject>()
        {
            public int compare(EObject o1, EObject o2)
            {
                String index1 = "" ; 
                String index2 = "";
                if (o1 instanceof NamedElement)
                {
                    index1 = ((NamedElement) o1).getName();
                }
                if (o2 instanceof NamedElement)
                {
                    index2 = ((NamedElement) o2).getName();
                }
                return index1.compareTo(index2);
            }
        });
        return result;
    }

    public boolean canOrder(EObject context)
    {
        return context instanceof NamedElement;
    }

}
