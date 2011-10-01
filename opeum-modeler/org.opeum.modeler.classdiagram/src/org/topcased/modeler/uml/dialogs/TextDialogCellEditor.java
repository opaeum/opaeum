/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (Atos Origin) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.dialogs;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * The Class TextDialogCellEditor.
 */
public class TextDialogCellEditor extends ExtendedDialogCellEditor
{

    /** The value to initialize the component. */
    private final String oldValueString;

    /** The text control; initially <code>null</code>. */
    private Text newText;

    /** The is deleteable. */
    private boolean isDeleteable = false;

    /** The is selectable. */
    private boolean isSelectable = false;

    /** The is selection. */
    private boolean isSelection = false;

    /** The isReadOnly */
    private boolean isReadOnly;

    /**
     * Instantiates a new text dialog cell editor.
     * 
     * @param composite the composite
     * @param labelProvider the label provider
     * @param oldValueString the old value string
     */
    public TextDialogCellEditor(Composite composite, ILabelProvider labelProvider, String oldValueString)
    {
        this(composite, labelProvider, oldValueString, false);
    }

    public TextDialogCellEditor(Composite composite, ILabelProvider labelProvider, String oldValueString, boolean isReadOnly)
    {
        super(composite, labelProvider);
        this.oldValueString = oldValueString;
        this.isReadOnly = isReadOnly;
    }

    /**
     * The <code>TextCellEditor</code> implementation of this <code>CellEditor</code> framework method accepts a text
     * string (type <code>String</code>).
     * 
     * @param value a text string (type <code>String</code>)
     */
    protected void doSetValue(Object value)
    {
        Object string = "";
        if (value != null && value instanceof String)
        {
            string = value;
        }
        newText.setText((String) string);
    }

    /**
     * The <code>TextCellEditor</code> implementation of this <code>CellEditor</code> framework method returns the text
     * string.
     * 
     * @return the text string
     */
    protected Object doGetValue()
    {
        return newText.getText();
    }

    /**
     * Open the TextAreaDialogBox and get the result when the dialog is closed.
     * 
     * @param cellEditorWindow the cell editor window
     * 
     * @return the object
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow)
    {
        // Create the dialog
        TextAreaDialog dialog = new TextAreaDialog(cellEditorWindow.getShell(), oldValueString, isReadOnly);
        Object toReturn = null;
        if (dialog.open() == Dialog.OK)
        {
            toReturn = dialog.getResult();
        }
        return toReturn;
    }

    protected int getTextStyle()
    {
        int style = SWT.NONE;
        if (isReadOnly)
        {
            style = style | SWT.READ_ONLY;
        }
        return style;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.DialogCellEditor#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite cell)
    {
        newText = new Text(cell, getTextStyle());
        newText.addKeyListener(new KeyAdapter()
        {
            // hook key pressed - see PR 14201
            public void keyPressed(KeyEvent e)
            {
                keyReleaseOccured(e);

                // as a result of processing the above call, clients may have
                // disposed this cell editor
                if ((getControl() == null) || getControl().isDisposed())
                {
                    return;
                }
                checkSelection(); // see explanation below
                checkDeleteable();
                checkSelectable();
            }
        });
        newText.addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent e)
            {
                if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN)
                {
                    e.doit = false;
                }
            }
        });
        // We really want a selection listener but it is not supported so we
        // use a key listener and a mouse listener to know when selection changes
        // may have occurred
        newText.addMouseListener(new MouseAdapter()
        {
            public void mouseUp(MouseEvent e)
            {
                checkSelection();
                checkDeleteable();
                checkSelectable();
            }
        });
        newText.addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent e)
            {
                TextDialogCellEditor.this.focusLost();
            }
        });
        newText.setFont(cell.getFont());
        newText.setBackground(cell.getBackground());
        newText.setText("");
        return newText;
    }

    /**
     * Check deleteable.
     */
    private void checkDeleteable()
    {
        boolean oldIsDeleteable = isDeleteable;
        isDeleteable = isDeleteEnabled();
        if (oldIsDeleteable != isDeleteable)
        {
            fireEnablementChanged(DELETE);
        }
    }

    /**
     * Checks to see if the "selectable" state (can select) has changed and if so fire an enablement changed
     * notification.
     */
    private void checkSelectable()
    {
        boolean oldIsSelectable = isSelectable;
        isSelectable = isSelectAllEnabled();
        if (oldIsSelectable != isSelectable)
        {
            fireEnablementChanged(SELECT_ALL);
        }
    }

    /**
     * Checks to see if the selection state (selection / no selection) has changed and if so fire an enablement changed
     * notification.
     */
    private void checkSelection()
    {
        boolean oldIsSelection = isSelection;
        isSelection = newText.getSelectionCount() > 0;
        if (oldIsSelection != isSelection)
        {
            fireEnablementChanged(COPY);
            fireEnablementChanged(CUT);
        }
    }

    /**
     * The focus is set to the text field (not on the button).
     */
    @Override
    protected void doSetFocus()
    {
        newText.setFocus();
    }

}
