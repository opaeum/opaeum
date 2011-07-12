package org.nakeduml.event;

import java.io.Serializable;

public interface AsynchronouslyDelivered extends Serializable{
	String getDescription();
	int getRetryCount();
	void incrementRetryCount();
}
