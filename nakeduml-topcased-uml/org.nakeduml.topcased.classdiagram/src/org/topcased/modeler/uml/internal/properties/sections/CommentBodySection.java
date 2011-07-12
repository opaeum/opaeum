/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;

/**
 * The section for the body property of a Comment Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public class CommentBodySection extends AbstractStringPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Body:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getComment_Body();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getStyle()
     */
    protected int getStyle()
    {
        return SWT.MULTI | SWT.WRAP | SWT.V_SCROLL;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    public boolean shouldUseExtraSpace()
    {
        return true;
    }
}