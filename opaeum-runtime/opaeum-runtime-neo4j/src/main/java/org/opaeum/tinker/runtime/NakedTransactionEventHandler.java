package org.opeum.tinker.runtime;

import java.util.Date;
import java.util.List;

import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.opeum.runtime.domain.BaseTinkerAuditable;
import org.opeum.runtime.domain.TinkerCompositionNode;
import org.opeum.runtime.domain.TinkerNode;
import org.util.TransactionThreadEntityVar;
import org.util.TransactionThreadVar;

public class NakedTransactionEventHandler<T> implements TransactionEventHandler<T> {

	@Override
	public T beforeCommit(TransactionData data) throws Exception {
		if (!isEmpty(data)) {
			TransactionThreadVar.clear();
			List<TinkerCompositionNode> entities = TransactionThreadEntityVar.get();
			for (TinkerCompositionNode entity : entities) {
				TinkerNode tinkerNode = (TinkerNode) entity;
				if (!tinkerNode.isTinkerRoot() && entity.getOwningObject() == null) {

					if (entity instanceof BaseTinkerAuditable && ((BaseTinkerAuditable) entity).getDeletedOn().before(new Date())) {
						return null;
					}
					TransactionThreadEntityVar.clear();
					throw new IllegalStateException(String.format("Entity %s must have a composite owner of type %s", tinkerNode.getVertex().getId(), "ahem"));

				}
			}
			TransactionThreadEntityVar.clear();
		}
		return null;
	}

	private boolean isEmpty(TransactionData data) {
		return !data.assignedNodeProperties().iterator().hasNext() && !data.assignedRelationshipProperties().iterator().hasNext()
				&& !data.createdNodes().iterator().hasNext() && !data.createdRelationships().iterator().hasNext() && !data.deletedNodes().iterator().hasNext()
				&& !data.deletedRelationships().iterator().hasNext() && !data.removedNodeProperties().iterator().hasNext()
				&& !data.removedRelationshipProperties().iterator().hasNext();
	}

	@Override
	public void afterCommit(TransactionData data, T state) {

	}

	@Override
	public void afterRollback(TransactionData data, T state) {

	}

}
