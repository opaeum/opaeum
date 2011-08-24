/*
 * File generated by Grasland Grammar Generator on Dec 23, 2006 7:26:03 PM
 */
package org.nakeduml.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJElement;
import org.nakeduml.java.metamodel.OJInterface;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibleElement;
import org.nakeduml.java.metamodel.utilities.InvariantError;


/** Class ...
 */
abstract public class OJClassifierGEN extends OJVisibleElement {
	private int f_uniqueNumber = 0;
	private boolean f_isDerived = false;
	private boolean f_isAbstract = false;
	private Set<OJOperation> f_operations = new HashSet<OJOperation>();
	private Set<OJPathName> f_imports = new HashSet<OJPathName>();
	private OJPackage f_myPackage = null;
	static protected boolean usesAllInstances = false;
	static protected List<OJClassifier> allInstances = new ArrayList<OJClassifier>();

	/** Constructor for OJClassifierGEN
	 * 
	 * @param name 
	 * @param comment 
	 * @param isStatic 
	 * @param isFinal 
	 * @param isVolatile 
	 * @param uniqueNumber 
	 * @param isDerived 
	 * @param isAbstract 
	 */
	protected OJClassifierGEN(String name, String comment, boolean isStatic, boolean isFinal, boolean isVolatile, int uniqueNumber, boolean isDerived, boolean isAbstract) {
		super();
		super.setName(name);
		super.setComment(comment);
		super.setStatic(isStatic);
		super.setFinal(isFinal);
		super.setVolatile(isVolatile);
		this.setUniqueNumber(uniqueNumber);
		this.setDerived(isDerived);
		this.setAbstract(isAbstract);
		this.setDerived( true );
		if ( usesAllInstances ) {
			allInstances.add(((OJClassifier)this));
		}
	}
	
	/** Default constructor for OJClassifier
	 */
	protected OJClassifierGEN() {
		super();
		this.setDerived( true );
		if ( usesAllInstances ) {
			allInstances.add(((OJClassifier)this));
		}
	}

	/** Implements the user defined operation '+ findIdentOper() : OJOperation'
	 */
	public OJOperation findIdentOper() {
		return any1();
	}
	
	/** Implements the user defined operation '+ findToString() : OJOperation'
	 */
	public OJOperation findToString() {
		return any2();
	}
	
	/** Implements the user defined operation '+ findOperation( name: String, types: Sequence(OJPathName) ) : OJOperation'
	 * 
	 * @param name 
	 * @param types 
	 */
	public OJOperation findOperation(String name, List<OJPathName> types) {
		return (any3(name, types));
	}
	
	/** Implements the user defined operation '+ getPathName() : OJPathName'
	 */
	public OJPathName getPathName() {
		return this.getMyPackage().getPathName().append(this.getName());
	}
	
	/** Implements the getter for feature '+ uniqueNumber : Integer'
	 */
	public int getUniqueNumber() {
		return f_uniqueNumber;
	}
	
	/** Implements the setter for feature '+ uniqueNumber : Integer'
	 * 
	 * @param element 
	 */
	public void setUniqueNumber(int element) {
		if ( f_uniqueNumber != element ) {
			f_uniqueNumber = element;
		}
	}
	
	/** Implements the getter for feature '+ isDerived : Boolean'
	 */
	public boolean isDerived() {
		return f_isDerived;
	}
	
	/** Implements the setter for feature '+ isDerived : Boolean'
	 * 
	 * @param element 
	 */
	public void setDerived(boolean element) {
		if ( f_isDerived != element ) {
			f_isDerived = element;
		}
	}
	
	/** Implements the getter for feature '+ isAbstract : Boolean'
	 */
	public boolean isAbstract() {
		return f_isAbstract;
	}
	
	/** Implements the setter for feature '+ isAbstract : Boolean'
	 * 
	 * @param element 
	 */
	public void setAbstract(boolean element) {
		if ( f_isAbstract != element ) {
			f_isAbstract = element;
		}
	}
	
