/*
 * Created on Dec 26, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.model;

/**
 * IStructuralFeature represents a structural feature of IClassifier, i.e. an attribute
 * or association end.
 * 
 * @version $Id: IStructuralFeature.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IStructuralFeature extends IPackageableElement {

	/** Returns the type of the structural feature. Note that this will take the multiplicity
	 *  in account and return a CollectionType is the multiplicity is larger than one.
	 * 
	 * @return IClassifier
	 */
	public IClassifier getType();
	 
	/** Returns the signature of the structural feature as a String.
	 * 
	 * @return String
	 */
	public String getSignature();

	/** Returns the classifier that holds the structural feature.
	 *  This will _never_ return null.
	 * 
	 * @return IClassifier
	 */
	public IClassifier getOwner();
		
	/** Returns the multiplicity.
	 * 
	 * @return int A constant from MultiplicityKind.
	 */
	public IMultiplicityKind getMultiplicity();
		
	/** Returns whether or not this structural feature is ordered (i.e. an OrderedSet or Sequence)
	 * @return
	 */
	public boolean isOrdered();
	
	/**
	 * Returns whether or not this structural feature is derived.
	 * @return
	 */
	public boolean isDerived();
	
	/**
	 * Returns whether or not elements in this structural feature are unique (i.e a Set or OrderedSet).
	 * @return boolean
	 */
	public boolean isUnique();

	/**
	 * Returns whether or not this structural feature is has a lifetime dependency upon its owner.
	 * @return boolean
	 */
	public boolean isComposite();
	
	/**
	 * Returns whether or not this structural feature is marker 'aggregate'.
	 * @return boolean
	 */
	public boolean isAggregate();
	
	/** Returns true if this attribute has class scope.
	 *  
	 * @return boolean
	 */
	boolean hasClassScope();
}
