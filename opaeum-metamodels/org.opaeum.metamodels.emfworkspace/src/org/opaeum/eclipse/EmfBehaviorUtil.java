package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EmfBehaviorUtil{
	public static boolean canContainStandaloneTasks(Classifier selectedObject){
		return (selectedObject instanceof Class && EmfClassifierUtil.isBusinessRole(selectedObject))
				|| (selectedObject instanceof Actor && EmfClassifierUtil.isBusinessActor(selectedObject))
				|| (selectedObject instanceof Component && EmfClassifierUtil.isBusinessComponent(selectedObject));
	}
	public static Parameter getLinkedParameter(Parameter p){
		if(p.getOwner() instanceof Behavior){
			Behavior b = (Behavior) p.getOwner();
			if(b.getSpecification() instanceof Operation){
				Operation o = (Operation) b.getSpecification();
				if(o.getOwnedParameters().size() == b.getOwnedParameters().size()){
					return o.getOwnedParameters().get(b.getOwnedParameters().indexOf(p));
				}else{
					return null;
				}
			}else{
				return null;
			}
		}else{
			return p;
		}
	}
	public static BehavioredClassifier getContext(Element behavioralElement){
		while(!(behavioralElement instanceof Behavior || behavioralElement == null)){
			behavioralElement = (Element)EmfElementFinder.getContainer(behavioralElement);
		}
		if(behavioralElement instanceof Behavior){
			Behavior behavior = (Behavior) behavioralElement;
			if(behavior.getOwner() instanceof Transition || behavior.getOwner() instanceof State){
				return getContext(behavior.getOwner());
			}else if(behavior.getContext() != null){
				return behavior.getContext();
			}
		}
		return null;
	}
	public static Classifier getSelf(Element behavioralElement){
		while(!(behavioralElement instanceof Classifier || behavioralElement == null)){
			behavioralElement = (Element) EmfElementFinder.getContainer(behavioralElement);
		}
		if(behavioralElement instanceof Behavior){
			Behavior behavior = (Behavior) behavioralElement;
			if(behavioralElement.getOwner() instanceof Transition || behavioralElement.getOwner() instanceof State){
				return EmfStateMachineUtil.getStateMachine(behavioralElement.getOwner());
			}else if(hasExecutionInstance(behavior)){
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
	private static boolean hasMultipleConcurrentResults(List<Parameter> p){
		boolean outFound = false;
		for(Parameter parameter:p){
			if(parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL || parameter.getDirection() == ParameterDirectionKind.OUT_LITERAL
					|| parameter.getDirection() == ParameterDirectionKind.RETURN_LITERAL && !parameter.isException()){
				if(outFound){
					return true;
				}
				outFound = true;
			}
		}
		return false;
	}
	public static boolean hasExecutionInstance(Behavior b){
		if(isProcess(b) || isStandaloneTask(b)){
			return true;
		}else if(b.getAllAttributes().size() > 0 || b.getOperations().size() > 0 || b instanceof StateMachine
				|| hasMultipleConcurrentResults(b.getOwnedParameters())){
			return true;
		}else if(b instanceof Activity){
			Activity a = (Activity) b;
			return isLongRunning(a.getNodes());
		}else{
			return false;
		}
	}
	public static boolean isStandaloneTask(Behavior b){
		return StereotypesHelper.hasStereotype(b, StereotypeNames.STANDALONE_SCREENFLOW_TASK,StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
	}
	protected static boolean isLongRunning(EList<ActivityNode> nodes){
		boolean is = false;
		for(ActivityNode n:nodes){
			if(n instanceof AcceptEventAction){
				is = true;
			}else if(n instanceof CallBehaviorAction){
				CallBehaviorAction cba = (CallBehaviorAction) n;
				is = cba.getBehavior() != null && hasExecutionInstance(cba.getBehavior());
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
	static void addBehaviors(Set<Behavior> behaviors,Classifier c){
		if(c instanceof BehavioredClassifier){
			behaviors.addAll(((BehavioredClassifier) c).getOwnedBehaviors());
			addBehaviorsRecursively(behaviors, c.getGenerals());
		}
	}
	private static void addPotentialSpecificationsRecursively(TreeSet<Operation> behaviors,EList<? extends Classifier> generals){
		for(Classifier c:generals){
			addPotentialSpecifications(behaviors, c);
		}
	}
	private static void addPotentialSpecifications(TreeSet<Operation> behaviors,Classifier c){
		if(c instanceof Class){
			behaviors.addAll(((Class) c).getOperations());
			addPotentialSpecificationsRecursively(behaviors, c.getGenerals());
			addPotentialSpecificationsRecursively(behaviors, ((Class) c).getImplementedInterfaces());
			if(c instanceof Component){
				for(Port port:((Component) c).getOwnedPorts()){
					addPotentialSpecificationsRecursively(behaviors, port.getProvideds());
				}
			}
		}else if(c instanceof Interface){
			behaviors.addAll(((Interface) c).getOperations());
			addPotentialSpecificationsRecursively(behaviors, ((Interface) c).getGenerals());
		}
	}
	public static Collection<Operation> findSpecificationsInScope(Behavior behavior){
		Classifier context = getContext(behavior);
		TreeSet<Operation> operations = new TreeSet<Operation>(new ElementComparator());
		addPotentialSpecifications(operations, context);
		return operations;
	}
	public static Set<Behavior> getEffectiveBehaviors(BehavioredClassifier context){
		TreeSet<Behavior> operations = new TreeSet<Behavior>(new ElementComparator());
		addBehaviors(operations, context);
		return operations;
	}
	@Deprecated
	public static boolean isTask(Operation op){
		return StereotypesHelper.hasStereotype(op, "userresponsibility", "task", "responsibility");
	}
	public static Collection<Activity> getAllOwnedActivities(Class representedClass){
		Collection<Activity> results = new ArrayList<Activity>();
		for(Behavior b:getEffectiveBehaviors(representedClass)){
			if(b instanceof Activity){
				results.add((Activity) b);
			}
		}
		return results;
	}
	public static boolean isClassifierBehavior(Behavior behavior){
		return behavior.getContext() != null && behavior.getContext().getClassifierBehavior() == behavior;
	}
	public static boolean isHumanTrigger(Trigger t){
		Stereotype st = StereotypesHelper.getStereotype(t, StereotypeNames.TRIGGER);
		if(st != null){
			return Boolean.TRUE.equals(t.getValue(st, "isHumanTrigger"));
		}
		return false;
	}
	public static boolean hasMessageStructure(CallAction node){
		if(node instanceof CallOperationAction){
			return hasExecutionInstance(((CallOperationAction) node).getOperation());
		}else if(node instanceof CallBehaviorAction){
			return hasExecutionInstance(((CallBehaviorAction) node).getBehavior());
		}
		return false;
	}
	public static boolean requiresExternalInput(Activity a){
		return requiresExternalInput(a, a);
	}
	private static boolean requiresExternalInput(Activity a,Activity origin){
		if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINES_PROCESS)){
			return true;
		}else{
			Collection<ActivityNode> nodes = EmfActivityUtil.getActivityNodesRecursively(a);
			for(ActivityNode node:nodes){
				if(requiresExternalInput(origin, node)){
					return true;
				}
			}
		}
		return false;
	}
	private static boolean requiresExternalInput(Activity origin,ActivityNode node){
		if((node instanceof AcceptEventAction)){
			return true;
		}else if(node instanceof StartClassifierBehaviorAction){
			StartClassifierBehaviorAction a = (StartClassifierBehaviorAction) node;
			if(a.getObject() instanceof BehavioredClassifier){
				BehavioredClassifier b = (BehavioredClassifier) a.getObject();
				if(b.getClassifierBehavior() != null){
					return parameterOwnerRequiresExternalInput(origin, b.getClassifierBehavior());
				}
			}
		}else if(EmfActionUtil.isEmbeddedTask(node)){
			return true;
		}else if(node instanceof CallAction){
			CallAction callAction = (CallAction) node;
			if(!callAction.isSynchronous()){
				// Asynchronous invocations do not require external input for the activity to continue
				return false;
			}else if(callAction instanceof CallBehaviorAction){
				return parameterOwnerRequiresExternalInput(origin, ((CallBehaviorAction) callAction).getBehavior());
			}else{
				return parameterOwnerRequiresExternalInput(origin, ((CallOperationAction) callAction).getOperation());
			}
		}else if(node instanceof StructuredActivityNode){
			for(ActivityNode n:((StructuredActivityNode) node).getContainedNodes()){
				if(requiresExternalInput(origin, n)){
					return true;
				}
			}
		}
		return false;
	}
	private static boolean parameterOwnerRequiresExternalInput(Activity origin,NamedElement calledElement){
		if(calledElement instanceof StateMachine){
			return true;
		}else if(calledElement instanceof Activity){
			return requiresExternalInput((Activity) calledElement, origin);
		}else if(calledElement instanceof Operation){
			Operation o = (Operation) calledElement;
			return isLongRunning(origin, o);
		}
		return false;
	}
	private static boolean isLongRunning(Activity origin,Operation o){
		if(isMarkedAsLongRunning(o)){
			return true;
		}else{
			for(Behavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof Activity){
					// Break recursion here
					if(!origin.equals(method) && requiresExternalInput((Activity) method, origin)){
						return true;
					}
				}else if(method instanceof StateMachine){
					return true;
				}
			}
			Collection<AcceptCallAction> acas = getCallActions(o);
			if(acas != null){
				for(AcceptCallAction aca:acas){
					if(requiresExternalInputBeforeReply(origin, aca, EmfActionUtil.getReplyAction(aca))){
						return true;
					}
				}
			}
		}
		return false;
	}
	private static Collection<AcceptCallAction> getCallActions(Operation o){
		Set<AcceptCallAction> callActions = new TreeSet<AcceptCallAction>(new ElementComparator());
		for(Element e:EmfElementFinder.getDependentElements(o)){
			if(e instanceof CallEvent){
				Set<Element> dependentElements = EmfElementFinder.getDependentElements(e);
				for(Element element:dependentElements){
					if(element instanceof Trigger && element.getOwner() instanceof AcceptCallAction){
						callActions.add((AcceptCallAction) element);
					}
				}
			}
		}
		return callActions;
	}
	private static boolean requiresExternalInputBeforeReply(Activity origin,ActivityNode aca,ReplyAction replyAction){
		Set<ActivityEdge> allEffectiveOutgoing = EmfActivityUtil.getAllEffectiveOutgoing(aca);
		for(ActivityEdge edge:allEffectiveOutgoing){
			if(leadsTo(edge, replyAction)){
				// TODO check for joins that have a long running node predecessor
				if(requiresExternalInput(origin, EmfActivityUtil.getEffectiveTarget(edge))){
					return true;
				}
				if(requiresExternalInputBeforeReply(origin, EmfActivityUtil.getEffectiveTarget(edge), replyAction)){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isLongRunning(Behavior o){
		if(isProcess(o) || isStandaloneTask(o)){
			return true;
		}else if(o instanceof Activity){
			return requiresExternalInput((Activity) o);
		}else{
			return false;
		}
	}
	public static boolean isLongRunning(Operation o){
		if(isMarkedAsLongRunning(o)){
			return true;
		}else{
			for(Behavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof Activity){
					if(requiresExternalInput((Activity) method)){
						return true;
					}
				}else if(method instanceof StateMachine){
					return true;
				}
			}
			Collection<AcceptCallAction> acas = getCallActions(o);
			if(acas != null){
				for(AcceptCallAction aca:acas){
					Set<ActivityEdge> allEffectiveOutgoing = EmfActivityUtil.getAllEffectiveOutgoing(aca);
					for(ActivityEdge edge:allEffectiveOutgoing){
						if(leadsTo(edge, EmfActionUtil.getReplyAction(aca))
								&& requiresExternalInput(aca.getActivity(), EmfActivityUtil.getEffectiveTarget(edge))){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	private static boolean leadsTo(ActivityEdge prevEdge,ReplyAction replyAction){
		if(replyAction == null){
			return false;
		}
		if(EmfActivityUtil.getEffectiveTarget(prevEdge).equals(EmfActionUtil.getCause(replyAction))){
			// Loopbacks
			return false;
		}
		ActivityNode effectiveTarget = EmfActivityUtil.getEffectiveTarget(prevEdge);
		if(effectiveTarget.equals(replyAction)){
			return true;
		}else{
			Set<ActivityEdge> allEffectiveOutgoing = EmfActivityUtil.getAllEffectiveOutgoing(effectiveTarget);
			for(ActivityEdge edge:allEffectiveOutgoing){
				if(leadsTo(edge, replyAction)){
					return true;
				}
			}
			// TODO Auto-generated method stub
		}
		return false;
	}
	public static boolean hasParallelFlows(Activity a){
		return hasParallelFlows(a, a);
	}
	private static boolean hasParallelFlows(Activity a,Activity origin){
		Collection<ActivityNode> nodes = EmfActivityUtil.getActivityNodesRecursively(a);
		for(ActivityNode node:nodes){
			if(node instanceof ControlNode && isForkOrJoin((ControlNode) node)){
				// TODO implicit joins or forks on Actions
				return true;
			}else if(node instanceof CallAction){
				CallAction ca = (CallAction) node;
				if(ca instanceof CallBehaviorAction && ((CallBehaviorAction) ca).getBehavior() instanceof StateMachine){
					return true;
				}else if(caledActivityHasParallelFlows(ca, origin)){
					return true;
				}
			}
		}
		return false;
	}
	private static boolean isForkOrJoin(ControlNode node){
		return node instanceof JoinNode || node instanceof ForkNode;
	}
	private static boolean caledActivityHasParallelFlows(CallAction ca,Activity origin){
		if(ca instanceof CallBehaviorAction && ((CallBehaviorAction) ca).getBehavior() instanceof Activity){
			return hasParallelFlows((Activity) ((CallBehaviorAction) ca).getBehavior(), origin);
		}else if(ca instanceof CallOperationAction){
			Operation o = (Operation) ((CallOperationAction) ca).getOperation();
			for(Behavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof Activity){
					// Break recursion here
					if(!origin.equals(method) && hasParallelFlows((Activity) method, origin)){
						return true;
					}
				}
			}
		}
		return false;
	}
	private static boolean isMarkedAsLongRunning(Operation oper){
		if(isResponsibility(oper)){
			return true;
		}else if(StereotypesHelper.hasStereotype(oper, StereotypeNames.OPERATION)){
			Stereotype stereotype = StereotypesHelper.getStereotype(oper, StereotypeNames.OPERATION);
			return Boolean.TRUE.equals(oper.getValue(stereotype, "isLongRunning"));
		}
		return false;
	}
	public static boolean hasExecutionInstance(Operation no){
		if(isMarkedAsLongRunning(no) || hasMultipleConcurrentResults(no.getOwnedParameters())){
			return true;
		}else{
			EList<Behavior> methods = no.getMethods();
			for(Behavior method:methods){
				if(hasExecutionInstance(method)){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasLoopBack(Activity a){
		Collection<ActivityNode> activityNodesRecursively = EmfActivityUtil.getActivityNodesRecursively(a);
		for(ActivityNode node:activityNodesRecursively){
			if(flowsIntoSelf(node, node)){
				return true;
			}
		}
		return false;
	}
	private static boolean flowsIntoSelf(ActivityNode node1,ActivityNode node2){
		Set<ActivityEdge> outgoing = EmfActivityUtil.getAllEffectiveOutgoing(node2);
		for(ActivityEdge successor:outgoing){
			if(EmfActivityUtil.getEffectiveTarget(successor) == node1){
				return true;
			}else if(flowsIntoSelf(node1, EmfActivityUtil.getEffectiveTarget(successor))){
				return true;
			}
		}
		return false;
	}
	public static boolean shouldSurrounWithTry(CallAction node){
		return !EmfActionUtil.isLongRunning(node) && EmfActionUtil.hasExceptions(node) && node.isSynchronous();
	}
	public static boolean isEffectiveFinalNode(ActivityNode node2){
		boolean hasExceptionHandler = node2 instanceof Action && ((Action) node2).getHandlers().size() > 0;
		return EmfActivityUtil.getAllEffectiveOutgoing(node2).isEmpty() && node2.getActivity() != null && !hasExceptionHandler;
	}
	public static boolean isRestingNode(ActivityNode n){
		if(n instanceof Pin){
			return false;
		}else if(n instanceof StructuredActivityNode){
			StructuredActivityNode s = (StructuredActivityNode) n;
			for(ActivityNode child:s.getContainedNodes()){
				if(isRestingNode(child)){
					return true;
				}
			}
			return false;
		}else if(isEffectiveFinalNode(n)){
			return true;
		}else if(n instanceof Action){
			return EmfActionUtil.isLongRunning((Action) n);
		}else if(n instanceof ActivityParameterNode){
			return ((ActivityParameterNode) n).getParameter().getDirection() == ParameterDirectionKind.RETURN_LITERAL;
		}else if(n instanceof ControlNode){
			ControlNode cNode = (ControlNode) n;
			boolean flowFinalNode = cNode instanceof FlowFinalNode && cNode.getActivity() != null;// TODO check if the
			// owner has
			// multiple
			// concurrent flows
			// has parallel
			// flows
			return cNode instanceof ActivityFinalNode || flowFinalNode || cNode instanceof JoinNode;
		}else{
			return false;
		}
	}
	public static boolean isComplectSynchronousMethod(Behavior behavior){
		return behavior instanceof Activity && hasExecutionInstance(behavior) && !requiresExternalInput((Activity) behavior);
	}
	public static List<Parameter> getArgumentParameters(NamedElement parameterOwner){
		List<Parameter> result = new ArrayList<Parameter>();
		for(Parameter parameter:getParameters(parameterOwner)){
			if(parameter.getDirection() == ParameterDirectionKind.IN_LITERAL || parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL){
				result.add(parameter);
			}
		}
		return result;
	}
	private static List<Parameter> getParameters(NamedElement parameterOwner){
		if(parameterOwner instanceof Behavior){
			return ((Behavior) parameterOwner).getOwnedParameters();
		}else if(parameterOwner instanceof Operation){
			return ((Operation) parameterOwner).getOwnedParameters();
		}
		return Collections.emptyList();
	}
	public static boolean isProcess(Behavior o){
		if(o instanceof StateMachine){
			return true;
		}else if(o instanceof Activity){
			if(StereotypesHelper.hasStereotype(o, StereotypeNames.BUSINES_PROCESS)){
				return true;
			}else if(StereotypesHelper.hasStereotype(o, StereotypeNames.METHOD)){
				return false;
			}else{
				return requiresExternalInput((Activity) o);
			}
		}else{
			return false;
		}
	}
	public static boolean isResponsibility(BehavioralFeature specification){
		return StereotypesHelper.hasStereotype(specification, StereotypeNames.RESPONSIBILITY)
				|| StereotypesHelper.hasStereotype(specification.getOwner(), StereotypeNames.BUSINESS_SERVICE);
	}
	public static List<Parameter> getResultParameters(NamedElement operation){
		List<Parameter> result = new ArrayList<Parameter>();
		for(Parameter parameter:getParameters(operation)){
			if((parameter.getDirection() == ParameterDirectionKind.OUT_LITERAL
					|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL || parameter.getDirection() == ParameterDirectionKind.RETURN_LITERAL)){
				result.add(parameter);
			}
		}
		return result;
	}
	public static Parameter getReturnParameter(NamedElement operation){
		for(Parameter parameter:getParameters(operation)){
			if(parameter.getDirection() == ParameterDirectionKind.RETURN_LITERAL){
				return parameter;
			}
		}
		return null;
	}
	public static boolean isArgument(Parameter parameter){
		return parameter.getDirection() == ParameterDirectionKind.IN_LITERAL
				|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL;
	}
	public static boolean hasSuperClass(Behavior umlStateMachine){
		return (umlStateMachine.getSpecification() != null)
				|| (umlStateMachine.getGenerals().size() > 0 && umlStateMachine.eClass().isInstance(umlStateMachine.getGenerals().get(0)))
				|| (umlStateMachine.getRedefinedBehaviors().size() > 0 && umlStateMachine.eClass().isInstance(
						umlStateMachine.getRedefinedBehaviors().get(0)));
	}
	public static boolean hasSuperClass(Action a){
		return a.getRedefinedNodes().size()>0 && a.eClass().isInstance(a.getRedefinedNodes().get(0));
	}
}
