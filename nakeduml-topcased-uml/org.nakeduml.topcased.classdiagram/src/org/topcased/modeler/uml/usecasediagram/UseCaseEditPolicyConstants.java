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
 * A collection of Roles. Each identifier is used to key the EditPolicy. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public interface UseCaseEditPolicyConstants
{

    /** The key used to install an <i>Package</i> EditPolicy. */
    String PACKAGE_EDITPOLICY = "Package EditPolicy";

    /** The key used to install an <i>Classifier</i> EditPolicy. */
    String CLASSIFIER_EDITPOLICY = "Classifier EditPolicy";

    /** The key used to install an <i>Actor</i> EditPolicy. */
    String ACTOR_EDITPOLICY = "Actor EditPolicy";

    /** The key used to install an <i>Association</i> EditPolicy. */
    String ASSOCIATION_EDITPOLICY = "Association EditPolicy";

    /** The key used to install an <i>Generalization</i> EditPolicy. */
    String GENERALIZATION_EDITPOLICY = "Generalization EditPolicy";

    /** The key used to install an <i>Extend</i> EditPolicy. */
    String EXTEND_EDITPOLICY = "Extend EditPolicy";

    /** The key used to install an <i>Include</i> EditPolicy. */
    String INCLUDE_EDITPOLICY = "Include EditPolicy";

    /** The key used to install an <i>UseCase</i> EditPolicy. */
    String USECASE_EDITPOLICY = "UseCase EditPolicy";

    /** The key used to install an <i>Comment</i> EditPolicy. */
    String COMMENT_EDITPOLICY = "Comment EditPolicy";

    /** The key used to install an <i>CommentLink</i> EditPolicy. */
    String COMMENTLINK_EDITPOLICY = "CommentLink EditPolicy";
    
    //Fix #792
    /** The key used to install an <i>BoundaryBox</i> EditPolicy. */
    String BOUNDARYBOX_EDITPOLICY = "BoundaryBox EditPolicy";
    //EndFix #792
    
    /** The key used to install an <i>ConstraintLink</i> EditPolicy. */
    String CONSTRAINTLINK_EDITPOLICY = "ConstraintLink EditPolicy";

}