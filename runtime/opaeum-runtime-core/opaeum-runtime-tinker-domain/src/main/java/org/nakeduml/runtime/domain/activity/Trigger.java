package org.nakeduml.runtime.domain.activity;

import org.opaeum.runtime.domain.ISignal;

public class Trigger {

	private Class<? extends ISignal> signalType;
	private String name;

	public Trigger(String name, Class<? extends ISignal> signalType) {
		super();
		this.name = name;
		this.signalType = signalType;
	}

	public String getName() {
		return name;
	}

	public Class<? extends ISignal> getSignalType() {
		return signalType;
	}
	
}
