package net.sf.nakeduml.metamodel.statemachines;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedNameSpace;
public interface INakedRegion extends INakedNameSpace {
	List<INakedState> getStates();
	IRegionOwner getRegionOwner();
	/**
	 * Returns all the regions in this region's RegionOwner excluding this
	 * region
	 */
	Set<INakedRegion> getPeerRegions();
	boolean hasPeerRegions();
	boolean hasFinalStates();
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
	List<INakedTransition> getTransitions();
	boolean hasInitialState();
}
