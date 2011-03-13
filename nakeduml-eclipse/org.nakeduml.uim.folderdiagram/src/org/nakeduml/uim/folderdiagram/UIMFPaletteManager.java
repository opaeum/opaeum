/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.nakeduml.uim.UIMPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class UIMFPaletteManager extends ModelerPaletteManager {
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer folderDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer formsDrawer;

	/**
	 * @generated
	 */
	private ICreationUtils creationUtils;

	/**
	 * The Constructor
	 *
	 * @param utils the creation utils for the tools of the palette 
	 * @generated
	 */
	public UIMFPaletteManager(ICreationUtils utils) {
		super();
		this.creationUtils = utils;
	}

	/**
	 * Creates the main categories of the palette
	 *
	 * @generated
	 */
	protected void createCategories() {
		createFolderDrawer();
		createFormsDrawer();
	}

	/**
	 * Updates the main categories of the palette
	 *
	 * @generated
	 */
	protected void updateCategories() {
		// deletion of the existing categories and creation of the updated categories

		getRoot().remove(folderDrawer);
		createFolderDrawer();

		getRoot().remove(formsDrawer);
		createFormsDrawer();
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createFolderDrawer() {
		folderDrawer = new PaletteDrawer("Folder", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getPackageFolder(), "default");
		entries.add(new ModelerCreationToolEntry("Package Folder",
				"Package Folder", factory, UIMFImageRegistry
						.getImageDescriptor("PACKAGEFOLDER"), UIMFImageRegistry
						.getImageDescriptor("PACKAGEFOLDER_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getEntityFolder(), "default");
		entries.add(new ModelerCreationToolEntry("Entity Folder",
				"Entity Folder", factory, UIMFImageRegistry
						.getImageDescriptor("ENTITYFOLDER"), UIMFImageRegistry
						.getImageDescriptor("ENTITYFOLDER_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getStateMachineFolder(), "default");
		entries.add(new ModelerCreationToolEntry("State Machine Folder",
				"State Machine Folder", factory, UIMFImageRegistry
						.getImageDescriptor("STATEMACHINEFOLDER"),
				UIMFImageRegistry
						.getImageDescriptor("STATEMACHINEFOLDER_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getActivityFolder(), "default");
		entries.add(new ModelerCreationToolEntry("Process Folder",
				"Process Folder", factory, UIMFImageRegistry
						.getImageDescriptor("ACTIVITYFOLDER"),
				UIMFImageRegistry.getImageDescriptor("ACTIVITYFOLDER_LARGE")));

		folderDrawer.addAll(entries);
		getRoot().add(folderDrawer);
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createFormsDrawer() {
		formsDrawer = new PaletteDrawer("Forms", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CreationFactory factory;

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getClassForm(), "default");
		entries.add(new ModelerCreationToolEntry("Entity Form", "Entity Form",
				factory, UIMFImageRegistry.getImageDescriptor("CLASSFORM"),
				UIMFImageRegistry.getImageDescriptor("CLASSFORM_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getStateForm(), "default");
		entries.add(new ModelerCreationToolEntry("Form for State",
				"Form for State", factory, UIMFImageRegistry
						.getImageDescriptor("STATEFORM"), UIMFImageRegistry
						.getImageDescriptor("STATEFORM_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getOperationInvocationForm(), "default");
		entries.add(new ModelerCreationToolEntry("Operation Invocation Form",
				"Operation Invocation Form", factory, UIMFImageRegistry
						.getImageDescriptor("OPERATIONINVOCATIONFORM"),
				UIMFImageRegistry
						.getImageDescriptor("OPERATIONINVOCATIONFORM_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getActionTaskForm(), "default");
		entries.add(new ModelerCreationToolEntry("Embedded Task",
				"Embedded Task", factory, UIMFImageRegistry
						.getImageDescriptor("ACTIONTASKFORM"),
				UIMFImageRegistry.getImageDescriptor("ACTIONTASKFORM_LARGE")));

		factory = new GraphElementCreationFactory(creationUtils,
				UIMPackage.eINSTANCE.getOperationTaskForm(), "default");
		entries
				.add(new ModelerCreationToolEntry("Stand-alone Task",
						"Stand-alone Task", factory, UIMFImageRegistry
								.getImageDescriptor("OPERATIONTASKFORM"),
						UIMFImageRegistry
								.getImageDescriptor("OPERATIONTASKFORM_LARGE")));

		formsDrawer.addAll(entries);
		getRoot().add(formsDrawer);
	}

}
