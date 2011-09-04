package org.nakeduml.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nakeduml.runtime.persistence.UmtPersistence;

public class HibernateUmtPersistence extends AbstractHibernatePersistence implements UmtPersistence{
	private Transaction tx;
	int timeout;

	public HibernateUmtPersistence(Session session){
		super(session);
	}

	@Override
	public void beginTransaction(){
		this.tx = super.session.beginTransaction();
		tx.setTimeout(timeout);
		
	}

	@Override
	public boolean isActive(){
		return tx.isActive();
	}

	@Override
	public void rollbackTransaction(){
		tx.rollback();
		
	}

	@Override
	public void commitTransaction(){
		tx.commit();
		
	}

	@Override
	public boolean isRolledBack(){
		return tx.wasRolledBack();
	}

	@Override
	public void setTransactionTimeout(int i){
		this.timeout=i;
	}

	@Override
	public void close(){
		session.close();
		
	}

}
