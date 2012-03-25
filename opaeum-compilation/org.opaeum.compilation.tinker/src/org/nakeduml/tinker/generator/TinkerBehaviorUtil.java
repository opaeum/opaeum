package org.nakeduml.tinker.generator;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.name.NameConverter;

public class TinkerBehaviorUtil {
	
	
	public static final OJPathName tinkerAcceptCallEventBlockingQueue = new OJPathName("org.nakeduml.runtime.domain.TinkerAcceptCallEventBlockingQueue");
	public static final OJPathName tinkerIEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.IEvent");
	public static final OJPathName tinkerEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Event");
	public static final OJPathName tinkerSignalEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.SignalEvent");
	public static final OJPathName tinkerISignalEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ISignalEvent");
	public static final OJPathName tinkerCallEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.CallEvent");
	public static final OJPathName tinkerAbstractActivityPathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractActivity");
	public static final OJPathName tinkerActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityParameterNode");
	public static final OJPathName tinkerInActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InActivityParameterNode");
	public static final OJPathName tinkerValuePinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ValuePin");
	public static final OJPathName tinkerInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.InputPin");
	public static final OJPathName tinkerReturnInformationInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReturnInformationInputPin");
	public static final OJPathName tinkerReturnInformationOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReturnInformationOutputPin");
	public static final OJPathName tinkerOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutputPin");
	public static final OJPathName tinkerOutActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutActivityParameterNode");
	public static final OJPathName tinkerActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Action");
	public static final OJPathName tinkerOpaqueActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OpaqueAction");
	public static final OJPathName tinkerReplyActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReplyAction");
	public static final OJPathName tinkerJoinNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNode");
	public static final OJPathName tinkerJoinNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeControlToken");
	public static final OJPathName tinkerJoinNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenKnown");
	public static final OJPathName tinkerJoinNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenUnknown");
	public static final OJPathName tinkerJoinNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenUnknownWithInControlToken");
	public static final OJPathName tinkerJoinNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenKnownWithInControlToken");
	public static final OJPathName tinkerForkNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNode");
	public static final OJPathName tinkerForkNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeControlToken");
	public static final OJPathName tinkerForkNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeObjectTokenKnown");
	public static final OJPathName tinkerForkNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeObjectTokenUnknown");
	public static final OJPathName tinkerDecisionNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionNode");
	public static final OJPathName tinkerDecisionControlTokenNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionControlToken");
	public static final OJPathName tinkerDecisionObjectTokenNodeKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionObjectTokenKnown");
	public static final OJPathName tinkerDecisionObjectTokenNodeUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionObjectTokenUnknown");
	public static final OJPathName tinkerInitialNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InitialNode");
	public static final OJPathName tinkerMergeNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNode");
	public static final OJPathName tinkerMergeNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeControlToken");
	public static final OJPathName tinkerMergeNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenKnown");
	public static final OJPathName tinkerMergeNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenKnownWithInControlToken");
	public static final OJPathName tinkerMergeNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenUnknownWithInControlToken");
	public static final OJPathName tinkerMergeNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenUnknown");
	public static final OJPathName tinkerActivityNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityNode");
	public static final OJPathName tinkerActivityEdgePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityEdge");
	public static final OJPathName tinkerControlFlowPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlFlow");
	public static final OJPathName tinkerObjectFlowKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectFlowKnown");
	public static final OJPathName tinkerObjectFlowUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectFlowUnknown");
	public static final OJPathName tinkerActivityEdgePathNameWithToken = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityEdge<? extends Token>");
	public static final OJPathName tinkerFinalNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.FinalNode");
	public static final OJPathName tinkerSingleIteratorPathName = new OJPathName("com.tinkerpop.pipes.util.SingleIterator");
	public static final OJPathName tinkerTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Token");
	public static final OJPathName tinkerControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlToken");
	public static final OJPathName tinkerObjectTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectToken");
	public static final OJPathName tinkerNodeStatusPathName = new OJPathName("org.nakeduml.runtime.domain.activity.NodeStatus");
	public static final OJPathName signalPathName = new OJPathName("org.opaeum.runtime.domain.ISignal");
	public static final OJPathName tinkerClassifierSignalEvent = new OJPathName("org.nakeduml.runtime.domain.IClassifierSignalEvent");
	public static final OJPathName tinkerClassifierEvent = new OJPathName("org.nakeduml.runtime.domain.IClassifierEvent");
	public static final OJPathName tinkerClassifierCallEvent = new OJPathName("org.nakeduml.runtime.domain.IClassifierCallEvent");
	public static final OJPathName tinkerBaseTinkerBehavioredClassifier = new OJPathName("org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier");
	public static final OJPathName tinkerClassifierBehaviorExecutorService = new OJPathName("org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService");
	public static final OJPathName tinkerAcceptEventAction = new OJPathName("org.nakeduml.runtime.domain.activity.AcceptEventAction");
	public static final OJPathName tinkerAcceptCallAction = new OJPathName("org.nakeduml.runtime.domain.activity.AcceptCallAction");
	public static final OJPathName tinkerSendSignalAction = new OJPathName("org.nakeduml.runtime.domain.activity.SendSignalAction");
	public static final OJPathName tinkerSignalPathName = new OJPathName("org.opaeum.runtime.domain.ISignal");
	public static final OJPathName tinkerObjectTokenInteratorPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectTokenInterator");
	
	
	public static String edgeGetter(INakedActivityEdge edge) {
		return "get" + NameConverter.capitalize(edge.getName());
	}
	public static OJPathName edgePathname(INakedActivityEdge edge) {
		String qualifiedJavaName = edge.getMappingInfo().getQualifiedJavaName();
		String packageName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf(".")).toLowerCase();
		return new OJPathName(packageName + "." + NameConverter.capitalize(edge.getName()));
	}
	public static String actionTargetGetter(INakedActivityEdge edge) {
		return "get" + NameConverter.capitalize(edge.getTarget().getName());
	}
	public static String actionSourceGetter(INakedActivityEdge edge) {
		return "get" + NameConverter.capitalize(edge.getSource().getName());
	}
