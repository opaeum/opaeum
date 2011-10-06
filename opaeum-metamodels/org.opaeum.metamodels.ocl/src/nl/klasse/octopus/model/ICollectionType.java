/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** ICollectionType represents all collection types in OCL. There are
 *  no separate classes for SetType, OrderedSetType, BagType and SequenceType.
 * 
 * @author  Jos Warmer
 * @version $Id: ICollectionType.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface ICollectionType extends IDataType { 
	
	/** Returns the type of elements in the collection.
	 *  This can, of course, be a ICollectionType itself.
	 * @return IClassifier
	 */
	public IClassifier getElementType();
	
	/**
	 * Returns the metatype, i.e. an indicator that tells whether this collection type is
	 * a set, sequence, bag or orderedSet type.
	 * @return CollectionMetaType
	 */
  	public CollectionMetaType getMetaType();

	/** Returns true when this CollectionType is a set.
	 * @return
	 */
	public boolean isSet();
	/** Returns true when this CollectionType is an ordered set.
	 * @return
	 */
	public boolean isOrderedSet();
	/** Returns true when this CollectionType is a bag.
	 * @return
	 */
	public boolean isBag();
	/** Returns true when this CollectionType is a sequence.
	 * @return
	 */
	public boolean isSequence();

}
