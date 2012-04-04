package org.opaeum.runtime.strategy;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory.ISimpleTypeRuntimeStrategy;

public interface AfterConvertValidator extends ISimpleTypeRuntimeStrategy{
	ValidationResult validate(Object value);
}
