package org.opaeum.runtime.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.opaeum.hibernate.domain.AbstractHibernatePersistence;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IPersistentStringEnum;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.Query;
import org.opaeum.runtime.persistence.UmtPersistence;

public class JpaUmtPersistence extends AbstractHibernatePersistence implements UmtPersistence{
	private Transaction tx;
	private int timeout;
	public JpaUmtPersistence(EntityManager entityManager,Environment e){
		super((Session) entityManager.getDelegate(), e);
		if(getSession().getTransaction() != null && getSession().getTransaction().isActive()){
			throw new IllegalStateException("UserManagedTransactionPersistence cannot be used when there is an active transaction");
		}
		getSession().setFlushMode(FlushMode.COMMIT);
	}
	protected void checkActive(){
		if(!isActive()){
			throw new IllegalStateException("No active transaction");
		}
	}
	@Override
	public void beginTransaction(){
		this.tx = super.getSession().beginTransaction();
		tx.setTimeout(timeout);
	}
	@Override
	public boolean isActive(){
		return tx!=null && tx.isActive();
	}
	
	@Override
	public <T extends IPersistentStringEnum>T find(Class<T> t,String id){
		checkActive();
		return super.find(t, id);
	}
	@Override
	public <T>T find(Class<T> t,Long id){
		checkActive();
		return super.find(t, id);
	}
	@Override
	public void persist(Object object){
		checkActive();
		super.persist(object);
	}
	@Override
	public Query createQuery(String q){
		checkActive();
		return super.createQuery(q);
	}
	@Override
	public <T>Collection<T> readAll(Class<T> c){
		checkActive();
		return super.readAll(c);
	}
	@Override
	public void remove(IPersistentObject o){
		checkActive();
		super.remove(o);
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
		this.timeout = i;
		if(tx!=null){
			tx.setTimeout(i);
		}
	}
	@Override
	public void clear(){
		getSession().clear();
	}
	@Override
	public void close(){
		getSession().close();
	}
}
