/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package org.opeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.List;

import org.opeum.java.metamodel.OJElement;
import org.opeum.java.metamodel.OJVisibilityKind;
import org.opeum.java.metamodel.OJVisibleElement;
import org.opeum.java.metamodel.utilities.InvariantError;


/** Class ...
 */
abstract public class OJVisibleElementGEN extends OJElement {
	private boolean f_isStatic = false;
	private boolean f_isFinal = false;
	private boolean f_isVolatile = false;
	private OJVisibilityKind f_visibility = OJVisibilityKind.lookup(0);
	static protected boolean usesAllInstances = false;
	static protected List<OJVisibleElement> allInstances = new ArrayList<OJVisibleElement>();

	/** Default constructor for OJVisibleElement
	 */
	protected OJVisibleElementGEN() {
		super();
		this.setVisibility( OJVisibilityKind.PUBLIC );
		if ( usesAllInstances ) {
			allInstances.add(((OJVisibleElement)this));
		}
	}
	
	/** Constructor for OJVisibleElementGEN
	 * 
	 * @param name 
	 * @param comment 
	 * @param isStatic 
	 * @param isFinal 
	 * @param isVolatile 
	 */
	protected OJVisibleElementGEN(String name, String comment, boolean isStatic, boolean isFinal, boolean isVolatile) {
		super();
		super.setName(name);
		super.setComment(comment);
		this.setStatic(isStatic);
		this.setFinal(isFinal);
		this.setVolatile(isVolatile);
		this.setVisibility( OJVisibilityKind.PUBLIC );
		if ( usesAllInstances ) {
			allInstances.add(((OJVisibleElement)this));
		}
	}

	/** Implements the getter for feature '+ isStatic : Boolean'
	 */
	public boolean isStatic() {
		return f_isStatic;
	}
	
	/** Implements the setter for feature '+ isStatic : Boolean'
	 * 
	 * @param element 
	 */
	public void setStatic(boolean element) {
		if ( f_isStatic != element ) {
			f_isStatic = element;
		}
	}
	
	/** Implements the getter for feature '+ isFinal : Boolean'
	 */
	public boolean isFinal() {
		return f_isFinal;
	}
	
	/** Implements the setter for feature '+ isFinal : Boolean'
	 * 
	 * @param element 
	 */
	public void setFinal(boolean element) {
		if ( f_isFinal != element ) {
			f_isFinal = element;
		}
	}
	
	/** Implements the getter for feature '+ isVolatile : Boolean'
	 */
	public boolean isVolatile() {
		return f_isVolatile;
	}
	
	/** Implements the setter for feature '+ isVolatile : Boolean'
	 * 
	 * @param element 
	 */
	public void setVolatile(boolean element) {
		if ( f_isVolatile != element ) {
			f_isVolatile = element;
		}
	}
	
	/** Implements the getter for feature '+ visibility : OJVisibilityKind'
	 */
	public OJVisibilityKind getVisibility() {
		return f_visibility;
	}
	
	/** Implements the setter for feature '+ visibility : OJVisibilityKind'
	 * 
	 * @param element 
	 */
	public void setVisibility(OJVisibilityKind element) {
		if ( f_visibility != element ) {
			f_visibility = element;
		}
	}
	
	/** Checks all invariants of this object and returns a list of messages about broken invariants
	 */
	public List<InvariantError> checkAllInvariants() {
		List<InvariantError> result = new ArrayList<InvariantError>();
		return result;
	}
	
	/** Implements a check on the multiplicities of all attributes and association ends
	 */
	public List<InvariantError> checkMultiplicities() {
		List<InvariantError> result = new ArrayList<InvariantError>();
		return result;
	}
	
	/** Default toString implementation for OJVisibleElement
	 */
	public String toString() {
		String result = "";
		result = super.toString();
		result = result + " isStatic:" + this.isStatic() + ", ";
		result = result + " isFinal:" + this.isFinal() + ", ";
		result = result + " isVolatile:" + this.isVolatile() + ", ";
		if ( this.getVisibility() != null ) {
			result = result + " visibility:" + this.getVisibility();
		}
		return result;
	}
	
	/** Returns the default identifier for OJVisibleElement
	 */
	public String getIdString() {
		String result = "";
		result = super.getIdString();
		return result;
	}
	
	/** Implements the OCL allInstances operation
	 */
	static public List allInstances() {
		if ( !usesAllInstances ) {
			throw new RuntimeException("allInstances is not implemented for ((OJVisibleElement)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
		}
		return allInstances;
	}
	
	/** Returns a copy of this instance. True parts, i.e. associations marked
			'aggregate' or 'composite', and attributes, are copied as well. References to
			other objects, i.e. associations not marked 'aggregate' or 'composite', will not
			be copied. The returned copy will refer to the same objects as the original (this)
			instance.
	 */
	public OJElement getCopy() {
		OJVisibleElement result = new OJVisibleElement();
		this.copyInfoInto(result);
		return result;
	}
	
	/** Copies all attributes and associations of this instance into 'copy'.
			True parts, i.e. associations marked 'aggregate' or 'composite', and attributes, 
			are copied as well. References to other objects, i.e. associations not marked 
			'aggregate' or 'composite', will not be copied. The 'copy' will refer 
			to the same objects as the original (this) instance.
	 * 
	 * @param copy 
	 */
	public void copyInfoInto(OJVisibleElement copy) {
		super.copyInfoInto(copy);
		copy.setStatic(isStatic());
		copy.setFinal(isFinal());
		copy.setVolatile(isVolatile());
		copy.setVisibility(getVisibility());
	}

}