package org.opaeum.runtime.jpa;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.opaeum.hibernate.domain.AbstractHibernatePersistence;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.hibernate.domain.SessionAttachment;
import org.opaeum.runtime.domain.ExceptionAnalyser;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.event.ChangedEntity;

public class JpaConversationalPersistence extends AbstractHibernatePersistence implements ConversationalPersistence{
	public JpaConversationalPersistence(EntityManager entityManager,Environment e){
		super((Session) entityManager.getDelegate(), e);
		getSession().setFlushMode(FlushMode.MANUAL);
	}
	@Override
	public void close(){
		getSession().close();
	}
	@Override
	public void flush(){
		Transaction tx = null;
		try{
			if(getSession().getTransaction().isActive()){
				// Assume it is managed from either the client code or the container
			}else{
				// Need to ensure that at least the flush is transactional,in case it fails in the middle of the flush
				tx = getSession().beginTransaction();
			}
		}catch(Exception e){
			// May not be a transaction availabl, as in a non-jee thread in a container.Just leave tx null
		}
		try{
			getSession().flush();
		}catch(Exception e){
			if(tx != null){
				// Need to ensure that at least the flush is transactional,in case it fails in the middle of the flush
				tx.rollback();
				getSession().close();
			}
			throwRootCause(e);
		}
		if(tx != null){
			try{
				tx.commit();
			}catch(Exception e){
				// Need to disable the session - it is now invalid
				getSession().close();
				throwRootCause(e);
			}
		}
	}
	protected void throwRootCause(Exception e){
		ExceptionAnalyser ea = new ExceptionAnalyser(e);
		ea.throwRootCause();
	}
	@Override
	public Map<ChangedEntity,IPersistentObject> synchronizeWithDatabaseAndFindConflicts(){
		SessionAttachment sa = EventDispatcher.sessionAttachments.get(getSession());
		if(sa != null){
			return sa.synchronizeWithDatabaseAndFindConflicts();
		}
		return Collections.emptyMap();
	}
	@Override
	public void overwriteConflictsFromDatabase(Map<ChangedEntity,IPersistentObject> changes){
		SessionAttachment sa = EventDispatcher.sessionAttachments.get(getSession());
		if(sa != null){
			Collection<IPersistentObject> values = changes.values();
			refresh((IPersistentObject[]) values.toArray(new IPersistentObject[values.size()]));
		}
	}
	@Override
	public void overwriteDatabaseWithConflicts(Map<ChangedEntity,IPersistentObject> changes){
		SessionAttachment sa = EventDispatcher.sessionAttachments.get(getSession());
		if(sa != null){
			sa.overwriteConflicts(changes);
		}
	}
	@Override
	public Collection<IPersistentObject> reloadStaleObjects(){
		SessionAttachment sa = EventDispatcher.sessionAttachments.get(getSession());
		if(sa != null){
			return sa.reloadStaleObjects();
		}
		return Collections.emptySet();
	}
}
