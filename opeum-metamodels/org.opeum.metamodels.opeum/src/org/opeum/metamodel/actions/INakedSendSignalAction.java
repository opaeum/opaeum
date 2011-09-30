package org.opeum.metamodel.actions;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.commonbehaviors.INakedSignal;
public interface INakedSendSignalAction extends INakedInvocationAction ,IActionWithTargetPin{
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
	void setTarget(INakedInputPin target);
	boolean hasTarget();

}