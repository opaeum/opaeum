/** (c) Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IBooleanLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** @author  Jos Warmer
 * @version $Id: BooleanLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class BooleanLiteralExp extends PrimitiveLiteralExp implements IBooleanLiteralExp {

    /** Creates a new instance of BooleanLiteral */
    public BooleanLiteralExp(String symbol) {
        super(symbol);
    }

    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
    }
    
    public Boolean getSymbol(){
    	return Boolean.valueOf(symbol);
    }

}
