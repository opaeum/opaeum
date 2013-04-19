/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

/** @author  Jos Warmer
 * @version $Id: ParsedCollectionType.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedCollectionType extends ParsedType {

    /** Creates a new instance of ParsedCollectionType
      */
    public ParsedCollectionType(String collType, ParsedType element) {
        collectionType = collType;
        elementType    = element;
    }

    private ParsedType elementType    = null;
    private String     collectionType = null;

    public ParsedType getElementType()
    {
        return elementType;
    }
    
    public String getCollectionName() {
        return collectionType;
    }

    public String toString()
    {
        return collectionType + "( " + elementType.toString() + ")";
    }
}
