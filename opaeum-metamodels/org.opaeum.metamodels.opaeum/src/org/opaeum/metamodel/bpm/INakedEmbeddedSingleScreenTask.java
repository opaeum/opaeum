package org.opaeum.metamodel.bpm;

import java.util.List;

import org.opaeum.metamodel.actions.INakedOpaqueAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedPin;

public interface INakedEmbeddedSingleScreenTask extends INakedOpaqueAction,INakedEmbeddedTask{
	boolean isSynchronous();
	List<INakedPin> getPins();
	List<INakedOutputPin> getOutputValues();
}
