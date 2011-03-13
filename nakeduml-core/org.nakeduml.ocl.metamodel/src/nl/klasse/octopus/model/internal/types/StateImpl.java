/** (c) Copyright 2002, Klasse Objecten
 */

package nl.klasse.octopus.model.internal.types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IState;


/**
 * StateImpl : 
 */
/**
 *
 * @author anneke
 * @version $Id: StateImpl.java,v 1.2 2008/01/19 13:33:33 annekekleppe Exp $
 */
/** Implementation of IState for Octopus
 *
 * @author  Jos Warmer
 * @version $Id: StateImpl.java,v 1.2 2008/01/19 13:33:33 annekekleppe Exp $
 */
public class StateImpl extends NameSpaceImpl implements IState {

  private IClassifier 		owner 		   	= null;
  private IState			enclosingState 	= null;
  private List<IState>	substates	   	= new ArrayList<IState>();
  private PathName		statePath	   	= null; 		// holds the names of super states
  
  /** Creates new StateImpl */
  public StateImpl(PathName n) {
      super(n.toString());
      this.statePath = n;
  }
  
  /** Getter for property owner.
   * @return Value of property owner.
   */
  public IClassifier getOwner() {
    return owner;
  }

  /** Setter for property owner.
   * @param owner New value of property owner.
   */
  public void setOwner(IClassifier owner) {
    this.owner = owner;
  }
  
	/**
	 * Returns the substates.
	 * @return List
	 */
	public List<IState> getSubstates() {
		return substates;
	}	

	/**
	 * Method addSubstate.
	 * @param sub: the state to add as substate to this
	 */
	public void addSubstate(IState sub) {
		if ( !substates.contains(sub.getName())) {
			substates.add(sub);
			if (sub instanceof StateImpl) {
				((StateImpl)sub).setEnclosingState(this);
			}
		} 
	}
	
	public String toString() {
		return getStatePath().toString();
	}

	/**
	 * @param string
	 * @return
	 */
	protected IModelElement lookupLocal(String name) {
		Iterator it = this.substates.iterator();
		while( it.hasNext() ){
			IState c = (IState) it.next();
			if( c.getName().equals( name) ){
				return c;
			}
		}
        return null;
	}

	/* Overriden for efficiency.
	 * (non-Javadoc)
	 * @see nl.klasse.octopus.model.INameSpace#lookupOperation(nl.klasse.octopus.expressions.internal.types.PathName, java.util.List)
	 */
	public IOperation lookupOperation(PathName path, List types) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IState#getEnclosingState()
	 */
	public IState getEnclosingState() {
		return enclosingState;
	}
	
	/**
	 * @param state
	 */
	public void setEnclosingState(IState state) {
		enclosingState = state;
	}

	/**
	 * @return
	 */
	public PathName getStatePath() {
		return statePath;
	}

}
