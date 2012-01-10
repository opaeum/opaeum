package org.opaeum.metamodel.actions;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedValueSpecification;
public interface INakedSendSignalAction extends INakedInvocationAction ,IActionWithTargetPin{
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
	void setTarget(INakedInputPin target);
	boolean hasTarget();
	INakedValueSpecification getFromExpression();
	INakedValueSpecification getCcExpression();
	INakedValueSpecification getBccExpression();


}