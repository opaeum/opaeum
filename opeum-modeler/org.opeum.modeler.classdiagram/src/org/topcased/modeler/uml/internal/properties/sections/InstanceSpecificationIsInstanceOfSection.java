/*******************************************************************************
 * Copyright (c) 2006, 2008 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * The section for the classifier property of an InstanceSpecification model object.
 * 
 * Creation 12 sept. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class InstanceSpecificationIsInstanceOfSection extends AbstractChooserPropertySection
{

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    @Override
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getInstanceSpecification_Classifier();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText()
    {
        return "Is Instance Of:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
     */
    @Override
    protected Object[] getComboFeatureValues()
    {
        InstanceSpecification instanceSpecification = (InstanceSpecification) getEObject();
        // If the instanceSpecification contains at least 2 slots, then, we need an association
        Class< ? extends Classifier> expectedClass;
        if (instanceSpecification.getSlots().size() >= 2)
        {
            expectedClass = Association.class;
        }
        else
        {
            expectedClass = org.eclipse.uml2.uml.Class.class;
        }

        return UMLTools.getAllObjects(UMLTools.findRootElement(instanceSpecification), expectedClass).toArray();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getAdvancedLabeProvider()
     */
    protected ILabelProvider getAdvancedLabeProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory())
        {
            @Override
            public String getText(Object object)
            {
                IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);

                return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
            }
        };
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
     */
    @Override
    protected ILabelProvider getLabelProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
     */
    @Override
    protected Object getFeatureValue()
    {
        if (((InstanceSpecification) getEObject()).getClassifiers() != null && ((InstanceSpecification) getEObject()).getClassifiers().size() > 0)
        {
            return ((InstanceSpecification) getEObject()).getClassifiers().get(0);
        }
        return null;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#handleComboModified()
     */
    @Override
    protected void handleComboModified()
    {
        if (!isRefreshing() && getFeatureValue() != getCSingleObjectChooser().getSelection())
        {
            EditingDomain editingDomain = ((Modeler) getPart().getAdapter(Modeler.class)).getEditingDomain();
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, getEObject(), getFeature(), getCSingleObjectChooser().getSelection()));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(AddCommand.create(editingDomain, nextObject, getFeature(), getCSingleObjectChooser().getSelection()));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

}