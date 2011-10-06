/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.opaeum.bpmn2.diagram.edit.BoundaryEventEditPart;
import org.opaeum.bpmn2.diagram.edit.EndEventEditPart;
import org.opaeum.bpmn2.diagram.edit.FlowNodeEditPart;
import org.opaeum.bpmn2.diagram.edit.IntermediateCatchEventEditPart;
import org.opaeum.bpmn2.diagram.edit.IntermediateThrowEventEditPart;
import org.opaeum.bpmn2.diagram.edit.MessageEventDefinitionEditPart;
import org.opaeum.bpmn2.diagram.edit.SequenceFlowEditPart;
import org.opaeum.bpmn2.diagram.edit.SignalEventDefinitionEditPart;
import org.opaeum.bpmn2.diagram.edit.StartEventEditPart;
import org.opaeum.bpmn2.diagram.edit.UserTaskEditPart;
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
public class BPMN2Configuration implements IConfiguration{
	/**
	 * @generated
	 */
	private BPMN2PaletteManager paletteManager;
	/**
	 * @generated
	 */
	private BPMN2EditPartFactory editPartFactory;
	/**
	 * @generated
	 */
	private BPMN2CreationUtils creationUtils;
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
	public BPMN2Configuration(){
		registerAdapters();
	}
	/**
	 * Registers the Adapter Factories for all the EditParts
	 *
	 * @generated
	 */
	private void registerAdapters(){
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SequenceFlowEditPart.class, org.eclipse.bpmn2.SequenceFlow.class),
				SequenceFlowEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UserTaskEditPart.class, org.eclipse.bpmn2.UserTask.class), UserTaskEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(BoundaryEventEditPart.class, org.eclipse.bpmn2.BoundaryEvent.class),
				BoundaryEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(MessageEventDefinitionEditPart.class, org.eclipse.bpmn2.MessageEventDefinition.class),
				MessageEventDefinitionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(FlowNodeEditPart.class, org.eclipse.bpmn2.FlowNode.class), FlowNodeEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(IntermediateCatchEventEditPart.class, org.eclipse.bpmn2.IntermediateCatchEvent.class),
				IntermediateCatchEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(StartEventEditPart.class, org.eclipse.bpmn2.StartEvent.class),
				StartEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EndEventEditPart.class, org.eclipse.bpmn2.EndEvent.class), EndEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(
				new EditPart2ModelAdapterFactory(IntermediateThrowEventEditPart.class, org.eclipse.bpmn2.IntermediateThrowEvent.class),
				IntermediateThrowEventEditPart.class);
		Platform.getAdapterManager()
				.registerAdapters(new EditPart2ModelAdapterFactory(SignalEventDefinitionEditPart.class, org.eclipse.bpmn2.SignalEventDefinition.class),
						SignalEventDefinitionEditPart.class);
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getId()
	 * @generated
	 */
	public String getId(){
		return new String("org.opaeum.bpmn2.diagram");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getName()
	 * @generated
	 */
	public String getName(){
		return new String("BPMN2 Diagram");
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
	 * @generated
	 */
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new BPMN2EditPartFactory();
		}
		return editPartFactory;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
	 * @generated
	 */
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new BPMN2PaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
	 * @generated
	 */
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new BPMN2CreationUtils(getDiagramGraphConf());
		}
		return creationUtils;
	}
	/**
	 * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
	 * @generated
	 */
	public DiagramGraphConf getDiagramGraphConf(){
		if(diagramGraphConf == null){
			URL url = BPMN2Plugin.getDefault().getBundle().getResource("org/opaeum/bpmn2/diagram/diagram.graphconf");
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