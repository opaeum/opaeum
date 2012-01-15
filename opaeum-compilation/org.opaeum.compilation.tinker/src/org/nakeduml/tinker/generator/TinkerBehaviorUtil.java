package org.nakeduml.tinker.generator;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.name.NameConverter;

public class TinkerBehaviorUtil {
	public static final OJPathName tinkerAbstractActivityPathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractActivity");
	public static final OJPathName tinkerAbstractActionPathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractAction");
	public static final OJPathName tinkerAbstractJoinNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractJoinNode");
	public static final OJPathName tinkerAbstractForkNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractForkNode");
	public static final OJPathName tinkerAbstractDecisionNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractDecisionNode");
	public static final OJPathName tinkerAbstractInitialNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractInitialNode");
	public static final OJPathName tinkerAbstractMergeNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractMergeNode");
	public static final OJPathName tinkerAbstractNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractNode");
	public static final OJPathName tinkerAbstractControlFlowEdgePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractControlFlowEdge");
	public static final OJPathName tinkerAbstractFinalNodePathName = new OJPathName("org.nakeduml.runtime.domain.activity.AbstractFinalNode");
	public static final OJPathName tinkerSingleIteratorPathName = new OJPathName("com.tinkerpop.pipes.util.SingleIterator");
	public static final OJPathName tinkerControlTokenPathName = new OJPathName("org.nakeduml.runtime.domain.activity.ControlToken");
	public static final OJPathName tinkerNodeStatusPathName = new OJPathName("org.nakeduml.runtime.domain.activity.NodeStatus");
	
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
	public static OJPathName actionPathName(INakedActivityNode target) {
		String qualifiedJavaName = target.getMappingInfo().getQualifiedJavaName();
		String packageName = qualifiedJavaName.substring(0, qualifiedJavaName.lastIndexOf(".")).toLowerCase();
		return new OJPathName(packageName + "." + NameConverter.capitalize(target.getName()));
	}
}
