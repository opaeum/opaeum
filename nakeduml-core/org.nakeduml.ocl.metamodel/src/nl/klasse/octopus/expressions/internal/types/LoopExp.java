/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.ILoopExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;


/** @author  Jos Warmer
 * @version $Id: LoopExp.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class LoopExp extends PropertyCallExp implements ILoopExp {

    private OclExpression 					body 		= null;
    private List<IVariableDeclaration>	iterators 	= new ArrayList<IVariableDeclaration>();

    /** Creates a new instance of LoopExp */
    public LoopExp() {
    }

    /** Getter for property body.
     * @return The expression that is the body of the loop.
     */
    public OclExpression getBody() {
        return body;
    }
    
    /** Setter for property body.
     * @param body New value of property body.
     */
    public void setBody(OclExpression body) {
        this.body = body;
    }
    
    /** Getter for property iterators.
     * @return Value of property iterators.
     */
    public Collection<IVariableDeclaration> getIterators() {
        return iterators;
    }
    
    /** Setter for property iterators.
     * @param iterators New value of property iterators.
     */
    public void addIterator(VariableDeclaration varDecl) {
        this.iterators.add(varDecl);
    }

}
