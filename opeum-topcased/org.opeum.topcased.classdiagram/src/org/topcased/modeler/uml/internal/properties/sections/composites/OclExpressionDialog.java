/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ludi Akue (ATOS ORIGIN INTEGRATION) ludi.akue@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/

package org.topcased.modeler.uml.internal.properties.sections.composites;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OCLSourceViewer;
import org.topcased.modeler.uml.oclinterpreter.UMLOCLFactory;

public class OclExpressionDialog extends Dialog
{
    /** The Constant MIN_HEIGHT_FOR_OCL_BODY_AREA. */
    public static final int MIN_HEIGHT_FOR_OCL_BODY_AREA = 50;

    /** The title of the dialog. */
    private String title;

    /** The message to display, or <code>null</code> if none. */
    private String message;

    /** The input value; the empty string by default. */
    private String value = "";

    /** The input validator, or <code>null</code> if none. */
    private IInputValidator validator;

    /** Ok button widget. */
    private Button okButton;

    /** The composite. */
    private Composite oclComposite = null;

    /** OCL source viewer. */

    private OCLSourceViewer viewer;

    /** The OCL document. */

    private OCLDocument document;

    /** The factory. */
    private UMLOCLFactory umlOclFactory;

    private final BehavioredClassifier context;

    /**
     * Creates an input dialog with OK and Cancel buttons. Note that the dialog will have no visual representation (no
     * widgets) until it is told to open.
     * <p>
     * Note that the <code>open</code> method blocks for input dialogs.
     * </p>
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
     * @param dialogTitle the dialog title, or <code>null</code> if none
     * @param dialogMessage the dialog message, or <code>null</code> if none
     * @param initialValue the initial input value, or <code>null</code> if none (equivalent to the empty string)
     * @param validator an input validator, or <code>null</code> if none
     * @param behavioredClassifier the context of the OCL expression
     */
    public OclExpressionDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue, IInputValidator validator, BehavioredClassifier behavioredClassifier)
    {
        super(parentShell);
        this.title = dialogTitle;
        message = dialogMessage;
        this.context = behavioredClassifier;
        if (initialValue == null)
        {
            value = "";
        }
        else
        {
            value = initialValue;
        }
        this.validator = validator;

    }

    /**
     * Manage content assist.
     */
    protected void manageContentAssist()
    {
        viewer.enableOperation(SourceViewer.CONTENTASSIST_PROPOSALS, true);
        viewer.enableOperation(SourceViewer.QUICK_ASSIST, true);
        viewer.enableOperation(SourceViewer.CONTENTASSIST_CONTEXT_INFORMATION, true);
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId)
    {
        if (buttonId == IDialogConstants.OK_ID)
        {
            value = getValue();
        }
        else
        {
            value = null;
        }
        super.buttonPressed(buttonId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets .Shell)
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        if (title != null)
        {
            shell.setText(title);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse .swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        // create OK and Cancel buttons by default
        okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

        oclComposite.setFocus();
        if (value != null)
        {
            // document.set(value);
            viewer.getDocument().set(value);
            viewer.refresh();
        }
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent)
    {
        // create composite
        Composite composite = (Composite) super.createDialogArea(parent);
        // create message
        if (message != null)
        {
            Label label = new Label(composite, SWT.WRAP);
            label.setText(message);
            GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
            label.setLayoutData(data);
            label.setFont(parent.getFont());
        }

        oclComposite = new Composite(parent, SWT.NONE);
        oclComposite.setLayout(new GridLayout(1, false));
        GridData layoutDataComposite = new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1);
        layoutDataComposite.minimumHeight = MIN_HEIGHT_FOR_OCL_BODY_AREA;
        layoutDataComposite.grabExcessVerticalSpace = true;
        oclComposite.setLayoutData(layoutDataComposite);

        viewer = new OCLSourceViewer(oclComposite, new ColorManager(), SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
        viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        document = new OCLDocument();
        umlOclFactory = new UMLOCLFactory();
        document.setOCLFactory(umlOclFactory);
        document.setModelingLevel(ModelingLevel.M1);
        viewer.setInput(document);

        umlOclFactory.setContext(context);
        document.setOCLContext(context);

        manageContentAssist();

        return composite;
    }

    /**
     * Returns the ok button.
     * 
     * @return the ok button
     */
    protected Button getOkButton()
    {
        return okButton;
    }

    /**
     * Returns the validator.
     * 
     * @return the validator
     */
    protected IInputValidator getValidator()
    {
        return validator;
    }

    /**
     * Returns the string typed into this input dialog.
     * 
     * @return the input string
     */
    public String getValue()
    {
        return document.get();
    }

    // /**
    // * Validates the input.
    // * <p>
    // * The default implementation of this framework method delegates the request to the supplied input validator
    // object;
    // * if it finds the input invalid, the error message is displayed in the dialog's message line. This hook method is
    // * called whenever the text changes in the input field.
    // * </p>
    // */
    // protected void validateInput()
    // {
    // String errorMessage = null;
    // if (validator != null)
    // {
    // errorMessage = validator.isValid(getValue());
    // }
    //
    //      
    // }

    /**
     * Returns the style bits that should be used for the input text field. Defaults to a single line entry. Subclasses
     * may override.
     * 
     * @return the integer style bits that should be used when creating the input text
     * 
     * @since 3.4
     */
    protected int getInputTextStyle()
    {
        return SWT.SINGLE | SWT.BORDER;
    }

}
