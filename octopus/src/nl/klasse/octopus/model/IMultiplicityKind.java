/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;


/** MultiplicityKind is the representation of the multiplicity at
 * associationEnds and attribute types.
 *
 * @author  anneke
 * @version $Id: IMultiplicityKind.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IMultiplicityKind {
	// TODO should this one extends IModelElement???
	
	/** Returns true if the upperbound of this multiplicty is not larger
	 *  than one.
	 * 
	 * @return
	 */
	public boolean isSingleObject();
	
	/** Returns true if both the upperbound and the lowerbound are equals of the bounderies
	 * in the 'other' instance.
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(IMultiplicityKind other);

	/**
	 * @return the value of the lower bound
	 */
	public int getLower();

	/**
	 * @return the value of the upper bound
	 */
	public int getUpper();
	
}
