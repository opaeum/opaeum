/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.topcased.modeler.ImageRegistry;

/**
 * Handle image <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class UMLImageRegistry
{

    /** the bundle id of the images */
    public static final String BUNDLE = "org.topcased.modeler.uml.images";

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private UMLImageRegistry()
    {
        // do nothing
    }

    /**
     * Clients should not dispose the Image returned. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param key the key (one of the constants defined in this class)
     * @return the Image associated with the given key
     * @generated
     */
    public static Image getImage(String key)
    {
        return ImageRegistry.getInstance().get(UMLPlugin.getDefault().getBundle(), getImageLocation(key));
    }

    /**
     * Return the image location <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param key the key
     * @return the Image location associated with the given key
     * @generated
     */
    private static String getImageLocation(String key)
    {
        return ResourceBundle.getBundle(BUNDLE).getString(key);
    }

    /**
     * Build an image descriptor for the given key <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param key the key
     * @return the ImageDescriptor associated with the given key
     * @generated
     */
    public static ImageDescriptor getImageDescriptor(String key)
    {
        try
        {
            return ImageRegistry.getInstance().getDescriptor(UMLPlugin.getDefault().getBundle(), getImageLocation(key));
        }
        catch (MissingResourceException mre)
        {
            return null;
        }
    }

}
