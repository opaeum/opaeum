package org.nakeduml.runtime.domain;


public interface IProcessStep {
	public abstract String getQualifiedName();
	public abstract String getHumanName();
	public TriggerMethod[] getTriggerMethods();
}
