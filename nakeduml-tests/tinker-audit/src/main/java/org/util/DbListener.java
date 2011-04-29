package org.util;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseListener;

public class DbListener implements ODatabaseListener {

	@Override
	public void onCreate(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onDelete(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onOpen(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onBeforeTxBegin(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxRollback(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onAfterTxRollback(ODatabase iDatabase) {
	}

	@Override
	public void onBeforeTxCommit(ODatabase iDatabase) {
		TransactionThreadVar.clear();
		TransactionAuditThreadVar.clear();
	}

	@Override
	public void onAfterTxCommit(ODatabase iDatabase) {
	}

	@Override
	public void onClose(ODatabase iDatabase) {
	}

}
