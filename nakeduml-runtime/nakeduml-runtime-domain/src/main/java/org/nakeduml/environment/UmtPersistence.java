package org.nakeduml.environment;

public interface UmtPersistence extends AbstractPersistence{
	void beginTransaction();
	boolean isActive();
	void rollbackTransaction();
	void commitTransaction();
	boolean isRolledBack();
	void setTransactionTimeout(int i);
}
