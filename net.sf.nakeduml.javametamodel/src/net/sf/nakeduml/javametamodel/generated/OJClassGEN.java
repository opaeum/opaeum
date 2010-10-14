/*
 * File generated by Octopus Code Generator on Dec 29, 2006 11:21:31 AM
 */
package net.sf.nakeduml.javametamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.utilities.InvariantError;

/** Class ...
 */
abstract public class OJClassGEN extends OJClassifier {
	private boolean f_needsSuppress = false;
	private List<OJField> f_fields = new ArrayList<OJField>();
	private OJPackage f_myPackage = null;
	private Set<OJConstructor> f_constructors = new HashSet<OJConstructor>();
	private Set<OJPathName> f_implementedInterfaces = new HashSet<OJPathName>();
	private OJPathName f_superclass = null;
	static protected boolean usesAllInstances = false;
	static protected List<OJClass> allInstances = new ArrayList<OJClass>();

	/** Default constructor for OJClass
	 */
	protected OJClassGEN() {
		super();
		if ( usesAllInstances ) {
			allInstances.add(((OJClass)this));
		}
	}
	
	/** Constructor for OJClassGEN
	 * 
	 * @param name 
	 * @param comment 
	 * @param isStatic 
	 * @param isFinal 
	 * @param isVolatile 
	 * @param uniqueNumber 
	 * @param isDerived 
	 * @param isAbstract 
	 * @param needsSuppress 
	 */
	protected OJClassGEN(String name, String comment, boolean isStatic, boolean isFinal, boolean isVolatile, int uniqueNumber, boolean isDerived, boolean isAbstract, boolean needsSuppress) {
		super();
		super.setName(name);
		super.setComment(comment);
		super.setStatic(isStatic);
		super.setFinal(isFinal);
		super.setVolatile(isVolatile);
		super.setUniqueNumber(uniqueNumber);
		super.setDerived(isDerived);
		super.setAbstract(isAbstract);
		this.setNeedsSuppress(needsSuppress);
		if ( usesAllInstances ) {
			allInstances.add(((OJClass)this));
		}
	}

	/** Implements the user defined operation '+ getDefaultConstructor() : OJConstructor'
	 */
	public OJConstructor getDefaultConstructor() {
		return any1();
	}
	
	/** Implements the user defined operation '+ findField( name: String ) : OJField'
	 * 
	 * @param name 
	 */
	public OJField findField(String name) {
		return any2(name);
	}
	
	/** Implements the getter for attribute '+ needsSuppress : Boolean'
	 */
	public boolean getNeedsSuppress() {
		return f_needsSuppress;
	}
	
	/** Implements the setter for attribute '+ needsSuppress : Boolean'
	 * 
	 * @param element 
	 */
	public void setNeedsSuppress(boolean element) {
		if ( f_needsSuppress != element ) {
			f_needsSuppress = element;
		}
	}
	
	/** XXXX implements the setter for association end '+ fields : OrderedSet(OJField)'
	 * 
	 * @param elements 
	 */
	public void setFields(List<OJField> elements) {
		if ( this.f_fields != elements ) {
			for ( OJField _internal : this.f_fields ) {
				_internal.z_internalRemoveFromOwner(((OJClass)this));
			}
			this.f_fields = elements;
			if ( f_fields != null ) {
				for ( OJField _internal : f_fields ) {
					_internal.z_internalAddToOwner(((OJClass)this));
				}
			}
		}
	}
	
	/** Implements addition of a single element to association end '+ fields : OrderedSet(OJField)'
	 * 
	 * @param element 
	 */
	public void addToFields(OJField element) {
		if ( element == null ) {
			return;
		}
		if ( this.f_fields.contains(element) ) {
			return;
		}
		this.f_fields.add(element);
		if ( element.getOwner() != null ) {
			element.getOwner().z_internalRemoveFromFields(element);
		}
		element.z_internalAddToOwner(((OJClass)this));
	}
	
	/** Implements removal of a single element from association end '+ fields : OrderedSet(OJField)'
	 * 
	 * @param element 
	 */
	public void removeFromFields(OJField element) {
		if ( element == null ) {
			return;
		}
		this.f_fields.remove(element);
		element.z_internalRemoveFromOwner(((OJClass)this));
	}
	
	/** Implements the getter for association end '+ fields : OrderedSet(OJField)'
	 */
	public List<OJField> getFields() {
		if ( f_fields != null ) {
			return Collections.unmodifiableList(f_fields);
		} else {
			return null;
		}
	}
	
