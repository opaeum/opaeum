package net.sf.nakeduml.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedMessageEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedBehaviorImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;
import net.sf.nakeduml.metamodel.statemachines.StateMachineKind;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IState;

public class NakedStateMachineImpl extends NakedBehaviorImpl implements INakedStateMachine{
	private static final long serialVersionUID = -3976968471783003485L;
	static public final String META_CLASS = "stateMachine";
	private StateMachineKind stateMachineKind;
	private List<INakedRegion> regions = new ArrayList<INakedRegion>();
	public NakedStateMachineImpl(){
		super();
	}
	@Override
	protected List<IAttribute> getAllAttributesForOcl(boolean classScope){
		List<IAttribute> results = super.getAllAttributesForOcl(classScope);
		if(!classScope){
			for(INakedParameter p:getArgumentParameters()){
				results.add(new TypedElementPropertyBridge(this, p));
			}
		}
		return results;
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
	protected <T> Set<T> getEvents(boolean messageEventsOnly){
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
	public void removeOwnedElement(INakedElement element){
		super.removeOwnedElement(element);
		if(element instanceof INakedRegion){
			this.regions.remove(element);
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
}
