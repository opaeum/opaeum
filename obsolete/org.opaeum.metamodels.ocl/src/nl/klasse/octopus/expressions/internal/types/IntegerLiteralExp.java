/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressionVisitors.IAstVisitor;
import nl.klasse.octopus.expressionVisitors.IAstVisitorBackwards;
import nl.klasse.octopus.expressions.IIntegerLiteralExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;


/** @author  Jos Warmer
 * @version $Id: IntegerLiteralExp.java,v 1.1 2006/03/01 19:13:24 jwarmer Exp $
 */
public class IntegerLiteralExp extends NumericLiteralExp implements IIntegerLiteralExp {
    
    /** Creates a new instance of IntegerLiteral */
    public IntegerLiteralExp(String symbol) {
        super(symbol);
    }

    public IClassifier getNodeType() {
        return OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
    }

    public Object visit(IAstVisitor visitor) {
      return visitor.integerLiteralExp(this);
    }

    public Object visitBackwards(IAstVisitorBackwards visitor) {
      return visitor.integerLiteralExp(this);
    }
    public int getSymbol(){
    	return Integer.parseInt(symbol);
    }
}
