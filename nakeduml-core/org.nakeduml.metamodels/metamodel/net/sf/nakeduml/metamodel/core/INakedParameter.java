package net.sf.nakeduml.metamodel.core;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.ParameterDirectionKind;
public interface INakedParameter extends IModifiableTypedElement, IParameter {
	void setLinkedParameter(INakedParameter p);
//	IParameterOwner getOwner();
	/**
	 * Octopus does not support a return direction. This augments Octopus with return semantics
	 * @param isReturn
	 */
	void setReturn(boolean isReturn);
	boolean isReturn();
	/**
	 * Returns true if this parameter is a return parameter or an out parameter.
	 * @return
	 */
	boolean isResult();
	/**
	 * Index of this parameter in the list of parameters that could possibly have an incoming value.
	 * i.e. parameters with a direction of INOUT or IN
	 * Useful for call actions to identify a parameter in the argument pins
	 * @return
	 */
	int getArgumentIndex();
	void setArgumentIndex(int elementIndex);
	/**
	 * Index of this parameter in the list of exception types thrown by the container
	 * TODO should not really be used anywhere
	 * @return
	 */
	int getExceptionIndex();
	void setExceptionIndex(int elementIndex);
	/**
	 * Index of this parameter in the list of parameters that could possibly have a resulting value.
	 * i.e. parameters with a direction of INOUT, OUT or RESULT
	 * Useful for call actions to identify a parameter in the result pins
	 * @return
	 */
	int getResultIndex();
	void setResultIndex(int index);
	
	void setDirection(ParameterDirectionKind in);
	boolean isException();
	void setException(boolean e);
	boolean isArgument();

}