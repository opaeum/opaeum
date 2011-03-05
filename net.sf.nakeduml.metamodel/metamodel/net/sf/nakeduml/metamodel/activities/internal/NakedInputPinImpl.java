package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public class NakedInputPinImpl extends NakedPinImpl implements INakedInputPin {
	private static final long serialVersionUID = 8677054720437056501L;


	@Override
	public boolean hasValidInput() {
		return getFeedingNode() != null;
	}
	
	
}