	/** This operation should NOT be used by clients. It implements the correct addition of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalAddToFields(OJField element) {
		this.f_fields.add(element);
	}
	
	/** This operation should NOT be used by clients. It implements the correct removal of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalRemoveFromFields(OJField element) {
		this.f_fields.remove(element);
	}
	
	/** XXXX implements the addition of a number of elements to association end '+ fields : OrderedSet(OJField)'
	 * 
	 * @param newElems 
	 */
	public void addToFields(Collection<OJField> newElems) {
		for ( OJField item : newElems ) {
			this.addToFields(item);
		}
	}
	
	/** Implements the removal of a number of elements from association end '+ fields : OrderedSet(OJField)'
	 * 
	 * @param oldElems 
	 */
	public void removeFromFields(Collection<OJField> oldElems) {
		for ( OJField item : oldElems ) {
			this.removeFromFields(item);
		}
	}
	
	/** Implements the removal of all elements from association end '+ fields : OrderedSet(OJField)'
	 */
	public void removeAllFromFields() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		for ( OJField item : new ArrayList<OJField>(getFields()) ) {
			this.removeFromFields(item);
		}
	}
	
	/** Implements the setter of association end '+ myPackage : OJPackage'
	 * 
	 * @param element 
	 */
	public void setMyPackage(OJPackage element) {
		if ( this.f_myPackage != element ) {
			if ( this.f_myPackage != null ) {
				this.f_myPackage.z_internalRemoveFromClasses(((OJClass)this));
			}
			this.f_myPackage = element;
			if ( element != null ) {
				element.z_internalAddToClasses(((OJClass)this));
			}
		}
	}
	
	/** Implements the getter for association end '+ myPackage : OJPackage'
	 */
	public OJPackage getMyPackage() {
		return f_myPackage;
	}
	
	/** Should NOT be used by clients! Implements the correct setting of the link for association end '+ myPackage : OJPackage' 
						when a single element is added to it.
	 * 
	 * @param element 
	 */
	public void z_internalAddToMyPackage(OJPackage element) {
		this.f_myPackage = element;
	}
	
	/** Should NOT be used by clients! Implements the correct setting of the link for association end '+ myPackage : OJPackage' 
						when a single element is removed to it.
	 * 
	 * @param element 
	 */
	public void z_internalRemoveFromMyPackage(OJPackage element) {
		this.f_myPackage = null;
	}
	
	/** XXXX implements the setter for association end '+ constructors : Set(OJConstructor)'
	 * 
	 * @param elements 
	 */
	public void setConstructors(Set<OJConstructor> elements) {
		if ( this.f_constructors != elements ) {
			for ( OJConstructor _internal : this.f_constructors ) {
				_internal.z_internalRemoveFromOwningClass(((OJClass)this));
			}
			this.f_constructors = elements;
			if ( f_constructors != null ) {
				for ( OJConstructor _internal : f_constructors ) {
					_internal.z_internalAddToOwningClass(((OJClass)this));
				}
			}
		}
	}
	
	/** Implements addition of a single element to association end '+ constructors : Set(OJConstructor)'
	 * 
	 * @param element 
	 */
	public void addToConstructors(OJConstructor element) {
		if ( element == null ) {
			return;
		}
		if ( this.f_constructors.contains(element) ) {
			return;
		}
		this.f_constructors.add(element);
		if ( element.getOwningClass() != null ) {
			element.getOwningClass().z_internalRemoveFromConstructors(element);
		}
		element.z_internalAddToOwningClass(((OJClass)this));
	}
	
	/** Implements removal of a single element from association end '+ constructors : Set(OJConstructor)'
	 * 
	 * @param element 
	 */
	public void removeFromConstructors(OJConstructor element) {
		if ( element == null ) {
			return;
		}
		this.f_constructors.remove(element);
		element.z_internalRemoveFromOwningClass(((OJClass)this));
	}
	
	/** Implements the getter for association end '+ constructors : Set(OJConstructor)'
	 */
	public Set<OJConstructor> getConstructors() {
		if ( f_constructors != null ) {
			return Collections.unmodifiableSet(f_constructors);
		} else {
			return null;
		}
	}
	
	/** This operation should NOT be used by clients. It implements the correct addition of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalAddToConstructors(OJConstructor element) {
		this.f_constructors.add(element);
	}
	
	/** This operation should NOT be used by clients. It implements the correct removal of an element in an association.
	 * 
	 * @param element 
	 */
	public void z_internalRemoveFromConstructors(OJConstructor element) {
		this.f_constructors.remove(element);
	}
	
	/** XXXX implements the addition of a number of elements to association end '+ constructors : Set(OJConstructor)'
	 * 
	 * @param newElems 
	 */
	public void addToConstructors(Collection<OJConstructor> newElems) {
		for ( OJConstructor item : newElems ) {
			this.addToConstructors(item);
		}
	}
	
	/** Implements the removal of a number of elements from association end '+ constructors : Set(OJConstructor)'
	 * 
	 * @param oldElems 
	 */
	public void removeFromConstructors(Collection<OJConstructor> oldElems) {
		for ( OJConstructor item : oldElems ) {
			this.removeFromConstructors(item);
		}
	}
	
	/** Implements the removal of all elements from association end '+ constructors : Set(OJConstructor)'
	 */
	public void removeAllFromConstructors() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		for ( OJConstructor item : new HashSet<OJConstructor>(getConstructors()) ) {
			this.removeFromConstructors(item);
		}
	}
	
	/** Implements the getter for association end '+ implementedInterfaces : Set(OJPathName)'
	 */
	public Set<OJPathName> getImplementedInterfaces() {
		return f_implementedInterfaces;
	}
	
	/** Implements the setter for association end '+ implementedInterfaces : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void setImplementedInterfaces(Set<OJPathName> element) {
		if ( f_implementedInterfaces != element ) {
			f_implementedInterfaces = element;
		}
	}
	
	/** Implements the add element function for association end '+ implementedInterfaces : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void addToImplementedInterfaces(OJPathName element) {
		if ( f_implementedInterfaces.contains(element) ) {
			return;
		}
		f_implementedInterfaces.add(element);
	}
	
	/** Implements the remove element function for association end '+ implementedInterfaces : Set(OJPathName)'
	 * 
	 * @param element 
	 */
	public void removeFromImplementedInterfaces(OJPathName element) {
		f_implementedInterfaces.remove(element);
	}
	
	/** Implements the addition of a number of elements to association end '+ implementedInterfaces : Set(OJPathName)'
	 * 
	 * @param newElems 
	 */
	public void addToImplementedInterfaces(Collection<OJPathName> newElems) {
		for ( OJPathName item : newElems ) {
			addToImplementedInterfaces(item);
		}
	}
	
	/** Implements the removal of a number of elements from association end '+ implementedInterfaces : Set(OJPathName)'
	 * 
	 * @param oldElems 
	 */
	public void removeFromImplementedInterfaces(Collection<OJPathName> oldElems) {
		for ( OJPathName item : oldElems ) {
			removeFromImplementedInterfaces(item);
		}
	}
	
	/** Implements the removal of all elements from association end '+ implementedInterfaces : Set(OJPathName)'
	 */
	public void removeAllFromImplementedInterfaces() {
		/* make a copy of the collection in order to avoid a ConcurrentModificationException*/
		for ( OJPathName item : new HashSet<OJPathName>(getImplementedInterfaces()) ) {
			removeFromImplementedInterfaces(item);
		}
	}
	
	/** Implements the getter for association end '+ superclass : OJPathName'
	 */
	public OJPathName getSuperclass() {
		return f_superclass;
	}
	
	/** Implements the setter for association end '+ superclass : OJPathName'
	 * 
	 * @param element 
	 */
	public void setSuperclass(OJPathName element) {
		if ( f_superclass != element ) {
			f_superclass = element;
		}
	}
	
	/** Implements ->any( c : OJConstructor | c.parameters->isEmpty() )
	 */
	private OJConstructor any1() {
		OJConstructor result = null;
		Iterator it = this.getConstructors().iterator();
		while ( it.hasNext() ) {
			OJConstructor c = (OJConstructor) it.next();
			if ( c.getParameters().isEmpty() ) {
				return c;
			}
		}
		return result;
	}
	
	/** Implements ->any( f : OJField | f.name = name )
	 * 
	 * @param name 
	 */
	private OJField any2(String name) {
		OJField result = null;
		Iterator it = this.getFields().iterator();
		while ( it.hasNext() ) {
			OJField f = (OJField) it.next();
			if ( f.getName().equals(name) ) {
				return f;
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
			result.add(new InvariantError(((OJClass)this), message));
		}
		if ( getConstructors().size() < 1 ) {
			String message = "Lower bound of feature 'constructors' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' is 1" +
			  			", yet it has size " + getConstructors().size() + ".";
			result.add(new InvariantError(((OJClass)this), message));
		}
		return result;
	}
	
	/** Returns the default identifier for OJClass
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
			throw new RuntimeException("allInstances is not implemented for ((OJClass)this) class. Set usesAllInstances to true, if you want allInstances() implemented.");
		}
		return allInstances;
	}

}