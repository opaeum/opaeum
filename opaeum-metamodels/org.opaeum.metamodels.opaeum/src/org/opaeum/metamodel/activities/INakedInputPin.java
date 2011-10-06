package org.opaeum.metamodel.activities;

import org.opaeum.metamodel.actions.ITargetElement;

public interface INakedInputPin extends INakedPin,ITargetElement{
	boolean hasValidInput();
}
