package net.sf.nakeduml.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.internal.NakedNameSpaceImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;
import net.sf.nakeduml.metamodel.statemachines.StateKind;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.VisibilityKind;

public class NakedStateImpl extends NakedNameSpaceImpl implements INakedState{
	private static final long serialVersionUID = 1L;
	static public final String META_CLASS = "state";
	private StateKind kind = StateKind.SIMPLE;
	private INakedBehavior entry;
	private INakedBehavior doActivity;
	private INakedBehavior exit;
	private List<INakedRegion> regions = new ArrayList<INakedRegion>();
	private List<INakedTransition> outgoing=new ArrayList<INakedTransition>();
	private List<INakedTransition> incoming=new ArrayList<INakedTransition>();
	public NakedStateImpl(){
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
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		element.setOwnerElement(this);
		if(element instanceof INakedRegion){
			this.regions.add((INakedRegion) element);
		}
	}
	public INakedRegion getTopMostRegionContaining(INakedState state){
		return RegionOwnerUtil.getTopmostRegionContaining(this, state);
	}
	public List<INakedTransition> getIncoming(){
		return this.incoming;
	}
	public List<INakedTransition> getOutgoing(){
		return this.outgoing;
	}
	public List<INakedBehavior> getActions(){
		List<INakedBehavior> results = new ArrayList<INakedBehavior>();
		if(getEntry() != null){
			results.add(getEntry());
		}
		if(getExit() != null){
			results.add(getExit());
		}
		if(getDoActivity() != null){
			results.add(getDoActivity());
		}
		return results;
	}
	@Override
	public List<INakedTransition> getCompletionTransitions(){
		List<INakedTransition> outgoing = new ArrayList<INakedTransition>();
		for(INakedTransition t:getOutgoing()){
			if(t.getTrigger() == null){
				outgoing.add(t);
			}
		}
		return outgoing;
	}
	@Override
	public Collection<INakedTransition> getTimeTriggerTransitions(){
		Collection<INakedTransition> results = new ArrayList<INakedTransition>();
		for(INakedTransition t:this.getOutgoing()){
			if(t.getTrigger() !=null && t.getTrigger().getEvent() instanceof INakedTimeEvent){
				results.add(t);
			}
		}
		return results;
	}
	public List<IState> getSubstates(){
		List<IState> results = new ArrayList<IState>();
		Iterator iter = getRegions().iterator();
		while(iter.hasNext()){
			INakedRegion region = (INakedRegion) iter.next();
			results.addAll(region.getStates());
		}
		return results;
	}
	public INakedStateMachine getStateMachine(){
		INakedNameSpace parent = getNameSpace();
		while(!(parent == null || parent instanceof INakedStateMachine)){
			parent = parent.getNameSpace();
		}
		return (NakedStateMachineImpl) parent;
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	public StateKind getKind(){
		return this.kind;
	}
	public INakedRegion getContainer(){
		return (INakedRegion) getNameSpace();
	}
	public boolean hasEnclosingState(){
		return getContainer().hasOwningState();
	}
	public INakedClassifier getOwner(){
		return getStateMachine();
	}
	public PathName getStatePath(){
		PathName results = null;
		if(getContainer().hasOwningState()){
			results = getContainer().getOwningState().getStatePath();
		}else{
			results = new PathName();
		}
		results.addString(getName());
		return results;
	}
	public INakedState getEnclosingState(){
		if(hasEnclosingState()){
			return getContainer().getOwningState();
		}else{
			return null;
		}
	}
	public void setKind(StateKind kind){
		this.kind = kind;
	}
	public INakedBehavior getDoActivity(){
		return this.doActivity;
	}
	public void setDoActivity(INakedBehavior doActivity){
		this.doActivity = doActivity;
	}
	public INakedBehavior getEntry(){
		return this.entry;
	}
	public void setEntry(INakedBehavior entry){
		this.entry = entry;
	}
	public INakedBehavior getExit(){
		return this.exit;
	}
	public void setExit(INakedBehavior exit){
		this.exit = exit;
	}
	@Override
	public VisibilityKind getVisibility(){
		return VisibilityKind.PUBLIC;
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> children = new HashSet<INakedElement>(super.getOwnedElements());
		children.addAll(getActions());
		return children;
	}
	@Override
	public void removeFromOutgoing(INakedTransition nakedTransitionImpl){
		this.outgoing.remove(nakedTransitionImpl);
		
	}
	@Override
	public void addToOutgoing(INakedTransition nakedTransitionImpl){
		this.outgoing.add(nakedTransitionImpl);
		
	}
	@Override
	public void removeFromIncoming(INakedTransition nakedTransitionImpl){
		this.incoming.remove(nakedTransitionImpl);
		
	}
	@Override
	public void addToIncoming(INakedTransition nakedTransitionImpl){
		this.incoming.add(nakedTransitionImpl);
		
	}
}
