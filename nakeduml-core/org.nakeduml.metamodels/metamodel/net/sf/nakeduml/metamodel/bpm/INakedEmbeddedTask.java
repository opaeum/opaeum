package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	void initMessageStructure();
	INakedMessageStructure getMessageStructure();
}
