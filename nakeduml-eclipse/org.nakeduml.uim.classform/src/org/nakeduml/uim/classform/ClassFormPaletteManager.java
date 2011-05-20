/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.nakeduml.uim.UimPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;

/**
 * Generated Palette Manager
 * 
 * @generated
 */
public class ClassFormPaletteManager extends ModelerPaletteManager{
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer controlsDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer containersDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer layoutsDrawer;
	/**
	 * @generated
	 */
	private ICreationUtils creationUtils;
	/**
	 * The Constructor
	 * 
	 * @param utils
	 *            the creation utils for the tools of the palette
	 * @generated
	 */
	public ClassFormPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	/**
	 * Creates the main categories of the palette
	 * 
	 * @generated
	 */
	protected void createCategories(){
		createControlsDrawer();
		createContainersDrawer();
		createLayoutsDrawer();
	}
	/**
	 * Updates the main categories of the palette
	 * 
	 * @generated
	 */
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated categories
		getRoot().remove(controlsDrawer);
		createControlsDrawer();
		getRoot().remove(containersDrawer);
		createContainersDrawer();
		getRoot().remove(layoutsDrawer);
		createLayoutsDrawer();
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 * 
	 * @generated
	 */
	private void createControlsDrawer(){
		controlsDrawer = new PaletteDrawer("Controls", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimField(), "default");
		entries.add(new ModelerCreationToolEntry("Field", "Field", factory, ClassFormImageRegistry.getImageDescriptor("UIMFIELD"), ClassFormImageRegistry
				.getImageDescriptor("UIMFIELD_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getNavigationToEntity(), "default");
		entries.add(new ModelerCreationToolEntry("Link to Entity", "Link to Entity", factory, ClassFormImageRegistry.getImageDescriptor("NAVIGATIONTOENTITY"),
				ClassFormImageRegistry.getImageDescriptor("NAVIGATIONTOENTITY_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getNavigationToOperation(), "default");
		entries.add(new ModelerCreationToolEntry("Link to Operation", "Link to Operation", factory, ClassFormImageRegistry.getImageDescriptor("NAVIGATIONTOOPERATION"),
				ClassFormImageRegistry.getImageDescriptor("NAVIGATIONTOOPERATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getOperationAction(), "default");
		entries.add(new ModelerCreationToolEntry("Operation Action", "Operation Action", factory, ClassFormImageRegistry.getImageDescriptor("OPERATIONACTION"),
				ClassFormImageRegistry.getImageDescriptor("OPERATIONACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getBuiltInAction(), "default");
		entries.add(new ModelerCreationToolEntry("Built-in Action", "Built-in Action", factory, ClassFormImageRegistry.getImageDescriptor("BUILTINACTION"),
				ClassFormImageRegistry.getImageDescriptor("BUILTINACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getTransitionAction(), "default");
		entries.add(new ModelerCreationToolEntry("Transition Action", "Transition Action", factory, ClassFormImageRegistry.getImageDescriptor("TRANSITIONACTION"),
				ClassFormImageRegistry.getImageDescriptor("TRANSITIONACTION_LARGE")));
		controlsDrawer.addAll(entries);
		getRoot().add(controlsDrawer);
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 * 
	 * @generated
	 */
	private void createContainersDrawer(){
		containersDrawer = new PaletteDrawer("Containers", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getFormPanel(), "default");
		entries.add(new ModelerCreationToolEntry("Form", "Form", factory, ClassFormImageRegistry.getImageDescriptor("FORMPANEL"), ClassFormImageRegistry
				.getImageDescriptor("FORMPANEL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimPanel(), "default");
		entries.add(new ModelerCreationToolEntry("Panel", "Panel", factory, ClassFormImageRegistry.getImageDescriptor("UIMPANEL"), ClassFormImageRegistry
				.getImageDescriptor("UIMPANEL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimTabPanel(), "default");
		entries.add(new ModelerCreationToolEntry("Tabbed Panel", "Tabbed Panel", factory, ClassFormImageRegistry.getImageDescriptor("UIMTABPANEL"),
				ClassFormImageRegistry.getImageDescriptor("UIMTABPANEL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimTab(), "default");
		entries.add(new ModelerCreationToolEntry("Tab", "Tab", factory, ClassFormImageRegistry.getImageDescriptor("UIMTAB"), ClassFormImageRegistry
				.getImageDescriptor("UIMTAB_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimDataTable(), "default");
		entries.add(new ModelerCreationToolEntry("Data Table", "Data Table", factory, ClassFormImageRegistry.getImageDescriptor("UIMDATATABLE"), ClassFormImageRegistry
				.getImageDescriptor("UIMDATATABLE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimDataColumn(), "default");
		entries.add(new ModelerCreationToolEntry("Data Colum", "Data Colum", factory, ClassFormImageRegistry.getImageDescriptor("UIMDATACOLUMN"), ClassFormImageRegistry
				.getImageDescriptor("UIMDATACOLUMN_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getDetailPanel(), "default");
		entries.add(new ModelerCreationToolEntry("Master/Detail Panel", "Master/Detail Panel", factory, ClassFormImageRegistry.getImageDescriptor("DETAILPANEL"),
				ClassFormImageRegistry.getImageDescriptor("DETAILPANEL_LARGE")));
		containersDrawer.addAll(entries);
		getRoot().add(containersDrawer);
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 * 
	 * @generated
	 */
	private void createLayoutsDrawer(){
		layoutsDrawer = new PaletteDrawer("Layouts", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimXYLayout(), "default");
		entries.add(new ModelerCreationToolEntry("XY Layout", "XY Layout", factory, ClassFormImageRegistry.getImageDescriptor("UIMXYLAYOUT"), ClassFormImageRegistry
				.getImageDescriptor("UIMXYLAYOUT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimGridLayout(), "default");
		entries.add(new ModelerCreationToolEntry("Grid Layout", "Grid Layout", factory, ClassFormImageRegistry.getImageDescriptor("UIMGRIDLAYOUT"),
				ClassFormImageRegistry.getImageDescriptor("UIMGRIDLAYOUT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimToolbarLayout(), "default");
		entries.add(new ModelerCreationToolEntry("Toolbar Layout", "Toolbar Layout", factory, ClassFormImageRegistry.getImageDescriptor("UIMTOOLBARLAYOUT"),
				ClassFormImageRegistry.getImageDescriptor("UIMTOOLBARLAYOUT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimBorderLayout(), "default");
		entries.add(new ModelerCreationToolEntry("Border Layout", "Border Layout", factory, ClassFormImageRegistry.getImageDescriptor("UIMBORDERLAYOUT"),
				ClassFormImageRegistry.getImageDescriptor("UIMBORDERLAYOUT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UimPackage.eINSTANCE.getUimFullLayout(), "default");
		entries.add(new ModelerCreationToolEntry("Full Layout", "Full Layout", factory, ClassFormImageRegistry.getImageDescriptor("UIMFULLLAYOUT"),
				ClassFormImageRegistry.getImageDescriptor("UIMFULLLAYOUT_LARGE")));
		layoutsDrawer.addAll(entries);
		getRoot().add(layoutsDrawer);
	}
}
