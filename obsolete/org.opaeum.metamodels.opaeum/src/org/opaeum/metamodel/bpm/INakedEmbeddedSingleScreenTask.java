package org.opaeum.metamodel.bpm;

import java.util.List;

import org.eclipse.uml2.uml.INakedOpaqueAction;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedPin;

public interface INakedEmbeddedSingleScreenTask extends INakedOpaqueAction,INakedEmbeddedTask{
	boolean isSynchronous();
	List<INakedPin> getPins();
	List<INakedOutputPin> getOutputValues();
}
