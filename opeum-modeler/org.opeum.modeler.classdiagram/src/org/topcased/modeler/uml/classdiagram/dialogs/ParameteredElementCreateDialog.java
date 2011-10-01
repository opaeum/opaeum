/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 * 
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: eperico (atos origin) emilien.perico@atosorigin.com - Initial
 * API and implementation
 * 
 *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.dialogs;

import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.facilities.dialogs.ITopcasedDialogConstants;
import org.topcased.modeler.uml.UMLTools;

/**
 * Class to create or select the parameteredElement of a TemplateParameter
 * 
 * @author eperico
 */
public class ParameteredElementCreateDialog extends Dialog implements ITopcasedDialogConstants
{

    /** The TemplateParameter that will contain the parameterableElement */
    private TemplateParameter templateParameter;

    /** The TemplateSignature that contains the template parameter */
    private TemplateSignature templateSignature;

    /** The parameterableElement to affect */
    private ParameterableElement parameterableElement;

    /** Name of the new object */
    private String parameterableElementName;

    /**
     * Indicates if a new Type must be created or if an existing Type must be
     * used
     */
    private boolean createType;

    // ====== SWT Objects ======

    private Button existingTypeBt;

    private Button newTypeBt;
    
    private Button noTypeBt;

    private Combo typeCb;

    private ComboViewer typeCbViewer;

    private Text typeFd;

    /**
     * The Constructor.
     * 
     * @param parentShell the parent shell
     * @param childObject the child object
     * @param parentObject the parent object
     */
    public ParameteredElementCreateDialog(Element childObject, Element parentObject, Shell parentShell)
    {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        setBlockOnOpen(true);
        this.templateParameter = (TemplateParameter) childObject;
        this.templateSignature = (TemplateSignature) parentObject;
    }

    /**
     * Configure shell.
     * 
     * @param newShell the new shell
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell)
    {
        newShell.setText("ParameteredElement");
        newShell.setMinimumSize(MIN_DIALOG_WIDTH, MIN_DIALOG_HEIGHT);

        super.configureShell(newShell);
    }

    /**
     * Creates the dialog area.
     * 
     * @param parent the parent
     * 
     * @return the control
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        Composite dialogComposite = (Composite) super.createDialogArea(parent);
        createDialogComposite(dialogComposite);
        createMainGroup(dialogComposite);
        hookListeners();
        loadData();
        return dialogComposite;
    }

    /**
     * Creates the dialog Contents.
     * 
     * @param composite the composite
     */
    private void createDialogComposite(Composite composite)
    {
        GridLayout dialogLayout = new GridLayout();
        GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
        composite.setLayout(dialogLayout);
        composite.setLayoutData(dialogLayoutData);
    }

    /**
     * Creates the SWT Objects of the Dialog
     * 
     * @param parent the parent Composite
     */
    protected void createMainGroup(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);

        // Add layout on composite
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        existingTypeBt = new Button(composite, SWT.RADIO);
        existingTypeBt.setText("Choose an existing classifier exposed by this template parameter");
        
        typeCb = new Combo(composite, SWT.READ_ONLY);
        typeCb.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        typeCbViewer = new ComboViewer(typeCb);

        Label separationLbl = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separationLbl.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

        newTypeBt = new Button(composite, SWT.RADIO);
        newTypeBt.setText("Create a new class exposed by this template parameter");

        typeFd = new Text(composite, SWT.BORDER);
        typeFd.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

        Label separationLbl2 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separationLbl2.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        noTypeBt = new Button(composite, SWT.RADIO);
        noTypeBt.setText("No type");
    }
    
    /**
     * Initializes widgets value
     */
    private void loadData()
    {
        typeCbViewer.setContentProvider(new TypeContentProvider());
        typeCbViewer.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));
        // Retrieve all the available Classifier in the current model
        List < Classifier > existingClassifiers = UMLTools.getAllObjects(templateSignature.getModel(), Classifier.class);
        if (existingClassifiers.isEmpty())
        {
            typeCb.setEnabled(false);
            existingTypeBt.setSelection(false);
            newTypeBt.setSelection(true);
            typeFd.setEnabled(true);
        }
        else
        {
            typeCbViewer.add(existingClassifiers.toArray());
            existingTypeBt.setSelection(true);
            newTypeBt.setSelection(false);
            typeFd.setEnabled(false);
        }
        noTypeBt.setSelection(false);
    }

    /**
     * Hooks the listeners
     */
    private void hookListeners()
    {
        existingTypeBt.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                handleOKButton();
            }
        });

        newTypeBt.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                handleOKButton();
            }
        });

        typeCbViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            // This ensures that we handle selections correctly.
            public void selectionChanged(SelectionChangedEvent event)
            {
                if (event.getSelection() instanceof IStructuredSelection)
                {
                    parameterableElement = (ParameterableElement) ((IStructuredSelection) event.getSelection()).getFirstElement();
                }
            }
        });

        typeFd.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                handleOKButton();
            }
        });

        typeCb.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                handleOKButton();
            }
        });
    }

    /**
     * Determines whether the OK button should be enable or disable.
     */
    private void handleOKButton()
    {
        if (newTypeBt.getSelection())
        {
            typeCb.setEnabled(false);
            typeFd.setEnabled(true);
            if (!"".equals(typeFd.getText()))
            {
                getButton(Dialog.OK).setEnabled(true);
            }
            else
            {
                getButton(Dialog.OK).setEnabled(false);
            }
        }

        if (existingTypeBt.getSelection())
        {
            typeCb.setEnabled(true);
            typeFd.setEnabled(false);
            if (!"".equals(typeCb.getText()))
            {
                getButton(Dialog.OK).setEnabled(true);
            }
            else
            {
                getButton(Dialog.OK).setEnabled(false);
            }
        }
        if (noTypeBt.getSelection())
        {
            getButton(Dialog.OK).setEnabled(true);
        }
    }

    /**
     * Ok pressed.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed()
    {
        createType = newTypeBt.getSelection();
        parameterableElementName = typeFd.getText();

        super.okPressed();
    }

    /**
     * Overrides the initial method to allow the OK button to be created with a
     * disabled status.
     * 
     * @param parent the parent
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        super.createButtonsForButtonBar(parent);
        getButton(Dialog.OK).setEnabled(false);
    }

    /**
     * This Content Provider displays contents of activities. For the model,
     * this provider delegates contents computing to the model content provider. <br>
     */
    public class TypeContentProvider implements IStructuredContentProvider
    {
        /**
         * Constructor
         */
        public TypeContentProvider()
        {
            // Do nothing
        }

        /**
         * Gets the elements.
         * 
         * @param inputElement the input element
         * 
         * @return the elements
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement)
        {
            return UMLTools.getAllObjects((Model) inputElement, Type.class).toArray();
        }

        /**
         * Dispose.
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose()
        {
            // Do nothing
        }

        /**
         * Input changed.
         * 
         * @param viewer the viewer
         * @param oldInput the old input
         * @param newInput the new input
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
            // Do nothing
        }
    }

    // =============================== Getters ================================

    /**
     * Gets the parameterable element name.
     * 
     * @return the parameterable element name
     */
    public String getParameterableElementName()
    {
        return parameterableElementName;
    }

    /**
     * Checks if is creates the type.
     * 
     * @return true, if is creates the type
     */
    public boolean isCreateType()
    {
        return createType;
    }

    /**
     * Gets the parameterable element.
     * 
     * @return the parameterable element
     */
    public ParameterableElement getParameterableElement()
    {
        return parameterableElement;
    }

}
