package org.opaeum.runtime.environment;

import java.util.HashMap;
import java.util.Map;

public class SimpleTypeRuntimeStrategyFactory{
	public static interface ISimpleTypeRuntimeStrategy{
	}
	Map<Class<? extends ISimpleTypeRuntimeStrategy>,ISimpleTypeRuntimeStrategy> strategies = new HashMap<Class<? extends ISimpleTypeRuntimeStrategy>,SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy>();
	private Class<?> type;
	public Class<?> getType(){
		return type;
	}
	public String getTypeUuid(){
		return typeUuid;
	}
	private String typeUuid;
	public SimpleTypeRuntimeStrategyFactory(){
		this(String.class);
	}
	protected SimpleTypeRuntimeStrategyFactory(Class<?> type,Class<? extends ISimpleTypeRuntimeStrategy>...classes){
		for(Class<? extends ISimpleTypeRuntimeStrategy> cls:classes){
			addStrategy(cls);
			this.type = type;
			this.typeUuid = Environment.getInstance().getMetaInfoMap().getUuidFor(type);
		}
	}
	@SuppressWarnings("unchecked")
	public <T extends ISimpleTypeRuntimeStrategy>T getStrategy(Class<T> typeSystem){
		return (T) strategies.get(typeSystem);
	}
	private void addStrategy(Class<? extends ISimpleTypeRuntimeStrategy> strategy){
		try{
			strategies.put(strategy, strategy.newInstance());
		}catch(InstantiationException e){
			throw new IllegalStateException(e);
		}catch(IllegalAccessException e){
			throw new IllegalStateException(e);
		}
	}
	public <T extends ISimpleTypeRuntimeStrategy>boolean hasStrategy(Class<T> typeSystem){
		T result = getStrategy(typeSystem);
		return result != null;
	}
}
