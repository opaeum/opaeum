/*****************************************************************************
 * Copyright (c) 2008; Atos Origin.
 *
 * Contributors:
 *  Frédéric Barraillé; [(Atos Origin)] [frederic.barraille@atosorigin.com]
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram.figures;

/**
 * The ConstraintNodeFigure object figure 
 */
public class ConstraintNodeFigure extends CommentFigure
{
    /**
     * Constructor
     * 
     * @generated
     */
    public ConstraintNodeFigure()
    {
        super();
    }

    /**
     * To set a label's content 
     * @param String, the string for label
     */
    public void setText(String txt)
    {
        if (txt != null)
        {
            super.setText(txt);
        }
        else
        {
            super.setText("{}");
        }
    }
}
