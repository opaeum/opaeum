/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 * Jacques Lescot (Anyware Technologies) - initial API and implementation
 * Ludi Akue (ATOS ORIGIN INTEGRATION) ludi.akue@atosorigin.com - OCL Expression management
 * Vincent Hemery [(Atos Origin)] [vincent.hemery@atosorigin.com]
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.composites;

import java.util.ArrayList;
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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.editor.properties.TextChangeHelper;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * Creation 10 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class ConstraintComposite extends Composite
{
    /** the constant language, here OCL. */
    public static final String LANGUAGE = "ocl";

    /** The selected constraint. */
    private Constraint modelObject;

    /** The opaque expression. */
    private OpaqueExpression opaqueExpr = null;

    /** The text of the constraint. */
    private Text constraintTxt;

    /** The dialog to select the constraint specification. */
    private CSingleObjectChooser specChooser;

    /** A helper to listen for events that indicate that a text field has been changed. */
    private TextChangeHelper listener;

    /** The shortcut button to create an ocl expression. */
    private Button oclExprButton;

    /** The widget factory */
    private TabbedPropertySheetWidgetFactory widgetFactory;

    /** The current editing domain */
    private EditingDomain currentEditingDomain;

    /** The adapter for listening model notifications */
    private Adapter notificationAdapter = null;

    /**
     * Constructor
     * 
     * @param widgetFactory
     * @param parent The parent Composite
     * @param modelObj The model object
     * @param style the style of the composite
     * @param editingDomain
     */
    public ConstraintComposite(TabbedPropertySheetWidgetFactory factory, Composite parent, final Constraint modelObj, int style, final EditingDomain editingDomain)
    {
        super(parent, style);

        modelObject = modelObj;
        widgetFactory = factory;
        currentEditingDomain = editingDomain;
        if (modelObject.getSpecification() != null && modelObject.getSpecification() instanceof OpaqueExpression)
        {
            opaqueExpr = (OpaqueExpression) modelObject.getSpecification();
        }

        // create contents of the composite
        setLayout(new GridLayout(4, false));
        setLayoutData(new GridData(GridData.FILL_BOTH));

        widgetFactory.createLabel(this, "Name:");
        constraintTxt = widgetFactory.createText(this, modelObject.getName(), SWT.FLAT | SWT.BORDER);
        constraintTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        // Add a listener to the constraint text
        listener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, modelObj, UMLPackage.eINSTANCE.getNamedElement_Name(), constraintTxt.getText()));
            }
        };
        listener.startListeningTo(constraintTxt);
        listener.startListeningForEnter(constraintTxt);

        widgetFactory.createLabel(this, "Specification:");
        specChooser = new CSingleObjectChooser(this, widgetFactory, SWT.NONE);
        specChooser.setLabelProvider(new TabbedPropertiesLabelProvider(new UMLItemProviderAdapterFactory()));
        specChooser.setChoices(getComboFeatureValues());
        specChooser.setSelection(modelObject.getSpecification());
        specChooser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        // Add a listener to the specification chooser
        specChooser.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e)
            {
                if (modelObject.getSpecification() != specChooser.getSelection())
                {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, modelObj, UMLPackage.eINSTANCE.getConstraint_Specification(), specChooser.getSelection()));
                }
            }
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
        
        // the Constraint is not the property view's selected element. We must listen it through an adapter
        modelObj.eAdapters().add(getNotificationAdapter());
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
                    // update appropriate widget if not disposed
                    if (UMLPackage.eINSTANCE.getNamedElement_Name().equals(notification.getFeature()))
                    {
                        if (!constraintTxt.isDisposed())
                        {
                            constraintTxt.setText(notification.getNewStringValue());
                        }
                    }
                    else if (UMLPackage.eINSTANCE.getConstraint_Specification().equals(notification.getFeature()))
                    {
                        if (!specChooser.isDisposed())
                        {
                            specChooser.setSelection(notification.getNewValue());
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
        modelObject.eAdapters().remove(getNotificationAdapter());
        notificationAdapter = null;
        super.dispose();
    }

    /**
     * Creates an input dialog for ocl expression body
     */
    private void createOclDialog()
    {
        // retrieve the initial value if it exists
        String initialValue = getOpaqueExpressionBody(opaqueExpr, LANGUAGE);

        OclExpressionDialog oclDialog = new OclExpressionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "OCL Expression", "Please, fill the OCL Expression body",
                initialValue, null, getContext(modelObject));

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
            currentEditingDomain.getCommandStack().execute(command);
            //specChooser.setSelection(modelObject.getSpecification());
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

        if (modelObject.getSpecification() == null)
        {
            // no specification constraint. Add one
            opaqueExpr = UMLFactory.eINSTANCE.createOpaqueExpression();
            opaqueExpr.getLanguages().add(LANGUAGE);
            opaqueExpr.getBodies().add(newOclBody);
            globalCommand.append(SetCommand.create(currentEditingDomain, modelObject, getFeature(modelObject, UMLPackage.CONSTRAINT__SPECIFICATION), opaqueExpr));
        }
        else if ((modelObject.getSpecification() != null) && (modelObject.getSpecification() instanceof OpaqueExpression))
        {
            // Update the specification constraint which is an Opaque Expression
            opaqueExpr = (OpaqueExpression) modelObject.getSpecification();
            EList<String> bodies = opaqueExpr.getBodies();
            Boolean exist = false;
            int index = 0;

            for (String c : opaqueExpr.getLanguages())
            {
                if ((LANGUAGE.trim()).equalsIgnoreCase(c.trim()))
                {
                    exist = true;
                    break;
                }
                index++;
            }

            if (!exist)
            {
                // no OCL rule. Insert it at beginning (in case there is a conflict with indexes)
                globalCommand.append(AddCommand.create(currentEditingDomain, opaqueExpr, getFeature(opaqueExpr, UMLPackage.OPAQUE_EXPRESSION__LANGUAGE), LANGUAGE, 0));
                globalCommand.append(AddCommand.create(currentEditingDomain, opaqueExpr, getFeature(opaqueExpr, UMLPackage.OPAQUE_EXPRESSION__BODY), newOclBody, 0));
            }
            else if (index >= bodies.size())
            {
                // there is an OCL language entry but no corresponding body
                int bodiesNumber = bodies.size();
                while (bodiesNumber < index)
                {
                    // insert an empty body to reach the OCL language index
                    globalCommand.append(AddCommand.create(currentEditingDomain, opaqueExpr, opaqueExpr, ""));
                    bodiesNumber++;
                }
                globalCommand.append(AddCommand.create(currentEditingDomain, opaqueExpr, opaqueExpr, newOclBody));
            }
            else if (!bodies.get(index).equals(newOclBody))
            {
                // there is an OCL entry to replace
                globalCommand.append(RemoveCommand.create(currentEditingDomain, opaqueExpr, getFeature(opaqueExpr, UMLPackage.OPAQUE_EXPRESSION__BODY), bodies.get(index)));
                globalCommand.append(AddCommand.create(currentEditingDomain, opaqueExpr, getFeature(opaqueExpr, UMLPackage.OPAQUE_EXPRESSION__BODY), newOclBody, index));
            }
            // else, the body must not be changed.
        }
        else if ((modelObject.getSpecification() != null) && !(modelObject.getSpecification() instanceof OpaqueExpression))
        {
            // Replace the specification constraint by an Opaque Expression
            opaqueExpr = UMLFactory.eINSTANCE.createOpaqueExpression();
            opaqueExpr.getLanguages().add(LANGUAGE);
            opaqueExpr.getBodies().add(newOclBody);
            globalCommand.append(SetCommand.create(currentEditingDomain, modelObject, getFeature(modelObject, UMLPackage.CONSTRAINT__SPECIFICATION), opaqueExpr));
        }
        globalCommand.append(SetCommand.create(currentEditingDomain, opaqueExpr, getFeature(opaqueExpr, UMLPackage.OPAQUE_EXPRESSION__NAME), newOclBody));
        return globalCommand;
    }

    /**
     * retrieves an opaque expression body corresponding to a given language.
     * 
     * @param opaqueExpression the opaque expression
     * @param language the corresponding language
     * @return
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
    private BehavioredClassifier getContext(Constraint modelObject2)
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

    /**
     * Returns the feature corresponding to feature id.
     * 
     * @param e the e
     * @param id the id
     * @return the feature
     */
    private EStructuralFeature getFeature(EObject e, int id)
    {
        return e.eClass().getEStructuralFeature(id);
    }

    /**
     * Search for all reachable ValueSpecifications in the model
     * 
     * @return An array of ValueSpecifications
     */
    private Object[] getComboFeatureValues()
    {
        List<Object> choices = new ArrayList<Object>();
        choices.add("");
        choices.addAll(TypeCacheAdapter.getExistingTypeCacheAdapter(modelObject).getReachableObjectsOfType(modelObject, UMLPackage.eINSTANCE.getValueSpecification()));

        return choices.toArray();
    }
}
