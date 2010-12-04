package net.sf.nakeduml.util;

public class TriggerMethod {
	private boolean isHumanTrigger;
	private String methodName;
	private String humanName;
	
	public TriggerMethod(boolean isHumanTrigger, String methodName, String humanName) {
		super();
		this.methodName = methodName;
		this.humanName = humanName;
		this.isHumanTrigger=isHumanTrigger;
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
}