//	public static OJPathName actionPathName(INakedActivityNode target) {
//		String qualifiedJavaName = target.getMappingInfo().getQualifiedJavaName();
//		String packageName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf(".")).toLowerCase();
//		return new OJPathName(packageName + "." + NameConverter.capitalize(target.getName()));
//	}
	public static OJPathName activityNodePathName(INakedActivityNode target) {
		OJPathName path = OJUtil.packagePathname(target.getNameSpace());
		String packageName = path.toJavaString();
		if (target instanceof INakedPin) {
			return new OJPathName(packageName + "." + pinPathName((INakedPin)target));
		} else  if (target instanceof INakedParameterNode) {
			return new OJPathName(packageName + "." + parameterNodePathName((INakedParameterNode)target));
		} else {
			return new OJPathName(packageName + "." + NameConverter.capitalize(target.getName()));
		}
	}
	public static String activityNodeGetter(INakedActivityNode node) {
		return "get" + activityNodePathName(node).getLast();
	}
	public static String outputPinGetterName(INakedOutputPin outputPin) {
		return "get" + activityNodePathName(outputPin).getLast() + "OutputPin";
	}
	public static String inputPinGetter(INakedInputPin inputPin) {
		return "get" + activityNodePathName(inputPin).getLast() + "InputPin";
	}
	public static String pinPathName(INakedPin inputPin) {
		return NameConverter.capitalize(inputPin.getAction().getName()) + NameConverter.capitalize(inputPin.getName());
	}
	public static String parameterNodePathName(INakedParameterNode inputPin) {
		return NameConverter.capitalize(inputPin.getName());
	}
	public static OJPathName activityEdgePathName(INakedActivityEdge edge) {
		OJPathName path = OJUtil.packagePathname(edge.getNameSpace());
		String packageName = path.toJavaString();
		return new OJPathName(packageName + "." + edgePathName((INakedActivityEdge)edge));
	}
	private static String edgePathName(INakedActivityEdge edge) {
		return NameConverter.capitalize(edge.getName());
	}
	public static String pinActionEdgeName(INakedPin pin) {
		return NameConverter.decapitalize(pin.getAction().getName()) + NameConverter.capitalize(pin.getName());
	}
	public static String eventName(INakedMessageEvent callEvent) {
		return NameConverter.capitalize(callEvent.getName());
	}

	public static INakedAcceptCallAction findCallActionForEventAndClassifier(INakedCallEvent callEvent, INakedBehavioredClassifier behavioredClassifier) {
		for (INakedBehavior behavior : behavioredClassifier.getEffectiveBehaviors()) {
			if (behavior instanceof INakedActivity) {
				INakedActivity activity = (INakedActivity)behavior;
				for (INakedActivityNode node : activity.getActivityNodes()) {
					if (node instanceof INakedAcceptCallAction) {
						INakedAcceptCallAction acceptCallAction = (INakedAcceptCallAction)node;
						for (INakedTrigger trigger : acceptCallAction.getTriggers()) {
							if (trigger.getEvent().equals(callEvent)) {
								return acceptCallAction;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
}
