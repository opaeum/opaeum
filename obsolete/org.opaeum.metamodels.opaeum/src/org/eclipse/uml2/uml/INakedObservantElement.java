package org.eclipse.uml2.uml;

import java.util.Collection;
import java.util.HashSet;


public interface INakedObservantElement{
	Collection<INakedTimeObservation> getTimeObservations();
	Collection<INakedDurationObservation> getDurationObservations();
	public Collection<INakedDurationObservation> findDurationObservationFrom(INakedElement e);
	public Collection<INakedDurationObservation> findDurationObservationTo(INakedElement e);
	Collection<INakedTimeObservation> findTimeObservation(INakedElement e);
}
