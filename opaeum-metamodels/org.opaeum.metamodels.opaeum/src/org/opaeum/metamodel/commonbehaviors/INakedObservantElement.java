package org.opaeum.metamodel.commonbehaviors;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.metamodel.core.INakedElement;

public interface INakedObservantElement{
	Collection<INakedTimeObservation> getTimeObservations();
	Collection<INakedDurationObservation> getDurationObservations();
	public Collection<INakedDurationObservation> findDurationObservationFrom(INakedElement e);
	public Collection<INakedDurationObservation> findDurationObservationTo(INakedElement e);
	Collection<INakedTimeObservation> findTimeObservation(INakedElement e);
}
