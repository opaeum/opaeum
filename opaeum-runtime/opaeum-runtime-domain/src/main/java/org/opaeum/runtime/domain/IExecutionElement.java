package org.opaeum.runtime.domain;

public interface IExecutionElement<BE extends IBehaviorExecution> {
	String getId();
	BE getBehaviorExecution();
	
}
