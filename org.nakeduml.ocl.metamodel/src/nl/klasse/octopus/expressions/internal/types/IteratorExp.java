/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IIteratorExp;
import nl.klasse.octopus.stdlib.IOclIterator;
import nl.klasse.octopus.stdlib.internal.types.OclIterator;
import nl.klasse.tools.common.Util;



/** 
 * 
 * @author  Jos Warmer
 * @version $Id: IteratorExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class IteratorExp extends LoopExp implements IIteratorExp {

    private OclIterator referredIterator = null;
    
    /** Creates a new instance of IteratorExp */
    public IteratorExp() {
    }
    
    public String toString() {
		String result = "";
		String body = getBody().asOclString();
		String iterStr = Util.collectionToString(getIterators(), ", ");
		if( iterStr.equals("") ) {
		  result = "->" + this.getName() + "( " + (String)body + " )";
		} else {
		  result = "->" + this.getName() + "( " + iterStr + " | " + (String)body + " )";
		}
		return result;
    }

    /** Getter for property referredIterator.
     * NB: This is different from the OCL 2.0 Standard
     * @return Value of property referredIterator.
     */
    public IOclIterator getReferredIterator() {
        return referredIterator;
    }
    
    /** Setter for property referredIterator.
     * NB: This is different from the OCL 2.0 Standard
     * @param referredIterator New value of property referredIterator.
     */
    public void setReferredIterator(OclIterator referredIterator) {
        this.referredIterator = referredIterator;
        this.setName(referredIterator.getName());
    }
    
}
