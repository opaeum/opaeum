/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * UserTask restore connection command
 *
 * @generated
 */
public class UserTaskRestoreConnectionCommand extends FlowNodeRestoreConnectionCommand{
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public UserTaskRestoreConnectionCommand(EditPart part){
		super(part);
	}
	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands(){
		super.initializeCommands();
		// Do nothing
	}
	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated NOT
	 */
	private void createSequenceFlowFromUserTaskToUserTask_Incoming(GraphElement srcElt,GraphElement targetElt){
		UserTask sourceObject = (UserTask) Utils.getElement(srcElt);
		UserTask targetObject = (UserTask) Utils.getElement(targetElt);
		List edgeObjectList = ((org.eclipse.bpmn2.UserTask) Utils.getDiagramModelObject(srcElt)).getIncoming();
		for(Iterator it = edgeObjectList.iterator();it.hasNext();){
			Object obj = it.next();
			if(obj instanceof SequenceFlow){
				SequenceFlow edgeObject = (SequenceFlow) obj;
				if(targetObject.equals(edgeObject.getTargetRef()) && sourceObject.equals(edgeObject.getSourceRef()) && sourceObject.getOutgoing().contains(edgeObject)
						&& targetObject.getIncoming().contains(edgeObject)){
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, SequenceFlow.class);
					if(!isAlreadyPresent(existing, edgeObject)){
						ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory.createGraphElement(edgeObject);
						if(edge instanceof GraphEdge){
							SequenceFlowEdgeCreationCommand cmd = new SequenceFlowEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}
}