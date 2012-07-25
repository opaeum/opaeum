package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EmfTimeUtil{
	private static Stereotype getObservationStereotype(Namespace container){
		if(container instanceof StateMachine){
			return StereotypesHelper.getStereotype(container, StereotypeNames.BUSINES_STATE_MACHINE);
		}else if(container instanceof Activity){
			return StereotypesHelper.getStereotype(container, StereotypeNames.BUSINES_PROCESS);
		}else if(container instanceof StructuredActivityNode){
			return StereotypesHelper.getStereotype(container, StereotypeNames.STRUCTURED_BUSINESS_PROCESS_NODE);
		}else{
			return null;
		}
	}
	public static Collection<TimeObservation> findTimeObservationsOn(Namespace container,Element node){
		Collection<TimeObservation> result = new HashSet<TimeObservation>();
		Collection<TimeObservation> value = getTimeObservations(container);
		for(TimeObservation timeObservation:value){
			if(timeObservation.getEvent()==node){
				result.add(timeObservation);
			}
		}
		return result;
	}
	public static Collection<TimeObservation> getTimeObservations(Namespace container){
		Collection<TimeObservation> value = getObservations(container, "timeObservations");
		return value;
	}
	private static <T extends Observation> Collection<T> getObservations(Namespace container,String propName){
		@SuppressWarnings("unchecked")
		Collection<T> value = (Collection<T>) container.getValue(getObservationStereotype(container), propName);
		if(value ==null){
			value=Collections.emptySet();
		}
		return value;
	}

	public static Collection<DurationObservation> findDurationObservationsFrom(Namespace container,Element node){
		Collection<DurationObservation> result = new HashSet<DurationObservation>();
		Collection<DurationObservation> value = getDurationObservations(container);
		for(DurationObservation durObservation:value){
			if(durObservation.getEvents().size()==2 && durObservation.getEvents().get(0) ==node){
				result.add(durObservation);
			}
		}
		return result;
	}
	public static Collection<DurationObservation> getDurationObservations(Namespace container){
		Collection<DurationObservation> value = getObservations(container,"durationObservations");
		return value;
	}

	public static Collection<DurationObservation> findDurationObservationsTo(Namespace container,Element node){
		Collection<DurationObservation> result = new HashSet<DurationObservation>();
		Collection<DurationObservation> value = getDurationObservations(container);
		for(DurationObservation durObservation:value){
			if(durObservation.getEvents().size()==2 && durObservation.getEvents().get(1) ==node){
				result.add(durObservation);
			}
		}
		return result;
	}
}
