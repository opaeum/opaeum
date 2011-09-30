/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.dialogs;

import java.util.Arrays;

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.modeler.utils.LabelHelper;

/**
 * Updating operation parameters
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 */
public class ExtendEditDialog extends Dialog
{
    /** Current edited operation */
    private Extend extend;

    // SWT Objects
    private Composite dialogComposite;

    private Combo extensionPoints;

    private ExtensionPoint extensionPoint;

    private UseCase useCaseTarget;

    private Text conditionText;

    private String condition;

    private final EditDomain domain;

    /**
     * Create the dialog for a given extend link
     * 
     * @param ext the extend to edit
     * @param target the target use case
     * @param editDomain the edit domain
     * @param parentShell the parent shell
     */
    public ExtendEditDialog(Extend ext, UseCase target, EditDomain editDomain, Shell parentShell)
    {
        super(parentShell);
        this.domain = editDomain;
        setBlockOnOpen(true);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        extend = ext;
        useCaseTarget = target;
    }

    /**
     * Set the title of the shell
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell)
    {
        newShell.setText("Extend");
        super.configureShell(newShell);
    }

    /**
     * Creates the group
     * 
     * @param parent the parent Composite
     */
    protected void createOperationGroup(Composite parent)
    {
        parent.setLayout(new GridLayout(2, false));
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));

        new Label(parent, SWT.NONE).setText("Extension Point");

        extensionPoints = new Combo(parent, SWT.NONE);
        String[] extensionPointArray = new String[useCaseTarget.getExtensionPoints().size()];
        int i = 0;
        for (ExtensionPoint point : useCaseTarget.getExtensionPoints())
        {
            extensionPointArray[i++] = point.getName();
        }
        extensionPoints.setItems(extensionPointArray);
        if (extend.getExtensionLocations().size() != 0)
        {
            extensionPoints.select(Arrays.asList(extensionPointArray).indexOf(extend.getExtensionLocations().get(0).getName()));
        }
        extensionPoints.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(parent, SWT.NONE).setText("Condition");
        conditionText = new Text(parent, SWT.BORDER);
        conditionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        if (extend.getCondition() != null && extend.getCondition().getSpecification().stringValue() != null)
        {
            conditionText.setText(extend.getCondition().getSpecification().stringValue());
        }

    }

    /**
     * Create the Dialog area
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        // Dialog
        dialogComposite = (Composite) super.createDialogArea(parent);
        createOperationGroup(dialogComposite);
        return dialogComposite;
    }

    /**
     * Save the values before the widgets are disposed
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed()
    {
        // Prepare general data
        int selectedIndex = extensionPoints.getSelectionIndex();
        if (selectedIndex == -1)
        {
            extensionPoint = useCaseTarget.createExtensionPoint(extensionPoints.getText());
            if (extensionPoint.getName().length() == 0)
            {
                LabelHelper.INSTANCE.initName(domain, useCaseTarget, extensionPoint);
            }
        }
        else
        {
            extensionPoint = useCaseTarget.getExtensionPoints().get(selectedIndex);
        }

        condition = conditionText.getText();
        super.okPressed();
    }

    /**
     * Return the new extension point
     * 
     * @return the new extension point
     */
    public ExtensionPoint getExtensionPoint()
    {
        return extensionPoint;
    }

    /**
     * Return the new condition
     * 
     * @return the new condition
     */
    public String getCondition()
    {
        return condition;
    }

}
