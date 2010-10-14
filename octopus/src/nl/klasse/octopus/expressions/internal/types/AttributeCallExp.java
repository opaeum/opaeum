/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: AttributeCallExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class AttributeCallExp extends ModelPropertyCallExp implements IAttributeCallExp {

    private IAttribute referredAttribute = null;
    
    /** Creates a new instance of AttributeCallExp */
    public AttributeCallExp() {
    }

    /** Creates a new instance of AttributeCallExp */
    public AttributeCallExp(IAttribute referredAttribute) {
        this.setReferredAttribute(referredAttribute);
    }

    public IClassifier getNodeType() {
        return referredAttribute.getType();
    }


    /** Getter for property referredAttribute.
     * @return Value of property referredAttribute.
     */
    public IAttribute getReferredAttribute() {
        return referredAttribute;
    }
    
    /** Setter for property referredAttribute.
     * @param referredAttribute New value of property referredAttribute.
     */
    public void setReferredAttribute(IAttribute referredAttribute) {
        this.referredAttribute = referredAttribute;
    }
    
    public String toString(){
        return "." + referredAttribute.getName();
    }

}
