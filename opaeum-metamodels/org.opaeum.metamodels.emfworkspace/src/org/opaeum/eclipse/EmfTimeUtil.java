package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.eclipse.emulated.ObservationPropertyBridge;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmfTimeUtil{
	public static boolean isDeadline(Event e){
		return e instanceof TimeEvent && StereotypesHelper.hasKeyword(e, StereotypeNames.DEADLINE);
	}
	private static Stereotype getObservationStereotype(NamedElement container){
		for(Stereotype st:container.getAppliedStereotypes()){
			if(st.getMember(TagNames.DURATION_OBSERVATIONS) != null){
				return st;
			}
		}
		return null;
	}
	public static Collection<TimeObservation> findTimeObservationsOn(Namespace container,Element node){
		Collection<TimeObservation> result = new TreeSet<TimeObservation>(new ElementComparator());
		Collection<TimeObservation> value = getTimeObservations(container);
		for(TimeObservation timeObservation:value){
			if(timeObservation.getEvent() == node){
				result.add(timeObservation);
			}
		}
		return result;
	}
	public static Collection<TimeObservation> getTimeObservations(NamedElement container){
		Collection<TimeObservation> value = getObservations(container, "timeObservations");
		return value;
	}
	@SuppressWarnings("unchecked")
	private static <T extends Observation>Collection<T> getObservations(NamedElement container,String propName){
		Stereotype st = getObservationStereotype(container);
		Collection<T> value = null;
		if(st == null){
			value = Collections.emptySet();
		}else{
			value = (Collection<T>) container.getValue(st, propName);
			if(value == null){
				value = Collections.emptySet();
			}
		}
		return value;
	}
	public static Collection<DurationObservation> findDurationObservationsFrom(Namespace container,Element node){
		Collection<DurationObservation> result = new TreeSet<DurationObservation>(new ElementComparator());
		Collection<DurationObservation> value = getDurationObservations(container);
		for(DurationObservation durObservation:value){
			if(durObservation.getEvents().size() == 2 && durObservation.getEvents().get(0) == node){
				result.add(durObservation);
			}
		}
		return result;
	}
	public static Collection<DurationObservation> getDurationObservations(NamedElement container){
		Collection<DurationObservation> value = getObservations(container, "durationObservations");
		return value;
	}
	public static Collection<DurationObservation> findDurationObservationsTo(Namespace container,Element node){
		Collection<DurationObservation> result = new TreeSet<DurationObservation>(new ElementComparator());
		Collection<DurationObservation> value = getDurationObservations(container);
		for(DurationObservation durObservation:value){
			if(durObservation.getEvents().size() == 2 && durObservation.getEvents().get(1) == node){
				result.add(durObservation);
			}
		}
		return result;
	}
	public static EList<ObservationPropertyBridge> buildObservationPropertiess(Classifier owner,IPropertyEmulation e,Namespace element){
		EList<ObservationPropertyBridge> result = new BasicEList<ObservationPropertyBridge>();
		for(EObject eObject:element.getStereotypeApplications()){
			EStructuralFeature feature = eObject.eClass().getEStructuralFeature("timeObservations");
			if(feature != null){
				EList<TimeObservation> obs = (EList<TimeObservation>) eObject.eGet(feature);
				for(TimeObservation to:obs){
					result.add(new ObservationPropertyBridge(owner, to, e));
				}
			}
			feature = eObject.eClass().getEStructuralFeature("durationObservations");
			if(feature != null){
				EList<DurationObservation> obs = (EList<DurationObservation>) eObject.eGet(feature);
				for(DurationObservation to:obs){
						result.add(new ObservationPropertyBridge(owner, to, e));
				}
			}
		}
		return result;
	}
	public static boolean isCumulative(DurationObservation to){
		Stereotype st = StereotypesHelper.getStereotype(to, StereotypeNames.BUSINESS_DURATION_OBSERVATION);
		boolean b = st != null && Boolean.TRUE.equals(to.getValue(st, TagNames.IS_CUMULATIVE));
		return b;
	}
	public static boolean isDurationBasedCostObservation(DurationObservation dob){
		return StereotypesHelper.hasStereotype(dob, StereotypeNames.DURATION_BASED_COST_OBSERVATION);
	}
}
