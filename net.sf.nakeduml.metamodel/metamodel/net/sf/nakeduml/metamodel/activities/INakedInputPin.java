package net.sf.nakeduml.metamodel.activities;

import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.ITargetElement;

public interface INakedInputPin extends INakedPin,ITargetElement{
	boolean hasValidInput();
}
