/** (c) Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IUnspecifiedValueExp;


/** @author  Jos Warmer
 * @version $Id: UnspecifiedValueExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class UnspecifiedValueExp extends OclExpression implements IUnspecifiedValueExp {

    /** Creates a new instance of UnspecifiedValueExp */
    public UnspecifiedValueExp(String name) {
        super(name);
    }
}
