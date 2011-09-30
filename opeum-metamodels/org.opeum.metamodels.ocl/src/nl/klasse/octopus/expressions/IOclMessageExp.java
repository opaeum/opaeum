package nl.klasse.octopus.expressions;

import java.util.List;

import nl.klasse.octopus.model.IOperation;


/** IOclMessageExp : an expression that reflects a message that was sent 
 *  The ^ or ^^ in the OCL spec.
 */
public interface IOclMessageExp extends IOclExpression {

	/** Getter for property target.
	 * @return Value of property target.
	 */
	public abstract IOclExpression getTarget();

	/** Getter for the arguments.
	 * @return Value of property arguments.
	 */
	public abstract List<IOclExpression> getArguments();

	/** Gets the operation to which this message refers.
	 * @return The operation.
	 */
	public abstract IOperation getCalledOperation();
}