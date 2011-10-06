package org.opeum.runtime.domain;

import java.io.Serializable;

public class TriggerMethod implements Serializable {
	private static final long serialVersionUID = 687535240459011644L;
	private boolean isHumanTrigger;
	private String methodName;
	private String humanName;

	public TriggerMethod(boolean isHumanTrigger, String methodName, String humanName) {
		super();
		this.methodName = methodName;
		this.humanName = humanName;
		this.isHumanTrigger = isHumanTrigger;
	}

	public TriggerMethod() {
		super();
	}

	public String getMethodName() {
		return methodName;
	}

	public String getHumanName() {
		return humanName;
	}

	public boolean isHumanTrigger() {
		return isHumanTrigger;
	}

	public void setHumanTrigger(boolean isHumanTrigger) {
		this.isHumanTrigger = isHumanTrigger;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
}
