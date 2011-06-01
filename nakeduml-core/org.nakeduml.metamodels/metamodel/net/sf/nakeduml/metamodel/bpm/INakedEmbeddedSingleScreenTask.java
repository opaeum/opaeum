package net.sf.nakeduml.metamodel.bpm;

import java.util.List;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;

public interface INakedEmbeddedSingleScreenTask extends INakedOpaqueAction,INakedEmbeddedTask{
	boolean isSynchronous();
	List<INakedPin> getPins();
	List<INakedOutputPin> getOutputValues();
	INakedMessageStructure getMessageStructure();
}
