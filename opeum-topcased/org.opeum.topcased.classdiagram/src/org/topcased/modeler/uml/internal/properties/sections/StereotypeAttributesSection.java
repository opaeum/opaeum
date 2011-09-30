/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/

package org.topcased.modeler.uml.internal.properties.sections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.edit.providers.ElementItemProvider;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.properties.ModelerPropertySheetPage;
import org.topcased.modeler.uml.dialogs.TextDialogCellEditor;
import org.topcased.modeler.utils.Utils;
import org.topcased.tabbedproperties.providers.TopcasedPropertyDescriptor;
import org.topcased.tabbedproperties.sections.AdvancedPropertySection;

/**
 * TODO comment this class
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class StereotypeAttributesSection extends AdvancedPropertySection
{

    /**
     * TODO comment this class
     * 
     * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
     */
    protected static class StereotypeAdapterFactoryContentProvider extends AdapterFactoryContentProvider
    {

        /**
         * Constructor
         * 
         * @param adapterFactory
         */
        protected StereotypeAdapterFactoryContentProvider(AdapterFactory adapterFactory)
        {
            super(adapterFactory);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getPropertySource(java.lang.Object)
         */
        public IPropertySource getPropertySource(Object object)
        {
            Object copiedObject = object;
            if (copiedObject instanceof EditPart)
            {
                copiedObject = ((EditPart) copiedObject).getModel();
                if (copiedObject instanceof GraphElement)
                {
                    EObject model = Utils.getElement((GraphElement) copiedObject);
                    if (model != null)
                    {
                        copiedObject = model;
                    }
                }
            }
            return super.getPropertySource(copiedObject);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#createPropertySource(java.lang.Object,
         *      org.eclipse.emf.edit.provider.IItemPropertySource)
         */
        protected IPropertySource createPropertySource(Object object, IItemPropertySource itemPropertySource)
        {
            return new StereotypePropertySource(object, itemPropertySource);
        }
    }

    /**
     * TODO comment this class
     * 
     * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
     */
    protected static class StereotypePropertySource extends PropertySource
    {

        private List<IItemPropertyDescriptor> stereotypeApplicationItemPropertyDescriptors = null;

        /**
         * Constructor
         * 
         * @param object
         * @param itemPropertySource
         */
        protected StereotypePropertySource(Object object, IItemPropertySource itemPropertySource)
        {
            super(object, itemPropertySource);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#createPropertyDescriptor(org.eclipse.emf.edit.provider.IItemPropertyDescriptor)
         */
        protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor)
        {
            return new StereotypePropertyDescriptor(object, itemPropertyDescriptor);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#getPropertyDescriptors()
         */
        public IPropertyDescriptor[] getPropertyDescriptors()
        {
            List<IPropertyDescriptor> propertyDescriptors = new ArrayList<IPropertyDescriptor>();

            if (itemPropertySource instanceof ElementItemProvider)
            {
                stereotypeApplicationItemPropertyDescriptors = ((ElementItemProvider) itemPropertySource).getStereotypeApplicationPropertyDescriptors(object);
                if (stereotypeApplicationItemPropertyDescriptors != null)
                {
                    for (Iterator<IItemPropertyDescriptor> i = stereotypeApplicationItemPropertyDescriptors.iterator(); i.hasNext();)
                    {
                        propertyDescriptors.add(createPropertyDescriptor(i.next()));
                    }
                }
            }
            return propertyDescriptors.toArray(new IPropertyDescriptor[propertyDescriptors.size()]);
        }

        /**
         * TODO comment this method
         * 
         * @param propertyId
         * @return IItemPropertyDescriptor
         */
        protected IItemPropertyDescriptor getItemPropertyDescriptor(Object propertyId)
        {
            IItemPropertyDescriptor itemPropertyDescriptor = itemPropertySource.getPropertyDescriptor(object, propertyId);

            return itemPropertyDescriptor == null && itemPropertySource instanceof ElementItemProvider ? ((ElementItemProvider) itemPropertySource).getStereotypeApplicationPropertyDescriptor(object,
                    propertyId) : itemPropertyDescriptor;
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#getPropertyValue(java.lang.Object)
         */
        public Object getPropertyValue(Object propertyId)
        {
            return getItemPropertyDescriptor(propertyId).getPropertyValue(object);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#isPropertySet(java.lang.Object)
         */
        public boolean isPropertySet(Object propertyId)
        {
            return getItemPropertyDescriptor(propertyId).isPropertySet(object);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#resetPropertyValue(java.lang.Object)
         */
        public void resetPropertyValue(Object propertyId)
        {
            getItemPropertyDescriptor(propertyId).resetPropertyValue(object);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
         */
        public void setPropertyValue(Object propertyId, Object value)
        {
            getItemPropertyDescriptor(propertyId).setPropertyValue(object, value);
        }

    }

    /**
     * TODO comment this class
     * 
     * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
     */
    protected static class StereotypePropertyDescriptor extends TopcasedPropertyDescriptor
    {
        @Override
        public CellEditor createPropertyEditor(Composite composite)
        {

            CellEditor result = null;
            final Object genericFeature = itemPropertyDescriptor.getFeature(object);

            // if the feature is a ereference and if it is not many else we apply default behavior
            if (genericFeature instanceof EAttribute)
            {
                // Get the old value
                String oldValue = "";
                Object value = itemPropertyDescriptor.getPropertyValue(genericFeature);
                if (value instanceof PropertyValueWrapper)
                {
                    Object objectValue = ((PropertyValueWrapper) value).getEditableValue(object);
                    if (objectValue instanceof String)
                    {
                        oldValue = (String) objectValue;
                    }
                }
                // The dialog is only for String attribute
                EAttribute eAttribute = (EAttribute) genericFeature;
                if (eAttribute.isChangeable() && eAttribute.getEType() != null && eAttribute.getEType().getInstanceClass() != null
                        && eAttribute.getEType().getInstanceClass().isAssignableFrom(String.class))
                {
                    final ILabelProvider editLabelProvider = getEditLabelProvider();
                    result = new TextDialogCellEditor(composite, editLabelProvider, oldValue, !itemPropertyDescriptor.canSetProperty(object));
                }
            }
            if (result == null)
            {
                result = super.createPropertyEditor(composite);
            }
            return result;
        }

        @Override
        public ILabelProvider getLabelProvider()
        {
            // TODO Auto-generated method stub
            return super.getLabelProvider();
        }

        /**
         * Constructor
         * 
         * @param object
         * @param itemPropertyDescriptor
         */
        protected StereotypePropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
        {
            super(object, itemPropertyDescriptor);
        }

        /**
         * @see org.eclipse.emf.edit.ui.provider.PropertyDescriptor#getEditLabelProvider()
         */
        protected ILabelProvider getEditLabelProvider()
        {
            final ILabelProvider editLabelProvider = super.getEditLabelProvider();

            return new LabelProvider()
            {

                /**
                 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
                 */
                public String getText(Object element)
                {
                    return itemPropertyDescriptor instanceof IItemQualifiedTextProvider ? ((IItemQualifiedTextProvider) itemPropertyDescriptor).getQualifiedText(element)
                            : editLabelProvider.getText(element);
                }

                /**
                 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
                 */
                public Image getImage(Object element)
                {
                    return editLabelProvider.getImage(element);
                }
            };
        }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage)
    {
        super.createControls(parent, tabbedPropertySheetPage);
        ModelerPropertySheetPage modelerPropertySheetPage = (ModelerPropertySheetPage) tabbedPropertySheetPage;
        page.setPropertySourceProvider(new StereotypeAdapterFactoryContentProvider(modelerPropertySheetPage.getAdapterFactory()));
    }
}
