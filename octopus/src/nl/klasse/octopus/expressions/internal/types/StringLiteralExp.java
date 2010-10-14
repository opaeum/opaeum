/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** @author  Jos Warmer
 * @version $Id: StringLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class StringLiteralExp extends PrimitiveLiteralExp implements IStringLiteralExp {

    /** Creates a new instance of StringLiteral */
    public StringLiteralExp(String symbol) {
        super( symbol );
    }

    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.StringTypeName);
    }

	public String getSymbol(){
		return symbol;
	}

}
