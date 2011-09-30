/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.List;

import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IOperation;


/** StdlibCollectionType represents a collection type in the default implementation
 * of the standard libraray.
 * 
 * @author  Jos warmer
 * @version $Id: StdlibCollectionType.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 */
public class StdlibCollectionType extends StdlibClassifier implements ICollectionType {

    private IClassifier 			elementType = null;
    private CollectionMetaType  myMetaType  = CollectionMetaType.NOCOLLECTIONTYPE;		// indicates which kind of collection this is, set, sequence, etc.
 
    public StdlibCollectionType(CollectionMetaType metaType,  IClassifier elType){
        super(metaType.toString()); 
        elementType = elType;
        myMetaType = metaType;
    }
/*******************************************************************************
 * The methods that implement the ICollectionType interface
 ******************************************************************************/
     public IClassifier getElementType(){
        return elementType;
    }
    
    public CollectionMetaType getMetaType() {
      return myMetaType;
    }
    
    public boolean isSet() {
      return myMetaType == CollectionMetaType.SET;
    }
        
    public boolean isOrderedSet() {
      return myMetaType == CollectionMetaType.ORDEREDSET;
    }
        
    public boolean isBag() {
      return myMetaType == CollectionMetaType.BAG;
    }
        
    public boolean isSequence() {
      return myMetaType == CollectionMetaType.SEQUENCE;
    }
        
    public boolean isCollection() {
      return myMetaType == CollectionMetaType.COLLECTION;
    }
            
    public boolean isCollectionKind() {
      return true;
    }
        
    public String getName() {
        return myMetaType.toString() + "(" + (elementType == null ? "<null>" : elementType.getName()) + ")";
    }
    
    /** Standard collections have no attributes.
      */
    public IAttribute findAttribute(String attName) {
        return null;
    }
    
    public IOperation findOperation(String opName, List<IClassifier> parameter) {
        IOperation result = super.findOperation(opName, parameter);
        return result;
    }    
}
