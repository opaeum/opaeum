package org.opaeum.runtime.domain;

import java.util.Map;

public interface IObserver{

	void registerObservations(Map<String,IExecutionElement> executionElements);
}
