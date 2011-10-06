/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;
/** @author  Jos Warmer
 * @version $Id: ParsedSimpleType.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedSimpleType extends ParsedType {

    /** Creates a new instance of ParsedSimpleType
      */
    public ParsedSimpleType(ParsedName name) {
        typeName = name;
    }

    private ParsedName typeName = null;

    public ParsedName getName()
    {
        return typeName;
    }

    public String toString()
    {
        if( typeName != null ){
            return typeName.toString();
        } else {
            return "TypeName::null";
        }
    }

    // ------------------- Token management -----------------
    public Token getStart()
    {
        return typeName.getStart();
    }
    public Token getEnd()
    {
        return typeName.getEnd();
    }
}
