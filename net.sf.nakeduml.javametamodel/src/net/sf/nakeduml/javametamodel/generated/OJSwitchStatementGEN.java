/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package net.sf.nakeduml.javametamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJSwitchCase;
import net.sf.nakeduml.javametamodel.OJSwitchStatement;
import net.sf.nakeduml.javametamodel.utilities.InvariantError;

/** Class ...
 */
abstract public class OJSwitchStatementGEN extends OJStatement {
	private String f_condition = "";
	private Set<OJSwitchCase> f_cases = new HashSet<OJSwitchCase>();
	private OJSwitchCase f_defCase = null;
	static protected boolean usesAllInstances = false;
	static protected List<OJSwitchStatement> allInstances = new ArrayList<OJSwitchStatement>();

	/** Default constructor for OJSwitchStatement
	 */
	protected OJSwitchStatementGEN() {
		super();
		if ( usesAllInstances ) {
			allInstances.add(((OJSwitchStatement)this));
		}
	}
	
	/** Constructor for OJSwitchStatementGEN
	 * 
	 * @param name 
	 * @param comment 
	 * @param condition 
	 */
	protected OJSwitchStatementGEN(String name, String comment, String condition) {
		super();
		super.setName(name);
		super.setComment(comment);
		this.setCondition(condition);
		if ( usesAllInstances ) {
			allInstances.add(((OJSwitchStatement)this));
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
	
	/** Implements the getter for feature '+ cases : Set(OJSwitchCase)'
	 */
	public Set<OJSwitchCase> getCases() {
		return f_cases;
	}
	
	/** Implements the setter for feature '+ cases : Set(OJSwitchCase)'
	 * 
	 * @param element 
	 */
	public void setCases(Set<OJSwitchCase> element) {
		if ( f_cases != element ) {
			f_cases = element;
		}
	}
	
	/** Implements the add element function for feature '+ cases : Set(OJSwitchCase)'
	 * 
	 * @param element 
	 */
	public void addToCases(OJSwitchCase element) {
		if ( f_cases.contains(element) ) {
			return;
		}
		f_cases.add(element);
	}
	
	/** Implements the remove element function for feature '+ cases : Set(OJSwitchCase)'
	 * 
	 * @param element 
	 */
	public void removeFromCases(OJSwitchCase element) {
		f_cases.remove(element);
	}
	
	/** Implements the addition of a number of elements to feature '+ cases : Set(OJSwitchCase)'
	 * 
	 * @param newElems 
	 */
	public void addToCases(Collection<OJSwitchCase> newElems) {
		Iterator it = newElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJSwitchCase ) {
				addToCases((OJSwitchCase)item);
			}
		}
	}
	
	/** Implements the removal of a number of elements from feature '+ cases : Set(OJSwitchCase)'
	 * 
	 * @param oldElems 
	 */
	public void removeFromCases(Collection<OJSwitchCase> oldElems) {
		Iterator it = oldElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJSwitchCase ) {
				removeFromCases((OJSwitchCase)item);
			}
		}
	}
	
	/** Implements the removal of all elements from feature '+ cases : Set(OJSwitchCase)'
	 */
	public void removeAllFromCases() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		Iterator it = new HashSet<OJSwitchCase>(getCases()).iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJSwitchCase ) {
				removeFromCases((OJSwitchCase)item);
			}
		}
	}
	
	/** Implements the getter for feature '+ defCase : OJSwitchCase'
	 */
	public OJSwitchCase getDefCase() {
		return f_defCase;
	}
	
	/** Implements the setter for feature '+ defCase : OJSwitchCase'
	 * 
	 * @param element 
	 */
	public void setDefCase(OJSwitchCase element) {
		if ( f_defCase != element ) {
			f_defCase = element;
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
		if ( getCases().size() < 1 ) {
			String message = "Lower bound of feature 'cases' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' is 1" +
			  			", yet it has size " + getCases().size() + ".";
			result.add(new InvariantError(((OJSwitchStatement)this), message));
		}
		if ( getDefCase() == null ) {
			String message = "Mandatory feature 'defCase' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJSwitchStatement)this), message));
		}
		return result;
	}
	
	/** Default toString implementation for OJSwitchStatement
	 */
	public String toString() {
		String result = "";
		result = super.toString();
		if ( this.getCondition() != null ) {
			result = result + " condition:" + this.getCondition();
		}
		return result;
	}
	
	/** Returns the default identifier for OJSwitchStatement
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
			throw new RuntimeException("allInstances is not implemented for ((OJSwitchStatement)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
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
		OJSwitchStatement result = new OJSwitchStatement();
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
	public void copyInfoInto(OJSwitchStatement copy) {
		super.copyInfoInto(copy);
		copy.setCondition(getCondition());
		Iterator casesIt = new ArrayList<OJSwitchCase>(getCases()).iterator();
		while ( casesIt.hasNext() ) {
			OJSwitchCase elem = (OJSwitchCase) casesIt.next();
			copy.addToCases(elem);
		}
		if ( getDefCase() != null ) {
			copy.setDefCase(getDefCase());
		}
	}

}