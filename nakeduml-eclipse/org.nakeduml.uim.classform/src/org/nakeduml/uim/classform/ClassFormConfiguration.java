/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.nakeduml.uim.classform.edit.BuiltInActionEditPart;
import org.nakeduml.uim.classform.edit.DetailPanelEditPart;
import org.nakeduml.uim.classform.edit.FormPanelEditPart;
import org.nakeduml.uim.classform.edit.NavigationToEntityEditPart;
import org.nakeduml.uim.classform.edit.NavigationToOperationEditPart;
import org.nakeduml.uim.classform.edit.OperationActionEditPart;
import org.nakeduml.uim.classform.edit.TransitionActionEditPart;
import org.nakeduml.uim.classform.edit.UIMBorderLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMDataColumnEditPart;
import org.nakeduml.uim.classform.edit.UIMDataTableEditPart;
import org.nakeduml.uim.classform.edit.UIMFieldEditPart;
import org.nakeduml.uim.classform.edit.UIMGridLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMPanelEditPart;
import org.nakeduml.uim.classform.edit.UIMTabEditPart;
import org.nakeduml.uim.classform.edit.UIMTabPanelEditPart;
import org.nakeduml.uim.classform.edit.UIMToolbarLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMXYLayoutEditPart;
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
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMFieldEditPart.class, org.nakeduml.uim.UIMField.class), UIMFieldEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(NavigationToEntityEditPart.class, org.nakeduml.uim.NavigationToEntity.class),
				NavigationToEntityEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(NavigationToOperationEditPart.class, org.nakeduml.uim.NavigationToOperation.class), NavigationToOperationEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(BuiltInActionEditPart.class, org.nakeduml.uim.BuiltInAction.class),
				BuiltInActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(OperationActionEditPart.class, org.nakeduml.uim.OperationAction.class),
				OperationActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TransitionActionEditPart.class, org.nakeduml.uim.TransitionAction.class),
				TransitionActionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(FormPanelEditPart.class, org.nakeduml.uim.FormPanel.class),
				FormPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMPanelEditPart.class, org.nakeduml.uim.UIMPanel.class), UIMPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMTabPanelEditPart.class, org.nakeduml.uim.UIMTabPanel.class),
				UIMTabPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMTabEditPart.class, org.nakeduml.uim.UIMTab.class), UIMTabEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMDataTableEditPart.class, org.nakeduml.uim.UIMDataTable.class),
				UIMDataTableEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMDataColumnEditPart.class, org.nakeduml.uim.UIMDataColumn.class),
				UIMDataColumnEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DetailPanelEditPart.class, org.nakeduml.uim.DetailPanel.class),
				DetailPanelEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMXYLayoutEditPart.class, org.nakeduml.uim.UIMXYLayout.class),
				UIMXYLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMGridLayoutEditPart.class, org.nakeduml.uim.UIMGridLayout.class),
				UIMGridLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMToolbarLayoutEditPart.class, org.nakeduml.uim.UIMToolbarLayout.class),
				UIMToolbarLayoutEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UIMBorderLayoutEditPart.class, org.nakeduml.uim.UIMBorderLayout.class),
				UIMBorderLayoutEditPart.class);
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId(){
		return new String("org.nakeduml.uim.classform");
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
			URL url = ClassFormPlugin.getDefault().getBundle().getResource("org/nakeduml/uim/classform/diagram.graphconf");
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