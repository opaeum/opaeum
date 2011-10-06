package org.opaeum.metamodel.actions;
import java.util.List;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedInputPin;
/**
 * 
 * An abstract super interface for the two incarnations of opaqueActions
 * 1. Ocl Actions, with a body expression and a single return pin 
 * 2. Embedded Tasks with a taskDefinition and multiple output values
 * 
 */
public interface INakedOpaqueAction extends INakedAction{
	List<INakedInputPin> getInputValues();

}
