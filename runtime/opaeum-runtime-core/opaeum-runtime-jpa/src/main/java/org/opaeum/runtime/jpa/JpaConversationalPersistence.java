package org.opaeum.runtime.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.hibernate.domain.HibernateConversationalPersistence;
import org.opaeum.hibernate.domain.SessionAttachment;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.ConversationalPersistence;

public class JpaConversationalPersistence extends HibernateConversationalPersistence implements ConversationalPersistence{
	public JpaConversationalPersistence(EntityManager entityManager,Environment e){
		super((Session) entityManager.getDelegate(),e);
	}
}
