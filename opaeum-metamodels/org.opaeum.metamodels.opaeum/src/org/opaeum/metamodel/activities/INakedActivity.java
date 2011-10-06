package org.opaeum.metamodel.activities;

import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;

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