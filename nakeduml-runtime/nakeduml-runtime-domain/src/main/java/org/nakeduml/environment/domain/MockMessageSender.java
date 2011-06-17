package org.nakeduml.environment.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.nakeduml.environment.IMessageSender;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.event.AbstractNakedUmlEvent;
import org.nakeduml.event.ChangeEvent;
import org.nakeduml.event.TimeEvent;

public class MockMessageSender implements IMessageSender{
	protected Map<String,Collection<Serializable>> messageMap = new HashMap<String,Collection<Serializable>>();
	@Override
	public void sendObjectToQueue(Serializable object,String queueName){
		Collection<Serializable> queue = messageMap.get(queueName);
		if(queue == null){
			queue = new ArrayList<Serializable>();
			messageMap.put(queueName, queue);
		}
		queue.add(object);
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
	protected void deliver(Serializable serializable){
		if(serializable instanceof SignalToDispatch){
			((SignalToDispatch) serializable).getTarget().processSignal(((SignalToDispatch) serializable).getSignal());
		}else if(serializable instanceof AbstractNakedUmlEvent){
			((AbstractNakedUmlEvent) serializable).invokeCallback(((AbstractNakedUmlEvent) serializable).getEventSource());
		}else if(serializable instanceof TimeEvent){
			TimeEvent timeEvent = (TimeEvent) serializable;
			timeEvent.invokeCallback(timeEvent.getEventSource());
		}else if(serializable instanceof ChangeEvent){
			ChangeEvent changeEvent = (ChangeEvent) serializable;
			changeEvent.evaluateConditionOn(changeEvent.getEventSource());
			if(changeEvent.isTrue()){
				//TODO enlist event int static store and re-evaluate
				changeEvent.invokeCallback(changeEvent.getEventSource());
			}
		}
	}
}
