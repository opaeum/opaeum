/*****************************************************************************
 * 
 * OpaqueExpressionComposite - This class provides a composite to easily edit
 *  an OpaqueExpression element's properties.
 * 
 * Copyright (c) 2009 TOPCASED consortium.
 *
 * Contributors:
 * Vincent Hemery [(Atos Origin)] [vincent.hemery@atosorigin.com]
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.composites;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

/**
 * This class provides a composite to easily edit an OpaqueExpression element's properties.
 * 
 * @author vhemery
 */
public class OpaqueExpressionComposite extends Composite
{
    /** The label for Body property. */
    private static final String BODY_FIELD_LABEL = "Body:";

    /** The label for Body property in dialog. */
    private static final String BODY_DIALOG_LABEL = "Body";

    /** The label for Language property. */
    private static final String LANGUAGE_FIELD_LABEL = "Language:";

    /** The label for Language property in dialog. */
    private static final String LANGUAGE_DIALOG_LABEL = "Language";

    /** the constant language for OCL. */
    private static final String OCL_LANGUAGE = "ocl";

    /** The OpaqueExpression edited element. */
    private OpaqueExpression opaqueExpressionAttribute = null;

    /** The text widget displaying the language property. */
    private Text languageText = null;

    /** The text widget displaying the body property. */
    private Text bodyText = null;

    /** The edition domain */
    private EditingDomain domainAttribute = null;

    /** The shortcut button to create an ocl expression. */
    private Button oclExprButton = null;

    /** The adapter for listening model notifications */
    private Adapter notificationAdapter = null;

