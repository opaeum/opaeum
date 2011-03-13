/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;
import nl.klasse.tools.common.Check;


/**
 * @author  Jos Warmer
 * @version $Id: Conformance.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class Conformance {

    static public boolean argumentsConformTo(List actualTypes, IOperation op) {
        if (Check.ENABLED) Check.pre("Conformance.argumentsConformTo: op is null", op != null );
    	if (Check.ENABLED) Check.pre("Conformance.argumentsConformTo: actualTypes should hold Classifiers",
    								 Check.elementsOfType(actualTypes, IClassifier.class) );
    
        int size = 0;
        if( actualTypes == null ) { 
            size = 0;
        } else {
            size = actualTypes.size();
        }
        List resultPars = op.getParameters();
        if( size == resultPars.size() ) {
            boolean ok = true;
            for( int i = 0; i < size ; i++) {
                IClassifier actualType =  (IClassifier) actualTypes.get(i);
                IClassifier formalType =  ((IParameter)resultPars.get(i)).getType();
                ok = ok && conformsTo(actualType,  formalType );
            }
            return ok;
        }
        return false;
    }

    /** Conformance for all types: actualType should be conform formalType.
     *  If formalType is equal to DependsOnSourceType then the check returns true.
     * 
     * @param toConform
     * @param c
     * @return boolean
     */
    static public boolean conformsTo(IClassifier actualType, IClassifier formalType) {
        if (Check.ENABLED) Check.pre("Conformance.conformsTo: toConform is null", actualType != null );

        if( formalType == OclEngine.getCurrentOclLibrary().lookupStandardType("DependsOnSourceType")) {
            return true;
        } // should be checked where source type is available
		if( actualType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName)) {
			return true;
		}
		if( formalType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName)) {
			return true;
		}        
        if( actualType.isCollectionKind() ) {
            if( formalType.isCollectionKind() ){
                ICollectionType actualCollectionType = (ICollectionType)actualType;
                ICollectionType formalCollectionType         = (ICollectionType)formalType;
                if( actualCollectionType.getMetaType() == formalCollectionType.getMetaType() ||
                    formalCollectionType.getMetaType() == CollectionMetaType.COLLECTION )
                {
                    return conformsTo( ((ICollectionType)actualType).getElementType(),
                                       ((ICollectionType)formalType).getElementType() );
                } else {
                    return false;   
                }
            } else {
                return false;
            }
        } else if( formalType.isCollectionKind() ) { 
        	return false; 
        }
        
        //Hack for Strings
        if (actualType.getName().equals(formalType.getName()) && actualType.getName().equals("String")) {
        	return true;
        }
        
        
        if( actualType == formalType ){
            return true; 
        } else {
            Iterator it = actualType.getGeneralizations().iterator();
            while( it.hasNext() ){
                IClassifier gen = (IClassifier) it.next();
                if( conformsTo(gen, formalType) ) {
                    return true;
                }
            }
			it = actualType.getInterfaces().iterator();
		    while( it.hasNext() ){
			   IInterface gen = (IInterface) it.next();
			   if( conformsTo(gen, formalType) ) {
				   return true;
			   }
		    }
       }
        return false;
    }

}
