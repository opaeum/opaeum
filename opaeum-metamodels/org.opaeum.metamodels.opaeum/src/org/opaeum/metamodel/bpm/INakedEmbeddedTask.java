package org.opaeum.metamodel.bpm;

import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.core.INakedMessageStructure;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	void initMessageStructure();
	INakedMessageStructure getMessageStructure();
}
