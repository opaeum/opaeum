package org.opeum.metamodel.core.internal;
import java.io.Serializable;

import nl.klasse.octopus.model.IMultiplicityKind;

import org.opeum.metamodel.core.INakedMultiplicity;
public class NakedMultiplicityImpl  implements Serializable, INakedMultiplicity {
	private static final long serialVersionUID = -6607665956639495962L;
	private int lowerbound = 0;	
	private int upperbound = 0;	
	
	public static final int MAX = Integer.MAX_VALUE;	public static final NakedMultiplicityImpl ONE_ONE = new NakedMultiplicityImpl(1, 1);
	public static final IMultiplicityKind ZERO_ONE = new NakedMultiplicityImpl(0, 1);
	public static final IMultiplicityKind ZERO_MANY = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
	public NakedMultiplicityImpl(String lower, String upper) {
		this(calculateLower(parseMultiplicity(lower)), parseMultiplicity(upper));
	}

	/**
	 * Constructor for MultiplicityKind.
	 */
	public NakedMultiplicityImpl(int lower, int upper) {
		this.lowerbound = lower;
		this.upperbound = upper;
	}
	@Override
	public String toString() { 
		if (upperbound == Integer.MAX_VALUE) {
			return lowerbound + "..*";		
		} else {
			return lowerbound + ".." + upperbound;
		} 		
	}
		
	public boolean isSingleObject() {
		return this.upperbound <= 1;
	}
	
	public boolean equals(IMultiplicityKind other){
		if (!(other instanceof NakedMultiplicityImpl)) {
			return false;
		} else { 
			return ( upperbound == ((NakedMultiplicityImpl)other).upperbound) 
			     && (lowerbound == ((NakedMultiplicityImpl)other).lowerbound);
		}
	}

	public int getLower() {
		return lowerbound;
	}
	public int getUpper() {
		return upperbound;
	}
	private static int calculateLower(int lower) {
		return lower = lower == Integer.MAX_VALUE ? 0 : lower;
	}
	private static int parseMultiplicity(String s) {
		s = s.trim();
		if (s.length() == 0) {
			return 1;
		}
		if (s.equals("*") || s.equals("-1")) {
			return Integer.MAX_VALUE;
		}
		int i = Integer.parseInt(s);
		return i;
	}
	public boolean isUnlimited() {
		return getUpper() == Integer.MAX_VALUE;
	}

	public boolean isMany(){
		return getUpper()>1;
	}

	public boolean isOne(){
		return isSingleObject();
	}

	public boolean isRequired(){
		return getLower()>=1;
	}
}
