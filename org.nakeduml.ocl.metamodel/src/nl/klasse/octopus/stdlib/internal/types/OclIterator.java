/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.HashMap;

import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclIterator;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** OclIterator represents the Iterators that are defined in the OCL Standard Library.
 *  There is only a fixed number of iterators, users cannot add new iterators.
 * 
 * @author  Jos Warmer
 * @version $Id: OclIterator.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 */
public class OclIterator implements IOclIterator  {

    protected static OclIterator OCL_Select        		= new OclIterator("select");
    protected static OclIterator OCL_ForAll        		= new OclIterator("forAll");
    protected static OclIterator OCL_Exists        		= new OclIterator("exists");
    protected static OclIterator OCL_Reject        		= new OclIterator("reject");
    protected static OclIterator OCL_Collect       		= new OclIterator("collect");
    protected static OclIterator OCL_IsUnique      	= new OclIterator("isUnique");
    protected static OclIterator OCL_SortedBy      	= new OclIterator("sortedBy");
    protected static OclIterator OCL_AnyIterator   	= new OclIterator("any");
    protected static OclIterator OCL_One          	 	= new OclIterator("one");
    protected static OclIterator OCL_CollectNested	= new OclIterator("collectNested");
    protected static OclIterator OCL_Iterate       		= new OclIterator("iterate");

    private static HashMap<String, OclIterator> iterators = new HashMap<String, OclIterator>();
    private String name = null;

    /**
	 * Constructor OclIterator.
	 * @param string
	 */
	private OclIterator(String string) {
        name = string;
	}


    public static OclIterator getOclIterator(String name) {
       OclIterator result = (OclIterator) iterators.get(name);
       return result;
    }

    public static void initialize() {
        iterators.put("select"       , OCL_Select);        
        iterators.put("reject"       , OCL_Reject);        
        iterators.put("exists"       , OCL_Exists);        
        iterators.put("forAll"       , OCL_ForAll);        
        iterators.put("collect"      , OCL_Collect);        
        iterators.put("collectNested", OCL_CollectNested);        
        iterators.put("isUnique"     , OCL_IsUnique);        
        iterators.put("sortedBy"     , OCL_SortedBy);        
        iterators.put("one"          , OCL_One);        
        iterators.put("any"          , OCL_AnyIterator);        
        iterators.put("iterate"      , OCL_Iterate);        
    }	    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Check whether the type of the body is correct.
     */
    public boolean checkBodyType(IClassifier bodyType) {
        if( (this == OCL_Exists) || (this == OCL_ForAll     ) ||
            (this == OCL_One   ) || (this == OCL_Select     ) ||
            (this == OCL_Reject) || (this == OCL_AnyIterator) )
        {
            return Conformance.conformsTo(bodyType,OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
        }
        return true;
    }

    /** Get the return type for this iterator, given that 'source'
     *  is the source expression and 'body' the body.
     */
    public IClassifier getReturnType(ICollectionType sourceType, IClassifier bodyType) {
        if( (this == OCL_Exists) || (this == OCL_ForAll  ) ||
            (this == OCL_One   ) || (this == OCL_IsUnique) ){
          return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
        }
        if( (this == OCL_Select) || (this == OCL_Reject) ){
          return sourceType;
        }
        if( (this == OCL_AnyIterator) ) {
          return sourceType.getElementType();
        }
        if( (this == OCL_SortedBy) ) {
        	if( sourceType.isBag() || sourceType.isSequence() ) {
          		return OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.SEQUENCE, sourceType.getElementType() );
        	} else if( sourceType.isSet() || sourceType.isOrderedSet()) {
          		return OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.ORDEREDSET, sourceType.getElementType() );
        	}
        }
        if( (this == OCL_CollectNested) ) {
          return OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.BAG, bodyType);
        }
        if( (this == OCL_Collect) ) {
            // flatten any levels of nesting
            IClassifier baseType = bodyType;
            boolean test = bodyType.isCollectionKind();
            while (test) {
                baseType = ((ICollectionType)baseType).getElementType();
                test = baseType.isCollectionKind();
            }                
        	if( sourceType.isBag() || sourceType.isSet() ) {
	            return OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.BAG, baseType);
        	} else if( sourceType.isSequence() || sourceType.isOrderedSet()) {
	            return OclEngine.getCurrentOclLibrary().lookupCollectionType(CollectionMetaType.SEQUENCE, baseType);
        	}
        }
        // TBD
        if( this == OCL_Iterate ) {
            if( bodyType == null ) { 
              return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName); // TBD: nonsense
            } else {
              return bodyType;
            }
        }
        return null;
    }
}
