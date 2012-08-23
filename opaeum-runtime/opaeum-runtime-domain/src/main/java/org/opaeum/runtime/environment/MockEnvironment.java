package org.opaeum.runtime.environment;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public class MockEnvironment extends Environment{
	private Map<String, Object> components = new HashMap<String, Object>();
	public static MockEnvironment getInstance(){
		defaultImplementation=MockEnvironment.class;
		return (MockEnvironment) Environment.getInstance();
	}
	public <T> void mockComponent(Class<T> clazz, T component) {
		this.components.put(clazz.getName(), component);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>T getComponent(Class<T> clazz){
		return (T) components.get(clazz);
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return getComponent(clazz);
	}
	@Override
	public void reset(){
	}
	@Override
	public void endRequestContext(){
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public void sendSignal(IActiveObject target,ISignal s){
	}
	@Override
	public UmtPersistence newUmtPersistence(){
		return null;
	}

	@Override
	public ConversationalPersistence createConversationalPersistence(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UmtPersistence getUmtPersistence(){
		// TODO Auto-generated method stub
		return null;
	}

}
