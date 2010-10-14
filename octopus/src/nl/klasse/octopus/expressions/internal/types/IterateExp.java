/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IIterateExp;
import nl.klasse.tools.common.Util;



/** @author  Jos Warmer
 * @version $Id: IterateExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class IterateExp extends LoopExp implements IIterateExp {

    private VariableDeclaration result = null;
    
    /** Creates a new instance of IterateExp */
    public IterateExp() {
    }

    /** Getter for property result.
     * @return Value of property result.
     */
    public VariableDeclaration getResult() {
        return result;
    }
    
    /** Setter for property result.
     * @param result New value of property result.
     */
    public void setResult(VariableDeclaration result) {
        this.result = result;
    }
    
	public String toString(){
		String result = "";
		String resultStr = getResult() != null ? getResult().toString() : "";
		String body = getBody().asOclString();
		String iterStr = Util.collectionToString(getIterators(), ", ");
		if( iterStr.equals("") ) {
		  result = "->" + this.getName() + "( " + resultStr + " | " + (String)body + " )";
		} else {
		  result = "->" + this.getName() + "( " + iterStr + "; " + resultStr + " | " + (String)body + " )";
		}
		return result;
	}

}
