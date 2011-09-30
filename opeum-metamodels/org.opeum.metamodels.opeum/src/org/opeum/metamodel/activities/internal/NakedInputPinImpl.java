package org.opeum.metamodel.activities.internal;

import org.opeum.metamodel.activities.INakedInputPin;

public class NakedInputPinImpl extends NakedPinImpl implements INakedInputPin {
	private static final long serialVersionUID = 8677054720437056501L;


	@Override
	public boolean hasValidInput() {
		return getFeedingNode() != null;
	}
	
	
}
