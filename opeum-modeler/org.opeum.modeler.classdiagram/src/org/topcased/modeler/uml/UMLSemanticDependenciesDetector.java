/*******************************************************************************
 * Copyright (c) 2008 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Pierre-Charles David (Obeo) - initial API and implementation
 *******************************************************************************/
package org.topcased.modeler.uml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.topcased.modeler.SemanticDependenciesHandler;

/**
 * Detects semantic dependencies for UML elements which can not be found in a generic way at the EMF level.
 * 
 * @author <a href="mailto:pierre-charles.david@obeo.fr">Pierre-Charles David</a>
 */
public class UMLSemanticDependenciesDetector implements SemanticDependenciesHandler
{
    /**
     * @see org.topcased.modeler.SemanticDependenciesDetector#getSemanticDependencies(org.eclipse.emf.ecore.EObject)
     */
    public Set<EObject> getSemanticDependencies(EObject obj)
    {
        if (!(obj instanceof Element))
        {
            return Collections.emptySet();
        }
        Element element = (Element) obj;
        Set<EObject> result = new HashSet<EObject>();
        result.addAll(element.getStereotypeApplications());
        if (element instanceof Package)
        {
            Package pkg = (Package) element;
            result.addAll(pkg.getAllProfileApplications());
        }
        return result;
    }

    /**
     * @see org.topcased.modeler.SemanticDependenciesHandler#afterExport(org.eclipse.emf.ecore.resource.Resource,
     *      org.eclipse.emf.ecore.resource.Resource)
     */
    public void afterExport(Resource partResource, Resource cacheResource)
    {
        moveModelStereotypesToPart(partResource, cacheResource);
    }

    /**
     * @see org.topcased.modeler.SemanticDependenciesHandler#beforeImport(org.eclipse.emf.ecore.resource.Resource,
     *      org.eclipse.emf.ecore.resource.Resource)
     */
    public void beforeImport(Resource controlledResource, Resource exportedResource)
    {
        removeStereotypesFromControlledModel(controlledResource);
    }

    private void removeStereotypesFromControlledModel(Resource controlledResource)
    {
        TreeIterator<EObject> iter = controlledResource.getAllContents();
        while (iter.hasNext())
        {
            EObject obj = iter.next();
            if (obj instanceof Element)
            {
                Element elt = (Element) obj;
                if (!controlledResource.equals(elt.eResource()))
                {
                    iter.prune();
                    continue;
                }
                List<Stereotype> stereotypes = new ArrayList<Stereotype>(elt.getAppliedStereotypes());
                for (Stereotype st : stereotypes)
                {
                    elt.unapplyStereotype(st);
                }
            }
        }
    }

    private void moveModelStereotypesToPart(Resource partResource, Resource cacheResource)
    {
        Collection<EObject> stereotypes = new ArrayList<EObject>();
        for (EObject rootObject : cacheResource.getContents())
        {
            if (UMLUtil.getStereotype(rootObject) != null)
            {
                Element base = UMLUtil.getBaseElement(rootObject);
                if (base != null && base.eResource().equals(partResource))
                {
                    stereotypes.add(rootObject);
                }
            }
        }
        cacheResource.getContents().removeAll(stereotypes);
        partResource.getContents().addAll(stereotypes);
    }

    /**
     * @see org.topcased.modeler.SemanticDependenciesHandler#afterImport(org.eclipse.emf.ecore.resource.Resource)
     */
    public void afterImport(Resource controlledResource)
    {
        // Do nothng
    }
}
