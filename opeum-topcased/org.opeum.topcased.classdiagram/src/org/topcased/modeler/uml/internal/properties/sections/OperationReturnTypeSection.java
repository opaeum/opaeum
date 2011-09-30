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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.editor.Modeler;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * A section for the type property of a selected TypedElement Object. (the returnType of an Operation here)
 * 
 * Creation 5 avr. 2006
 * 
 * @author jlescot
 */
public class OperationReturnTypeSection extends AbstractTabbedPropertySection
{
    /**
     * A boolean that store if refreshing is happening and no model modifications should be performed
     */
    private boolean isRefreshing = false;

    /**
     * The check button to indicate whether the Operation has a return Parameter
     */
    private Button checkButton;

    /**
     * The combo box control for the section.
     */
    private CSingleObjectChooser cSingleObjectChooser;

    // /**
    // * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
    // * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
    // */
    // public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    // {
    // super.createControls(parent, aTabbedPropertySheetPage);
    // Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    // FormData data;
    //
    // checkButton = getWidgetFactory().createButton(composite, getLabelText(), SWT.CHECK);
    //
    // cSingleObjectChooser = new CSingleObjectChooser(composite, getWidgetFactory(), SWT.NONE);
    // cSingleObjectChooser.setLabelProvider(getLabelProvider());
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(cSingleObjectChooser, -ITabbedPropertyConstants.HSPACE);
    // data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
    // checkButton.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(checkButton, ITabbedPropertyConstants.HSPACE);
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(checkButton, 0, SWT.CENTER);
    // cSingleObjectChooser.setLayoutData(data);
    //
    // hookListeners();
    // }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        checkButton = getWidgetFactory().createButton(composite, getLabelText(), SWT.CHECK);

        cSingleObjectChooser = new CSingleObjectChooser(composite, getWidgetFactory(), SWT.NONE);
        cSingleObjectChooser.setLabelProvider(getLabelProvider());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(cSingleObjectChooser, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        checkButton.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(checkButton, ITabbedPropertyConstants.HSPACE);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(checkButton, 0, SWT.CENTER);
        cSingleObjectChooser.setLayoutData(data);
    }

    /**
     * Adds the listeners on the widgets
     */
    protected void hookListeners()
    {
        checkButton.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e)
            {
                handleCheckButtonModified();
            }
        });

        cSingleObjectChooser.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e)
            {
                handleComboModified();
            }
        });
    }

    /**
     * Handle the combo modified event.
     */
    protected void handleComboModified()
    {
        if (!isRefreshing && getFeatureValue() != cSingleObjectChooser.getSelection())
        {
            EditingDomain editingDomain = ((Modeler) getPart().getAdapter(Modeler.class)).getEditingDomain();
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, ((Operation) getEObject()).getReturnResult(), getFeature(), cSingleObjectChooser.getSelection()));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, ((Operation) nextObject).getReturnResult(), getFeature(), cSingleObjectChooser.getSelection()));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Handle the checkbutton modified event.
     */
    protected void handleCheckButtonModified()
    {
        EditingDomain editingDomain = ((Modeler) getPart().getAdapter(Modeler.class)).getEditingDomain();

        if (getEObjectList().size() == 1)
        {
            // Perform update for return type data
            if (checkButton.getSelection())
            {
                // 
                // Create the ReturnResult Parameter with its name and type
                ((Operation) getEObject()).createReturnResult("return", (Type) cSingleObjectChooser.getSelection());
            }
            else
            {
                // Apply the property change to single selected object
                editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, ((Operation) getEObject()).getReturnResult()));
            }
        }
        else
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            /* apply the property change to all selected elements */
            for (EObject eObject : getEObjectList())
            {
                Operation operation = (Operation) eObject;
                // Perform update for return type data
                if (checkButton.getSelection())
                {
                    // 
                    // Create the ReturnResult Parameter with its name and type
                    operation.createReturnResult("return", (Type) cSingleObjectChooser.getSelection());
                }
                else
                {
                    // Apply the property change to single selected object
                    editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, operation.getReturnResult()));
                }
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }
        cSingleObjectChooser.setEnabled(checkButton.getSelection());
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    public void refresh()
    {
        super.refresh();
        isRefreshing = true;
        checkButton.setSelection(((Operation) getEObject()).getReturnResult() != null);
        cSingleObjectChooser.setEnabled(checkButton.getSelection());
        cSingleObjectChooser.setChoices(getComboFeatureValues());
        cSingleObjectChooser.setSelection(getFeatureValue());
        isRefreshing = false;
    }

    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (checkButton != null)
        {
            checkButton.setEnabled(enabled);
        }
        if (cSingleObjectChooser != null)
        {
            cSingleObjectChooser.setEnabled(enabled);
        }
            
    }

    /**
     * Get the feature associated with the Section
     * 
     * @return EStructuralFeature
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getTypedElement_Type();
    }

    /**
     * Get the List of Object to associate with the CCombo
     * 
     * @return Object[]
     */
    protected Object[] getComboFeatureValues()
    {
        return getChoices(getEObject(), UMLPackage.eINSTANCE.getType());
    }

    /**
     * Get the LabelProvider
     * 
     * @return IBaseLabelProvider
     */
    protected ILabelProvider getLabelProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    }

    /**
     * Returns an array of all reachable objects of a given type from the current selection.
     * 
     * @param object current EObject selection
     * @param type Reachable object which have this type
     * @return An array of objects of the given type
     */
    protected Object[] getChoices(EObject object, EClassifier type)
    {
        List<Object> choices = new ArrayList<Object>();
        choices.add("");        
        ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
        choices.addAll(typeCacheAdapter.getReachableObjectsOfType(object, type));     
        return choices.toArray();
    }

    /**
     * Get the current Feature Value
     * 
     * @return Type
     */
    protected Object getFeatureValue()
    {
        return ((Operation) getEObject()).getType();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Return Type:";
    }

}