package org.nakeduml.nakeduml.tinker.runtime;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.nakeduml.runtime.domain.BaseTinkerAuditable;
import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.util.TransactionThreadVar;

public class NakedTransactionEventHandler<T> implements TransactionEventHandler<T> {

	Validator validator;
	
	public NakedTransactionEventHandler(Validator validator) {
		super();
		this.validator = validator;
	}

	@Override
	public T beforeCommit(TransactionData data) throws Exception {
		if (!isEmpty(data)) {
			Set<ConstraintViolation<TinkerNode>> constraintViolations = new HashSet<ConstraintViolation<TinkerNode>>();
			TransactionThreadVar.clear();
			GraphDb.incrementTransactionCount();
			List<TinkerCompositionNode> entities = TransactionThreadEntityVar.get();
			for (TinkerCompositionNode entity : entities) {
				TinkerNode tinkerNode = (TinkerNode) entity;
				constraintViolations.addAll(validator.validate(tinkerNode));
				if (!tinkerNode.isTinkerRoot() && entity.getOwningObject() == null) {

					if (entity instanceof BaseTinkerAuditable && ((BaseTinkerAuditable) entity).getDeletedOn().before(new Date())) {
						return null;
					}
					TransactionThreadEntityVar.clear();
					throw new IllegalStateException(String.format("Entity %s %s does not have a composite owner", tinkerNode.getClass().getSimpleName(), tinkerNode.getName()));

				}
			}
			TransactionThreadEntityVar.clear();
			if (!constraintViolations.isEmpty()) {
				throw new IllegalStateException("Constraint violations, need to pass violations along//TODO");
			}
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
