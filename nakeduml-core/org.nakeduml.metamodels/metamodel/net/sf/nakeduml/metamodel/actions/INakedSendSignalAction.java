package net.sf.nakeduml.metamodel.actions;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
public interface INakedSendSignalAction extends INakedInvocationAction ,IActionWithTargetPin{
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
	void setTarget(INakedInputPin target);
	boolean hasTarget();

}