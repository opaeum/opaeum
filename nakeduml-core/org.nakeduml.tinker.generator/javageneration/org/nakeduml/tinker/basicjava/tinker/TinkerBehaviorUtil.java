package org.nakeduml.tinker.basicjava.tinker;

import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.name.NameConverter;

public class TinkerBehaviorUtil {
	public static final OJPathName tinkerAbstractActionPathName = new OJPathName("org.nakeduml.tinker.activity.AbstractAction");
	public static final OJPathName tinkerAbstractJoinNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractJoinNode");
	public static final OJPathName tinkerAbstractForkNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractForkNode");
	public static final OJPathName tinkerAbstractDecisionNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractDecisionNode");
	public static final OJPathName tinkerAbstractInitialNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractInitialNode");
	public static final OJPathName tinkerAbstractMergeNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractMergeNode");
	public static final OJPathName tinkerAbstractNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractNode");
	public static final OJPathName tinkerAbstractControlFlowEdgePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractControlFlowEdge");
	public static final OJPathName tinkerAbstractFinalNodePathName = new OJPathName("org.nakeduml.tinker.activity.AbstractFinalNode");
	public static final OJPathName tinkerSingleIteratorPathName = new OJPathName("com.tinkerpop.pipes.SingleIterator");
	public static final OJPathName tinkerControlTokenPathName = new OJPathName("org.nakeduml.tinker.activity.ControlToken");
	public static final OJPathName tinkerNodeStatusPathName = new OJPathName("org.nakeduml.tinker.activity.NodeStatus");
	
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
