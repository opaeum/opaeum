package org.opaeum.environment.seam2;

import java.lang.annotation.Annotation;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.jboss.seam.Component;
import org.jboss.seam.contexts.Lifecycle;
import org.opaeum.hibernate.domain.HibernateEnvironment;

@SuppressWarnings("unchecked")
public class Seam2Environment extends HibernateEnvironment{
	@Override
	public <T>T getComponent(Class<T> clazz){
		if(clazz == Session.class){
			return (T) ((EntityManager) Component.getInstance("entityManager")).getDelegate();
		}else{
			return (T) Component.getInstance(clazz, true);
		}
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return (T) Component.getInstance(clazz, true);
	}
	@Override
	public void reset(){
		// Yikes, good luck
	}
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) Component.forClass(o.getClass()).getBeanClass();
	}
	@Override
	public void endRequestContext(){
		Lifecycle.endCall();
	}
	@Override
	public void startRequestContext(){
		Lifecycle.beginCall();
	}
}
