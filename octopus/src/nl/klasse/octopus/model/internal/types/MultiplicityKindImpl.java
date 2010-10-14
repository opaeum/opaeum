/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.tools.common.Check;


/** MultiplicityKind is the representation of the multiplicity at
 * associationEnds and attribute types.
 *
 * @author  anneke
 * @version $Id: MultiplicityKindImpl.java,v 1.1 2006/03/01 19:13:34 jwarmer Exp $
 */
public class MultiplicityKindImpl implements IMultiplicityKind {
	private int lowerbound = 0;	
	private int upperbound = 0;	
	
	public static final MultiplicityKindImpl UNKNOWN = new MultiplicityKindImpl();
	public static final int MAX = Integer.MAX_VALUE;
	/**
	 * Constructor for MultiplicityKind.
	 */
	public MultiplicityKindImpl(int lower, int upper) {
		Check.isTrue("upper bound must not be less than lower bound", upper >= lower);
		Check.isTrue("lower bound must be >= 0", lower >= 0);
		Check.isTrue("upper bound must be > 0" , upper > 0);
		this.lowerbound = lower;
		this.upperbound = upper;
	}
	
	/** Private constructor to create the UNKNOWN multiplicity value
	 */
	private MultiplicityKindImpl() {
		this.lowerbound = 0;
		this.upperbound = 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		if (upperbound == Integer.MAX_VALUE) {
			return lowerbound + "..*";		
		} else {
			return lowerbound + ".." + upperbound;
		} 		
	}
		
	/** Returns true if the upperbound of this multiplicty is not larger
	 *  than one.
	 * 
	 * @return
	 */
	public boolean isSingleObject() {
		return this.upperbound <= 1;
	}
	
	/** Returns true if both the upperbound and the lowerbound are equals of the bounderies
	 * in the 'other' instance.
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(IMultiplicityKind other){
		if (!(other instanceof MultiplicityKindImpl)) {
			return false;
		} else { 
			return ( upperbound == ((MultiplicityKindImpl)other).upperbound) 
			     && (lowerbound == ((MultiplicityKindImpl)other).lowerbound);
		}
	}
	
	/** Returns true if 'mult' is not equal to MultiplicityKind.UNKNOWN.
	 * 
	 * @param mult
	 * @return
	 */
	public static boolean isValid(IMultiplicityKind mult){
		return ! ( mult.equals(UNKNOWN) );
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IMultiplicityKind#getLower()
	 */
	public int getLower() {
		return lowerbound;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IMultiplicityKind#getUpper()
	 */
	public int getUpper() {
		return upperbound;
	}
}
