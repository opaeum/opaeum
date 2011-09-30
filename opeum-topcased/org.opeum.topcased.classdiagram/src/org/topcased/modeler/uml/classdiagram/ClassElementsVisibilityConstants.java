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
package org.topcased.modeler.uml.classdiagram;

/**
 * Constants used to show/hide graphical elements of a class.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public interface ClassElementsVisibilityConstants
{
    /** The Prefix to use for class elements' visibility. */
    String CLASS_PREFERENCE_PREFIX = "Class_";

    /** The Prefix to use for interface elements' visibility. */
    String INTERFACE_PREFERENCE_PREFIX = "Interface_";

    /** The Prefix to use for data type elements' visibility. */
    String DATATYPE_PREFERENCE_PREFIX = "DataType_";

    /** The Prefix to use for primitive_type elements' visibility. */
    String PRIMITIVE_TYPE_PREFERENCE_PREFIX = "Primitive_";

    /**
     * Show/Hide the return type of an operation.
     */
    String SHOW_OPERATION_RETURN_TYPE = "showOperationReturnType";

    /**
     * Show/Hide the return type of a property.
     */
    String SHOW_PROPERTY_TYPE = "showPropertyType";

    /**
     * Show/Hide the default value of a property.
     */
    String SHOW_PROPERTY_DEFAULT_VALUE = "showPropertyDefaultValue";

    /**
     * Show/Hide the type of each parameter of an operation.
     */
    String SHOW_OPERATION_PARAMETER_TYPE = "showOperationParameterType";

    /**
     * Show/Hide the default value of each parameter of an operation.
     */
    String SHOW_OPERATION_PARAMETER_DEFAULT_VALUE = "showOperationParameterDefaultValue";

    /**
     * Show/Hide the parameters of an operation.
     */
    String SHOW_OPERATION_PARAMETERS = "showOperationParameters";
}
