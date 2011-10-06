package org.opaeum.metamodel.actions;

import java.util.List;

import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.IParameterOwner;

/**
 * The superclass action for the invocation of either behviors or operations.
 * 
 * @author barnar_a
 * 
 */
public interface INakedCallAction extends INakedInvocationAction,IActionWithTargetElement {
	/**
	 * All the exceptions, returnPins, and parameters with and Out- or InOut
	 * direction. I.e. all the pins that could possibly receive a value from the
	 * called originalElement
	 */
	List<INakedOutputPin> getResult();

	/**
	 * The one pin that is not an exception, but has a return direction. Logic
	 * undefined if multiple exception parameters are return parameteres
	 */
	INakedPin getReturnPin();

	List<INakedOutputPin> getExceptionPins();

	IParameterOwner getCalledElement();

	boolean isLongRunning();

	void initMessageStructure();
	INakedMessageStructure getMessageStructure();

	public boolean isSynchronous();

	public void setSynchronous(boolean isSynchronous);
}
