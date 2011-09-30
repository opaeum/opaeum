/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package org.opeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.List;

import org.opeum.java.metamodel.OJElement;
import org.opeum.java.metamodel.utilities.InvariantError;


/** Class ...
 */
abstract public class OJElementGEN {
	private String f_name = "";
	private String f_comment = "";
	static protected boolean usesAllInstances = false;
	static protected List<OJElement> allInstances = new ArrayList<OJElement>();

	/** Default constructor for OJElement
	 */
	protected OJElementGEN() {
		if ( usesAllInstances ) {
			allInstances.add(((OJElement)this));
		}
	}
	
	/** Constructor for OJElementGEN
	 * 
	 * @param name 
	 * @param comment 
	 */
	protected OJElementGEN(String name, String comment) {
		super();
		this.setName(name);
		this.setComment(comment);
		if ( usesAllInstances ) {
			allInstances.add(((OJElement)this));
		}
	}

	/** Implements the getter for feature '+ name : String'
	 */
	public String getName() {
		return f_name;
	}
	
	/** Implements the setter for feature '+ name : String'
	 * 
	 * @param element 
	 */
	public void setName(String element) {
		if ( f_name != element ) {
			f_name = element;

		}
	}
	
	/** Implements the getter for feature '+ comment : String'
	 */
	public String getComment() {
		return f_comment;
	}
	
	/** Implements the setter for feature '+ comment : String'
	 * 
	 * @param element 
	 */
	public void setComment(String element) {
		if ( f_comment != element ) {
			f_comment = element;
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
	
	/** Default toString implementation for OJElement
	 */
	public String toString() {
		String result = "";
		if ( this.getName() != null ) {
			result = result + " name:" + this.getName() + ", ";
		}
		if ( this.getComment() != null ) {
			result = result + " comment:" + this.getComment();
		}
		return result;
	}
	
	/** Returns the default identifier for OJElement
	 */
	public String getIdString() {
		String result = "";
		if ( this.getName() != null ) {
			result = result + this.getName();
		}
		return result;
	}
	
	/** Implements the OCL allInstances operation
	 */
	static public List allInstances() {
		if ( !usesAllInstances ) {
			throw new RuntimeException("allInstances is not implemented for ((OJElement)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
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
		OJElement result = new OJElement();
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
	public void copyInfoInto(OJElement copy) {
		copy.setName(getName());
		copy.setComment(getComment());
	}

}