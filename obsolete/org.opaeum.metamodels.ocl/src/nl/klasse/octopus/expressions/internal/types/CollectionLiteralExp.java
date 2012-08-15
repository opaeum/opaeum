/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.ICollectionLiteralExp;
import nl.klasse.octopus.expressions.ICollectionLiteralPart;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: CollectionLiteralExp.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class CollectionLiteralExp extends LiteralExp implements ICollectionLiteralExp {

	private List<ICollectionLiteralPart> parts = new ArrayList<ICollectionLiteralPart>();
	
    /** Creates a new instance of CollectionLiteralExp */
    public CollectionLiteralExp() {
    }

    public void addPart(CollectionLiteralPart part) {
    	parts.add(part);
    }
    
    public List<ICollectionLiteralPart> getParts() {
    	return parts;
    } 
    
    public String toString(){
    	String typeStr = "";
		ICollectionType type = (ICollectionType) this.getNodeType();
		typeStr = type.getMetaType().toString();
    	return typeStr + "{" + Util.collectionToString(parts, ", ") + "}";   
    }
    
}
