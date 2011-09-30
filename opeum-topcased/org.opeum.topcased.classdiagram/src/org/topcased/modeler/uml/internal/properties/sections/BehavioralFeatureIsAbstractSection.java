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
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

/**
 * A section for the isAbstract property of a BehavioralFeature Object.
 * 
 * @author Jacques LESCOT
 */
public class BehavioralFeatureIsAbstractSection extends AbstractBooleanPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract();
    }

}