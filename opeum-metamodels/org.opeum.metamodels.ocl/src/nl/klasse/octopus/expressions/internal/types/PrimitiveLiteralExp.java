/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IPrimitiveLiteralExp;

/** @author  Jos Warmer
 * @version $Id: PrimitiveLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class PrimitiveLiteralExp extends LiteralExp implements IPrimitiveLiteralExp {

    protected String symbol = null;

    /** Creates a new instance of PrimitiveLiteralExp */
    public PrimitiveLiteralExp(String symbol) {
        this.symbol = symbol;
    }

    public String toString(){
      return symbol;
    }
    
}
