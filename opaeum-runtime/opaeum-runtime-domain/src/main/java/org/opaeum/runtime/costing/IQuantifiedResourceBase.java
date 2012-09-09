package org.opaeum.runtime.costing;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IQuantifiedResourceBase extends IPersistentObject, CompositionNode{
	IPricePerUnit getPriceEffectiveOn(Date d);

	String toXmlReferenceString();
}
