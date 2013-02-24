package org.opaeum.runtime.environment;

import java.util.HashMap;
import java.util.Map;

public class SimpleTypeRuntimeStrategyFactory {
	public static interface ISimpleTypeRuntimeStrategy {
	}

	Map<Class<? extends ISimpleTypeRuntimeStrategy>, ISimpleTypeRuntimeStrategy> strategies = new HashMap<Class<? extends ISimpleTypeRuntimeStrategy>, SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy>();
	private Class<?> type;

	public Class<?> getType() {
		return type;
	}

	// public String getTypeUuid(){
	// return typeUuid;
	// }
	private String typeUuid;

	@SuppressWarnings("unchecked")
	public SimpleTypeRuntimeStrategyFactory() {
		this("", String.class);
	}

	// TODO clean this up
	protected SimpleTypeRuntimeStrategyFactory(String typeUuid, Class<?> type,
			Class<? extends ISimpleTypeRuntimeStrategy>... classes) {
		this.type = type;
		this.typeUuid = typeUuid;
		for (Class<? extends ISimpleTypeRuntimeStrategy> cls : classes) {
			addStrategy(cls);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends ISimpleTypeRuntimeStrategy> T getStrategy(
			Class<T> typeSystem) {
		return (T) strategies.get(typeSystem);
	}

	private void addStrategy(
			Class<? extends ISimpleTypeRuntimeStrategy> strategy) {
		try {
			for (Class<?> it : strategy.getInterfaces()) {
				if (ISimpleTypeRuntimeStrategy.class.isAssignableFrom(it)) {
					strategies.put((Class<? extends ISimpleTypeRuntimeStrategy>) it, strategy.newInstance());
				}
			}
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public <T extends ISimpleTypeRuntimeStrategy> boolean hasStrategy(
			Class<T> typeSystem) {
		T result = getStrategy(typeSystem);
		return result != null;
	}
}
