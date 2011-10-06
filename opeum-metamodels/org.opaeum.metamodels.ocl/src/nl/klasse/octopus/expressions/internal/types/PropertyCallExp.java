/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IPropertyCallExp;

/** @author  Jos Warmer
 * @version $Id: PropertyCallExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class PropertyCallExp extends OclExpression implements IPropertyCallExp {

    private OclExpression source = null;
    private boolean	isMarkedPre = false;
    
    /** Creates a new instance of PropertyCallExp */
    public PropertyCallExp() {
    }

    /** Creates a new instance of PropertyCallExp */
    public PropertyCallExp(String name) {
        super(name);
    }

    /** Getter for property source.
     * @return Value of property source.
     */
    public IOclExpression getSource() {
        return source;
    }
    
    /** Setter for property source.
     * @param source New value of property source.
     */
    public void setSource(OclExpression source) {
        this.source = source;
    }

    public String toString() {
        return getName();
    }

	/**
	 * @return
	 */
	public boolean isMarkedPre() {
		return isMarkedPre;
	}

	/**
	 * @param b
	 */
	public void setMarkedPre(boolean b) {
//		System.out.println("Setting isMarkedPre for " + this.getName() + 
//				"\n of type " + this.getClass() +
//				"\n value is " + b);
		isMarkedPre = b;
	}

}
