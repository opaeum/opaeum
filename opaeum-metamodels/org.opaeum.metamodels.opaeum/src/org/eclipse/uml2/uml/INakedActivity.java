package org.eclipse.uml2.uml;

import java.util.List;
import java.util.Set;


/**
 * 
 */
public interface INakedActivity extends INakedBehavior, INakedTriggerContainer ,ActivityNodeContainer{
	String getUuid();
	ActivityKind getActivityKind();
	void setActivityKind(ActivityKind kind);

	Set<INakedActivityPartition> getPartitions();

	void setSpecification(INakedOperation operation);

	INakedOperation getSpecification();

	List<INakedActivityNode> getActivityNodesRecursively();
	INakedProperty findEmulatedAttribute(INakedAction node);

}