package org.nakeduml.runtime.domain;


public interface IProcessStep {
	public long getId();
	public String getQualifiedName();
	public String getHumanName();
	public TriggerMethod[] getTriggerMethods();
}
