package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import nl.klasse.octopus.stdlib.IOclLibrary;

public interface INakedEmbeddedTask extends IActionWithTargetElement,INakedDefinedResponsibility{
	INakedMessageStructure getMessageStructure(IOclLibrary lib);
}
