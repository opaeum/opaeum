/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.modeleditor.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.nakeduml.uim.modeleditor.UIMPlugin;
import org.topcased.facilities.preferences.AbstractTopcasedPreferencePage;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;

/**
 * A preference page to configure all preferences of the UIM editor.
 *
 * @generated
 */
public class UIMPreferencePage extends AbstractTopcasedPreferencePage{
	/**
	 * @generated
	 */
	private BooleanFieldEditor deleteGraphElements;
	/**
	 * @generated
	 */
	private BooleanFieldEditor deleteModelElements;
	/**
	 * @generated
	 */
	private BooleanFieldEditor moveModelElements;
	/**
	 * Creates this preference page contents.
	 *
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	protected Control createContents(Composite parent){
		// Create the container composite
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout containerLayout = new GridLayout();
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		container.setLayout(containerLayout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		createHeader(container);
		createDeleteActionsGroup(container);
		createMoveActionGroup(container);
		loadPreferences();
		return container;
	}
	/**
	 * Initializes the HMI with preference values.
	 *
	 * @generated
	 */
	private void loadPreferences(){
		deleteGraphElements.load();
		deleteModelElements.load();
		moveModelElements.load();
	}
	/**
	 * Stores the HMI values into the preference store.
	 *
	 * @generated
	 */
	private void storePreferences(){
		deleteGraphElements.store();
		deleteModelElements.store();
		moveModelElements.store();
	}
	/**
	 * Initializes the HMI with default preference values.
	 *
	 * @generated
	 */
	private void loadDefaultPreferences(){
		deleteGraphElements.loadDefault();
		deleteModelElements.loadDefault();
		moveModelElements.loadDefault();
	}
	/**
	 * Creates this preference page header.
	 *
	 * @param parent the parent composite
	 * @generated
	 */
	private void createHeader(Composite parent){
		new Label(parent, SWT.WRAP).setText("UIM editor preference page");
	}
	/**
	 * Creates the group to configure the delete actions preferences.
	 * 
	 * @param parent the parent composite
	 * @generated
	 */
	private void createDeleteActionsGroup(Composite parent){
		Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		group.setLayout(new GridLayout());
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setText("Delete actions");
		Composite fieldsContainer = new Composite(group, SWT.NONE);
		fieldsContainer.setLayout(new GridLayout());
		fieldsContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteGraphElements = new BooleanFieldEditor(ModelerPreferenceConstants.DELETE_ACTION_CONFIRM, "Do not ask for confirmation before deleting graphical elements.",
				fieldsContainer);
		deleteGraphElements.setPreferenceStore(getPreferenceStore());
		deleteModelElements = new BooleanFieldEditor(ModelerPreferenceConstants.DELETE_MODEL_ACTION_CONFIRM,
				"Do not ask for confirmation before deleting model elements.", fieldsContainer);
		deleteModelElements.setPreferenceStore(getPreferenceStore());
	}
	/**
	 * Creates the group to configure the move actions preferences.
	 * 
	 * @param parent the parent composite
	 * @generated
	 */
	private void createMoveActionGroup(Composite parent){
		Group groupMove = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		groupMove.setLayout(new GridLayout());
		groupMove.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		groupMove.setText("Move actions");
		Composite fieldsContainerMove = new Composite(groupMove, SWT.NONE);
		fieldsContainerMove.setLayout(new GridLayout());
		fieldsContainerMove.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		moveModelElements = new BooleanFieldEditor(ModelerPreferenceConstants.MOVE_MODEL_ACTION_CONFIRM, "Do not ask for confirmation before moving model elements.",
				fieldsContainerMove);
		moveModelElements.setPreferenceStore(getPreferenceStore());
	}
	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 * @generated
	 */
	public void init(IWorkbench workbench){
		// Do nothing
	}
	/**
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 * @generated
	 */
	public boolean performOk(){
		storePreferences();
		return super.performOk();
	}
	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 * @generated
	 */
	protected void performDefaults(){
		loadDefaultPreferences();
		super.performDefaults();
	}
	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 * @generated
	 */
	protected String getBundleId(){
		return UIMPlugin.getId();
	}
}
