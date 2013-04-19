package org.opaeum.topcased.activitydiagram.actions;

import java.util.Iterator;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.opaeum.topcased.uml.editor.OpaeumEditor;
import org.topcased.modeler.commands.ChangeBoundsCommand;
import org.topcased.modeler.commands.ChangeGraphElementPresentationCommand;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.uml.activitydiagram.edit.ActionEditPart;
import org.topcased.modeler.utils.Utils;

public class DisplayPins implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private OpaeumEditor editor;
	@Override
	public void run(IAction a){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof ActionEditPart){
				ActionEditPart editPart = (ActionEditPart) element;
				ICreationUtils creationUtils = editor.getActiveConfiguration().getCreationUtils();
				Action action = (Action) editPart.getEObject();
				GraphNode actionNode = (GraphNode) Utils.getGraphElement(editor.getActiveDiagram(), action, true);
				int width = actionNode.getSize().width - 16;
				int height = actionNode.getSize().height - 16;
				if(actionNode != null){
					int ai = 0;
					for(InputPin pin:action.getInputs()){
						Point location = new Point();
						int position;
						location.x = 16 + ai * 80;
						location.y = 16;
						position = PositionConstants.TOP;
						createOrChange((MixedEditDomain) editor.getEditDomain(), creationUtils, actionNode, location, pin, position, editPart);
						ai++;
					}
					int ri = 0;
					for(OutputPin pin:action.getOutputs()){
						Point location = new Point();
						int position;
						location.x = 16 + ri * 80;
						location.y = Math.round(height - 16);
						position = PositionConstants.BOTTOM;
						createOrChange((MixedEditDomain) editor.getEditDomain(), creationUtils, actionNode, location, pin, position, editPart);
						ri++;
					}
					width = Math.max(ai, ri) * 80 + 32;
					Point location = actionNode.getPosition().getCopy();
					execute(editPart,
							new ChangeBoundsCommand(actionNode, new Rectangle(location.x, location.y, Math.max(actionNode.getSize().width, width),
									actionNode.getSize().height)));
				}
			}
		}
	}
	private GraphNode createOrChange(MixedEditDomain mixedEditDomain,ICreationUtils creationUtils,GraphNode actionNode,Point location,Pin pin,int position,
			ActionEditPart editPart){
		GraphNode inputGraph = (GraphNode) Utils.getGraphElement(actionNode, pin, true);
		if(inputGraph == null){
			inputGraph = (GraphNode) creationUtils.createGraphElement(pin);
			execute(editPart, new CreateGraphNodeCommand(mixedEditDomain, inputGraph, actionNode, editPart.getEObject(), location, new Dimension(20, 20), position, null,
					false));
			execute(editPart, new ChangeGraphElementPresentationCommand(inputGraph, "default"));
		}else{
			execute(editPart, new ChangeBoundsCommand(inputGraph, new Rectangle(location.x, location.y, inputGraph.getSize().width, inputGraph.getSize().height)));
		}
		return inputGraph;
	}
	@Override
	public void selectionChanged(IAction action,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction action,IWorkbenchPart targetPart){
		if(targetPart instanceof OpaeumEditor){
			this.editor = (OpaeumEditor) targetPart;
		}
		// TODO Auto-generated method stub
	}
	private void execute(ActionEditPart editPart,Command createGraphNodeCommand){
		editPart.getViewer().getEditDomain().getCommandStack().execute(createGraphNodeCommand);
	}
}
