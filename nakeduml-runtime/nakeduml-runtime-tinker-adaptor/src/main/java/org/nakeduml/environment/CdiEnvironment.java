package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import org.nakeduml.environment.Environment;

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
}
