package org.opeum.metamodel.bpm;

import java.util.List;

import org.opeum.metamodel.actions.INakedOpaqueAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.INakedPin;

public interface INakedEmbeddedSingleScreenTask extends INakedOpaqueAction,INakedEmbeddedTask{
	boolean isSynchronous();
	List<INakedPin> getPins();
	List<INakedOutputPin> getOutputValues();
}
