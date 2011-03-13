/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.stdlib.internal.types.StdlibTupleType;


/** The class StdlibCollections creates all collection types and operations
 *  from the OCL Standard Library. It also keeps an internal list of already
 *  created collection types to make sure that they are only created once.
 * 
 * @author  Jos Warmer
 * @version $Id: StdlibTupleTypes.java,v 1.2 2008/01/19 13:31:08 annekekleppe Exp $
 */
public class StdlibTupleTypes  {

    private HashMap<String, StdlibTupleType> tupleTypes = null;
    
    public StdlibTupleTypes  () {
    	initialize();
    }

	/**
	 * Method initialize.
	 */
	public void initialize() {   
	    tupleTypes = new HashMap<String, StdlibTupleType>();
	}
	
    private ITupleType createOrFindTupleType(List<IVariableDeclaration> parts) {
        String key = "";
        //build key from the types of the parts, in alphabetical order
        String[] typeNames = new String[parts.size()];
        for(int i=0; i<parts.size(); i++) {
        	IVariableDeclaration var = (IVariableDeclaration) parts.get(i);
			typeNames[i] = var.getType().toString();
        }
		Arrays.sort(typeNames);
        for(int i=0; i<typeNames.length; i++) {
        	key = key + "#" + typeNames[i];
        }
        
        StdlibTupleType result = (StdlibTupleType)tupleTypes.get(key);
        if( result != null ){ // found the type; it exists.
            return result; 
        }
        result = new StdlibTupleType ("TupleType(" + key + ")");
        tupleTypes.put(key, result);

		// add parts to the newly created type
		result.setParts(parts);

        return result;
    }
    
	public ITupleType lookupType(List<IVariableDeclaration> parts) {
        return createOrFindTupleType(parts);
	}

	/**
	 * @return
	 */
	public Collection<StdlibTupleType> getTupleTypes() {
		List<StdlibTupleType> result = new ArrayList<StdlibTupleType>();
		Iterator it = tupleTypes.values().iterator();
		while (it.hasNext()){
			StdlibTupleType elem = (StdlibTupleType) it.next();
			result.add(elem);
		}
		return result;
	}

}
