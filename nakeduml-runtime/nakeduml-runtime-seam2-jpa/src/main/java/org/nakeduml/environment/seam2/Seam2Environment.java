package org.nakeduml.environment.seam2;

import java.lang.annotation.Annotation;

import org.jboss.seam.Component;
import org.jboss.seam.contexts.Lifecycle;
import org.nakeduml.hibernate.domain.HibernateEnvironment;

public class Seam2Environment extends HibernateEnvironment{
	@Override
	public <T>T getComponent(Class<T> clazz){
		return (T) Component.getInstance(clazz, true);
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
		return (Class<T>) o.getClass();
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
