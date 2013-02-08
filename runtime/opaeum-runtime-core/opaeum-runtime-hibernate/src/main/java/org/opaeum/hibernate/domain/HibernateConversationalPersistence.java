package org.opaeum.hibernate.domain;

import java.util.Collection;

import org.hibernate.Session;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.ConversationalPersistence;

public class HibernateConversationalPersistence extends AbstractHibernatePersistence implements ConversationalPersistence{
	public HibernateConversationalPersistence(Session session,Environment environment){
		super(session,environment);
	}
	@Override
	public void close(){
		getSession().close();
	}
	@Override
	public void flush(){
		getSession().flush();
	}
	
	@Override
	public boolean containsStaleObjects(){
		SessionAttachment sa = EventDispatcher.sessionAttachments.get(getSession());
		return sa.containsStaleObjects();
	}
	@Override
	public Collection<IPersistentObject> refreshStaleObjects(){
	return	 EventDispatcher.sessionAttachments.get(getSession()).refreshStaleObjects();
		
	}
	@Override
	public void upgradeStaleObjects(){
		EventDispatcher.sessionAttachments.get(getSession()).upgradeStaleObjects();
		
	}
}
