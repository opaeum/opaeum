package org.nakeduml.environment;

import java.io.Serializable;

public interface IMessageSender extends Serializable{
	void sendObjectToQueue(Serializable object, String queue );
	void deliverMockedMessages();
}
