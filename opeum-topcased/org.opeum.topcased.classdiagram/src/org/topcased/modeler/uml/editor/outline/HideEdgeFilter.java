/*****************************************************************************
 * 
 * HideEdgeFilter.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/

package org.topcased.modeler.uml.editor.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.topcased.modeler.editor.outline.AdditionalResources;

public class HideEdgeFilter extends ViewerFilter
{

    /**
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
        if(element  instanceof AdditionalResources)
        {
            return true;
        }
        if (viewer instanceof TreeViewer)
        {
            if (element instanceof EObject || element instanceof IWrapperItemProvider
                    || element instanceof FeatureMap.Entry)
            {
                return hasElements((ITreeContentProvider) ((TreeViewer) viewer).getContentProvider(), element);
            }
        }

        return false;
    }

    /**
     * Tests wheter a specified object has one or more diagrams in its child
     * elements or if it is a Diagram itself.
     * 
     * @param a tree content provider to go through the element children
     *            hierarchy.
     * @param element the element to test
     * @return <code>true</code> or <code>false</code>
     */
    private boolean hasElements(ITreeContentProvider cp, Object element)
    {
        if (isNode(element))
        {
            return true;
        }
        return false;
    }
    
    private boolean isNode(Object element)
    {
        if(element instanceof org.eclipse.uml2.uml.Abstraction)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Association)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.CommunicationPath)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.ControlFlow)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Dependency)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Deployment)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Extend)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Extension)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Generalization)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Include)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.InterfaceRealization)
        {
            return false;
        }
        else if(element instanceof org.eclipse.uml2.uml.Manifestation)
        {
            return false;
        }     
        else if(element instanceof org.eclipse.uml2.uml.ObjectFlow)
        {
            return false;
        }    
        else if(element instanceof org.eclipse.uml2.uml.PackageImport)
        {
            return false;
        }     
        else if(element instanceof org.eclipse.uml2.uml.PackageMerge)
        {
            return false;
        }     
        else if(element instanceof org.eclipse.uml2.uml.Transition)
        {
            return false;
        }     
        else if(element instanceof org.eclipse.uml2.uml.Usage)
        {
            return false;
        }     

        return true;
    }
}
