package net.sf.nakeduml.metamodel.statemachines;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedNameSpace;
/**
 * A common superclass for model elements that can own regions. In UML2 both
 * statemachines and orthogonal states can own regions.
 */
public interface IRegionOwner extends INakedNameSpace {
	/**
	 * Returns a list of all the regions contained directly or indirectly by
	 * this RegionOwner. Results are order from outer most regions to inner most
	 * regions
	 */
	List<INakedRegion> getAllRegions();
	/**
	 * Returns a list of the regions contained directly by this RegionOwner.
	 */
	List<INakedRegion> getRegions();
	/**
	 * Returns true if this regionOwner is an ancestor of the specified
	 * regionOwner, i.e. it directly or indirectly contains the specified
	 * regionOwner. If this regionOwner is the specified regionOwner, it will
	 * return true;
	 */
	boolean isAncestorOf(IRegionOwner two);
	/**
	 * Returns null if this is a topmost regionOwner, i.e. a StateMachine.
	 * Returns the region that contains the state otherwise
	 */
	INakedRegion getContainer();
	/**
	 * Returns the topmost region inside this region owner that contains the
	 * specified state either directly or indirectly
	 */
	INakedRegion getTopMostRegionContaining(INakedState state);
	IRegionOwner getLeastCommonAncestor(IRegionOwner two);
	/**
	 * Returns all states contained by this region owner recursively down the the inner most region's states
	 * @return
	 */
	Set<INakedState> getAllStates(); 
}
