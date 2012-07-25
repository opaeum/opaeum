package org.eclipse.uml2.uml;
public interface INakedSendSignalAction extends INakedInvocationAction ,IActionWithTargetPin{
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
	void setTarget(INakedInputPin target);
	boolean hasTarget();
	INakedValueSpecification getFromExpression();
	INakedValueSpecification getCcExpression();
	INakedValueSpecification getBccExpression();


}