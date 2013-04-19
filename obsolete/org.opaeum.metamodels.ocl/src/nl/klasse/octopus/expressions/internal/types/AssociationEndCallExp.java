/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;


/** @author  Jos Warmer
 * @version $Id: AssociationEndCallExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class AssociationEndCallExp extends NavigationCallExp implements IAssociationEndCallExp {

    private IAssociationEnd referredAssociationEnd = null;
    
    /** Creates a new instance of AssociationEndCallExp */
    public AssociationEndCallExp() {
    }

    /** Creates a new instance of AssociationEndCallExp */
    public AssociationEndCallExp(IAssociationEnd referredAssociationEnd) {
        this.setReferredAssociationEnd(referredAssociationEnd);
    }
    
    public IClassifier getNodeType() {
		// For an associatioclass the type must be a simple object, not a collection
		if( (this.getSource()!= null) &&( this.getSource().getNodeType() instanceof IAssociationClass) ){
			return referredAssociationEnd.getBaseType();
    	} else {
	        return referredAssociationEnd.getType();
		}
    }

    /** Getter for property referredAssociationEnd.
     * @return Value of property referredAssociationEnd.
     */
    public IAssociationEnd getReferredAssociationEnd() {
        return referredAssociationEnd;
    }
    
    /** Setter for property referredAssociationEnd.
     * @param referredAssociationEnd New value of property referredAssociationEnd.
     */
    public void setReferredAssociationEnd(IAssociationEnd referredAssociationEnd) {
        this.referredAssociationEnd = referredAssociationEnd;
    }

    public String toString(){
        return "." + referredAssociationEnd.getName();
    }
    
}
