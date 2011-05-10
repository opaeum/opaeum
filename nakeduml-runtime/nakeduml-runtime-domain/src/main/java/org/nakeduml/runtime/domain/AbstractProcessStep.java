package org.nakeduml.runtime.domain;


public interface AbstractProcessStep {
	public abstract String getQualifiedName();
	public abstract String getHumanName();
	public TriggerMethod[] getTriggerMethods();
}
