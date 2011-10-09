package org.opaeum.metamodel.activities;

import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedMessageStructure;



public interface INakedStructuredActivityNode extends INakedAction,ActivityNodeContainer {

	public abstract void initMessageStructure();

	public abstract INakedMessageStructure getMessageStructure();


}
