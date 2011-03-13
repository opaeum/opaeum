package net.sf.nakeduml.metamodel.actions;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
public interface INakedCallBehaviorAction extends INakedCallAction{
	public boolean isAutomated();
	public void setAutomated(boolean isAutomated);
	void setBehavior(INakedBehavior Behavior);
	INakedBehavior getBehavior();

}