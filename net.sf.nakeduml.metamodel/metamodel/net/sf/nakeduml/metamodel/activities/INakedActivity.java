package net.sf.nakeduml.metamodel.activities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedOperation;

/**
 * 
 */
public interface INakedActivity extends INakedBehavior, INakedTriggerContainer {
	ActivityKind getActivityKind();
	void setActivityKind(ActivityKind kind);
	Collection<INakedActivityVariable> getVariables();

	Set<INakedActivityEdge> getActivityEdges();

	Set<INakedActivityNode> getActivityNodes();

	Set<INakedActivityPartition> getPartitions();

	void setSpecification(INakedOperation operation);

	INakedOperation getSpecification();

	List<INakedActivityNode> getActivityNodesRecursively();

	Collection<INakedActivityNode> getStartNodes();
}