package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	void initMessageStructure(NakedUmlLibrary lib);
	INakedMessageStructure getMessageStructure();
}
