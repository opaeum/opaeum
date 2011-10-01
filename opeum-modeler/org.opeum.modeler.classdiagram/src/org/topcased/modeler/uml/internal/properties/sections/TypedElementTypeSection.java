/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * A section for the type property of a selected TypedElement Object.
 * 
 * Creation 5 avr. 2006
 * 
 * @author jlescot
 */
public class TypedElementTypeSection extends AbstractChooserPropertySection
{

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getTypedElement_Type();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Type:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
     */
    protected Object[] getComboFeatureValues()
    {
        return getChoices(getEObject(), UMLPackage.eINSTANCE.getType());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
     */
    protected ILabelProvider getLabelProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getAdvancedLabeProvider()
     */
    protected ILabelProvider getAdvancedLabeProvider()
    {
        // Fix bug [#582] : Use the IItemQualifiedTextProvider to display full path types in Choice box
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory())
        {
            public String getText(Object object)
            {
                IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(
                        object, IItemQualifiedTextProvider.class);

                return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object)
                        : super.getText(object);
            }
        };
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
     */
    protected Object getFeatureValue()
    {
        return ((TypedElement) getEObject()).getType();
    }

}