/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.alldiagram;

/**
 * A Set of properties that are used for the graphical objects that are not associated with a model object. Each name is
 * used as the typeInfo attribute in the DI file.
 */
public interface AllSimpleObjectConstants
{
    /** The name that identify the <i>CommentLink</i> SimpleObject. */
    String SIMPLE_OBJECT_COMMENTLINK = "CommentLink";
    
    /**
     * The name that identify the <i>ConstraintLink</i> SimpleObject.
     */
    String SIMPLE_OBJECT_CONSTRAINTLINK = "ConstraintLink";
}