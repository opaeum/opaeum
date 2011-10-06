/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.opaeum.uim.classform.edit.BuiltInActionEditPart;
import org.opaeum.uim.classform.edit.DetailPanelEditPart;
import org.opaeum.uim.classform.edit.FormPanelEditPart;
import org.opaeum.uim.classform.edit.NavigationToEntityEditPart;
import org.opaeum.uim.classform.edit.NavigationToOperationEditPart;
import org.opaeum.uim.classform.edit.OperationActionEditPart;
import org.opaeum.uim.classform.edit.TransitionActionEditPart;
import org.opaeum.uim.classform.edit.UimBorderLayoutEditPart;
import org.opaeum.uim.classform.edit.UimColumnLayoutEditPart;
import org.opaeum.uim.classform.edit.UimDataTableEditPart;
import org.opaeum.uim.classform.edit.UimFieldEditPart;
import org.opaeum.uim.classform.edit.UimFullLayoutEditPart;
import org.opaeum.uim.classform.edit.UimGridLayoutEditPart;
import org.opaeum.uim.classform.edit.UimPanelEditPart;
import org.opaeum.uim.classform.edit.UimTabEditPart;
import org.opaeum.uim.classform.edit.UimTabPanelEditPart;
import org.opaeum.uim.classform.edit.UimToolbarLayoutEditPart;
import org.opaeum.uim.classform.edit.UimXYLayoutEditPart;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.graphconf.exceptions.MissingGraphConfFileException;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram.
 * 
 * @generated
 */
public class ClassFormConfiguration implements IConfiguration{
	/**
	 * @generated
	 */
	private ClassFormPaletteManager paletteManager;
	/**
	 * @generated
	 */
	private ClassFormEditPartFactory editPartFactory;
	/**
	 * @generated
	 */
	private ClassFormCreationUtils creationUtils;
	/**
	 * The DiagramGraphConf that contains graphical informations on the configuration
	 * 
	 * @generated
	 */
	private DiagramGraphConf diagramGraphConf;
	/**
	 * Constructor. Initialize Adapter factories.
	 * 
	 * @generated
	 */
	public ClassFormConfiguration(){
		registerAdapters();
	}
	/**
	 * Registers the Adapter Factories for all the EditParts
	 * 
	 * @generated
	 */
	private void registerAdapters(){
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimFieldEditPart.class, org.opaeum.uim.UimField.class), UimFieldEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(NavigationToEntityEditPart.class, org.opaeum.uim.action.NavigationToEntity.class), NavigationToEntityEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(NavigationToOperationEditPart.class, org.opaeum.uim.action.NavigationToOperation.class),
				NavigationToOperationEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(BuiltInActionEditPart.class, org.opaeum.uim.action.BuiltInAction.class),
				BuiltInActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(OperationActionEditPart.class, org.opaeum.uim.action.OperationAction.class),
				OperationActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TransitionActionEditPart.class, org.opaeum.uim.action.TransitionAction.class),
				TransitionActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(FormPanelEditPart.class, org.opaeum.uim.form.FormPanel.class),
				FormPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimPanelEditPart.class, org.opaeum.uim.UimPanel.class), UimPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimTabPanelEditPart.class, org.opaeum.uim.UimTabPanel.class),
				UimTabPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimTabEditPart.class, org.opaeum.uim.UimTab.class), UimTabEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimDataTableEditPart.class, org.opaeum.uim.UimDataTable.class),
				UimDataTableEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DetailPanelEditPart.class, org.opaeum.uim.form.DetailPanel.class),
				DetailPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimXYLayoutEditPart.class, org.opaeum.uim.layout.UimXYLayout.class),
				UimXYLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimGridLayoutEditPart.class, org.opaeum.uim.layout.UimGridLayout.class),
				UimGridLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimToolbarLayoutEditPart.class, org.opaeum.uim.layout.UimToolbarLayout.class),
				UimToolbarLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimBorderLayoutEditPart.class, org.opaeum.uim.layout.UimBorderLayout.class),
				UimBorderLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimFullLayoutEditPart.class, org.opaeum.uim.layout.UimFullLayout.class),
				UimFullLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UimColumnLayoutEditPart.class, org.opaeum.uim.layout.UimColumnLayout.class),
				UimColumnLayoutEditPart.class);
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId(){
		return new String("org.opaeum.uim.classform");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName(){
		return new String("Class Form Diagram");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new ClassFormEditPartFactory();
		}
		return editPartFactory;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new ClassFormPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new ClassFormCreationUtils(getDiagramGraphConf());
		}
		return creationUtils;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf(){
		if(diagramGraphConf == null){
			URL url = ClassFormPlugin.getDefault().getBundle().getResource("org/opaeum/uim/classform/diagram.graphconf");
			if(url != null){
				URI fileURI = URI.createURI(url.toString());
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(fileURI, true);
				if(resource != null && resource.getContents().get(0) instanceof DiagramGraphConf){
					diagramGraphConf = (DiagramGraphConf) resource.getContents().get(0);
				}
			}else{
				new MissingGraphConfFileException(
						"The *.diagramgraphconf file can not be retrieved. Check if the path is correct in the Configuration class of your diagram.");
			}
		}
		return diagramGraphConf;
	}
}