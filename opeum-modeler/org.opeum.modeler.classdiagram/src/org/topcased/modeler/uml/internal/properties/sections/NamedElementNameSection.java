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
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;

/**
 * The section for the name property of a NamedElement Object.
 * 
 * Creation 5 avr. 2006
 * 
 * @author jlescot
 */
public class NamedElementNameSection extends AbstractStringPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Name:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getNamedElement_Name();
    }
}