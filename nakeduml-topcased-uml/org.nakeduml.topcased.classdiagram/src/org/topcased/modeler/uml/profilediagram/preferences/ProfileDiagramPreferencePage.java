/***********************************************************************
 * Copyright (c) 2006, 2008 Anyware Technologies
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.profilediagram.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.topcased.facilities.preferences.AbstractTopcasedPreferencePage;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */
public class ProfileDiagramPreferencePage extends AbstractTopcasedPreferencePage
{
    private BooleanFieldEditor askDefineProfileOnSaving;

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {
        // Do nothing
    }

    /**
     * Creates this preference page contents.
     * 
     * @param parent the parent composite
     * @return the created Control element
     */
    protected Control createContents(Composite parent)
    {
        // Create the container composite
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout containerLayout = new GridLayout();
        containerLayout.marginWidth = 0;
        containerLayout.marginHeight = 0;
        container.setLayout(containerLayout);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        createHeader(container);

        createDeleteActionsGroup(container);

        loadPreferences();

        return container;
    }

    /**
     * Initializes the HMI with preference values.
     */
    private void loadPreferences()
    {
        askDefineProfileOnSaving.load();
    }

    /**
     * Stores the HMI values into the preference store.
     */
    private void storePreferences()
    {
        askDefineProfileOnSaving.store();
    }

    /**
     * Initializes the HMI with default preference values.
     */
    private void loadDefaultPreferences()
    {
        askDefineProfileOnSaving.loadDefault();
    }

    /**
     * Creates this preference page header.
     * 
     * @param parent the parent composite
     */
    private void createHeader(Composite parent)
    {
        new Label(parent, SWT.WRAP).setText("UML Profile diagram preference page");
    }

    /**
     * Creates the group to configure the delete actions preferences.
     * 
     * @param parent the parent composite
     */
    private void createDeleteActionsGroup(Composite parent)
    {
        Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // group.setText("Delete actions");

        Composite fieldsContainer = new Composite(group, SWT.NONE);
        fieldsContainer.setLayout(new GridLayout());
        fieldsContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        askDefineProfileOnSaving = new BooleanFieldEditor(ProfileDiagramPreferenceConstants.ASK_DEFINE_PROFILE_ON_SAVING, "Do not remind the need to execute 'Define Profile' before saving.",
                fieldsContainer);
        askDefineProfileOnSaving.setPreferenceStore(getPreferenceStore());
    }

    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk()
    {
        storePreferences();
        return super.performOk();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults()
    {
        loadDefaultPreferences();
        super.performDefaults();
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }
}
