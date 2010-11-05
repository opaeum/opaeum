package net.sf.nakeduml.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedBehaviorImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
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
	private Set<INakedElement> stateOperations;
	private List<INakedTransition> transitions = new ArrayList<INakedTransition>();
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
	 * Returns an array containing all the operations and signals that could possibly trigger a transition in this state
	 */
	public List<INakedElement> getAllMessageTriggers(){
		if(this.stateOperations == null){
			this.stateOperations = new HashSet<INakedElement>();
			for(INakedTransition element:transitions){
				INakedElement trigger = element.getTrigger();
				if(trigger instanceof INakedOperation || trigger instanceof INakedSignal){
					this.stateOperations.add(trigger);
				}
			}
		}
		return new ArrayList<INakedElement>(this.stateOperations);
	}
	public boolean hasTriggerFor(INakedOperation o){
		List stateOperations = getAllMessageTriggers();
		for(int i = 0;i < stateOperations.size();i++){
			INakedElement w = (INakedElement) stateOperations.get(i);
			if(o.equals(w)){
				return true;
			}
		}
		return false;
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
		if(element instanceof INakedTransition){
			this.transitions.add((INakedTransition) element);
		}
		this.stateOperations = null;
	}
	@Override
	public INakedEntity getContext(){
		if(hasEntityContext()){
			return (INakedEntity) getNameSpace();
		}else{
			return null;
		}
	}
	public List<INakedTransition> getTransitionsFrom(INakedState fromState){
		List<INakedTransition> results = new ArrayList<INakedTransition>();
		for(INakedTransition tw:this.transitions){
			if(tw.getSource().equals(fromState)){
				results.add(tw);
			}
		}
		return results;
	}
	public List<INakedTransition> getTransitionsTo(INakedState fromState){
		List<INakedTransition> results = new ArrayList<INakedTransition>();
		Iterator iter = this.transitions.iterator();
		while(iter.hasNext()){
			INakedTransition tw = (INakedTransition) iter.next();
			if(tw.getTarget().equals(fromState)){
				results.add(tw);
			}
		}
		return results;
	}
	public List<INakedTransition> getTransitions(){
		return this.transitions;
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
}
