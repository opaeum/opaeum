package org.nakeduml.topcased.classdiagram.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.uml2.uml.ElementImport;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.commands.ReconnectEdgeToNodeCommand;
import org.topcased.modeler.commands.ReconnectEdgeToTargetCommand;
import org.topcased.modeler.commands.ReconnectGraphEdgeCommand;
import org.topcased.modeler.commands.ReconnectSourceToEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.edit.PackageImportEditPart;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

public class ElementImportEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy{
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new ElementImportEdgeCreationCommand(domain, edge, source);
	}
	protected boolean checkEdge(GraphEdge edge){
		return Utils.getElement(edge) instanceof ElementImport;
	}
	protected boolean checkSource(GraphElement source){
		EObject object = Utils.getElement(source);
		if(object instanceof org.eclipse.uml2.uml.Namespace){
			return true;
		}
		return false;
	}
	protected boolean checkTargetForSource(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		if(targetObject instanceof org.eclipse.uml2.uml.PackageableElement){
			if(!sourceObject.equals(targetObject)){
				return true;
			}
		}
		System.out.println("ElementImportEdgeCreationEditPolicy.checkTargetForSource()" + targetObject);
		return false;
	}
	protected boolean checkCommand(Command command){
		return command instanceof ElementImportEdgeCreationCommand;
	}
	protected SourceTargetData getSourceTargetData(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		if(sourceObject instanceof org.eclipse.uml2.uml.Namespace && targetObject instanceof org.eclipse.uml2.uml.PackageableElement){
			return new SourceTargetData(false, false, SourceTargetData.SOURCE, "org.eclipse.uml2.uml.Namespace", "elementImport", null, "importedElement", null, null,
					null, null);
		}
		return null;
	}
	protected Command getReconnectSourceCommand(ReconnectRequest request){
		ConnectionEditPart connection = request.getConnectionEditPart();
		if(connection instanceof PackageImportEditPart){
			final PackageImportEditPart edit = (PackageImportEditPart) connection;
			final GraphElement newSource = (GraphElement) getHost().getModel();
			if(checkSource(newSource)){
				final GraphElement graphElt = (GraphElement) edit.getModel();
				ReconnectGraphEdgeCommand command = new ReconnectSourceToEdgeCommand(edit, "elementImport"){
					protected EObject getObjectToUpdate(){
						return Utils.getElement(graphElt);
					}
				};
				command.setNewElement(newSource);
				return command;
			}
		}
		return null;
	}
	protected Command getReconnectTargetCommand(ReconnectRequest request){
		ConnectionEditPart connection = request.getConnectionEditPart();
		if(connection instanceof PackageImportEditPart){
			final PackageImportEditPart edit = (PackageImportEditPart) connection;
			final GraphElement newTarget = (GraphElement) getHost().getModel();
			final GraphElement source = (GraphElement) edit.getSource().getModel();
			if(checkTargetForSource(source, newTarget)){
				final GraphElement graphElt = (GraphElement) edit.getModel();
				ReconnectEdgeToNodeCommand command = new ReconnectEdgeToTargetCommand(edit, "importedElement"){
					protected EObject getObjectToUpdate(){
						return Utils.getElement(graphElt);
					}
				};
				command.setNewElement(newTarget);
				return command;
			}
		}
		return null;
	}
}