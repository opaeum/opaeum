package org.nakeduml.runtime.domain;

public class CancelledEvent{
	private Object target;
	private String eventId;
	public CancelledEvent(Object target,String eventId){
		super();
		this.target = target;
		this.eventId=eventId;
	}
	public Object getTarget(){
		return target;
	}
	public String getEventId(){
		return eventId;
	}
}