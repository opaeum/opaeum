package org.nakeduml.environment;

import java.io.Serializable;

public interface IMessageSender{
	void sendObjectToQueue(Serializable object, String queue );
	void deliverMockedMessages();
}
