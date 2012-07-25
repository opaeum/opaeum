package org.opaeum.metamodel.activities.internal;

import org.eclipse.uml2.uml.INakedInputPin;

public class NakedInputPinImpl extends NakedPinImpl implements INakedInputPin {
	private static final long serialVersionUID = 8677054720437056501L;

	public NakedInputPinImpl() {
		super();
	}

	@Override
	public boolean hasValidInput() {
		return getFeedingNode() != null;
	}
	
	
}
