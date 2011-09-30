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
package org.topcased.modeler.uml.classdiagram.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that indicates to the property view that the given editpart uses a UML element that supports visibility
 * changes on operation/property content.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseElementsVisibility {
    /**
     * Returns the prefix used to distinguish the preferences elements.
     * 
     * @return The prefix to use.
     */
    String preferencePrefix();
}
