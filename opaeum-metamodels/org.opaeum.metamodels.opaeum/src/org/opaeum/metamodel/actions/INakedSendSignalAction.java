package org.opaeum.metamodel.actions;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
public interface INakedSendSignalAction extends INakedInvocationAction ,IActionWithTargetPin{
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
	void setTarget(INakedInputPin target);
	boolean hasTarget();

}