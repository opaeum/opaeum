package org.opaeum.topcased.activitydiagram;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLFactory;
import org.topcased.modeler.extensions.ICreationDiagram;
import org.topcased.modeler.uml.activitydiagram.commands.update.CreateActivityParametersCommand;

public class CreateActivityDiagramFromCallBehaviorAction extends Command implements ICreationDiagram{
	private Activity parentActivity;
	private Activity activity;
	private CallBehaviorAction callBehaviorAction;
	private Behavior oldBehavior;
	private CreateActivityParametersCommand createActivityParameters;
	public EObject getLinkedElementWithDiagram(EObject initialObject){
		if(initialObject instanceof CallBehaviorAction){
			callBehaviorAction = (CallBehaviorAction) initialObject;
			parentActivity = getPackageAncestor(callBehaviorAction);
			oldBehavior = callBehaviorAction.getBehavior();
			activity = UMLFactory.eINSTANCE.createActivity();
			activity.setName(callBehaviorAction.getName() + "Impl");
			if(StereotypesHelper.hasStereotype(callBehaviorAction, StereotypeNames.CALL_BUSINES_PROCESS_ACTION)){
				StereotypesHelper.getNumlAnnotation(activity).getDetails().put(StereotypeNames.BUSINES_PROCESS, "");
			}else if(StereotypesHelper.hasStereotype(callBehaviorAction, StereotypeNames.CALL_METHOD_ACTION)){
				StereotypesHelper.getNumlAnnotation(activity).getDetails().put(StereotypeNames.METHOD, "");
			}
			return activity;
		}
		return null;
	}
	public Activity getActivity(){
		return activity;
	}
	private Activity getPackageAncestor(Element pElement){
		Element owner = pElement.getOwner();
		if(owner instanceof Activity){
			return (Activity) owner;
		}else{
			return getPackageAncestor(owner);
		}
	}
	@Override
	public void execute(){
		redo();
	}
	@Override
	public void redo(){
		parentActivity.getOwnedBehaviors().add(activity);
		createActivityParameters = new CreateActivityParametersCommand(callBehaviorAction, activity);
		if(createActivityParameters.canExecute()){
			createActivityParameters.execute();
		}
		callBehaviorAction.setBehavior(activity);
	}
	@Override
	public void undo(){
		callBehaviorAction.setBehavior(oldBehavior);
		parentActivity.getOwnedBehaviors().remove(activity);
		if(createActivityParameters != null){
			createActivityParameters.undo();
		}
	}
}
