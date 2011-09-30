/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.ICollectionItem;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: CollectionItem.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class CollectionItem extends CollectionLiteralPart implements ICollectionItem {

    private OclExpression item = null;
    
    /** Creates a new instance of CollectionItem */
    public CollectionItem() {
    }

    /** Getter for property item.
     * @return Value of property item.
     */
    public OclExpression getItem() {
        return item;
    }
    
    /** Setter for property item.
     * @param item New value of property item.
     */
    public void setItem(OclExpression item) {
        this.item = item;
    }

    public IClassifier getType() {
        return item.getNodeType();
    }
    
    public String toString(){
    	return item.asOclString();
    }

}
