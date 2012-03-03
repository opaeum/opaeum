package org.nakeduml.tinker.generator;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.name.NameConverter;

public class TinkerBehaviorUtil {
	public static final OJPathName tinkerAbstractActivityPathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractActivity");
	public static final OJPathName tinkerActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityParameterNode");
	public static final OJPathName tinkerInActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InActivityParameterNode");
	public static final OJPathName tinkerValuePinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ValuePin");
	public static final OJPathName tinkerInputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.InputPin");
	public static final OJPathName tinkerOutputPinPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutputPin");
	public static final OJPathName tinkerOutActivityParameterNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.OutActivityParameterNode");
	public static final OJPathName tinkerActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Action");
	public static final OJPathName tinkerOpaqueActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.OpaqueAction");
	public static final OJPathName tinkerJoinNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNode");
	public static final OJPathName tinkerJoinNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeControlToken");
	public static final OJPathName tinkerJoinNodeObjectTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.JoinNodeObjectToken");
	public static final OJPathName tinkerForkNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNode");
	public static final OJPathName tinkerForkNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeControlToken");
	public static final OJPathName tinkerForkNodeObjectTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ForkNodeObjectToken");
	public static final OJPathName tinkerDecisionNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionNode");
	public static final OJPathName tinkerDecisionControlTokenNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionControlToken");
	public static final OJPathName tinkerDecisionObjectTokenNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.DecisionObjectToken");
	public static final OJPathName tinkerInitialNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.InitialNode");
	public static final OJPathName tinkerMergeNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNode");
	public static final OJPathName tinkerMergeNodeControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeControlToken");
	public static final OJPathName tinkerMergeNodeObjectTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.MergeNodeObjectToken");
	public static final OJPathName tinkerActivityNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityNode");
	public static final OJPathName tinkerActivityEdgePathName = new OJPathName("org.nakeduml.runtime.domain.activity.ActivityEdge");
	public static final OJPathName tinkerControlFlowPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlFlow");
	public static final OJPathName tinkerObjectFlowPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectFlow");
	public static final OJPathName tinkerFinalNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.FinalNode");
	public static final OJPathName tinkerSingleIteratorPathName = new OJPathName("com.tinkerpop.pipes.util.SingleIterator");
	public static final OJPathName tinkerTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.Token");
	public static final OJPathName tinkerControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlToken");
	public static final OJPathName tinkerObjectTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectToken");
	public static final OJPathName tinkerNodeStatusPathName = new OJPathName("org.nakeduml.runtime.domain.activity.NodeStatus");
	public static OJPathName signalPathName = new OJPathName("org.opaeum.runtime.domain.ISignal");
	public static OJPathName tinkerClassifierSignalEvent = new OJPathName("org.nakeduml.runtime.domain.IClassifierSignalEvent");
	public static OJPathName tinkerBaseTinkerBehavioredClassifier = new OJPathName("org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier");
	public static OJPathName tinkerClassifierBehaviorExecutorService = new OJPathName("org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService");
	public static OJPathName tinkerAcceptEventAction = new OJPathName("org.nakeduml.runtime.domain.activity.AcceptEventAction");
	public static OJPathName tinkerSendSignalAction = new OJPathName("org.nakeduml.runtime.domain.activity.SendSignalAction");
	public static OJPathName tinkerSignalPathName = new OJPathName("org.opaeum.runtime.domain.ISignal");
	public static OJPathName tinkerObjectTokenInteratorPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ObjectTokenInterator");
	
	
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
		return new OJPathName(packageName + "." + NameConverter.capitalize(target.getName()));
	}
	public static String activityNodeGetter(INakedActivityNode node) {
		return "get" + NameConverter.capitalize(node.getName());
	}
	public static String outputPinGetterName(INakedOutputPin outputPin) {
		return "get" + NameConverter.capitalize(outputPin.getName()) + "OutputPin";
	}
	public static String inputPinGetter(INakedInputPin inputPin) {
		return "get" + NameConverter.capitalize(inputPin.getName()) + "InputPin";
	}
	
}
