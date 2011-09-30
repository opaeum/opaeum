/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentLinkFigure extends PolylineConnectionEx
{
    /**
     * @generated
     */
    private static final int LINE_ON = 8;

    /**
     * @generated
     */
    private static final int LINE_OFF = 7;

    /**
     * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CommentLinkFigure()
    {
        super();

        setLineStyle(SWT.LINE_CUSTOM);
        setLineDash(new int[] {LINE_ON, LINE_OFF});
    }

}