	/** Implements the setter for feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param elements 
	 */
	public void setOperations(Set<OJOperation> elements) {
		if ( this.f_operations != elements ) {
			Iterator it = this.f_operations.iterator();
			while ( it.hasNext() ) {
				OJOperation x = (OJOperation) it.next();
				x.z_internalRemoveFromOwner( (OJClassifier)((OJClassifier)this) );
			}
			this.f_operations = elements;
			if ( f_operations != null ) {
				it = f_operations.iterator();
				while ( it.hasNext() ) {
					OJOperation x = (OJOperation) it.next();
					x.z_internalAddToOwner( (OJClassifier)((OJClassifier)this) );
				}
			}
		}
	}
	
	/** Implements addition of a single element to feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param element 
	 */
	public void addToOperations(OJOperation element) {
		if(element.getName().equals("processSignal")){
			System.out.println();
		}
		if ( element == null ) {
			return;
		}
		if ( this.f_operations.contains(element) ) {
			this.f_operations.remove(element);
		}
		this.f_operations.add(element);
		if ( element.getOwner() != null ) {
			element.getOwner().z_internalRemoveFromOperations(element);
		}
		element.z_internalAddToOwner( (OJClassifier)((OJClassifier)this) );
	}
	
	/** Implements removal of a single element from feature '+ operations : OrderedSet(OJOperation)'
	 * 
	 * @param element 
	 */
	public void removeFromOperations(OJOperation element) {
		if ( element == null ) {
			return;
		}
		this.f_operations.remove(element);
		element.z_internalRemoveFromOwner( (OJClassifier)((OJClassifier)this) );
	}
	
	/** Implements the getter for + operations : OrderedSet(OJOperation)
	 */
	public Set<OJOperation> getOperations() {
		if ( f_operations != null ) {
			return Collections.unmodifiableSet(f_operations);
		} else {
			return null;
		}
	}
	
