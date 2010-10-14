/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.octopus.model;

/** CollectionMetaType holds the constants that indicate whether a certain ICollectionType
 * is a set, sequence, or bag, etc. It is a type safe enumeration build according to item 21 in
 * "Effective Java" by Joshua Bloch.
 *
 * @version $Id: CollectionMetaType.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public class CollectionMetaType {

	public static final CollectionMetaType NOCOLLECTIONTYPE = new CollectionMetaType("no such collection type");
	public static final CollectionMetaType COLLECTION 		= new CollectionMetaType("Collection");
	public static final CollectionMetaType SET 				= new CollectionMetaType("Set");
	public static final CollectionMetaType ORDEREDSET 		= new CollectionMetaType("OrderedSet");
	public static final CollectionMetaType BAG 				= new CollectionMetaType("Bag");
	public static final CollectionMetaType SEQUENCE 		= new CollectionMetaType("Sequence");

	@SuppressWarnings("unused")
	private static final CollectionMetaType[] ALL
			 = {NOCOLLECTIONTYPE, COLLECTION, SET, ORDEREDSET, BAG, SEQUENCE};

	private final String name;
	
	private CollectionMetaType(String name) {
			this.name  = name;
		}

	/**
	 * Returns the correct name of this metatype according to the OCL syntax,
	 * that is, either "Collection", "Set", "OrderedSet", "Bag", or "Sequence".
	 * @return
	 */
	public String getName() { return name; }

	/** Returns the instance of this class that represents one of the standard
	 * OCL collection types.
	 * @param name should be equal to either "Collection", "Set", "OrderedSet", 
	 * "Bag", or "Sequence". If not <code>NOCOLLECTIONTYPE</code> is returned.
	 * @return
	 */
	static public CollectionMetaType getMetaType(String name ){
        if ( name.equals( "Collection")) return COLLECTION; 
        if ( name.equals( "Set"       )) return SET; 
        if ( name.equals( "OrderedSet")) return ORDEREDSET; 
        if ( name.equals( "Bag"  	  )) return BAG; 
        if ( name.equals( "Sequence"  )) return SEQUENCE; 
		return NOCOLLECTIONTYPE;
    } 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { return getName(); }
	
}
