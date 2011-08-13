package org.nakeduml.topcased.classdiagram;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.classdiagram.EditPart2ModelAdapterFactory;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;

public class ClassConfiguration extends org.topcased.modeler.uml.classdiagram.ClassConfiguration{
	private IPaletteManager paletteManager;
	private EditPartFactory editPartFactory;
	private ICreationUtils creationUtils;
	public ClassConfiguration(){
		super();
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClassEditPart.class, org.eclipse.uml2.uml.Component.class), ClassEditPart.class);
	}
	@Override
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new ClassPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new ClassEditPartFactory();
		}
		return editPartFactory;
	}
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new ClassCreationUtils(getDiagramGraphConf()){
				protected GraphElement createGraphElementEnumeration(org.eclipse.uml2.uml.Enumeration element,String presentation){
					GraphNode nodeParent = createGraphNode(element, presentation);
					GraphNode enumerationliteral = createGraphNode(element, UMLPackage.ENUMERATION__OWNED_LITERAL, presentation);
					enumerationliteral.setContainer(nodeParent);
					GraphNode attributes = createGraphNode(element, UMLPackage.DATA_TYPE__OWNED_ATTRIBUTE,presentation);
					GraphNode operations = createGraphNode(element, UMLPackage.DATA_TYPE__OWNED_OPERATION,presentation);
					attributes.setContainer(nodeParent);
					operations.setContainer(nodeParent);
					return nodeParent;
				}

			};
		}
		return creationUtils;
	}
}
