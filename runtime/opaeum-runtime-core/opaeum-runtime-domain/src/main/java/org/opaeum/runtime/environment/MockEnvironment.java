package org.opaeum.runtime.environment;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public class MockEnvironment extends Environment{
	private Map<String, Object> components = new HashMap<String, Object>();
	static MockEnvironment INSTANCE=new MockEnvironment(); 
	public static MockEnvironment getInstance(){
		return INSTANCE; 
	}
	public <T> void mockComponent(Class<T> clazz, T component) {
		this.components.put(clazz.getName(), component);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	@Override
	public <T>T getComponentImpl(Class<T> clazz,Annotation qualifiers){
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
	public ConversationalPersistence createConversationalPersistence(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JavaMetaInfoMap getMetaInfoMap(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UmtPersistence createUmtPersistence(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CmtPersistence getCurrentPersistence(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getApplicationIdentifier(){
		// TODO Auto-generated method stub
		return null;
	}

}
