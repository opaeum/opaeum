/***********************************************************************
 * Copyright (c) 2007, 2008 Atos Origin
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Szadel (Atos Origin) - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.classdiagram.util;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.Property;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.annotation.UseElementsVisibility;

/**
 * A classifier helper.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public class ClassifierHelper
{
    private IPreferenceStore preferenceStore;

    private GraphElement graphElement;

    // Use a prefix to use the same constants for all classifiers.
    private String prefix;

    /**
     * Constructor.
     * 
     * @param preferenceStore The preference store.
     * @param classElement The class.
     * @param editPartClass The edit Part class.
     */
    public ClassifierHelper(IPreferenceStore preferenceStore, GraphElement classElement, Class< ? extends EditPart> editPartClass)
    {
        this.preferenceStore = preferenceStore;
        this.graphElement = classElement;
        UseElementsVisibility annot = editPartClass.getAnnotation(UseElementsVisibility.class);
        prefix = annot != null ? annot.preferencePrefix() : "";
    }

    /**
     * Show the property type?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showPropertyType()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_PROPERTY_TYPE);
    }

    /**
     * Show the property default value?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showPropertyDefaultValue()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_PROPERTY_DEFAULT_VALUE);
    }

    /**
     * Show the operations' parameters types?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showOperationParametersType()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_TYPE);
    }

    /**
     * Show the operations' parameters?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showOperationParameters()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETERS);
    }

    /**
     * Show the operations' default value?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showOperationParameterDefaultValue()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE);
    }

    /**
     * Show the operations' return type?
     * 
     * @return True if element shown, false otherwise.
     */
    public boolean showOperationReturnType()
    {
        return showElement(ClassElementsVisibilityConstants.SHOW_OPERATION_RETURN_TYPE);
    }

    /**
     * Check if an element has to be visible or not.
     * 
     * @param property The property.
     * @return True if it has to be visible, false otherwise.
     * @see ClassElementsVisibilityConstants
     */
    private boolean showElement(String property)
    {
        Property prop = DIUtils.getProperty(graphElement, property);
        if (prop == null)
        {
            // Get the preference
            String prefixedProp = prefix + property;
            return preferenceStore.getBoolean(prefixedProp);
        }
        else
        {
            return "true".equals(prop.getValue());
        }
    }
}
