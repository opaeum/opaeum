package org.opaeum.runtime.environment;

import java.util.Collection;
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
		if(typeUuid == null){
			this.typeUuid = Environment.getInstance().getMetaInfoMap().getUuidFor(type);
		}
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
		}
	}
	@SuppressWarnings("unchecked")
	public <T extends ISimpleTypeRuntimeStrategy>T getStrategy(Class<T> typeSystem){
		Collection<ISimpleTypeRuntimeStrategy> values = strategies.values();
		for(ISimpleTypeRuntimeStrategy s:values){
			if(typeSystem.isInstance(s)){
				return (T) s;
			}
		}
		return null;
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
