package net.sf.nakeduml.util;
public interface AbstractProcessStep {
	public abstract String getQualifiedName();
	public abstract String getHumanName();
	public TriggerMethod[] getTriggerMethods();
}
