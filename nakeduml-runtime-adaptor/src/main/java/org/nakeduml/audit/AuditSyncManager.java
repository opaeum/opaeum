package org.nakeduml.audit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Transaction;
import org.hibernate.event.EventSource;

public class AuditSyncManager {
	
	private final Map<Transaction, AuditSync> auditSyncs;

	public AuditSyncManager() {
		auditSyncs = new ConcurrentHashMap<Transaction, AuditSync>();
	}

	public AuditSync get(EventSource session) {
		Transaction transaction = session.getTransaction();
		AuditSync verSync = auditSyncs.get(transaction);
		if (verSync == null) {
			// No worries about registering a transaction twice - a transaction
			// is single thread
			verSync = new AuditSync(this, session, false);
			auditSyncs.put(transaction, verSync);
			transaction.registerSynchronization(verSync);
		}
		return verSync;
	}

	public void remove(Transaction transaction) {
		auditSyncs.remove(transaction);
	}
	
}
