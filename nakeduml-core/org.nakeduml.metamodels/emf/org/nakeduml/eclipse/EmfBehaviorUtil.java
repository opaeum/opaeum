package org.nakeduml.eclipse;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;

public class EmfBehaviorUtil{
	public static Classifier getContext(Element behavioralElement){
		while(!(behavioralElement instanceof Behavior)){
			behavioralElement = behavioralElement.getOwner();
		}
		if(behavioralElement instanceof Behavior){
			Behavior behavior = (Behavior) behavioralElement;
			if(behavioralElement.getOwner() instanceof Transition || behavioralElement.getOwner() instanceof State){
				return getContext(behavioralElement.getOwner());
			}else if(behavior.getContext() != null){
				return behavior.getContext();
			}
		}
		return null;
	}
	public static Classifier getSelf(Element behavioralElement){
		while(!(behavioralElement instanceof Behavior)){
			behavioralElement = behavioralElement.getOwner();
		}
		if(behavioralElement instanceof Behavior){
			Behavior behavior = (Behavior) behavioralElement;
			if(behavioralElement.getOwner() instanceof Transition || behavioralElement.getOwner() instanceof State){
				return EmfStateMachineUtil.getStateMachine(behavioralElement.getOwner());
			}else if(hasMessageStructure(behavior)){
				return behavior;
			}else {
				return behavior.getContext();
			}
		}
		return null;
	}
	private static boolean hasMessageStructure(Behavior b){
		if(b.getAllAttributes().size()>0 || b instanceof StateMachine){
			return true;
		}else if(b instanceof Activity){
			Activity a = (Activity) b;
			return isLongRunning(a.getNodes());
		}else {
			return false;
		}
	}
	protected static boolean isLongRunning(EList<ActivityNode> nodes){
		boolean is=false;
		for(ActivityNode n:nodes){
			if(n instanceof AcceptEventAction){
				is=true;
			}else if(n instanceof CallBehaviorAction){
				is= hasMessageStructure(((CallBehaviorAction) n).getBehavior());
			}else if(n instanceof StructuredActivityNode){
				is=isLongRunning(((StructuredActivityNode) n).getNodes());
			}
			//TODO check for tasks
			if(is==true){
				return true;
			}
		}
		return false;
	}
	public static void addBehaviorsRecursively(ArrayList<Behavior> behaviors,EList<Classifier> generals){
		for(Classifier c:generals){
			if(c instanceof Class){
				behaviors.addAll(((Class) c).getOwnedBehaviors());
				addBehaviorsRecursively(behaviors, c.getGenerals());
			}
		}
	}
}
