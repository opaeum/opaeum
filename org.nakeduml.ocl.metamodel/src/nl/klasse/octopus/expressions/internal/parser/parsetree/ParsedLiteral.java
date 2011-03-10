/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

/** @author  Jos Warmer
 * @version $Id: ParsedLiteral.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public abstract class ParsedLiteral extends ParsedOclExpression {

    /** Creates a new instance of ParsedLiteral
      */
    public ParsedLiteral() {
    }
    public ParsedLiteral(String sym) {
        symbol = sym;
    }

    protected String symbol = "ParsedLiteral::null";

    public String getSymbol()
    {
        return symbol;
    }

    public String toString()
    {
      return appendAppliedPropertyString(symbol);
    }

}
