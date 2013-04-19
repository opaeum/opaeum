/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.ICollectionRange;


/** @author  Jos Warmer
 * @version $Id: CollectionRange.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class CollectionRange extends CollectionLiteralPart implements ICollectionRange {

    private OclExpression first = null;
    private OclExpression last  = null;
    
    /** Creates a new instance of CollectionRange */
    public CollectionRange() {
    }

    /** Getter for property first.
     * @return Value of property first.
     */
    public OclExpression getFirst() {
        return first;
    }
    
    /** Setter for property first.
     * @param first New value of property first.
     */
    public void setFirst(OclExpression first) {
        this.first = first;
    }
    
    /** Getter for property last.
     * @return Value of property last.
     */
    public OclExpression getLast() {
        return last;
    }
    
    /** Setter for property last.
     * @param last New value of property last.
     */
    public void setLast(OclExpression last) {
        this.last = last;
    }
    
    public String toString(){
    	return first.asOclString() + " .. " + last.asOclString();
    }
    
}