    /**
     * Constructor.
     * 
     * @param widgetFactory the widget factory
     * @param parent The parent Composite
     * @param opaqueExpr the opaque expression object to edit
     * @param style the style of the composite
     * @param editingDomain the editing domain
     */
    public OpaqueExpressionComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, final OpaqueExpression opaqueExpr, int style, final EditingDomain editingDomain)
    {
        super(parent, style);
        opaqueExpressionAttribute = opaqueExpr;
        domainAttribute = editingDomain;

        // create widgets
        setLayout(new GridLayout(3, false));
        setLayoutData(new GridData(GridData.FILL_BOTH));

        widgetFactory.createLabel(this, BODY_FIELD_LABEL);
        String listBodies = getCleanedText(getBodiesFeature());
        bodyText = widgetFactory.createText(this, listBodies, SWT.BORDER | SWT.READ_ONLY);
        bodyText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        widgetFactory.createButton(this, "...", SWT.NONE).addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                editMultipleStringFeature(getBodiesFeature(), true);
            };
        });

        widgetFactory.createLabel(this, LANGUAGE_FIELD_LABEL);
        String listLanguages = getCleanedText(getLanguagesFeature());
        languageText = widgetFactory.createText(this, listLanguages, SWT.BORDER | SWT.READ_ONLY);
        languageText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        widgetFactory.createButton(this, "...", SWT.NONE).addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                editMultipleStringFeature(getLanguagesFeature(), false);
            };
        });

        oclExprButton = widgetFactory.createButton(this, "create OCL Expression", SWT.NONE);
        // Add a listener to the OCL Expression Button
        oclExprButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                // open a popup for the ocl expression body
                createOclDialog();
            }
        });
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END);
        data.horizontalSpan = 3;
        oclExprButton.setLayoutData(data);

        // the OpaqueExpression is not the property view's selected element. We must listen it through an adapter
        getOpaqueExpression().eAdapters().add(getNotificationAdapter());
    }

    /**
     * Get an adapter that will listen to model notifications and update text widgets.
     * 
     * @return the notification adapter
     */
    private Adapter getNotificationAdapter()
    {
        if (notificationAdapter == null)
        {
            notificationAdapter = new AdapterImpl()
            {

                public void notifyChanged(Notification notification)
                {
                    Object feature = notification.getFeature();
                    if (getBodiesFeature().equals(feature) || getLanguagesFeature().equals(feature))
                    {
                        // update appropriate Text widget if not disposed
                        Text textWidget = getTextWidget((EStructuralFeature) feature);
                        if (textWidget != null && !textWidget.isDisposed())
                        {
                            String cleanedText = getCleanedText((EStructuralFeature) feature);
                            textWidget.setText(cleanedText);
                        }
                    }
                }
            };
        }
        return notificationAdapter;
    }

    /**
     * Remove adapters listening to the model, then dispose the widget.
     */
    public void dispose()
    {
        getOpaqueExpression().eAdapters().remove(getNotificationAdapter());
        notificationAdapter = null;
        super.dispose();
    }

    /**
     * Edit the values of a string property of the OpaqueExpression object.
     * 
     * @param feature the feature which values are edited ; this feature must have several String values
     * @param isMultiline whether the widget for typing a value is multiline
     */
    protected void editMultipleStringFeature(EStructuralFeature feature, boolean isMultiline)
    {
        String label = getLabel(feature);
        FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), getLabelProvider(), getOpaqueExpression(), getBodiesFeature().getEType(),
                (List< ? >) getOpaqueExpression().eGet(feature), label, null, isMultiline, false);
        dialog.open();
        if (dialog.getReturnCode() == Window.OK)
        {
            List< ? > newElements = dialog.getResult();
            getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getOpaqueExpression(), feature, newElements));
        }
    }

    /**
     * Get a string representation of the values for a feature of the OpaqueExpression.
     * 
     * @param feature the feature which the values are returned
     * @return the string representation as it should be displayed
     */
    private String getCleanedText(EStructuralFeature feature)
    {
        String listOfElements = getOpaqueExpression().eGet(feature).toString();
        return listOfElements.substring(1, listOfElements.length() - 1);
    }

    /**
     * Get the label to display for editing a feature's values
     * 
     * @param feature the feature to edit
     * @return the label
     */
    private String getLabel(EStructuralFeature feature)
    {
        if (getBodiesFeature().equals(feature))
        {
            return BODY_DIALOG_LABEL;
        }
        else if (getLanguagesFeature().equals(feature))
        {
            return LANGUAGE_DIALOG_LABEL;
        }
        else
        {
            return "";
        }
    }

    /**
     * Get the Text widget which displays the value for a particular feature of the OpaqueExpression
     * 
     * @param feature the feature to display
     * @return the displayed Text widget
     */
    private Text getTextWidget(EStructuralFeature feature)
    {
        if (getBodiesFeature().equals(feature))
        {
            return bodyText;
        }
        else if (getLanguagesFeature().equals(feature))
        {
            return languageText;
        }
        else
        {
            return null;
        }
    }

    /**
     * Get the edition domain
     * 
     * @return the edition domain
     */
    private EditingDomain getEditingDomain()
    {
        return domainAttribute;
    }

    /**
     * Get the feature for the Body property.
     * 
     * @return the feature
     */
    private EStructuralFeature getBodiesFeature()
    {
        return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
    }

    /**
     * Get the feature for the Language property.
     * 
     * @return the feature
     */
    private EStructuralFeature getLanguagesFeature()
    {
        return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
    }

    /**
     * Get the edited OpaqueExpression object
     * 
     * @return the OpaqueExpression object
     */
    private OpaqueExpression getOpaqueExpression()
    {
        return opaqueExpressionAttribute;
    }

    /**
     * Get the label provider
     * 
     * @return the label provider
     */
    private ILabelProvider getLabelProvider()
    {
        final TabbedPropertiesLabelProvider itemLabelProvider = new TabbedPropertiesLabelProvider(new UMLItemProviderAdapterFactory());
        return new LabelProvider()
        {
            @Override
            public String getText(Object object)
            {
                return itemLabelProvider.getText(object);
            }

            @Override
            public Image getImage(Object object)
            {
                return ExtendedImageRegistry.getInstance().getImage(itemLabelProvider.getImage(object));
            }
        };
    }

    /**
     * Creates an input dialog for ocl expression body
     */
    private void createOclDialog()
    {
        // retrieve the initial value if it exists
        String initialValue = getOpaqueExpressionBody(getOpaqueExpression(), OCL_LANGUAGE);
        OclExpressionDialog oclDialog = new OclExpressionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "OCL Expression", "Please, fill the OCL Expression body",
                initialValue, null, getContext(getOpaqueExpression()));
        int opened = oclDialog.open();

        // check if the body is set
        String body = oclDialog.getValue();
        if (body != null && body.trim().length() == 0)
        {
            body = null;
        }

        if (opened == Window.OK && body != null)
        {
            CompoundCommand command = getChangeOclSpecificationCommand(body);
            getEditingDomain().getCommandStack().execute(command);
        }

    }

    /**
     * Get the command which changes the OCL specification
     * 
     * @param newOclBody the new body for OCL specification
     * @return the complete command
     */
    private CompoundCommand getChangeOclSpecificationCommand(String newOclBody)
    {
        // create the global compound command which will be executed
        CompoundCommand globalCommand = new CompoundCommand();

        EList<String> bodies = getOpaqueExpression().getBodies();
        // check whether an ocl expression exists and record its index
        Boolean exist = false;
        int index = 0;
        for (String c : getOpaqueExpression().getLanguages())
        {
            if ((OCL_LANGUAGE.trim()).equalsIgnoreCase(c.trim()))
            {
                exist = true;
                break;
            }
            index++;
        }

        if (!exist)
        {
            // no OCL rule. Insert it at beginning (in case there is a conflict with indexes)
            globalCommand.append(AddCommand.create(getEditingDomain(), getOpaqueExpression(), getLanguagesFeature(), OCL_LANGUAGE, 0));
            globalCommand.append(AddCommand.create(getEditingDomain(), getOpaqueExpression(), getBodiesFeature(), newOclBody, 0));
        }
        else if (index >= bodies.size())
        {
            // there is an OCL language entry but no corresponding body
            int bodiesNumber = bodies.size();
            while (bodiesNumber < index)
            {
                // insert an empty body to reach the OCL language index
                globalCommand.append(AddCommand.create(getEditingDomain(), getOpaqueExpression(), getBodiesFeature(), ""));
                bodiesNumber++;
            }
            globalCommand.append(AddCommand.create(getEditingDomain(), getOpaqueExpression(), getBodiesFeature(), newOclBody));
        }
        else if (!bodies.get(index).equals(newOclBody))
        {
            // there is an OCL entry to replace
            globalCommand.append(RemoveCommand.create(getEditingDomain(), getOpaqueExpression(), getBodiesFeature(), bodies.get(index)));
            globalCommand.append(AddCommand.create(getEditingDomain(), getOpaqueExpression(), getBodiesFeature(), newOclBody, index));
        }
        // else, the body must not be changed.
        globalCommand.append(SetCommand.create(getEditingDomain(), getOpaqueExpression(), UMLPackage.eINSTANCE.getNamedElement_Name(), newOclBody));
        return globalCommand;
    }

    /**
     * retrieves an opaque expression body corresponding to a given language.
     * 
     * @param opaqueExpression the OpaqueExpression object to explore
     * @param language the corresponding language (case insensitive)
     * @return the corresponding body
     */
    private String getOpaqueExpressionBody(OpaqueExpression opaqueExpression, String language)
    {
        String body = null;
        if (opaqueExpression != null)
        {
            int index = 0;
            for (String c : opaqueExpression.getLanguages())
            {
                if (language.trim().equalsIgnoreCase(c.trim()))
                {
                    if (opaqueExpression.getBodies().size() > index)
                    {
                        body = opaqueExpression.getBodies().get(index);
                    }
                    break;
                }
                index++;
            }
        }
        return body;
    }

    /**
     * retrieves the class context of the ocl expression
     * 
     * @param modelObject2 the ocl constraint
     * @return the class
     */
    private BehavioredClassifier getContext(OpaqueExpression modelObject2)
    {
        BehavioredClassifier result = null;
        EObject behaviorParent = modelObject2;
        while (behaviorParent != null && !(behaviorParent instanceof Behavior))
        {
            behaviorParent = behaviorParent.eContainer();
        }
        if (behaviorParent instanceof Behavior)
        {
            result = ((Behavior) behaviorParent).getContext();
        }
        return result;
    }

}
