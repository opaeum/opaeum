package org.util;

import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;

public class Neo4jTransactionEventHandler<T> implements TransactionEventHandler<T> {

	@Override
	public T beforeCommit(TransactionData data) throws Exception {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
		return null;
	}

	@Override
	public void afterCommit(TransactionData data, T state) {
	}

	@Override
	public void afterRollback(TransactionData data, T state) {
	}

}
