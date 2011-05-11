package org.util;

import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseListener;

public class DbListener implements ODatabaseListener {

	@Override
	public void onCreate(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onDelete(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onOpen(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onBeforeTxBegin(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxRollback(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionThreadEntityVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onAfterTxRollback(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxCommit(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
//		List<TinkerCompositionNode> entities = TransactionThreadEntityVar.get();;
//		for (TinkerCompositionNode entity : entities) {
//			BaseTinkerAuditable baseAuditable = (BaseTinkerAuditable)entity;
//			TinkerNode tinkerNode = (TinkerNode)entity;
//			if (!tinkerNode.isTinkerRoot() && baseAuditable.getDeletedOn().after(new Date()) && entity.getOwningObject()==null) {
//				TransactionThreadEntityVar.clear();
//				throw new IllegalStateException(String.format("Entity %s must have a composite owner of type %s", tinkerNode.getVertex().getId(), "ahem"));
//			}
//		}
		TransactionThreadEntityVar.clear();
	}

	@Override
	public void onAfterTxCommit(ODatabase iDatabase) {
	}

	@Override
	public void onClose(ODatabase iDatabase) {
	}

}
