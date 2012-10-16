package org.opaeum.runtime.jpa;

import java.io.Serializable;
import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.Query;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class AbstractJpaUmtPersistence extends AbstractJpaPersistence implements UmtPersistence{
	@Override
	public void beginTransaction(){
		getEntityManager().getTransaction().begin();
	}
	@Override
	public boolean isActive(){
		return getEntityManager().getTransaction().isActive();
	}
	@Override
	public void rollbackTransaction(){
		getEntityManager().getTransaction().rollback();
	}
	@Override
	public void commitTransaction(){
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
	}
	@Override
	public boolean isRolledBack(){
		return getEntityManager().getTransaction().getRollbackOnly();
	}
	@Override
	public void setTransactionTimeout(int i){
		
	}
	public void close(){
		getEntityManager().close();
	}
	@Override
	public void refresh(IPersistentObject ... ctx) {
		checkTransaction();
		super.refresh(ctx);
		
	}
	@Override
	public <T> T getReference(Class<T> t, Long id) {
		checkTransaction();
		return super.getReference(t, id);
	}
	@Override
	public <T> T find(Class<T> t, Serializable id) {
		checkTransaction();
		return super.find(t, id);
	}
	@Override
	public void persist(Object object) {
		checkTransaction();
		super.persist(object);
	}
	@Override
	public Query createQuery(String q) {
		checkTransaction();
		return super.createQuery(q);
	}
	@Override
	public <T> Collection<T> readAll(Class<T> c) {
		checkTransaction();
		return super.readAll(c);
	}
	@Override
	public void remove(IPersistentObject event) {
		checkTransaction();
		super.remove(event);
	}
	private void checkTransaction() {
		if(!isActive()){
			throw new IllegalStateException("No active transaction");
		}
	}
}
