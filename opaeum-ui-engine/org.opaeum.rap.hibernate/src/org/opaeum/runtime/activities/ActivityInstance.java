package org.opaeum.runtime.activities;

public interface ActivityInstance extends ActivityNodeContainerInstance{
	Object getParameterValue(String name);
}
