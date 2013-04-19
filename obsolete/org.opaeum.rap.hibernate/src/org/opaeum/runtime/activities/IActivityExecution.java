package org.opaeum.runtime.activities;

public interface IActivityExecution extends IActivityNodeContainerExecution{
	Object getParameterValue(String name);
}
