package net.sf.nakeduml.metamodel.core;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import nl.klasse.octopus.model.IParameter;

/**
 * A common interface for Behaviors and Operations. Needs to implement the Octopus IOperation interface
 * to ensure that it can be called from OCL 
 */
public interface IParameterOwner extends PreAndPostConstrained,INakedElement/*,IOperation*/{
	INakedBehavioredClassifier getContext();
	/**
	 * Returns true if this invocations of this operation or behavior will result in a long running process. Screenflows are also considered
	 * long-running -i.e. anything longer than a synchronous immediate method
	 * 
	 * @return
	 */
	boolean isProcess();
	INakedTypedElement getReturnParameter();
	/**
	 * Returns an ordered list of all the input, output and return parameters of this operation or behavior
	 */
	List<? extends INakedParameter> getOwnedParameters();
	/**
	 * Returns an ordered list of all the parameters that could possibly feed a value into this operation or behavior,i.e. int and in-out
	 * parameters
	 */
	List<? extends INakedParameter> getArgumentParameters();
	/**
	 * Ditto, may change in future to include out-parameters. The concept of a non-returning out parameter that gets passed to an operation at
	 * invocation doesn't really make sense. Out parameters are available after execution from the "MessageTuple"
	 * 
	 * @return
	 */
	List<IParameter> getParameters();
	/**
	 * Returns an ordered list of all the parameters marked as exceptions that could possibly return a value from this operation or
	 * behavior,i.e. return, out and in-out parameters
	 */
	List<? extends INakedParameter> getExceptionParameters();
	/**
	 * Returns an ordered list of all the parameters that could possibly return a value from this operation or behavior,i.e. return, out and
	 * in-out parameters, inclusive of exception parameters
	 */
	List<? extends INakedParameter> getResultParameters();
	/**
	 * Returns true if this operation of behavior could possibly result in two or more concurrent results. Since exceptions parameters cause
	 * none of the other results to be populated
	 * 
	 * @return
	 */
	boolean hasMultipleConcurrentResults();
	
	INakedConstraint getBodyCondition();
}