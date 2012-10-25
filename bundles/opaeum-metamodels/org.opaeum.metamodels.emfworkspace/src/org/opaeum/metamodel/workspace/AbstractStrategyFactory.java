package org.opaeum.metamodel.workspace;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.DataType;

public abstract class AbstractStrategyFactory{
	public abstract String getRuntimeStrategyFactory();
	public static interface ISimpleTypeStrategy{
	}
	Set<ISimpleTypeStrategy> typeSystemStrategies = new HashSet<ISimpleTypeStrategy>();
	protected AbstractStrategyFactory(Class<? extends ISimpleTypeStrategy>...classes){
		for(Class<? extends ISimpleTypeStrategy> cls:classes){
			addStrategy(cls);
		}
	}
	public abstract boolean appliesTo(DataType st);
	@SuppressWarnings("unchecked")
	public <T>T getStrategy(Class<T> typeSystem){
		T result = null;
		for(Object strategy:this.typeSystemStrategies){
			if(typeSystem.isInstance(strategy)){
				result = (T) strategy;
				break;
			}
		}
		return result;
	}
	private void addStrategy(Class<? extends ISimpleTypeStrategy> strategy){
		try{
			typeSystemStrategies.add(strategy.newInstance());
		}catch(InstantiationException e){
			throw new IllegalStateException(e);
		}catch(IllegalAccessException e){
			throw new IllegalStateException(e);
		}
	}
	public <T>boolean hasStrategy(Class<T> typeSystem){
		T result = getStrategy(typeSystem);
		return result != null;
	}
}
