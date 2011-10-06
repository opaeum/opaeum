package org.opaeum.metamodel.activities.internal;

import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;

public class NakedExpansionNodeImpl extends NakedObjectNodeImpl implements INakedExpansionNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2266933881560401012L;

	@Override
	public INakedExpansionRegion getExpansionRegion() {
		return (INakedExpansionRegion) getOwnerElement();
	}

	@Override
	public boolean isOutputElement() {
		return getExpansionRegion().getOutputElement().contains(this);
	}

	@Override
	public boolean isInputElement() {
		return getExpansionRegion().getInputElement().contains(this);
	}
}
