/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.preferences;

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
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;

/**
 * A preference page to configure all preferences of the UML2 editor.<br>
 * 
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UMLPreferencePage extends AbstractTopcasedPreferencePage
{

    private BooleanFieldEditor deleteGraphElements;

    private BooleanFieldEditor deleteModelElements;

    /** @deprecated */
    private BooleanFieldEditor moveModelElements;

    private BooleanFieldEditor dragNdropConfirmation;
    
    private BooleanFieldEditor notifyAtLoad;
    
    private BooleanFieldEditor deferIdRef;
    
    private BooleanFieldEditor deferAttachment;
    
    private BooleanFieldEditor customResolveAll;

    /**
     * Creates this preference page contents.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param parent the parent composite
     * @return the created Control element
     * @generated
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
        // move action group put in the modelerpreferencepage
        // createMoveActionGroup(container);

        createDragAndDropActionGroup(container);
        createLoadOptions(container);

        loadPreferences();

        return container;
    }

    /**
     * Initializes the HMI with preference values.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private void loadPreferences()
    {
        deleteGraphElements.load();
        deleteModelElements.load();

        // moveModelElements.load();

        dragNdropConfirmation.load();
        
        notifyAtLoad.load();

        deferAttachment.load();
        
        deferIdRef.load();
        
        customResolveAll.load();
    }

    /**
     * Stores the HMI values into the preference store.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private void storePreferences()
    {
        deleteGraphElements.store();
        deleteModelElements.store();

        // moveModelElements.store();

        dragNdropConfirmation.store();
        notifyAtLoad.store();

        deferAttachment.store();
        
        deferIdRef.store();
        
        customResolveAll.store();
    }

    /**
     * Initializes the HMI with default preference values.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private void loadDefaultPreferences()
    {
        deleteGraphElements.loadDefault();
        deleteModelElements.loadDefault();

        // moveModelElements.loadDefault();

        dragNdropConfirmation.loadDefault();
        notifyAtLoad.loadDefault();

        deferAttachment.loadDefault();
        
        deferIdRef.loadDefault();
        
        customResolveAll.loadDefault();
        
    }

    /**
     * Creates this preference page header.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param parent the parent composite
     * @generated
     */
    private void createHeader(Composite parent)
    {
        new Label(parent, SWT.WRAP).setText("UML/SysML editor preference page");
    }

    private void createLoadOptions(Composite parent)
    {
        Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText("Load Options (for advanced users)");

        Composite fieldsContainer = new Composite(group, SWT.NONE);
        fieldsContainer.setLayout(new GridLayout());
        fieldsContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        notifyAtLoad = new BooleanFieldEditor(AllDiagramPreferenceConstants.LOAD_NOTIFY, "Disable notify elements when model is loaded", fieldsContainer);
        notifyAtLoad.setPreferenceStore(getPreferenceStore());

        deferAttachment = new BooleanFieldEditor(AllDiagramPreferenceConstants.LOAD_DEFER_ATTACHMENT, "Defer root element attachment", fieldsContainer);
        deferAttachment.setPreferenceStore(getPreferenceStore());
        
        deferIdRef = new BooleanFieldEditor(AllDiagramPreferenceConstants.LOAD_DEFER_IDREF, "Defer resolution of id in resource", fieldsContainer);
        deferIdRef.setPreferenceStore(getPreferenceStore());
        
        customResolveAll = new BooleanFieldEditor(AllDiagramPreferenceConstants.LOAD_CUSTOM_RESOLVE_ALL, "Use TOPCASED specific resolve all (experimental)", fieldsContainer);
        customResolveAll.setPreferenceStore(getPreferenceStore());
        
    }
    
    /**
     * Creates the group to configure the delete actions preferences.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param parent the parent composite
     * @generated
     */
    private void createDeleteActionsGroup(Composite parent)
    {
        Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        group.setLayout(new GridLayout());
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText("Delete actions");

        Composite fieldsContainer = new Composite(group, SWT.NONE);
        fieldsContainer.setLayout(new GridLayout());
        fieldsContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        deleteGraphElements = new BooleanFieldEditor(ModelerPreferenceConstants.DELETE_ACTION_CONFIRM, "Do not ask for confirmation before deleting graphical elements.", fieldsContainer);
        deleteGraphElements.setPreferenceStore(getPreferenceStore());

        deleteModelElements = new BooleanFieldEditor(ModelerPreferenceConstants.DELETE_MODEL_ACTION_CONFIRM, "Do not ask for confirmation before deleting model elements.", fieldsContainer);
        deleteModelElements.setPreferenceStore(getPreferenceStore());
    }

    /**
     * Creates the group to configure the move actions preferences.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param parent the parent composite
     * @generated NOT
     * @deprecated
     */
    private void createMoveActionGroup(Composite parent)
    {
        Group groupMove = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        groupMove.setLayout(new GridLayout());
        groupMove.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupMove.setText("Move actions");

        Composite fieldsContainerMove = new Composite(groupMove, SWT.NONE);
        fieldsContainerMove.setLayout(new GridLayout());
        fieldsContainerMove.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        moveModelElements = new BooleanFieldEditor(ModelerPreferenceConstants.MOVE_MODEL_ACTION_CONFIRM, "Do not ask for confirmation before moving model elements.", fieldsContainerMove);
        moveModelElements.setPreferenceStore(getPreferenceStore());
    }

    /**
     * Creates the group to configure the drag&drop actions preferences.
     * 
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param parent the parent composite
     * @generated
     */
    private void createDragAndDropActionGroup(Composite parent)
    {
        Group groupDragNDrop = new Group(parent, SWT.SHADOW_ETCHED_OUT);
        groupDragNDrop.setLayout(new GridLayout());
        groupDragNDrop.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupDragNDrop.setText("Drag and Drop actions");

        Composite fieldsContainerDragAndDrop = new Composite(groupDragNDrop, SWT.NONE);
        fieldsContainerDragAndDrop.setLayout(new GridLayout());
        fieldsContainerDragAndDrop.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        dragNdropConfirmation = new BooleanFieldEditor(ModelerPreferenceConstants.DRAGNDROP_MODEL_ACTION_PREF, "Do not display popup when dropping model elements.", fieldsContainerDragAndDrop);
        dragNdropConfirmation.setPreferenceStore(getPreferenceStore());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     * @generated
     */
    public void init(IWorkbench workbench)
    {
        // Do nothing
    }

    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     * @generated
     */
    public boolean performOk()
    {
        storePreferences();
        return super.performOk();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     * @generated
     */
    protected void performDefaults()
    {
        loadDefaultPreferences();
        super.performDefaults();
    }

    /**
     * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
     * @generated
     */
    protected String getBundleId()
    {
        return UMLPlugin.getId();
    }
}
