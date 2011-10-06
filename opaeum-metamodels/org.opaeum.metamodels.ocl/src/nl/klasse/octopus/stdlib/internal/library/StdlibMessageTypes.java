/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IOclMessageType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.stdlib.internal.types.StdlibOclMessageType;
import nl.klasse.octopus.stdlib.internal.types.StdlibOperation;


/** The class StdlibMessageTypes creates all OclMessage types and operations
 *  from the OCL Standard Library. It also keeps an internal list of already
 *  created OclMessage types to make sure that they are only created once.
 * 
 * @author  Jos Warmer
 * @version $Id: StdlibMessageTypes.java,v 1.2 2008/01/19 13:31:08 annekekleppe Exp $
 */
public class StdlibMessageTypes  {
	private List<StdlibOclMessageType>	myStdlibOclMessageTypes = null;
    
    public StdlibMessageTypes  () {
    	initialize();
    }

	/**
	 * Method initialize.
	 */
	public void initialize() {   
		myStdlibOclMessageTypes = new ArrayList<StdlibOclMessageType>();
	}
	
	public IOclMessageType lookupType(IOperation op) {
		StdlibOclMessageType messageType = null;
		Iterator types = myStdlibOclMessageTypes.iterator();
		while( types.hasNext() ){
			messageType = (StdlibOclMessageType) types.next();
			if( messageType.getReferredOperation() == op ){
				return messageType;
			}
		}
		// Not found, therefore have to create it.
		messageType = createMessageType(op);
		return messageType;
	}

    private StdlibOclMessageType createMessageType(IOperation op) {
		StdlibOclMessageType result = new StdlibOclMessageType("OclMessage");
		result.setReferredOperation(op);
		myStdlibOclMessageTypes.add(result);

		result.addOperation( new StdlibOperation ("result" , op.getReturnType() ) );
		result.addOperation( new StdlibOperation ("hasResturned"    , StdlibBasic.OCL_Boolean ) );
		result.addOperation( new StdlibOperation ("isSignalSent"    , StdlibBasic.OCL_Boolean ) );
		result.addOperation( new StdlibOperation ("isOperationCall" , StdlibBasic.OCL_Boolean ) );
        
		Iterator parameters = op.getParameters().iterator();
		while( parameters.hasNext() ){
			IParameter par = (IParameter) parameters.next();
			IAttribute att = new AttributeImpl(par.getName(), par.getType(), false);
			System.out.println("Adding attribute " + att.toString());
			result.addAttribute(att);
		}
        return result;
    }

}
