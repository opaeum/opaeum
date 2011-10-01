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
package org.topcased.modeler.uml.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.providers.LabelProviderFactory;

/**
 * Returns a label provider displaying qualified name
 * @author tfaure
 *
 */
public class QualifiedNameLabelProvider implements LabelProviderFactory
{
    private static AdapterFactoryLabelProvider instance = new QualifiedAdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    
    public AdapterFactoryLabelProvider createAdapterFactory()
    {
        return instance;
    }
    
    /**
     * Returns a label provider displaying qualified name
     * @author tfaure
     *
     */
    private static class QualifiedAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider 
    {
        public QualifiedAdapterFactoryLabelProvider(AdapterFactory adapterFactory)
        {
            super(adapterFactory);
        }

        public String getText(Object object)
        {
            IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(
                    object, IItemQualifiedTextProvider.class);

            return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object)
                    : super.getText(object);
        }
    }
}
