package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedAcceptCallAction;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedReplyAction;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class BehaviorUtil{
	Map<INakedOperation,Collection<INakedAcceptCallAction>> callActions;
	public BehaviorUtil(Map<INakedOperation,Collection<INakedAcceptCallAction>> callActions){
		super();
		this.callActions = callActions;
	}
	public static boolean hasMethodsWithStructure(INakedOperation no){
		Set<? extends INakedBehavior> methods = no.getMethods();
		for(INakedBehavior method:methods){
			if(BehaviorUtil.hasExecutionInstance(method)){
				return true;
			}
		}
		return false;
	}
	public boolean requiresExternalInput(INakedActivity a){
		return requiresExternalInput(a, a);
	}
	private boolean requiresExternalInput(INakedActivity a,INakedActivity origin){
		if(a.hasStereotype(StereotypeNames.BUSINES_PROCESS)){
			return true;
		}else{
			List<INakedActivityNode> nodes = a.getActivityNodesRecursively();
			for(INakedActivityNode node:nodes){
				if(requiresExternalInput(origin, node)){
					return true;
				}
			}
		}
		return false;
	}
	public boolean requiresExternalInput(INakedActivity origin,INakedActivityNode node){
		if((node instanceof INakedAcceptEventAction)){
			return true;
		}else if(node instanceof INakedStartClassifierBehaviorAction){
			INakedStartClassifierBehaviorAction a = (INakedStartClassifierBehaviorAction) node;
			if(a.getObject() instanceof INakedBehavioredClassifier){
				INakedBehavioredClassifier b = (INakedBehavioredClassifier) a.getObject();
				if(b.getClassifierBehavior() != null){
					return parameterOwnerRequiresExternalInput(origin, b.getClassifierBehavior());
				}
			}
		}else if(node instanceof INakedCallAction){
			INakedCallAction callAction = (INakedCallAction) node;
			if(!callAction.isSynchronous()){
				// Asynchronous invocations do not require external input for the activity to continue
				return false;
			}else if(callAction.isLongRunning()){
				return true;
			}else{
				return parameterOwnerRequiresExternalInput(origin, callAction.getCalledElement());
			}
		}else if(node instanceof INakedStructuredActivityNode){
			for(INakedActivityNode n:((INakedStructuredActivityNode) node).getActivityNodes()){
				if(requiresExternalInput(origin, n)){
					return true;
				}
			}
		}
		return false;
	}
	private boolean parameterOwnerRequiresExternalInput(INakedActivity origin,IParameterOwner calledElement){
		if(calledElement instanceof INakedStateMachine){
			return true;
		}else if(calledElement instanceof INakedActivity){
			return requiresExternalInput((INakedActivity) calledElement, origin);
		}else if(calledElement instanceof INakedOperation){
			INakedOperation o = (INakedOperation) calledElement;
			return isLongRunning(origin, o);
		}
		return false;
	}
	private boolean isLongRunning(INakedActivity origin,INakedOperation o){
		if(o.isLongRunning()){
			return true;
		}else{
			for(INakedBehavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof INakedActivity){
					// Break recursion here
					if(!origin.equals(method) && requiresExternalInput((INakedActivity) method, origin)){
						return true;
					}
				}else if(method instanceof INakedStateMachine){
					return true;
				}
			}
			Collection<INakedAcceptCallAction> acas = callActions.get(o);
			if(acas != null){
				for(INakedAcceptCallAction aca:acas){
					if(requiresExternalInputBeforeReply(origin, aca, aca.getReplyAction())){
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean requiresExternalInputBeforeReply(INakedActivity origin,INakedActivityNode aca,INakedReplyAction replyAction){
		Set<INakedActivityEdge> allEffectiveOutgoing = aca.getAllEffectiveOutgoing();
		for(INakedActivityEdge edge:allEffectiveOutgoing){
			if(leadsTo(edge, replyAction)){
				// TODO check for joins that have a long running node predecessor
				if(requiresExternalInput(origin, edge.getEffectiveTarget())){
					return true;
				}
				if(requiresExternalInputBeforeReply(origin, edge.getEffectiveTarget(), replyAction)){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isLongRunning(INakedOperation o){
		if(o.isLongRunning()){
			return true;
		}else{
			for(INakedBehavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof INakedActivity){
					if(requiresExternalInput((INakedActivity) method)){
						return true;
					}
				}else if(method instanceof INakedStateMachine){
					return true;
				}
			}
			Collection<INakedAcceptCallAction> acas = callActions.get(o);
			if(acas != null){
				for(INakedAcceptCallAction aca:acas){
					Set<INakedActivityEdge> allEffectiveOutgoing = aca.getAllEffectiveOutgoing();
					for(INakedActivityEdge edge:allEffectiveOutgoing){
						if(leadsTo(edge, aca.getReplyAction()) && requiresExternalInput(aca.getActivity(), edge.getEffectiveTarget())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	private boolean leadsTo(INakedActivityEdge prevEdge,INakedReplyAction replyAction){
		if(prevEdge.getEffectiveTarget().equals(replyAction.getCause())){
			// Loopbacks
			return false;
		}
		INakedActivityNode effectiveTarget = prevEdge.getEffectiveTarget();
		if(effectiveTarget.equals(replyAction)){
			return true;
		}else{
			Set<INakedActivityEdge> allEffectiveOutgoing = effectiveTarget.getAllEffectiveOutgoing();
			for(INakedActivityEdge edge:allEffectiveOutgoing){
				if(leadsTo(edge, replyAction)){
					return true;
				}
			}
			// TODO Auto-generated method stub
		}
		return false;
	}
	public static boolean hasParallelFlows(INakedActivity a){
		return hasParallelFlows(a, a);
	}
	private static boolean hasParallelFlows(INakedActivity a,INakedActivity origin){
		List<INakedActivityNode> nodes = a.getActivityNodesRecursively();
		for(INakedActivityNode node:nodes){
			if(node instanceof INakedControlNode && isForkOrJoin((INakedControlNode) node)){
				// TODO implicit joins or forks on Actions
				return true;
			}else if(node instanceof INakedCallAction){
				INakedCallAction ca = (INakedCallAction) node;
				if(ca.getCalledElement() instanceof INakedStateMachine){
					return true;
				}else if(caledActivityHasParallelFlows(ca, origin)){
					return true;
				}
			}
		}
		return false;
	}
	private static boolean isForkOrJoin(INakedControlNode node){
		return node.getControlNodeType() == ControlNodeType.JOIN_NODE || node.getControlNodeType() == ControlNodeType.FORK_NODE;
	}
	private static boolean caledActivityHasParallelFlows(INakedCallAction ca,INakedActivity origin){
		if(ca.getCalledElement() instanceof INakedActivity){
			return hasParallelFlows((INakedActivity) ca.getCalledElement(), origin);
		}else if(ca.getCalledElement() instanceof INakedOperation){
			INakedOperation o = (INakedOperation) ca.getCalledElement();
			for(INakedBehavior method:o.getMethods()){
				// polymorphic - counts as a process call if ANY of the possible
				// implementations are processes
				if(method instanceof INakedActivity){
					// Break recursion here
					if(!origin.equals(method) && hasParallelFlows((INakedActivity) method, origin)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean hasExecutionInstance(IParameterOwner owner){
		if(owner == null){
			return false;
		}else{
			return owner instanceof INakedStateMachine || owner.hasMultipleConcurrentResults()
					|| (owner instanceof INakedActivity && ((INakedActivity) owner).getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD)
					|| (owner instanceof INakedOperation && ((INakedOperation) owner).isLongRunning());
		}
	}
	public static boolean hasMessageStructure(INakedAction action){
		if(action instanceof INakedEmbeddedTask){
			return true;
		}else if(action instanceof INakedCallAction){
			return hasExecutionInstance(((INakedCallAction) action).getCalledElement());
		}else{
			return false;
		}
	}
	public static INakedClassifier getNearestActualClass(INakedElementOwner ownerElement){
		// Returns the first ownerElement that has an OJClass
		while(ownerElement instanceof INakedElement && !(ownerElement instanceof INakedClassifier && isClassInJava((INakedClassifier) ownerElement))){
			ownerElement = ((INakedElement) ownerElement).getOwnerElement();
		}
		if(ownerElement instanceof INakedClassifier){
			return (INakedClassifier) ownerElement;
		}else{
			return null;
		}
	}
	private static boolean isClassInJava(INakedClassifier c){
		if(c instanceof INakedClassifier){
			if(c instanceof INakedBehavior){
				return BehaviorUtil.hasExecutionInstance((IParameterOwner) c);
			}else if(c instanceof INakedAssociation){
				return ((INakedAssociation) c).isClass();
			}else if(c instanceof MessageStructureImpl){
				return true;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	public static boolean hasLoopBack(INakedActivity a){
		List<INakedActivityNode> activityNodesRecursively = a.getActivityNodesRecursively();
		for(INakedActivityNode node:activityNodesRecursively){
			if(flowsIntoSelf(node, node)){
				return true;
			}
		}
		return false;
	}
	private static boolean flowsIntoSelf(INakedActivityNode node1,INakedActivityNode node2){
		Set<INakedActivityEdge> outgoing = node2.getAllEffectiveOutgoing();
		for(INakedActivityEdge successor:outgoing){
			if(successor.getEffectiveTarget() == node1){
				return true;
			}else if(flowsIntoSelf(node1, successor.getEffectiveTarget())){
				return true;
			}
		}
		return false;
	}
	public static boolean mustBeStoredOnActivity(INakedExpansionNode node){
		return hasExecutionInstance(node.getActivity()) && node.isOutputElement() && node.getExpansionRegion().getOwnerElement() instanceof INakedActivity;
	}
	public static boolean mustBeStoredOnActivity(INakedActivityVariable var){
		return hasExecutionInstance(var.getActivity()) && var.getOwnerElement() instanceof INakedActivity;
	}
	public static boolean mustBeStoredOnActivity(INakedAction action){
		return hasExecutionInstance(action.getActivity()) && hasMessageStructure(action) && action.getOwnerElement() instanceof INakedActivity;
	}
	public static boolean mustBeStoredOnActivity(INakedOutputPin node){
		if(hasExecutionInstance(node.getActivity()) && node.getAction().getOwnerElement() instanceof INakedActivity){
			if(node.getOwnerElement() instanceof INakedCallAction){
				INakedCallAction callAction = (INakedCallAction) node.getOwnerElement();
				if(BehaviorUtil.hasMessageStructure(callAction)){
					// Results stored on the entity representing the message,
					// don't implement this outputpin
					return false;
				}else{
					return true;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	public static boolean shouldSurrounWithTry(INakedCallAction node){
		return !node.isLongRunning() && node.hasExceptions();
	}
	public static boolean isEffectiveFinalNode(INakedActivityNode node2){
		boolean hasExceptionHandler = node2 instanceof INakedAction && ((INakedAction) node2).getHandlers().size() > 0;
		return node2.getAllEffectiveOutgoing().isEmpty() && node2.getOwnerElement() instanceof INakedActivity && !hasExceptionHandler;
	}
	public static boolean isRestingNode(INakedActivityNode n){
		if(n instanceof INakedPin){
			return false;
		}else if(n instanceof INakedStructuredActivityNode){
			INakedStructuredActivityNode s = (INakedStructuredActivityNode) n;
			for(INakedActivityNode child:s.getActivityNodes()){
				if(isRestingNode(child)){
					return true;
				}
			}
			return false;
		}else if(BehaviorUtil.isEffectiveFinalNode(n)){
			return true;
		}else if(n instanceof INakedAction){
			return ((INakedAction) n).isLongRunning();
		}else if(n instanceof INakedParameterNode){
			return ((INakedParameterNode) n).getParameter().isResult();
		}else if(n instanceof INakedControlNode){
			INakedControlNode cNode = (INakedControlNode) n;
			ControlNodeType cNodeType = cNode.getControlNodeType();
			boolean flowFinalNode = cNodeType.isFlowFinalNode() && cNode.getOwnerElement() instanceof INakedActivity;// TODO check if the
																														// owner has
																														// multiple
																														// concurrent flows
																														// has parallel
																														// flows
			return cNodeType.isActivityFinalNode() || flowFinalNode || cNodeType.isJoinNode();
		}else{
			return false;
		}
	}
}
