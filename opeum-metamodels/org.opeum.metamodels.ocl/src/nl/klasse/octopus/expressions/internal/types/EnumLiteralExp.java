/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumLiteral;


/** @author  Jos Warmer
 * @version $Id: EnumLiteralExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class EnumLiteralExp extends LiteralExp implements IEnumLiteralExp {

    private IEnumLiteral referredEnumLiteral;
    
    /** Creates a new instance of EnumLiteralExp */
    public EnumLiteralExp (IEnumLiteral literal) {
        referredEnumLiteral = literal;
    }

	public void setReferredEnumLiteral(IEnumLiteral referredEnumLiteral) {
		this.referredEnumLiteral = referredEnumLiteral;
	}

	public IEnumLiteral getReferredEnumLiteral() {
		return referredEnumLiteral;
	}
    
    public IClassifier getNodeType() {
        return referredEnumLiteral.getEnumeration();
    }
    
    public String toString(){
    	return referredEnumLiteral.getEnumeration().getName() + "::" + referredEnumLiteral.getName();
    }
}
