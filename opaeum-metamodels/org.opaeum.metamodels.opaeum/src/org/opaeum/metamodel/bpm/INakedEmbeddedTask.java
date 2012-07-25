package org.opaeum.metamodel.bpm;

import org.eclipse.uml2.uml.IActionWithTargetElement;
import org.eclipse.uml2.uml.INakedMessageStructure;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	void initMessageStructure();
	INakedMessageStructure getMessageStructure();
}
