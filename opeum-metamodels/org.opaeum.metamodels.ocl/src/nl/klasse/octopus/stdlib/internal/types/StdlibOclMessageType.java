/** (c) Copyright Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import nl.klasse.octopus.model.IOclMessageType;
import nl.klasse.octopus.model.IOperation;

/**
 * @version $Id: StdlibOclMessageType.java,v 1.1 2006/03/01 19:13:32 jwarmer Exp $
 */
public class StdlibOclMessageType extends StdlibClassifier implements IOclMessageType {

	private IOperation referredOperation = null;
	
    /** Creates new DataTypeImpl */
    public StdlibOclMessageType(String n) {
        super(n);
    }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IOclMessageType#getReferredOperation()
	 */
	public IOperation getReferredOperation() {
		return referredOperation;
	}

	public void setReferredOperation(IOperation o){
		referredOperation = o;
	}
}
