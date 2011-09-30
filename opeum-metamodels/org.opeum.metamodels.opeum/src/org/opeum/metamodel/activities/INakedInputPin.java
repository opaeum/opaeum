package org.opeum.metamodel.activities;

import org.opeum.metamodel.actions.ITargetElement;

public interface INakedInputPin extends INakedPin,ITargetElement{
	boolean hasValidInput();
}