	/** This operation should NOT be used by clients. It implements the correct addition of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalAddToOperations(OJOperation element) {
		this.f_operations.add(element);
	}
	
	/** This operation should NOT be used by clients. It implements the correct removal of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalRemoveFromOperations(OJOperation element) {
		this.f_operations.remove(element);
	}
	
	/** Implements the addition of a number of elements to operations
	 * 
	 * @param newElems 
	 */
	public void addToOperations(Collection<OJOperation> newElems) {
		Iterator it = newElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJOperation ) {
				this.addToOperations((OJOperation)item);
			}
		}
	}
	
	/** Implements the removal of a number of elements from operations
	 * 
	 * @param oldElems 
	 */
	public void removeFromOperations(Collection<OJOperation> oldElems) {
		Iterator it = oldElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJOperation ) {
				this.removeFromOperations((OJOperation)item);
			}
		}
	}
	
	/** Implements the removal of all elements from operations
	 */
	public void removeAllFromOperations() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		Iterator it = new ArrayList<OJOperation>(getOperations()).iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJOperation ) {
				this.removeFromOperations((OJOperation)item);
			}
		}
	}
	
	/** Implements the getter for feature '+ imports : Set(OJPathName)'
	 */
	public Set<OJPathName> getImports() {
		return f_imports;
	}
	
	/** Implements the setter for feature '+ imports : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void setImports(Set<OJPathName> element) {
		if ( f_imports != element ) {
			f_imports = element;
		}
	}
	
	/** Implements the add element function for feature '+ imports : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void addToImports(OJPathName element) {
		if ( f_imports.contains(element) ) {
			return;
		}
		f_imports.add(element);
	}
	
	/** Implements the remove element function for feature '+ imports : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void removeFromImports(OJPathName element) {
		f_imports.remove(element);
	}
	
	/** Implements the addition of a number of elements to feature '+ imports : Set(OJPathName)'
	 * 
	 * @param newElems 
	 */
	public void addToImports(Collection<OJPathName> newElems) {
		Iterator it = newElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJPathName ) {
				addToImports((OJPathName)item);
			}
		}
	}
	
	/** Implements the removal of a number of elements from feature '+ imports : Set(OJPathName)'
	 * 
	 * @param oldElems 
	 */
	public void removeFromImports(Collection<OJPathName> oldElems) {
		Iterator it = oldElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJPathName ) {
				removeFromImports((OJPathName)item);
			}
		}
	}
	
	/** Implements the removal of all elements from feature '+ imports : Set(OJPathName)'
	 */
	public void removeAllFromImports() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		Iterator it = new HashSet<OJPathName>(getImports()).iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJPathName ) {
				removeFromImports((OJPathName)item);
			}
		}
	}
	
	/** Implements the getter for feature '+ myPackage : OJPackage'
	 */
	public OJPackage getMyPackage() {
		setMyPackage(((this.getClass() == OJClass.class) ?
			((OJClass) ((OJClassifier)this)).getMyPackage() :
			((this.getClass() == OJInterface.class) ?
				(OJPackage)((OJInterface) ((OJClassifier)this)).getMyPackage() :
				(OJPackage)null)));
		return f_myPackage;
	}
	
	/** Implements the setter for feature '+ myPackage : OJPackage'
	 * 
	 * @param element 
	 */
	protected void setMyPackage(OJPackage element) {
		if ( f_myPackage != element ) {
			f_myPackage = element;
		}
	}
	
	/** Implements ->any( o : OJOperation | o.name = 'getIdentifyingString' )
	 */
	private OJOperation any1() {
		OJOperation result = null;
		Iterator it = this.getOperations().iterator();
		while ( it.hasNext() ) {
			OJOperation o = (OJOperation) it.next();
			if ( o.getName().equals("getIdentifyingString") ) {
				return o;
			}
		}
		return result;
	}
	
	/** Implements ->any( o : OJOperation | o.name = 'toString' )
	 */
	private OJOperation any2() {
		OJOperation result = null;
		Iterator it = this.getOperations().iterator();
		while ( it.hasNext() ) {
			OJOperation o = (OJOperation) it.next();
			if ( o.getName().equals("toString") ) {
				return o;
			}
		}
		return result;
	}
	
	/** Implements ->any( o : OJOperation | o.isEqual(name, types) )
	 * 
	 * @param name 
	 * @param types 
	 */
	private OJOperation any3(String name, List<OJPathName> types) {
		OJOperation result = null;
		Iterator it = this.getOperations().iterator();
		while ( it.hasNext() ) {
			OJOperation o = (OJOperation) it.next();
			if ( (o.isEqual(name, types)) ) {
				return o;
			}
		}
		return result;
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
		if ( getMyPackage() == null ) {
			String message = "Mandatory feature 'myPackage' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(((OJClassifier)this), message));
		}
		return result;
	}
	
	/** Default toString implementation for OJClassifier
	 */
	public String toString() {
		String result = "";
		result = super.toString();
		result = result + " uniqueNumber:" + this.getUniqueNumber() + ", ";
		result = result + " isDerived:" + this.isDerived() + ", ";
		result = result + " isAbstract:" + this.isAbstract();
		return result;
	}
	
	/** Returns the default identifier for OJClassifier
	 */
	public String getIdString() {
		String result = "";
		result = result + this.getUniqueNumber();
		return result;
	}
	
	/** Implements the OCL allInstances operation
	 */
	static public List allInstances() {
		if ( !usesAllInstances ) {
			throw new RuntimeException("allInstances is not implemented for ((OJClassifier)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
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
		OJClassifier result = new OJClassifier();
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
	public void copyInfoInto(OJClassifier copy) {
		super.copyInfoInto(copy);
		copy.setUniqueNumber(getUniqueNumber());
		copy.setDerived(isDerived());
		copy.setAbstract(isAbstract());
		Iterator operationsIt = new ArrayList<OJOperation>(getOperations()).iterator();
		while ( operationsIt.hasNext() ) {
			OJOperation elem = (OJOperation) operationsIt.next();
			copy.addToOperations(elem);
		}
		Iterator importsIt = new ArrayList<OJPathName>(getImports()).iterator();
		while ( importsIt.hasNext() ) {
			OJPathName elem = (OJPathName) importsIt.next();
			copy.addToImports(elem);
		}
	}

}