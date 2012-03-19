package org.opaeum.runtime.strategy;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy;

public interface ToStringConverter extends ISimpleTypeRuntimeStrategy{
	String toString(Object val);
}
