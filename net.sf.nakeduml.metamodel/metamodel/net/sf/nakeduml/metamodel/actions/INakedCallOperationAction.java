package net.sf.nakeduml.metamodel.actions;
import net.sf.nakeduml.metamodel.core.INakedOperation;
/**
 * Implement INakedTypedElement to ensure the actual type is resolved correctly
 * @author barnar_a
 *
 */
public interface INakedCallOperationAction extends INakedCallAction{
	void setOperation(INakedOperation operation);
	INakedOperation getOperation();
}