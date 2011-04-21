package org.util;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseListener;

public class DbListener implements ODatabaseListener {

	@Override
	public void onCreate(ODatabase iDatabase) {
		System.out.println("onCreate");
		TransactionThreadVar.clear();
	}

	@Override
	public void onDelete(ODatabase iDatabase) {
		System.out.println("onDelete");
		TransactionThreadVar.clear();
	}

	@Override
	public void onOpen(ODatabase iDatabase) {
		System.out.println("onOpen");
		TransactionThreadVar.clear();
	}

	@Override
	public void onBeforeTxBegin(ODatabase iDatabase) {
		System.out.println("onBeforeTxBegin");
		TransactionThreadVar.clear();
	}

	@Override
	public void onBeforeTxRollback(ODatabase iDatabase) {
		System.out.println("onBeforeTxRollback");
		TransactionThreadVar.clear();
	}

	@Override
	public void onAfterTxRollback(ODatabase iDatabase) {
		System.out.println("onAfterTxRollback");
		TransactionThreadVar.clear();
	}

	@Override
	public void onBeforeTxCommit(ODatabase iDatabase) {
		System.out.println("onBeforeTxCommit");
	}

	@Override
	public void onAfterTxCommit(ODatabase iDatabase) {
		System.out.println("onAfterTxCommit");
		TransactionThreadVar.clear();
	}

	@Override
	public void onClose(ODatabase iDatabase) {
		System.out.println("onClose");
		TransactionThreadVar.clear();
	}

}
