/*******************************************************************************
 * Copyright (c) 2007 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.ui.navigator;

import org.topcased.ui.navigator.TopcasedModelAdapterFactoryLabelProvider;

/**
 * A LabelProvider used with UML Model files and its content.
 * 
 * Creation 4 janv. 07
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class UMLModelAdapterFactoryLabelProvider extends TopcasedModelAdapterFactoryLabelProvider
{
    /**
     * Constructor
     */
    public UMLModelAdapterFactoryLabelProvider()
    {
        super(UMLModelAdapterFactoryProvider.getAdapterFactory());
    }

}
