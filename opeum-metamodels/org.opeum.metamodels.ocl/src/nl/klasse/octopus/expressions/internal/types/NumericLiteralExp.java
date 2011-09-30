/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.INumericLiteralExp;

/** @author  Jos Warmer
 * @version $Id: NumericLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class NumericLiteralExp extends PrimitiveLiteralExp implements INumericLiteralExp {

    /** Creates a new instance of NumericLiteral */
    public NumericLiteralExp(String symbol) {
        super(symbol);
    }

}
