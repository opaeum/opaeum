/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.utilities.InvariantError;


/** Class ...
 */
abstract public class OJWhileStatementGEN extends OJStatement {
	private String f_condition = "";
	private OJBlock f_body = null;
	static protected boolean usesAllInstances = false;
	static protected List<OJWhileStatement> allInstances = new ArrayList<OJWhileStatement>();

	/** Constructor for OJWhileStatementGEN
	 * 
	 * @param name 
	 * @param comment 
	 * @param condition 
	 */
	protected OJWhileStatementGEN(String name, String comment, String condition) {
		super();
		super.setName(name);
		super.setComment(comment);
		this.setCondition(condition);
		if ( usesAllInstances ) {
			allInstances.add(((OJWhileStatement)this));
		}
	}
	
	/** Default constructor for OJWhileStatement
	 */
	protected OJWhileStatementGEN() {
		super();
		if ( usesAllInstances ) {
			allInstances.add(((OJWhileStatement)this));
		}
	}

	/** Implements the getter for feature '+ condition : String'
	 */
	public String getCondition() {
		return f_condition;
	}
	
	/** Implements the setter for feature '+ condition : String'
	 * 
	 * @param element 
	 */
	public void setCondition(String element) {
		if ( f_condition != element ) {
			f_condition = element;
		}
	}
	
	/** Implements the getter for feature '+ body : OJBlock'
	 */
	public OJBlock getBody() {
		return f_body;
	}
	
	/** Implements the setter for feature '+ body : OJBlock'
	 * 
	 * @param element 
	 */
	public void setBody(OJBlock element) {
		if ( f_body != element ) {
			f_body = element;
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
		if ( getBody() == null ) {
			String message = "Mandatory feature 'body' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJWhileStatement)this), message));
		}
		return result;
	}
	
	/** Default toString implementation for OJWhileStatement
	 */
	public String toString() {
		String result = "";
		result = super.toString();
		if ( this.getCondition() != null ) {
			result = result + " condition:" + this.getCondition();
		}
		return result;
	}
	
	/** Returns the default identifier for OJWhileStatement
	 */
	public String getIdString() {
		String result = "";
		if ( this.getCondition() != null ) {
			result = result + this.getCondition();
		}
		return result;
	}
	
	/** Implements the OCL allInstances operation
	 */
	static public List allInstances() {
		if ( !usesAllInstances ) {
			throw new RuntimeException("allInstances is not implemented for ((OJWhileStatement)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
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
		OJWhileStatement result = new OJWhileStatement();
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
	public void copyInfoInto(OJWhileStatement copy) {
		super.copyInfoInto(copy);
		copy.setCondition(getCondition());
		if ( getBody() != null ) {
			copy.setBody(getBody());
		}
	}

}