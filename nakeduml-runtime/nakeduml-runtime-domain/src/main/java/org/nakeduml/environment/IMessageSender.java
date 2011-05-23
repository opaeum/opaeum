package org.nakeduml.environment;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IMessageSender{
	void sendObjectsToQueue(Collection<? extends Serializable> objects, String queue );
	void deliverMockedMessages();
}
