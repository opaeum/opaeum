package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.UmtPersistence;

public class CdiEnvironment extends Environment{
	@Override
	public <T>T getComponent(Class<T> clazz){
		return Component.INSTANCE.getInstance(clazz);
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
	public void endRequestContext() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void startRequestContext() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendSignal(IActiveObject target, ISignal s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public UmtPersistence newUmtPersistence() {
		// TODO Auto-generated method stub
		return null;
	}
}
