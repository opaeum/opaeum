/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 *   Maxime Nauleau (Atos Origin) - Fix #792
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * A Set of properties that are used for the graphical objects that are not associated with a model object. Each name is
 * used as the typeInfo attribute in the DI file.
 * 
 * Creation : 11 may 2006
 * 
 * @generated
 */
public interface UseCaseSimpleObjectConstants
{
    /** The name that identify the <i>CommentLink</i> SimpleObject. */
    String SIMPLE_OBJECT_COMMENTLINK = "CommentLink";
    
    //Fix #792
    /** The name that identify the <i>BoundaryBox</i> SimpleObject. */
    String SIMPLE_OBJECT_BOUNDARYBOX = "BoundaryBox";
    //EndFix #792
    
    /**
     * The name that identify the <i>ConstraintLink</i> SimpleObject.
     */
    String SIMPLE_OBJECT_CONSTRAINTLINK = "ConstraintLink";
}