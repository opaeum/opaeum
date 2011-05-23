package org.nakeduml.event;

import java.io.Serializable;

public interface Retryable extends Serializable{
	String getDescription();
	int getRetryCount();
	void incrementRetryCount();
}
