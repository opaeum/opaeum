/** (c) Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** @author  Jos Warmer
 * @version $Id: OclUndefinedLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class OclUndefinedLiteralExp extends PrimitiveLiteralExp implements IOclUndefinedLiteralExp {

    /** Creates a new instance of BooleanLiteral */
    public OclUndefinedLiteralExp(String symbol) {
        super(symbol);
    }

    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
    }
    
    public String getSymbol(){
    	return symbol;
    }

}
