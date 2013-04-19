package org.opaeum.modeler.compositestructurediagram;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.graphconf.exceptions.MissingGraphConfFileException;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ElementEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresCreationUtils;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresPlugin;
import org.topcased.modeler.uml.compositestructuresdiagram.EditPart2ModelAdapterFactory;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.CollaborationUseEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.ConnectorEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.PortEditPart;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.PropertyEditPart;

public class BusinessStructureConfiguration implements IConfiguration{
	private BusinessStructurePaletteManager paletteManager;
	private BusinessStructureEditPartFactory editPartFactory;
	private CompositeStructuresCreationUtils creationUtils;
	private DiagramGraphConf diagramGraphConf;
	public BusinessStructureConfiguration(){
		registerAdapters();
	}
	private void registerAdapters(){
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ConnectorEditPart.class, org.eclipse.uml2.uml.Connector.class),
				ConnectorEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DependencyEditPart.class, org.eclipse.uml2.uml.Dependency.class),
				DependencyEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CommentEditPart.class, org.eclipse.uml2.uml.Comment.class), CommentEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PortEditPart.class, org.eclipse.uml2.uml.Port.class), PortEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PropertyEditPart.class, org.eclipse.uml2.uml.Property.class),
				PropertyEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(InterfaceEditPart.class, org.eclipse.uml2.uml.Interface.class),
				InterfaceEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ElementEditPart.class, org.eclipse.uml2.uml.Element.class), ElementEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CollaborationUseEditPart.class, org.eclipse.uml2.uml.CollaborationUse.class),
				CollaborationUseEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ConstraintEditPart.class, org.eclipse.uml2.uml.Constraint.class),
				ConstraintEditPart.class);
	}
	public String getId(){
		return new String("org.topcased.modeler.uml.compositestructuresdiagram");
	}
	public String getName(){
		return new String("Composite Structures Diagram (Class View)");
	}
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new BusinessStructureEditPartFactory();
		}
		return editPartFactory;
	}
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new BusinessStructurePaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new CompositeStructuresCreationUtils(getDiagramGraphConf());
		}
		return creationUtils;
	}
	public DiagramGraphConf getDiagramGraphConf(){
		if(diagramGraphConf == null){
			URL url = CompositeStructuresPlugin.getDefault().getBundle().getResource("org/topcased/modeler/uml/compositestructuresdiagram/common/diagram.graphconf");
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