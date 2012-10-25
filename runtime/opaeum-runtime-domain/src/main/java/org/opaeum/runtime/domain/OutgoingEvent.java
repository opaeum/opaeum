package org.opaeum.runtime.domain;

import org.opaeum.runtime.event.IEventHandler;

public class OutgoingEvent{
	private Object target;
	private IEventHandler handler;
	public OutgoingEvent(Object target,IEventHandler handler){
		super();
		this.target = target;
		this.handler = handler;
	}
	public Object getTarget(){
		return target;
	}
	public IEventHandler getHandler(){
		return handler;
	}
}