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
 *  Thibault Landré (ATOS ORIGIN INTEGRATION) thibault.landre@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.validation.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.ActivityEdge;
import org.topcased.modeler.diagrams.validation.IModelAnchors;

/**
 * The interface for the ActivityEdge in order to fix the anchors in the MissingAnchorScript
 */
public class ActivityEdgeAnchors implements IModelAnchors
{

    /**
     * @see org.topcased.modeler.diagrams.validation.IModelAnchors#getSourceEObject(org.eclipse.emf.ecore.EObject)
     */
    public EObject getSourceEObject(EObject edgeEObject)
    {
        if (edgeEObject instanceof ActivityEdge)
        {
            ActivityEdge controlFlow = (ActivityEdge) edgeEObject;
            return controlFlow.getSource();
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.diagrams.validation.IModelAnchors#getTargetEObject(org.eclipse.emf.ecore.EObject)
     */
    public EObject getTargetEObject(EObject edgeEObject)
    {
        if (edgeEObject instanceof ActivityEdge)
        {
            ActivityEdge controlFlow = (ActivityEdge) edgeEObject;
            return controlFlow.getTarget();
        }
        return null;
    }

}
