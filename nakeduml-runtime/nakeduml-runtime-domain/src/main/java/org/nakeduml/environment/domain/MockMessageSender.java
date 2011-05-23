package org.nakeduml.environment.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.nakeduml.environment.IMessageSender;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.event.AbstractNakedUmlEvent;

public class MockMessageSender implements IMessageSender{
	Map<String,Collection<Serializable>> messageMap = new HashMap<String,Collection<Serializable>>();
	@Override
	public void sendObjectsToQueue(Collection<? extends Serializable> object,String queueName){
		Collection<Serializable> queue = messageMap.get(queueName);
		if(queue == null){
			queue = new ArrayList<Serializable>();
			messageMap.put(queueName, queue);
		}
		queue.addAll(object);
	}
	@Override
	public void deliverMockedMessages(){
		Collection<Collection<Serializable>> values = messageMap.values();
		for(Collection<Serializable> collection:values){
			for(Serializable serializable:collection){
				deliver(serializable);
			}
		}
	}
	private void deliver(Serializable serializable){
		if(serializable instanceof SignalToDispatch){
			((SignalToDispatch) serializable).getTarget().processSignal(((SignalToDispatch) serializable).getSignal());
		}else if(serializable instanceof AbstractNakedUmlEvent){
			((AbstractNakedUmlEvent) serializable).invokeCallback(((AbstractNakedUmlEvent) serializable).getEventSource());
		}
	}
}
