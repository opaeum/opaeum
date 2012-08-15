/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: TupleLiteralExp.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class TupleLiteralExp extends LiteralExp implements ITupleLiteralExp {

    private List<IVariableDeclaration> tupleParts = new ArrayList<IVariableDeclaration>();     // holds VariableDeclarations
    
    /** Creates a new instance of TupleLiteralExp */
    public TupleLiteralExp() {
    }

    /** Getter for property tupleParts.
     * @return Value of property tupleParts.
     */
    public Collection<IVariableDeclaration> getTupleParts() {
        return tupleParts;
    }
    
    /** Setter for property tupleParts.
     * @param tupleParts New value of property tupleParts.
     */
    public void setTupleParts(List<IVariableDeclaration> tupleParts) {
        this.tupleParts = tupleParts;
    }
    
    public void addTuplePart(IVariableDeclaration tuplePart) {
        this.tupleParts.add(tuplePart);
    }
    
	/** Returns a completely new StdlibTupleType instance. Elsewere this has to be compared with
	 *  a expected StdlibTupleType.
	 * @see nl.klasse.octopus.oclengine.expressions.ast.OclExpression#getNodeType()
	 */
    public IClassifier getNodeType() {
		ITupleType tupleType = OclEngine.getCurrentOclLibrary().lookupTupleType(tupleParts);
		return tupleType;
    }
	
	public String toString() {
		return "Tuple{" + Util.collectionToString(tupleParts, ", ") + "}";		    
	}

}
