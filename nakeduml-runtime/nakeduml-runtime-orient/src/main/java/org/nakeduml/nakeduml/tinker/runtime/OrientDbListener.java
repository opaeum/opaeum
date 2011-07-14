package org.nakeduml.nakeduml.tinker.runtime;

import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.domain.BaseTinkerAuditable;
import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;
import org.util.TransactionThreadVar;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseListener;

public class OrientDbListener implements ODatabaseListener {

	@Override
	public void onCreate(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onDelete(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onOpen(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onBeforeTxBegin(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxRollback(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onAfterTxRollback(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxCommit(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		GraphDb.incrementTransactionCount();
		List<TinkerCompositionNode> entities = TransactionThreadEntityVar.get();
		for (TinkerCompositionNode entity : entities) {
			TinkerNode tinkerNode = (TinkerNode) entity;
			if (!tinkerNode.isTinkerRoot() && entity.getOwningObject() == null) {

				if (entity instanceof BaseTinkerAuditable && ((BaseTinkerAuditable) entity).getDeletedOn().before(new Date())) {
					return;
				}
				TransactionThreadEntityVar.clear();
				throw new IllegalStateException(String.format("Entity %s must have a composite owner of type %s", tinkerNode.getVertex().getId(), "ahem"));
				
			}
		}
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onAfterTxCommit(ODatabase iDatabase) {
	}

	@Override
	public void onClose(ODatabase iDatabase) {
	}

}
