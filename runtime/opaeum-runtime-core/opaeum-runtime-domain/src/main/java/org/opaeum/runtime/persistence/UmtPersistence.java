package org.opaeum.runtime.persistence;


/**
 * User Managed Transaction Persistence manages its own transactions. It will throw an exception if it 
 * is started within the context of an existing transaction. It flushes automatically on commit and then closes.
 * It is ideal for batch jobs. After commit this object is invalid and a new instance needs to be created.
 * It does not require a JTA transaction,but will use one if it is available.In the absence of a JTA transaction
 * it will use autocommit on the underlying JDBCconnection
 * @author ampie
 *
 */
public interface UmtPersistence extends AbstractPersistence{
	void beginTransaction();
	boolean isActive();
	void rollbackTransaction();
	void commitTransaction();
	boolean isRolledBack();
	void setTransactionTimeout(int i);
}
