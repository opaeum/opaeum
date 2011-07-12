/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: - Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 * - Thibaut Landré (Atos Origin) - add cSingleObjectChooser for parameter type - Feature Request #1377
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.facilities.widgets.ComboViewer;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.properties.TextChangeHelper;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * This Composite contains the necessary widgets for editing a Parameter in the UML editor
 * 
 * Creation 22 mai 06
 * 
 * @author jlescot
 */
public class ParameterComposite extends Composite
{
    /** The Parameter that is currently edited */
    private Parameter parameter;

    private MixedEditDomain mixedEditDomain;

    /** The widgetFactory to use to create the widgets */
    private TabbedPropertySheetWidgetFactory widgetFactory;

    /** The widgets */
    private Text parameterNameTxt;

    private CSingleObjectChooser parameterType;

    private CCombo parameterVisibilityCb;

    private ComboViewer parameterVisibilityCbViewer;

    private CCombo parameterDirectionCb;

    private ComboViewer parameterDirectionCbViewer;

    private CCombo parameterEffectCb;

    private ComboViewer parameterEffectCbViewer;

    /**
     * The constructor
     * 
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param style the style of widget to construct
     * @param widgetFactory the widgetFactory to use to create the widgets
     */
    ParameterComposite(Composite parent, int style, TabbedPropertySheetWidgetFactory widgetFactory)
    {
        super(parent, style);

        this.widgetFactory = widgetFactory;
        setLayout(new GridLayout(2, false));
        setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        widgetFactory.adapt(this);
        createContents(this);
        hookListeners();
    }

    /**
     * Set the Parameter and update its properties
     * 
     * @param parameter the Parameter model object
     */
    public void setParameter(Parameter parameter)
    {
        this.parameter = parameter;

        loadData();
    }

    /**
     * Set the MixedEditDomain
     * 
     * @param mixedEditDomain the MixedEditDomain
     */
    public void setMixedEditDomain(MixedEditDomain mixedEditDomain)
    {
        this.mixedEditDomain = mixedEditDomain;
    }

