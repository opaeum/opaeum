package org.opeum.metamodel.actions;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.core.INakedOperation;
/**
 * Implement INakedTypedElement to ensure the actual type is resolved correctly
 * @author barnar_a
 *
 */
public interface INakedCallOperationAction extends INakedCallAction,IActionWithTargetPin {
	void setOperation(INakedOperation operation);
	INakedOperation getOperation();
	void setTarget(INakedInputPin target);
	boolean hasTarget();

}