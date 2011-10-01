/*****************************************************************************
 * 
 * Copyright (c) 2009 Atos Origin.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com
 *
  *****************************************************************************/
package org.topcased.modeler.uml.editor.outline;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;

/**
 * A custom UMLItemProviderAdapterFactory It permits to override UMLItemProvider to add specific behavior. For example
 * to customize the label of an element displayed in the outline.
 */
public class CustomUMLItemProviderAdapterFactory extends UMLItemProviderAdapterFactory
{

    /**
     * Overrides to customize the label of the CallBehaviorAction displayed in the outline.
     */
    @Override
    public Adapter createCallBehaviorActionAdapter()
    {
        if (callBehaviorActionItemProvider == null)
        {
            callBehaviorActionItemProvider = new CustomCallBehaviorActionItemProvider(this);
        }
        return callBehaviorActionItemProvider;
    }
}
