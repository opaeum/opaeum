package org.opeum.metamodel.bpm;

import org.opeum.metamodel.actions.IActionWithTargetElement;
import org.opeum.metamodel.core.INakedMessageStructure;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	void initMessageStructure();
	INakedMessageStructure getMessageStructure();
}
