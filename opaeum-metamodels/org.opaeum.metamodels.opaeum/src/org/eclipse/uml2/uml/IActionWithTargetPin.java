package org.eclipse.uml2.uml;


public interface IActionWithTargetPin extends IActionWithTargetElement{
	INakedInputPin getTarget();

	INakedClassifier getExpectedTargetType();

}
