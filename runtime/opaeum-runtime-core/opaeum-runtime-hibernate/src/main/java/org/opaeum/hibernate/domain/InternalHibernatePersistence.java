package org.opaeum.hibernate.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.event.spi.EventSource;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.ConversationalPersistence;

public final class InternalHibernatePersistence extends AbstractHibernatePersistence{
	private Map<String,ConversationalPersistence> otherPersistence;
	InternalHibernatePersistence(Session session,Environment environment){
		super(session, environment);
	}
	public void persistBeforeFlush(IPersistentObject value){
		String entityName = IntrospectionUtil.getOriginalClass(value).getAnnotation(Entity.class).name();
		((EventSource) getSession()).persistOnFlush(entityName, value, new HashMap());
	}
	private ConversationalPersistence getPersistence(String applicationId){
		if(otherPersistence == null){
			otherPersistence = new HashMap<String,ConversationalPersistence>();
		}
		ConversationalPersistence result = otherPersistence.get(applicationId);
		if(result == null){
			Environment env = Environment.getEnvironment(applicationId);
			if(env != null){
				otherPersistence.put(applicationId, result = env.createConversationalPersistence());
			}
		}
		return result;
	}
	public <T>T getReference(String applicationId,Class<T> t,Long id){
		ConversationalPersistence op = getPersistence(applicationId);
		if(op == null){
			return null;
		}else{
			return op.getReference(t, id);
		}
	}
	public void persist(String applicationId,IPersistentObject object){
		ConversationalPersistence op = getPersistence(applicationId);
		if(op == null){
			throw new IllegalStateException("Application " + applicationId + " not found!");
		}else{
			op.persist(object);
		}
	}
	public void remove(String applicationId,IPersistentObject event){
		ConversationalPersistence op = getPersistence(applicationId);
		if(op == null){
			throw new IllegalStateException("Application " + applicationId + " not found!");
		}else{
			op.persist(event);
		}
	}
	public void cleanUp(){
		super.cleanUp();
		if(otherPersistence != null){
			for(Entry<String,ConversationalPersistence> entry:otherPersistence.entrySet()){
				entry.getValue().close();
			}
			otherPersistence.clear();
			otherPersistence = null;
		}
	}
	public void flushOtherPersistence(){
		if(otherPersistence != null){
			Collection<ConversationalPersistence> values = otherPersistence.values();
			for(ConversationalPersistence p:values){
				p.flush();
			}
		}
	}
	public JavaMetaInfoMap getMetaInfoMap(String applicationIdentifier){
		ConversationalPersistence persistence = getPersistence(applicationIdentifier);
		if(persistence != null){
			return persistence.getMetaInfoMap();
		}
		return null;
	}
}