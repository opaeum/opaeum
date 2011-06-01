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
package org.topcased.modeler.uml.editor.outline;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.outline.VirtualContainer;

/**
 * The Class InheritedMemberVirtualContainer get the list of EObjects derived from inherited member association
 */
public class InheritedMemberVirtualContainer implements VirtualContainer
{

    public Collection<EObject> getChildren(EObject eobject)
    {
        if (eobject instanceof Classifier)
        {
            Classifier classifier = (Classifier) eobject;
            Collection<EObject> result = new LinkedList<EObject>();
            result.addAll(classifier.getInheritedMembers());
            return result;
        }
        return null;
    }

    public EClass getEClassToMatch()
    {
        return UMLPackage.Literals.CLASSIFIER;
    }

    public Image getIcon(EObject eobject)
    {
        return null;
    }

    public String getLabel(EObject eobject)
    {
        return "Inherited Members";
    }

}
