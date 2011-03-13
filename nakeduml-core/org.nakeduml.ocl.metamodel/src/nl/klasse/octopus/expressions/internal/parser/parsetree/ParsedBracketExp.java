package nl.klasse.octopus.expressions.internal.parser.parsetree;


/**
 * @author  Jos Warmer
 * @version $Id: ParsedBracketExp.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedBracketExp extends ParsedOclExpression {

  /** Creates new ParsedBracketExp */
    public ParsedBracketExp(ParsedOclExpression e) {
      exp = e;
    }

    private ParsedOclExpression exp = null;

    public ParsedOclExpression getExp(){
      return exp;
    }

    public void applyPriority(){
      exp.applyPriority();
      super.applyPriority();
    }

    public void arrangeOperators(){
      exp.arrangeOperators();
      super.arrangeOperators();
    }


    public String toString() {
      String result = "( " + exp.toString() + " )";
      return this.appendAppliedPropertyString(result);
    }
}
