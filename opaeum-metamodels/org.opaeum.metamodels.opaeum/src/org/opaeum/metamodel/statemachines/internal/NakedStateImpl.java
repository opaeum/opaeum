package org.opaeum.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.VisibilityKind;

import org.eclipse.uml2.uml.DefaultOpaeumComparator;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedCompletionEvent;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedNameSpace;
import org.eclipse.uml2.uml.INakedRegion;
import org.eclipse.uml2.uml.INakedState;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.IRegionOwner;
import org.eclipse.uml2.uml.StateKind;
import org.opaeum.metamodel.core.internal.NakedNameSpaceImpl;

public class NakedStateImpl extends NakedNameSpaceImpl implements INakedState{
	private static final long serialVersionUID = 1L;
	static public final String META_CLASS = "state";
	private StateKind kind = StateKind.SIMPLE;
	private INakedBehavior entry;
	private INakedBehavior doActivity;
	private INakedBehavior exit;
	private SortedSet<INakedRegion> regions = new TreeSet<INakedRegion>(new DefaultOpaeumComparator());
	private List<INakedTransition> outgoing = new ArrayList<INakedTransition>();
	private List<INakedTransition> incoming = new ArrayList<INakedTransition>();
	private INakedState redefinedState; 
	private INakedCompletionEvent completionEvent;
	public NakedStateImpl(){
		completionEvent=new NakedCompletionEventImpl(this);
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
	public Collection<INakedRegion> getRegions(){
		return this.regions;
	}
	@Override
	public Collection<INakedElement> removeOwnedElement(INakedElement element, boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedRegion){
			this.regions.remove(element);
		}
		return result;
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
			if(t.getTriggers().isEmpty()){
				outgoing.add(t);
			}
		}
		return outgoing;
	}
	public List<IState> getSubstates(){
		List<IState> results = new ArrayList<IState>();
		for(INakedRegion r:getRegions()){
			results.addAll(r.getStates());
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
	public INakedCompletionEvent getCompletionEvent(){
		return completionEvent;
	}
	@Override
	public INakedState getRedefinedState(){
		return redefinedState;
	}
	public void setRedefinedState(INakedState redefinedState){
		this.redefinedState = redefinedState;
	}
}
