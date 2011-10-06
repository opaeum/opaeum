package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.emf.extraction.StereotypesHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;

public class EmfBehaviorUtil{
	public static BehavioredClassifier getContext(Element behavioralElement){
		while(!(behavioralElement instanceof Behavior || behavioralElement == null)){
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
		while(!(behavioralElement instanceof Classifier || behavioralElement == null)){
			behavioralElement = behavioralElement.getOwner();
		}
		if(behavioralElement instanceof Behavior){
			Behavior behavior = (Behavior) behavioralElement;
			if(behavioralElement.getOwner() instanceof Transition || behavioralElement.getOwner() instanceof State){
				return EmfStateMachineUtil.getStateMachine(behavioralElement.getOwner());
			}else if(hasMessageStructure(behavior)){
				return behavior;
			}else if(behavior.getContext() != null){
				return behavior.getContext();
			}else{
				return behavior;
			}
		}
		if(behavioralElement instanceof Classifier){
			return (Classifier) behavioralElement;
		}
		return null;
	}
	private static boolean hasMessageStructure(Behavior b){
		if(b.getAllAttributes().size() > 0 || b instanceof StateMachine){
			return true;
		}else if(b instanceof Activity){
			Activity a = (Activity) b;
			return isLongRunning(a.getNodes());
		}else{
			return false;
		}
	}
	protected static boolean isLongRunning(EList<ActivityNode> nodes){
		boolean is = false;
		for(ActivityNode n:nodes){
			if(n instanceof AcceptEventAction){
				is = true;
			}else if(n instanceof CallBehaviorAction){
				CallBehaviorAction cba = (CallBehaviorAction) n;
				is = cba.getBehavior()!=null&& hasMessageStructure(cba.getBehavior());
			}else if(n instanceof StructuredActivityNode){
				is = isLongRunning(((StructuredActivityNode) n).getNodes());
			}
			// TODO check for tasks
			if(is == true){
				return true;
			}
		}
		return false;
	}
	public static void addBehaviorsRecursively(Set<Behavior> behaviors,EList<Classifier> generals){
		for(Classifier c:generals){
			addBehaviors(behaviors, c);
		}
	}
	private static void addBehaviors(Set<Behavior> behaviors,Classifier c){
		if(c instanceof BehavioredClassifier){
			behaviors.addAll(((BehavioredClassifier) c).getOwnedBehaviors());
			addBehaviorsRecursively(behaviors, c.getGenerals());
		}
	}
	private static void addOperationsRecursively(HashSet<Operation> behaviors,EList<? extends Classifier> generals){
		for(Classifier c:generals){
			addOperations(behaviors, c);
		}
	}
	private static void addOperations(HashSet<Operation> behaviors,Classifier c){
		if(c instanceof Class){
			behaviors.addAll(((Class) c).getOperations());
			addOperationsRecursively(behaviors, c.getGenerals());
			addOperationsRecursively(behaviors, ((Class) c).getImplementedInterfaces());
			if(c instanceof Component){
				for(Port port:((Component) c).getOwnedPorts()){
					addOperationsRecursively(behaviors, port.getProvideds());
				}
			}
		}else if(c instanceof Interface){
			behaviors.addAll(((Interface) c).getOperations());
			addOperationsRecursively(behaviors, ((Interface) c).getGenerals());
		}
	}
	public static Collection<Operation> findSpecificationsInScope(Behavior behavior){
		Classifier context = getContext(behavior);
		HashSet<Operation> operations = new HashSet<Operation>();
		addOperations(operations, context);
		return operations;
	}
	public static Collection<Behavior> findBehaviorsInScope(CallBehaviorAction behavior){
		
		BehavioredClassifier context = getContext(behavior);
		Set<Behavior> behaviors = findBehaviorsInScope(context);
		addBehaviors(behaviors, behavior.getActivity());
		return behaviors;
	}
	public static Set<Behavior> findBehaviorsInScope(BehavioredClassifier context){
		HashSet<Behavior> operations = new HashSet<Behavior>();
		addBehaviors(operations, context);
		return operations;
	}
	public static boolean isTask(Operation op){
		return StereotypesHelper.hasStereotype(op, "userresponsibility", "task", "responsibility");
	}
	public static Collection<Activity> getAllOwnedActivities(Class representedClass){
		Collection<Activity> results = new ArrayList<Activity>();
		for(Behavior b:findBehaviorsInScope(representedClass)){
			if(b instanceof Activity){
				results.add((Activity) b);
			}
		}
		return results;
	}
}
