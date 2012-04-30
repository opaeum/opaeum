package org.nakeduml.tinker.generator;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
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
	
	public static final OJPathName tinkerSingleObjectToken = new OJPathName("org.nakeduml.runtime.domain.activity.SingleObjectToken");
	public static final OJPathName tinkerCollectionObjectToken = new OJPathName("org.nakeduml.runtime.domain.activity.CollectionObjectToken");

	public static final OJPathName tinkerOneAddVariableValueAction = new OJPathName("org.nakeduml.runtime.domain.activity.OneAddVariableValueAction");
	public static final OJPathName tinkerManyAddVariableValueAction = new OJPathName("org.nakeduml.runtime.domain.activity.ManyAddVariableValueAction");

	public static final OJPathName tinkerAddVariableValueAction = new OJPathName("org.nakeduml.runtime.domain.activity.AddVariableValueAction");

	public static final OJPathName tinkerOneReadVariableAction = new OJPathName("org.nakeduml.runtime.domain.activity.OneReadVariableAction");
	public static final OJPathName tinkerManyReadVariableAction = new OJPathName("org.nakeduml.runtime.domain.activity.ManyReadVariableAction");

	public static final OJPathName tinkerReadVariableAction = new OJPathName("org.nakeduml.runtime.domain.activity.ReadVariableAction");
	public static final OJPathName tinkerCreateObjectAction = new OJPathName("org.nakeduml.runtime.domain.activity.CreateObjectAction");
	public static final OJPathName tinkerOperationBlockingQueue = new OJPathName("org.nakeduml.runtime.domain.TinkerOperationBlockingQueue");
	public static final OJPathName tinkerTriggerPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Trigger");
	public static final OJPathName tinkerITriggerPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.ITrigger");
	public static final OJPathName tinkerICallEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.ICallEvent");
	public static final OJPathName tinkerIEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.IEvent");
	public static final OJPathName tinkerEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Event");
	public static final OJPathName tinkerSignalEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.SignalEvent");
	public static final OJPathName tinkerISignalEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.ISignalEvent");
	public static final OJPathName tinkerCallEventPathName = new OJPathName("org.nakeduml.runtime.domain.activity.CallEvent");
	public static final OJPathName tinkerAbstractActivityPathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractActivity");
	public static final OJPathName tinkerActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityParameterNode");

	public static final OJPathName tinkerOneInActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneInActivityParameterNode");
	public static final OJPathName tinkerManyInActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyInActivityParameterNode");

	public static final OJPathName tinkerInActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InActivityParameterNode");

	public static final OJPathName tinkerOneValuePinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneValuePin");
	public static final OJPathName tinkerManyValuePinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyValuePin");

	public static final OJPathName tinkerValuePinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ValuePin");

	public static final OJPathName tinkerIInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.IInputPin");
	public static final OJPathName tinkerIOneInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.IOneInputPin");
	public static final OJPathName tinkerIManyInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.IManyInputPin");
	public static final OJPathName tinkerOneInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneInputPin");
	public static final OJPathName tinkerManyInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyInputPin");

	public static final OJPathName tinkerInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.InputPin");

	public static final OJPathName tinkerOneReturnInformationInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneReturnInformationInputPin");
	public static final OJPathName tinkerManyReturnInformationInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyReturnInformationInputPin");

	public static final OJPathName tinkerReturnInformationInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReturnInformationInputPin");

	public static final OJPathName tinkerOneReturnInformationOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneReturnInformationOutputPin");
	public static final OJPathName tinkerManyReturnInformationOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyReturnInformationOutputPin");
	
	public static final OJPathName tinkerReturnInformationOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReturnInformationOutputPin");

	public static final OJPathName tinkerOneOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneOutputPin");
	public static final OJPathName tinkerManyOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyOutputPin");

	public static final OJPathName tinkerOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutputPin");

	public static final OJPathName tinkerOneOutActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneOutActivityParameterNode");
	public static final OJPathName tinkerManyOutActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyOutActivityParameterNode");

	public static final OJPathName tinkerOutActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutActivityParameterNode");
	public static final OJPathName tinkerActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Action");

	public static final OJPathName tinkerCallOperationActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.CallOperationAction");

	public static final OJPathName tinkerManyOpaqueActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyOpaqueAction");
	public static final OJPathName tinkerOneOpaqueActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneOpaqueAction");

	public static final OJPathName tinkerOpaqueActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OpaqueAction");
	public static final OJPathName tinkerAddStructuralFeatureValueAction = new OJPathName("org.nakeduml.runtime.domain.activity.AddStructuralFeatureValueAction");
	public static final OJPathName tinkerReplyActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ReplyAction");
	public static final OJPathName tinkerJoinNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNode");
	public static final OJPathName tinkerJoinNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeControlToken");

	public static final OJPathName tinkerOneJoinNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneJoinNodeObjectTokenKnown");
	public static final OJPathName tinkerManyJoinNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyJoinNodeObjectTokenKnown");

	public static final OJPathName tinkerJoinNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenKnown");

	public static final OJPathName tinkerOneJoinNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneJoinNodeObjectTokenUnknown");
	public static final OJPathName tinkerManyJoinNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyJoinNodeObjectTokenUnknown");

	public static final OJPathName tinkerJoinNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenUnknown");

	public static final OJPathName tinkerOneJoinNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.OneJoinNodeObjectTokenUnknownWithInControlToken");
	public static final OJPathName tinkerManyJoinNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.ManyJoinNodeObjectTokenUnknownWithInControlToken");

	public static final OJPathName tinkerJoinNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenUnknownWithInControlToken");

	public static final OJPathName tinkerOneJoinNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.OneJoinNodeObjectTokenKnownWithInControlToken");
	public static final OJPathName tinkerManyJoinNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.ManyJoinNodeObjectTokenKnownWithInControlToken");

	public static final OJPathName tinkerJoinNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectTokenKnownWithInControlToken");
	public static final OJPathName tinkerForkNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNode");
	public static final OJPathName tinkerForkNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeControlToken");

	public static final OJPathName tinkerOneForkNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneForkNodeObjectTokenKnown");
	public static final OJPathName tinkerManyForkNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyForkNodeObjectTokenKnown");

	public static final OJPathName tinkerForkNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeObjectTokenKnown");

	public static final OJPathName tinkerOneForkNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneForkNodeObjectTokenUnknown");
	public static final OJPathName tinkerManyForkNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyForkNodeObjectTokenUnknown");

	public static final OJPathName tinkerForkNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeObjectTokenUnknown");
	public static final OJPathName tinkerDecisionNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionNode");
	public static final OJPathName tinkerDecisionControlTokenNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionControlToken");

	public static final OJPathName tinkerOneDecisionObjectTokenNodeKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneDecisionObjectTokenKnown");
	public static final OJPathName tinkerManyDecisionObjectTokenNodeKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyDecisionObjectTokenKnown");

	public static final OJPathName tinkerDecisionObjectTokenNodeKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionObjectTokenKnown");

	public static final OJPathName tinkerOneDecisionObjectTokenNodeUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneDecisionObjectTokenUnknown");
	public static final OJPathName tinkerManyDecisionObjectTokenNodeUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyDecisionObjectTokenUnknown");

	public static final OJPathName tinkerDecisionObjectTokenNodeUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionObjectTokenUnknown");
	public static final OJPathName tinkerInitialNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InitialNode");
	public static final OJPathName tinkerMergeNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNode");
	public static final OJPathName tinkerMergeNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeControlToken");

	public static final OJPathName tinkerOneMergeNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneMergeNodeObjectTokenKnown");
	public static final OJPathName tinkerManyMergeNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyMergeNodeObjectTokenKnown");

	public static final OJPathName tinkerMergeNodeObjectTokenKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenKnown");

	public static final OJPathName tinkerOneMergeNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.OneMergeNodeObjectTokenKnownWithInControlToken");
	public static final OJPathName tinkerManyMergeNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.ManyMergeNodeObjectTokenKnownWithInControlToken");

	public static final OJPathName tinkerMergeNodeObjectTokenKnownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenKnownWithInControlToken");

	public static final OJPathName tinkerOneMergeNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.OneMergeNodeObjectTokenUnknownWithInControlToken");
	public static final OJPathName tinkerManyMergeNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.ManyMergeNodeObjectTokenUnknownWithInControlToken");

	public static final OJPathName tinkerMergeNodeObjectTokenUnknownWithInControlToken = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenUnknownWithInControlToken");

	public static final OJPathName tinkerOneMergeNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneMergeNodeObjectTokenUnknown");
	public static final OJPathName tinkerManyMergeNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyMergeNodeObjectTokenUnknown");

	public static final OJPathName tinkerMergeNodeObjectTokenUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectTokenUnknown");
	public static final OJPathName tinkerIActivityNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.interf.IActivityNode");
	public static final OJPathName tinkerActivityNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityNode");
	public static final OJPathName tinkerActivityEdgePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityEdge");
	public static final OJPathName tinkerControlFlowPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlFlow");

	public static final OJPathName tinkerOneObjectFlowKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneObjectFlowKnown");
	public static final OJPathName tinkerManyObjectFlowKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyObjectFlowKnown");

	public static final OJPathName tinkerObjectFlowKnownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectFlowKnown");

	public static final OJPathName tinkerOneObjectFlowUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OneObjectFlowUnknown");
	public static final OJPathName tinkerManyObjectFlowUnknownPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ManyObjectFlowUnknown");

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
	public static final OJPathName tinkerSingleObjectTokenInteratorPathName = new OJPathName("org.nakeduml.runtime.domain.activity.SingleObjectTokenInterator");
	public static final OJPathName tinkerCollectionObjectTokenInteratorPathName = new OJPathName("org.nakeduml.runtime.domain.activity.CollectionObjectTokenInterator");
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
		OJUtil.unlock();
		OJPathName path = OJUtil.packagePathname(edge.getNameSpace());
		OJUtil.lock();
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

	public static String triggerName(INakedTrigger trigger) {
		return NameConverter.capitalize(trigger.getName());
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
