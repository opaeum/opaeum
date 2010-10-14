/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;


/** IState represents a state in Octopus.
 * The only part of statecharts currently used in Octopus are the states,
 * all other information (like transitions, etc.) is not needed.
 * 
 * @author  Jos Warmer
 * @version $Id: IState.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface IState extends IPackageableElement, INameSpace {

    /** Return the owner of this state.
     *  This will _never_ return null.
     * @return Value of property owner.
     */
    public IClassifier getOwner();

	/** Return the collection of substates of this state.
	 * 
	 * @return List[IState]
	 */
	public List<IState> getSubstates();

	/** Return the list of state names starting from the first state.
	 * Note the difference with getPathName() from IModelElement.
	 * 
	 * @return state names in the form of a PathName.
	 */
	public PathName getStatePath() ;

	/** Returns the parent of this state. For instance, if state A has 
	 *  substates B, calling this operation on state B will result in A.
	 *  Calling this operation on state A will result in null.
	 * 
	 * @return
	 */
	public IState getEnclosingState();
}
