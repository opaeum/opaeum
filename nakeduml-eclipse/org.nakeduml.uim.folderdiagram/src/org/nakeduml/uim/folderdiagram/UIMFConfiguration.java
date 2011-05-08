/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.nakeduml.uim.folderdiagram.edit.ActionTaskFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.ActivityFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.ClassFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.EntityFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.OperationInvocationFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.OperationTaskFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.PackageFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.StateFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.StateMachineFolderEditPart;
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
public class UIMFConfiguration implements IConfiguration{
	/**
	 * @generated
	 */
	private UIMFPaletteManager paletteManager;
	/**
	 * @generated
	 */
	private UIMFEditPartFactory editPartFactory;
	/**
	 * @generated
	 */
	private UIMFCreationUtils creationUtils;
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
	public UIMFConfiguration(){
		registerAdapters();
	}
	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters(){
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PackageFolderEditPart.class, org.nakeduml.uim.PackageFolder.class),
				PackageFolderEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EntityFolderEditPart.class, org.nakeduml.uim.EntityFolder.class),
				EntityFolderEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(StateMachineFolderEditPart.class, org.nakeduml.uim.StateMachineFolder.class),
				StateMachineFolderEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ActivityFolderEditPart.class, org.nakeduml.uim.ActivityFolder.class),
				ActivityFolderEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClassFormEditPart.class, org.nakeduml.uim.ClassForm.class),
				ClassFormEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(StateFormEditPart.class, org.nakeduml.uim.StateForm.class),
				StateFormEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(OperationTaskFormEditPart.class, org.nakeduml.uim.OperationTaskForm.class),
				OperationTaskFormEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ActionTaskFormEditPart.class, org.nakeduml.uim.ActionTaskForm.class),
				ActionTaskFormEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(OperationInvocationFormEditPart.class, org.nakeduml.uim.OperationInvocationForm.class),
				OperationInvocationFormEditPart.class);
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId(){
		return new String("org.nakeduml.uim.folderdiagram");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName(){
		return new String("User Interaction Folder Diagram");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new UIMFEditPartFactory();
		}
		return editPartFactory;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new UIMFPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new UIMFCreationUtils(getDiagramGraphConf());
		}
		return creationUtils;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf(){
		if(diagramGraphConf == null){
			URL url = UIMFPlugin.getDefault().getBundle().getResource("org/nakeduml/uim/folderdiagram/diagram.graphconf");
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