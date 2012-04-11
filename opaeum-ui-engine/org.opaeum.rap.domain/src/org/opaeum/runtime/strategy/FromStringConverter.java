package org.opaeum.runtime.strategy;

import java.text.ParseException;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy;

public interface FromStringConverter extends ISimpleTypeRuntimeStrategy{
	Object fromString(String val) throws ParseException;
}
