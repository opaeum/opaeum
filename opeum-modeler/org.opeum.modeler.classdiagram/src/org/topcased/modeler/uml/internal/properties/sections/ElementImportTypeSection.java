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
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * A section for the importedElement property of a selected ElementImport Object.
 * 
 * Creation 19 June 2008
 * 
 * @author Jacques Lescot (Anyware Technologies)
 */
public class ElementImportTypeSection extends AbstractChooserPropertySection
{

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getElementImport_ImportedElement();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Metaclass:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
     */
    protected Object[] getComboFeatureValues()
    {
        List<Object> choices = new ArrayList<Object>();
        choices.add("");

        Resource resource = getEObject().eResource().getResourceSet().getResource(
                URI.createURI(UMLResource.UML_METAMODEL_URI), true);
        Iterator<EObject> contents = resource.getAllContents();
        while (contents.hasNext())
        {
            Object object = contents.next();
            if (org.eclipse.uml2.uml.Class.class.isInstance(object)
                    && ((org.eclipse.uml2.uml.Class) object).isMetaclass())
            {
                choices.add(object);
            }
        }
        return choices.toArray();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getAdvancedLabeProvider()
     */
    protected ILabelProvider getAdvancedLabeProvider()
    {
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
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
     */
    protected ILabelProvider getLabelProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
     */
    protected Object getFeatureValue()
    {
        return ((ElementImport) getEObject()).getImportedElement();
    }

}