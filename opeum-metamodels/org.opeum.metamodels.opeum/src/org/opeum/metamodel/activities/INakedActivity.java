package org.opeum.metamodel.activities;

import java.util.List;
import java.util.Set;

import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;

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