package org.nakeduml.runtime.domain.activity;


public class Trigger {

	private String name;
	private String eventName;

	public Trigger(String name, String eventName) {
		super();
		this.name = name;
		this.eventName = eventName;
	}

	public String getName() {
		return name;
	}

	public String getEventName() {
		return eventName;
	}

}
