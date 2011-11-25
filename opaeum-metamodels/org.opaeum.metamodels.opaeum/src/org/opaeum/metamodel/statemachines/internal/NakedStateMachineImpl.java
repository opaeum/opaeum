package org.opaeum.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IState;

import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.commonbehaviors.internal.NakedBehaviorImpl;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.metamodel.statemachines.INakedRegion;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.metamodel.statemachines.IRegionOwner;
import org.opaeum.metamodel.statemachines.StateMachineKind;

public class NakedStateMachineImpl extends NakedBehaviorImpl implements INakedStateMachine{
	private static final long serialVersionUID = -3976968471783003485L;
	static public final String META_CLASS = "stateMachine";
	private StateMachineKind stateMachineKind;
	private List<INakedRegion> regions = new ArrayList<INakedRegion>();
	private List<INakedProperty> emulatedAttributes;
	Collection<INakedTimeObservation> timeObservations = new HashSet<INakedTimeObservation>();
	Collection<INakedDurationObservation> durationObservations = new HashSet<INakedDurationObservation>();
	public NakedStateMachineImpl(){
		super();
	}
	public Collection<INakedTimeObservation> getTimeObservations(){
		return timeObservations;
	}
	public Collection<INakedDurationObservation> getDurationObservations(){
		return durationObservations;
	}
	@Override
	protected List<IAttribute> getAllAttributesForOcl(boolean classScope){
		List<IAttribute> results = super.getAllAttributesForOcl(classScope);
		if(!classScope){
			results.addAll(getEmulatedAttributes());
		}
		return results;
	}
	private Collection<? extends IAttribute> getEmulatedAttributes(){
		if(emulatedAttributes == null){
			emulatedAttributes = new ArrayList<INakedProperty>();
			for(INakedParameter p:getArgumentParameters()){
				emulatedAttributes.add(new TypedElementPropertyBridge(this, p));
			}
			for(INakedTimeObservation o:this.timeObservations){
				emulatedAttributes.add(new TypedElementPropertyBridge(this, o));
			}
			for(INakedDurationObservation o:this.durationObservations){
				emulatedAttributes.add(new TypedElementPropertyBridge(this, o));
			}
			if(getSpecification() != null){
				for(INakedParameter v:getSpecification().getOwnedParameters()){
					emulatedAttributes.add(new TypedElementPropertyBridge(this, v));
				}
			}
		}
		return emulatedAttributes;
	}
	public boolean isProcess(){
		return getStateMachineKind() == StateMachineKind.LONG_LIVED;
	}
	@Override
	// For Octopus
	public List<IState> getStates(){
		List<IState> results = new ArrayList<IState>();
		for(INakedState s:getAllStates()){
			// Non-resting states and anonymous states are useless to Octopus -
			// throws exceptions
			if(s.getKind().isRestingState() && s.getName() != null){
				results.add(s);
			}
		}
		return results;
	}
	/**
	 * Returns an array containing all the operations and signals that could possibly trigger a transition in this statemachine
	 */
	public Set<INakedMessageEvent> getAllMessageEvents(){
		boolean messageEventsOnly = true;
		Set<INakedMessageEvent> messageEvents = getEvents(messageEventsOnly);
		return messageEvents;
	}
	@SuppressWarnings("unchecked")
	protected <T>Set<T> getEvents(boolean messageEventsOnly){
		Set<T> messageEvents = new HashSet<T>();
		for(INakedTransition element:getTransitions()){
			Collection<INakedTrigger> triggers = element.getTriggers();
			for(INakedTrigger t:triggers){
				if(messageEventsOnly ? t.getEvent() instanceof INakedMessageEvent : true){
					messageEvents.add((T) t.getEvent());
				}
			}
		}
		return messageEvents;
	}
	public boolean hasEntityContext(){
		return getNameSpace() instanceof INakedEntity;
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedRegion){
			this.regions.add((INakedRegion) element);
		}
		if(element instanceof INakedTimeObservation){
			this.timeObservations.add((INakedTimeObservation) element);
		}
		if(element instanceof INakedDurationObservation){
			this.durationObservations.add((INakedDurationObservation) element);
		}
		emulatedAttributes = null;
	}
	@Override
	public INakedEntity getContext(){
		if(hasEntityContext()){
			return (INakedEntity) getNameSpace();
		}else{
			return null;
		}
	}
	public List<INakedTransition> getTransitions(){
		List<INakedTransition> result = new ArrayList<INakedTransition>();
		for(INakedRegion r:getAllRegions()){
			result.addAll(r.getTransitions());
		}
		return result;
	}
	public IRegionOwner getLeastCommonAncestor(IRegionOwner two){
		return RegionOwnerUtil.getLeastCommonAncestor(this, two);
	}
	public boolean isAncestorOf(IRegionOwner two){
		return RegionOwnerUtil.isAncestorOf(this, two);
	}
	public List<INakedRegion> getAllRegions(){
		return RegionOwnerUtil.getAllRegionsRecursively(this);
	}
	public Set<INakedState> getAllStates(){
		return RegionOwnerUtil.getAllStatesRecursively(this);
	}
	public List<INakedRegion> getRegions(){
		return this.regions;
	}
	@Override
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedRegion){
			this.regions.remove(element);
		}
		if(element instanceof INakedDurationObservation){
			this.durationObservations.remove(element);
		}
		if(element instanceof INakedTimeObservation){
			this.timeObservations.remove(element);
		}
	}
	public INakedRegion getTopMostRegionContaining(INakedState state){
		return RegionOwnerUtil.getTopmostRegionContaining(this, state);
	}
	public StateMachineKind getStateMachineKind(){
		return this.stateMachineKind;
	}
	public void setStateMachineKind(StateMachineKind stateMachineKind){
		this.stateMachineKind = stateMachineKind;
	}
	public INakedRegion getContainer(){
		return null;
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedState;
	}
	@Override
	public Set<INakedEvent> getAllEvents(){
		return getEvents(false);
	}
	@Override
	public Collection<INakedDurationObservation> findDurationObservationFrom(INakedElement e){
		Collection<INakedDurationObservation> result = new HashSet<INakedDurationObservation>();
		for(INakedDurationObservation d:this.durationObservations){
			if(d.getFromObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
	@Override
	public Collection<INakedDurationObservation> findDurationObservationTo(INakedElement e){
		Collection<INakedDurationObservation> result = new HashSet<INakedDurationObservation>();
		for(INakedDurationObservation d:this.durationObservations){
			if(d.getToObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
	@Override
	public Collection<INakedTimeObservation> findTimeObservation(INakedElement e){
		Collection<INakedTimeObservation> result = new HashSet<INakedTimeObservation>();
		for(INakedTimeObservation d:this.timeObservations){
			if(d.getObservedElement() == e){
				result.add(d);
			}
		}
		return result;
	}
}
