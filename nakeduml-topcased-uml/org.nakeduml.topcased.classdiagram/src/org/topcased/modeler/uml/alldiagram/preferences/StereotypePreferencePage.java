/***********************************************************************
 * Copyright (c) 2007, 2008 Atos Origin
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Szadel (Atos Origin) - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.alldiagram.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.topcased.facilities.preferences.AbstractTopcasedPreferencePage;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * The preference page for the stereotypes.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public class StereotypePreferencePage extends AbstractTopcasedPreferencePage
{
    /**
     * The different kind of display.
     */
    private static final String[][] DISPLAY_KIND_VALUES = { {"As a Chevron", "chevron"}, {"As an Icon (if defined - otherwise, displayed as a Chevron)", "icon"}, {"Do not display", "none"}};

    /**
     * The displayed kinds.
     */
    private RadioGroupFieldEditor displayKind;
    
    /**
     * The field to display stereotype as a note or no
     */
    private BooleanFieldEditor stereotypeAsANoteEditor ;
    

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     */
    @Override
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent)
    {
        // Create the container composite
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout containerLayout = new GridLayout();
        containerLayout.marginWidth = 0;
        containerLayout.marginHeight = 0;
        container.setLayout(containerLayout);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        createDisplay(container);

        loadPreferences();

        return container;
    }

    /**
     * Creates the content.
     * 
     * @param parent
     */
    private void createDisplay(Composite parent)
    {
        Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        group.setText("Display");
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite displayComp = new Composite(group, SWT.NONE);
        displayComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        displayKind = new RadioGroupFieldEditor(AllDiagramPreferenceConstants.STEREOTYPE_DEFAULT_DISPLAY, "Choose the way a stereotype is displayed:", 1, DISPLAY_KIND_VALUES, displayComp);
        displayKind.setPreferenceStore(getPreferenceStore());
        
        stereotypeAsANoteEditor = new BooleanFieldEditor(AllDiagramPreferenceConstants.STEREOTYPE_AS_A_NOTE, "Display stereotype attribute as a note", displayComp);
        stereotypeAsANoteEditor.setPreferenceStore(getPreferenceStore());
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {
        // Do nothing
    }

    /**
     * Initializes the HMI with preference values.
     * 
     * @generated
     */
    private void loadPreferences()
    {
        displayKind.load();
        stereotypeAsANoteEditor.load();
    }

    /**
     * Initializes the HMI with default preference values.
     * 
     * @generated
     */
    private void loadDefaultPreferences()
    {
        displayKind.loadDefault();
        stereotypeAsANoteEditor.loadDefault();
    }

    /**
     * Stores the HMI values into the preference store.
     * 
     * @generated
     */
    private void storePreferences()
    {
        displayKind.store();
        stereotypeAsANoteEditor.store();
    }

    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     * @generated
     */
    @Override
    public boolean performOk()
    {
        storePreferences();
        return super.performOk();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     * @generated
     */
    @Override
    protected void performDefaults()
    {
        loadDefaultPreferences();
        super.performDefaults();
    }
}
