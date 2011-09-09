package org.nakeduml.environment.adaptor;

import java.lang.annotation.Annotation;

import org.drools.runtime.StatefulKnowledgeSession;
import org.nakeduml.cdi.CdiHibernateKnowledgeSession;
import org.nakeduml.hibernate.domain.HibernateEnvironment;

public class CdiEnvironment extends HibernateEnvironment{
	@SuppressWarnings("unchecked")
	@Override
	public <T>T getComponent(Class<T> clazz){
		if(clazz == StatefulKnowledgeSession.class){
				return (T) Component.INSTANCE.getInstance(CdiHibernateKnowledgeSession.class).getKnowledgeSession();
		}else{
			return Component.INSTANCE.getInstance(clazz);
		}
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return Component.INSTANCE.getInstance(clazz, qualifiers);
	}
	@Override
	public void reset(){
		// Yikes, good luck
	}
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return Component.INSTANCE.getImplementationClass(o);
	}
	@Override
	public void endRequestContext(){
		
	}
	@Override
	public void startRequestContext(){
		
	}
}
