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
 *   Maxime Nauleau (Atos Origin) - Fix #1557
 ******************************************************************************/

package org.topcased.modeler.uml.actions;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.RetargetAction;
import org.topcased.modeler.actions.ModelerActionBarContributor;

/**
 * Generated Actions <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UMLEditorActionBarContributor extends ModelerActionBarContributor
{
    // TODO defined customized actions
    /**
     * Add the retarget actions
     * 
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
     * @override
     */
    protected void buildActions()
    {
        super.buildActions();

        RetargetAction action = null;
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());

        addRetargetAction(new DeleteRetargetAction());

        addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

        addRetargetAction(new MatchWidthRetargetAction());
        addRetargetAction(new MatchHeightRetargetAction());
    }

}