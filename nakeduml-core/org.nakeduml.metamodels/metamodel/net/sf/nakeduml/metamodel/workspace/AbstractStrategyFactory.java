package net.sf.nakeduml.metamodel.workspace;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractStrategyFactory {
	Set<Object> typeSystemStrategies = new HashSet<Object>();
	protected AbstractStrategyFactory(Class<?> ...classes){
		for (Class<?> cls : classes) {
			addStrategy(cls);
		}
	}
	public <T> T getStrategy(Class<T> typeSystem) {
		T result=null;
		for (Object strategy : this.typeSystemStrategies ){
			if(typeSystem.isInstance(strategy)){
				result=(T)strategy;
				break;
			}
		}
		return result;
	}
	private void addStrategy(Class<?> strategy){
		try{
			typeSystemStrategies.add(strategy.newInstance());
		}catch(InstantiationException e){
			throw new IllegalStateException(e);
		}catch(IllegalAccessException e){
			throw new IllegalStateException(e);
		}
	}
	public <T> boolean hasStrategy(Class<T> typeSystem){
		T result = getStrategy(typeSystem);
		return result!=null;
	}
}
