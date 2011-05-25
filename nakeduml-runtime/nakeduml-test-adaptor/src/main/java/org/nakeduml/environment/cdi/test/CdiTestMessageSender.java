package org.nakeduml.environment.cdi.test;

import java.io.Serializable;
import java.util.Collection;

import org.nakeduml.environment.adaptor.CdiEnvironment;
import org.nakeduml.environment.domain.MockMessageSender;

public class CdiTestMessageSender extends MockMessageSender{

	@Override
	public void deliverMockedMessages(){
		Collection<Collection<Serializable>> values = messageMap.values();
		for(Collection<Serializable> collection:values){
			for(Serializable serializable:collection){
				CdiTestEnvironment.getInstance().beforeRequest(this);
				deliver(serializable);
				CdiTestEnvironment.getInstance().afterRequest(this);
			}
		}
	}
}
