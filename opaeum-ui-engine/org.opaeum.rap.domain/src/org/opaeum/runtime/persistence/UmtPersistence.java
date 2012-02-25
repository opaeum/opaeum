package org.opaeum.runtime.persistence;



public interface UmtPersistence extends AbstractPersistence{
	void beginTransaction();
	boolean isActive();
	void rollbackTransaction();
	void commitTransaction();
	boolean isRolledBack();
	void setTransactionTimeout(int i);
	void close();
}
