/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.library;

import java.util.HashMap;

import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;
import nl.klasse.octopus.stdlib.internal.types.StdlibOperation;


/** The class StdlibCollections creates all collection types and operations
 *  from the OCL Standard Library. It also keeps an internal list of already
 *  created collection types to make sure that they are only created once.
 * 
 * @author  Jos Warmer
 * @version $Id: StdlibCollections.java,v 1.2 2008/01/19 13:31:08 annekekleppe Exp $
 */
public class StdlibCollections  {

    private HashMap<String, StdlibCollectionType> collectionTypes = null;
    private HashMap<String, StdlibCollectionType> setTypes = null;
    private HashMap<String, StdlibCollectionType> orderedSetTypes = null;
    private HashMap<String, StdlibCollectionType> bagTypes = null;
    private HashMap<String, StdlibCollectionType> sequenceTypes = null;
    
    public StdlibCollections  () {
    	initialize();
    }

	/**
	 * Method initialize.
	 */
	public void initialize() {   
	    collectionTypes = new HashMap<String, StdlibCollectionType>();
	    setTypes = new HashMap<String, StdlibCollectionType>();
	    orderedSetTypes = new HashMap<String, StdlibCollectionType>();
	    bagTypes = new HashMap<String, StdlibCollectionType>();
	    sequenceTypes = new HashMap<String, StdlibCollectionType>();
	}
	
    private ICollectionType createCollectionType(IClassifier elemType) {
        String key = elemType.getPathName().toString();
        
        StdlibCollectionType result = (StdlibCollectionType)collectionTypes.get(key);
        if( result != null ){ // found the type. it exists.
            return result; 
        }
        result = new StdlibCollectionType (CollectionMetaType.COLLECTION, elemType);
        collectionTypes.put(key, result);
        
        result.addOperation( new StdlibOperation ("size"      , StdlibBasic.OCL_Integer) );
        result.addOperation( new StdlibOperation ("flatten"   , StdlibBasic.DependsOnSourceType) );
        result.addOperation( new StdlibOperation ("isEmpty"   , StdlibBasic.OCL_Boolean) );
        result.addOperation( new StdlibOperation ("notEmpty"  , StdlibBasic.OCL_Boolean) );
        result.addOperation( new StdlibOperation ("sum"       , elemType ) );
		result.addOperation( new StdlibOperation ("asSequence", createSequenceType(elemType)) );
		result.addOperation( new StdlibOperation ("asOrderedSet", createOrderedSetType(elemType)) );
        result.addOperation( new StdlibOperation ("asSet"     , createSetType(elemType) ));
        result.addOperation( new StdlibOperation ("asBag"     , createBagType(elemType) ));
        result.addOperation( new StdlibOperation ("includes"   , "object", elemType , StdlibBasic.OCL_Boolean) );
        result.addOperation( new StdlibOperation ("excludes"   , "object", elemType , StdlibBasic.OCL_Boolean) );
        result.addOperation( new StdlibOperation ("count"      , "object", elemType , StdlibBasic.OCL_Integer) );
        result.addOperation( new StdlibOperation ("includesAll", "coll", result, StdlibBasic.OCL_Boolean) );
        result.addOperation( new StdlibOperation ("excludesAll", "coll", result, StdlibBasic.OCL_Boolean) );

        return result;
    }
    
