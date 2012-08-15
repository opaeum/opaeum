package org.eclipse.uml2.uml;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface INakedRegion extends INakedNameSpace {
	Collection<INakedState> getStates();
	IRegionOwner getRegionOwner();
	/**
	 * Returns all the regions in this region's RegionOwner excluding this
	 * region
	 */
	Set<INakedRegion> getPeerRegions();
	boolean hasPeerRegions();
	/**
	 * Returns the state that should be taken as the initial state for this
	 * region. This state could either be an Initial or a History pseudo state
	 */
	INakedState getInitialState();
	boolean hasOwningState();
	INakedState getOwningState();
	boolean hasOwningStateMachine();
	INakedStateMachine getOwningStateMachine();
	boolean contains(INakedState state);
	Collection<INakedTransition> getTransitions();
	boolean hasInitialState();
}