    /**
     * Create the Composite composed of a set of widget necessary to edit a Parameter
     * 
     * @param parent the parent Composite
     */
    protected void createContents(Composite parent)
    {
        widgetFactory.createLabel(parent, "Name : ");
        parameterNameTxt = widgetFactory.createText(parent, "", SWT.BORDER);
        parameterNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        widgetFactory.createLabel(parent, "Type : ");
        parameterType = new CSingleObjectChooser(parent, widgetFactory, SWT.NONE);
        parameterType.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));
        parameterType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        widgetFactory.createLabel(parent, "Visibility : ");
        parameterVisibilityCb = widgetFactory.createCCombo(parent, SWT.BORDER);
        parameterVisibilityCb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        parameterVisibilityCbViewer = new ComboViewer(parameterVisibilityCb);
        parameterVisibilityCbViewer.setContentProvider(new ArrayContentProvider());
        parameterVisibilityCbViewer.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));

        widgetFactory.createLabel(parent, "Direction : ");
        parameterDirectionCb = widgetFactory.createCCombo(parent, SWT.BORDER);
        parameterDirectionCb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        parameterDirectionCbViewer = new ComboViewer(parameterDirectionCb);
        parameterDirectionCbViewer.setContentProvider(new ArrayContentProvider());
        parameterDirectionCbViewer.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));

        widgetFactory.createLabel(parent, "Effect : ");
        parameterEffectCb = widgetFactory.createCCombo(parent, SWT.BORDER);
        parameterEffectCb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        parameterEffectCbViewer = new ComboViewer(parameterEffectCb);
        parameterEffectCbViewer.setContentProvider(new ArrayContentProvider());
        parameterEffectCbViewer.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));
    }

    private void hookListeners()
    {
        // Add the Listener for the name of the Parameter
        TextChangeHelper parameterNameListener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                String newText = parameterNameTxt.getText();
                if (parameter != null && !newText.equals(parameter.getName()))
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(
                            SetCommand.create(mixedEditDomain.getEMFEditingDomain(), parameter, UMLPackage.eINSTANCE.getNamedElement_Name(), newText));
                }
            }
        };
        parameterNameListener.startListeningTo(parameterNameTxt);
        parameterNameListener.startListeningForEnter(parameterNameTxt);

        // Add the listener for the type of the Parameter
        parameterType.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
             */
            public void widgetSelected(SelectionEvent event)
            {
                Object selectedElement = parameterType.getSelection();
                if (selectedElement != null && !selectedElement.equals(parameter.getType()))
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(
                            SetCommand.create(mixedEditDomain.getEMFEditingDomain(), parameter, UMLPackage.eINSTANCE.getTypedElement_Type(), selectedElement));
                }
            }
        });

        // Add the listener for the visibility of the Parameter
        parameterVisibilityCbViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            /**
             * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
             */
            public void selectionChanged(SelectionChangedEvent event)
            {
                Object selectedElement = ((StructuredSelection) parameterVisibilityCbViewer.getSelection()).getFirstElement();
                if (!selectedElement.equals(parameter.getVisibility()))
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(
                            SetCommand.create(mixedEditDomain.getEMFEditingDomain(), parameter, UMLPackage.eINSTANCE.getNamedElement_Visibility(), selectedElement));
                }
            }
        });

        // Add the listener for the direction of the Parameter
        parameterDirectionCbViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            /**
             * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
             */
            public void selectionChanged(SelectionChangedEvent event)
            {
                Object selectedElement = ((StructuredSelection) parameterDirectionCbViewer.getSelection()).getFirstElement();
                if (!selectedElement.equals(parameter.getDirection()))
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(
                            SetCommand.create(mixedEditDomain.getEMFEditingDomain(), parameter, UMLPackage.eINSTANCE.getParameter_Direction(), selectedElement));
                }
            }
        });

        // Add the listener for the effect of the Parameter
        parameterEffectCbViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            /**
             * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
             */
            public void selectionChanged(SelectionChangedEvent event)
            {
                Object selectedElement = ((StructuredSelection) parameterEffectCbViewer.getSelection()).getFirstElement();
                if (!selectedElement.equals(parameter.getEffect()))
                {
                    mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(
                            SetCommand.create(mixedEditDomain.getEMFEditingDomain(), parameter, UMLPackage.eINSTANCE.getParameter_Effect(), selectedElement));
                }
            }
        });
    }

    /**
     * Fill fields with current Parameter values
     */
    private void loadData()
    {
        if (parameter != null)
        {
            // Name
            String nameToDisplay = parameter.getName() != null ? parameter.getName() : "";
            parameterNameTxt.setText(nameToDisplay);

            // Types

            parameterType.setChoices(getChoices());
            if (parameter.getType() != null)
            {
                parameterType.setSelection(parameter.getType());
            }

            // Visibility
            parameterVisibilityCbViewer.setInput(VisibilityKind.VALUES);
            if (parameter.getVisibility() != null)
            {
                parameterVisibilityCbViewer.setSelection(new StructuredSelection(parameter.getVisibility()), true);
            }

            // Directions
            parameterDirectionCbViewer.setInput(ParameterDirectionKind.VALUES);
            if (parameter.getDirection() != null)
            {
                parameterDirectionCbViewer.setSelection(new StructuredSelection(parameter.getDirection()), true);
            }

            // Effects
            parameterEffectCbViewer.setInput(ParameterEffectKind.VALUES);
            if (parameter.getEffect() != null)
            {
                parameterEffectCbViewer.setSelection(new StructuredSelection(parameter.getEffect()), true);
            }
        }
        else
        {
            parameterNameTxt.setText("");
            parameterType.setSelection(null);
            parameterVisibilityCbViewer.setInput(null);
            parameterDirectionCbViewer.setInput(null);
            parameterEffectCbViewer.setInput(null);
        }
    }

    /**
     * Get the choices displayed in the parameterType CSingleObjectChooser
     * 
     * @return the choices
     */
    private Object[] getChoices()
    {
        List<Object> choices = new ArrayList<Object>();
        choices.add("");
        choices.addAll(TypeCacheAdapter.getExistingTypeCacheAdapter(parameter.getOperation()).getReachableObjectsOfType(parameter.getOperation(), UMLPackage.eINSTANCE.getType()));
        return choices.toArray();
    }

}