    private ICollectionType createSetType(IClassifier elemType) {
        String key = buildKey(elemType);

        StdlibCollectionType result = (StdlibCollectionType)setTypes.get(key);
        if( result != null ){ // found the type. it exists.
            return result; 
        }
        result = new StdlibCollectionType (CollectionMetaType.SET, elemType);
        setTypes.put(key, result);

        result.addOperation( new StdlibOperation ("union"       , "coll", result, result) );
        result.addOperation( new StdlibOperation ("union"       , "coll", createBagType(elemType) , createBagType(elemType) ) );
        result.addOperation( new StdlibOperation ("="           , "coll", result, StdlibBasic.OCL_Boolean, true) );
        result.addOperation( new StdlibOperation ("-"           , "coll", result, result, true) );
        result.addOperation( new StdlibOperation ("symmetricDifference", "coll", result, result) );
        result.addOperation( new StdlibOperation ("intersection", "coll", result, result) );
        result.addOperation( new StdlibOperation ("intersection", "coll", createBagType(elemType) , result) );
        result.addOperation( new StdlibOperation ("including"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("excluding"   , "coll", elemType, result) );
        result.addGeneralization(createCollectionType(elemType) );
        
        return result;
    }

	private String buildKey(IClassifier elemType) {
		String key = elemType.getPathName().toString();
        if (elemType.isCollectionKind()) {
        	key = key + "_" + buildKey( ((ICollectionType) elemType).getElementType());
        }
		return key;
	}

    private ICollectionType createBagType(IClassifier elemType) {
        String key = buildKey(elemType);

        StdlibCollectionType result = (StdlibCollectionType)bagTypes.get(key);
        if( result != null ){ // found the type. it exists.
            return result; 
        }
        result = new StdlibCollectionType (CollectionMetaType.BAG, elemType);
        bagTypes.put(key, result);

        result.addOperation( new StdlibOperation ("union"       , "coll", result, result) );
        result.addOperation( new StdlibOperation ("union"       , "coll", createSetType(elemType), result) );
        result.addOperation( new StdlibOperation ("="           , "coll", result, StdlibBasic.OCL_Boolean, true) );
        result.addOperation( new StdlibOperation ("intersection", "coll", result, result) );
        result.addOperation( new StdlibOperation ("intersection", "coll", createSetType(elemType), createSetType(elemType)) );
        result.addOperation( new StdlibOperation ("including"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("excluding"   , "coll", elemType, result) );
       //    flatten() : Bag(T2);

        result.addGeneralization( createCollectionType(elemType));
        return result;
    }

    private ICollectionType createSequenceType(IClassifier elemType) {
        String key = buildKey(elemType);

        StdlibCollectionType result = (StdlibCollectionType)sequenceTypes.get(key);
        if( result != null ){ // found the type. it exists.
            return result; 
        }
        result = new StdlibCollectionType (CollectionMetaType.SEQUENCE, elemType);
        sequenceTypes.put(key, result);

        result.addOperation( new StdlibOperation ("first"       , elemType) );
        result.addOperation( new StdlibOperation ("last"        , elemType) );
        result.addOperation( new StdlibOperation ("="           , "coll", result, StdlibBasic.OCL_Boolean, true) );
        result.addOperation( new StdlibOperation ("union"       , "coll", result, result) );
        result.addOperation( new StdlibOperation ("union"       , "coll", createSetType(elemType), createBagType(elemType)) );
        result.addOperation( new StdlibOperation ("append"      , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("prepend"     , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("at"          , "index", StdlibBasic.OCL_Integer, elemType) );
        result.addOperation( new StdlibOperation ("indexOf"     , "obj" , elemType, StdlibBasic.OCL_Integer) );
        result.addOperation( new StdlibOperation ("including"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("excluding"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("insertAt", "index", StdlibBasic.OCL_Integer,
                                                              "object", elemType, result) );
        result.addOperation( new StdlibOperation ("subSequence","from", StdlibBasic.OCL_Integer,
                                                                "to"  , StdlibBasic.OCL_Integer, result) );

        result.addGeneralization( createCollectionType(elemType));
        return result;
    }

	/**
	 * Method lookupType.
	 * @param name
	 * @param elemType
	 * @return ICollectionType
	 */
	public synchronized ICollectionType lookupType(CollectionMetaType type, IClassifier elemType) {
        if( type == CollectionMetaType.COLLECTION ){
            return createCollectionType(elemType);
        } else if ( type == CollectionMetaType.SET) {
            return createSetType(elemType);
        } else if ( type == CollectionMetaType.BAG) {
            return createBagType(elemType);
        } else if ( type == CollectionMetaType.SEQUENCE) {
            return createSequenceType(elemType);
        } else if ( type == CollectionMetaType.ORDEREDSET) {
            return createOrderedSetType(elemType);
        } else {
            return null;
        }
	}

	/**
	 * Method createOrderedSetType.
	 * Added by Anneke d.d. 01/07/03
	 * @param elemType
	 * @return ICollectionType
	 */
	private ICollectionType createOrderedSetType(IClassifier elemType) {
        String key = buildKey(elemType);

        StdlibCollectionType result = (StdlibCollectionType)orderedSetTypes.get(key);
        if( result != null ){ // found the type. it exists.
            return result; 
        }
        result = new StdlibCollectionType (CollectionMetaType.ORDEREDSET, elemType);
        orderedSetTypes.put(key, result);

        result.addOperation( new StdlibOperation ("union"       , "coll", result, result) );
        result.addOperation( new StdlibOperation ("union"       , "coll", createBagType(elemType) , createBagType(elemType) ) );
        result.addOperation( new StdlibOperation ("="           , "coll", result, StdlibBasic.OCL_Boolean, true) );
        result.addOperation( new StdlibOperation ("-"           , "coll", result, result, true) );
        result.addOperation( new StdlibOperation ("-"           , "coll", createSetType(elemType) , result, true ));
        result.addOperation( new StdlibOperation ("symmetricDifference", "coll", result, result) );
        result.addOperation( new StdlibOperation ("intersection", "coll", result, result) );
        result.addOperation( new StdlibOperation ("intersection", "coll", createBagType(elemType) , result) );
        result.addOperation( new StdlibOperation ("including"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("excluding"   , "coll", elemType, result) );

        result.addOperation( new StdlibOperation ("first"       , elemType) );
        result.addOperation( new StdlibOperation ("last"        , elemType) );
        result.addOperation( new StdlibOperation ("="           , "coll", result, StdlibBasic.OCL_Boolean, true) );
        result.addOperation( new StdlibOperation ("union"       , "coll", result, result) );
        result.addOperation( new StdlibOperation ("union"       , "coll", createSetType(elemType), createBagType(elemType)) );
        result.addOperation( new StdlibOperation ("append"      , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("prepend"     , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("at"          , "index", StdlibBasic.OCL_Integer, elemType) );
        result.addOperation( new StdlibOperation ("indexOf"     , "obj" , elemType, StdlibBasic.OCL_Integer) );
        result.addOperation( new StdlibOperation ("including"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("excluding"   , "coll", elemType, result) );
        result.addOperation( new StdlibOperation ("insertAt"	, "index", StdlibBasic.OCL_Integer,
                                                              	  "object", elemType, result) );
        result.addOperation( new StdlibOperation ("subOrderedSet", "from", StdlibBasic.OCL_Integer,
                                                                  "to"  , StdlibBasic.OCL_Integer, result) );

        result.addGeneralization(createCollectionType(elemType) );
        
        return result;
    }
}
