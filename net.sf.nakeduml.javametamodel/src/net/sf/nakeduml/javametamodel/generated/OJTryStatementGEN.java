/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package net.sf.nakeduml.javametamodel.generated;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.utilities.InvariantError;

/** Class ...
 */
abstract public class OJTryStatementGEN extends OJStatement {
	private OJBlock f_tryPart = null;
	private OJBlock f_catchPart = null;
	private OJParameter f_catchParam = null;
	static protected boolean usesAllInstances = false;
	static protected List<OJTryStatement> allInstances = new ArrayList<OJTryStatement>();

	/** Constructor for OJTryStatementGEN
	 * 
	 * @param name 
	 * @param comment 
	 */
	protected OJTryStatementGEN(String name, String comment) {
		super();
		super.setName(name);
		super.setComment(comment);
		if ( usesAllInstances ) {
			allInstances.add(((OJTryStatement)this));
		}
	}
	
	/** Default constructor for OJTryStatement
	 */
	protected OJTryStatementGEN() {
		super();
		if ( usesAllInstances ) {
			allInstances.add(((OJTryStatement)this));
		}
	}

	/** Implements the getter for feature '+ tryPart : OJBlock'
	 */
	public OJBlock getTryPart() {
		return f_tryPart;
	}
	
	/** Implements the setter for feature '+ tryPart : OJBlock'
	 * 
	 * @param element 
	 */
	public void setTryPart(OJBlock element) {
		f_tryPart = element;
		if ( f_tryPart != element ) {
		}
	}
	
	/** Implements the getter for feature '+ catchPart : OJBlock'
	 */
	public OJBlock getCatchPart() {
		return f_catchPart;
	}
	
	/** Implements the setter for feature '+ catchPart : OJBlock'
	 * 
	 * @param element 
	 */
	public void setCatchPart(OJBlock element) {
		if ( f_catchPart != element ) {
			f_catchPart = element;
		}
	}
	
	/** Implements the getter for feature '+ catchParam : OJParameter'
	 */
	public OJParameter getCatchParam() {
		return f_catchParam;
	}
	
	/** Implements the setter for feature '+ catchParam : OJParameter'
	 * 
	 * @param element 
	 */
	public void setCatchParam(OJParameter element) {
		if ( f_catchParam != element ) {
			f_catchParam = element;
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
		if ( getTryPart() == null ) {
			String message = "Mandatory feature 'tryPart' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJTryStatement)this), message));
		}
		if ( getCatchPart() == null ) {
			String message = "Mandatory feature 'catchPart' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJTryStatement)this), message));
		}
		if ( getCatchParam() == null ) {
			String message = "Mandatory feature 'catchParam' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJTryStatement)this), message));
		}
		return result;
	}
	
	/** Default toString implementation for OJTryStatement
	 */
	public String toString() {
		String result = "";
		result = super.toString();
		return result;
	}
	
	/** Returns the default identifier for OJTryStatement
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
			throw new RuntimeException("allInstances is not implemented for ((OJTryStatement)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
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
		OJTryStatement result = new OJTryStatement();
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
	public void copyInfoInto(OJTryStatement copy) {
		super.copyInfoInto(copy);
		if ( getTryPart() != null ) {
			copy.setTryPart(getTryPart());
		}
		if ( getCatchPart() != null ) {
			copy.setCatchPart(getCatchPart());
		}
		if ( getCatchParam() != null ) {
			copy.setCatchParam(getCatchParam());
		}
	}

}