/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** @author  Jos Warmer
 * @version $Id: RealLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class RealLiteralExp extends NumericLiteralExp implements IRealLiteralExp {

    /** Creates a new instance of RealLiteral */
    public RealLiteralExp(String symbol) {
        super(symbol);
    }

    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.RealTypeName);
    }

	public float getSymbol(){
		return Float.parseFloat(symbol);
	}

}
