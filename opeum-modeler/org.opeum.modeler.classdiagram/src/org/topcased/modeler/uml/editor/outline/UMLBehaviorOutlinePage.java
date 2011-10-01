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
 *  eperico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/
package org.topcased.modeler.uml.editor.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.editor.DiagramOutlinePageBehavior;

/**
 * Implementation of the specific behavior for the control mode that enables to load required resources
 */
public class UMLBehaviorOutlinePage implements DiagramOutlinePageBehavior
{

    /**
     * {@inheritDoc}
     * @see org.topcased.modeler.editor.DiagramOutlinePageBehavior#resolveEobject(org.eclipse.emf.ecore.EObject)
     */
    public boolean resolveEobject(EObject object)
    {
        // load the required resources without resolveAll
        boolean needToRefreshDiagram = false;
        if (object != null && object.eResource() != null)
        {
            //resolveAllResources(object);
            ResourceSet set = object.eResource().getResourceSet();
            for (int i = 0; i < set.getResources().size(); i++)
            {
                Resource res = set.getResources().get(i);
                if (res.getContents().size() > 0 && res.getContents().get(0) instanceof Diagrams)
                {
                    Diagrams diagram = (Diagrams) res.getContents().get(0);
                    diagram.getModel();
                }
                if (res.getContents().size() > 0 && res.getContents().get(0) instanceof Profile)
                {
                    needToRefreshDiagram = true;
                }
            }
        }
        return needToRefreshDiagram;
    }

    /**
     * other behavior to resolve all resources from the resourceSet of the owner model.
     * 
     * @param object the object
     * @deprecated use resolveEobject method
     * 
     */
    public void resolveAllResources(EObject object)
    {
        ResourceSet set = object.eResource().getResourceSet();
        ResourceSet newSet = new ResourceSetImpl();
        //List < Resource > resources = set.getResources();
        for (Resource r : set.getResources())
        {
            newSet.getResource(r.getURI(), true);
        }
        EcoreUtil.resolveAll(newSet);
        for (Resource r : newSet.getResources())
        {
            if (r.getContents().get(0) instanceof Profile || r.getContents().get(0) instanceof Model)
            {
                set.getResource(r.getURI(), true);
            }
        }
    }
   
}
