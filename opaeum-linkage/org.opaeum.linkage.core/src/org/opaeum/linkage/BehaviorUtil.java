package org.opaeum.linkage;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public class BehaviorUtil{
	private INakedModelWorkspace workspace;
	public BehaviorUtil(INakedModelWorkspace workspace){
		super();
		this.workspace = workspace;
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
	private boolean requiresExternalInput(INakedActivity origin,INakedActivityNode node){
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
		}else if(node instanceof INakedEmbeddedTask){
			return true;
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
			Collection<INakedAcceptCallAction> acas = getCallActions(o);
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
	private Collection<INakedAcceptCallAction> getCallActions(INakedOperation o){
		Set<INakedAcceptCallAction> callActions = new HashSet<INakedAcceptCallAction>();
		for(INakedElement e:workspace.getDependentElements(o)){
			if(e instanceof INakedAcceptCallAction){
				callActions.add((INakedAcceptCallAction) e);
			}
		}
		return callActions;
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
			Collection<INakedAcceptCallAction> acas = getCallActions(o);
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
		if(replyAction==null){
			return false;
		}
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
		}else if(owner instanceof INakedOperation){
			INakedOperation no = (INakedOperation) owner;
			if(no.isLongRunning() ||owner.hasMultipleConcurrentResults()){
				return true;
			}else{
				Set<? extends INakedBehavior> methods = no.getMethods();
				for(INakedBehavior method:methods){
					if(BehaviorUtil.hasExecutionInstance(method)){
						return true;
					}
				}
			}
			return false;
		}else{
			if(owner instanceof INakedBehavioredClassifier){
				INakedBehavioredClassifier bc = (INakedBehavioredClassifier) owner;
				if(bc.getOwnedBehaviors().size()>0){
					return true;
				}else if(bc.getOperations().size()>0){
					return true;
				}
			}
			return owner instanceof INakedStateMachine || owner.hasMultipleConcurrentResults()
					|| (owner instanceof INakedActivity && ((INakedActivity) owner).getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD);
		}
	}
	public static boolean hasMessageStructure(INakedAction action){
		if(action instanceof INakedEmbeddedTask){
			return true;
		}else if(action instanceof INakedCallAction){
			return hasExecutionInstance(((INakedCallAction) action).getCalledElement());
		}else if(action instanceof INakedAcceptCallAction){
			return hasExecutionInstance(((INakedAcceptCallAction) action).getOperation());
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
			}else if(c instanceof EmulatedCompositionMessageStructure){
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

	public static boolean shouldSurrounWithTry(INakedCallAction node){
		return !node.isLongRunning() && node.hasExceptions() && node.isSynchronous();
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
	public static INakedMessageStructure getMessageStructure(INakedAction action){
		if(action instanceof INakedEmbeddedTask){
			return ((INakedEmbeddedTask) action).getMessageStructure();
		}else if(action instanceof INakedCallAction){
			return ((INakedCallAction) action).getMessageStructure();
		}else if(action instanceof INakedAcceptCallAction){
			return ((INakedAcceptCallAction) action).getOperation().getMessageStructure();
		}else{
			return null;
		}
	}
}
