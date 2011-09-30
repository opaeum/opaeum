/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (2009) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/

package org.topcased.modeler.uml.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextAreaDialog extends Dialog
{

    /** The text control for the section. */
    private Text text;

    /** Input text */
    private String value;

    private Composite composite;

    private boolean isReadOnly;

    public TextAreaDialog(Shell parentShell, String defaultValue)
    {
        this(parentShell, defaultValue, false);
    }

    public TextAreaDialog(Shell parentShell, String defaultValue, boolean isReadOnly)
    {
        super(parentShell);
        this.value = defaultValue;
        this.isReadOnly = isReadOnly;
    }

    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText("Stereotype Attribute Value");
    }

    protected int getStyle()
    {
        int style = SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP;
        if (isReadOnly)
        {
            style = style | SWT.READ_ONLY;
        }
        return style;
    }

    protected Control createDialogArea(Composite parent)
    {
        // create composite
        composite = (Composite) super.createDialogArea(parent);

        // Create text area
        GridData layoutDataComposite = new GridData(GridData.FILL_BOTH);
        layoutDataComposite.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
        layoutDataComposite.grabExcessVerticalSpace = true;
        layoutDataComposite.grabExcessHorizontalSpace = true;
        layoutDataComposite.minimumHeight = 100;
        text = new Text(composite, getStyle());
        // text.addKeyListener(new KeyAdapter()
        // {
        // @Override
        // public void keyReleased(KeyEvent e)
        // {
        // if (e.stateMask == SWT.CTRL && e.character == SWT.CR)
        // {
        // buttonPressed(IDialogConstants.OK_ID);
        // }
        // }
        // });
        text.setLayoutData(layoutDataComposite);
        return composite;
    }

    protected void createButtonsForButtonBar(Composite parent)
    {
        // create OK and Cancel buttons by default
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

        text.setFocus();
        if (value != null && value.length() > 0)
        {
            text.setText(value);
        }
    }

    /**
     * 
     */
    protected void buttonPressed(int buttonId)
    {
        if (buttonId == IDialogConstants.OK_ID)
        {
            value = text.getText();
        }
        else
        {
            value = null;
        }
        super.buttonPressed(buttonId);
    }

    /**
     * Get the input text
     * 
     * @return the input text
     */
    public String getResult()
    {
        return value;
    }
}
