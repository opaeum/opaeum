package net.sf.nakeduml.metamodel.activities;

import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedOperation;

/**
 * 
 */
public interface INakedActivity extends INakedBehavior, INakedTriggerContainer ,ActivityNodeContainer{
	ActivityKind getActivityKind();
	void setActivityKind(ActivityKind kind);

	Set<INakedActivityPartition> getPartitions();

	void setSpecification(INakedOperation operation);

	INakedOperation getSpecification();

	List<INakedActivityNode> getActivityNodesRecursively();

}