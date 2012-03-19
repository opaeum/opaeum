package org.opaeum.runtime.strategy;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy;

public interface FromStringConverter extends ISimpleTypeRuntimeStrategy{
	String toString(Object val);
}
