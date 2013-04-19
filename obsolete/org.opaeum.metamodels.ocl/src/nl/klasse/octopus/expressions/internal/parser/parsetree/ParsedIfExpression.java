package nl.klasse.octopus.expressions.internal.parser.parsetree;


/** @author  Jos Warmer
 * @version $Id: ParsedIfExpression.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedIfExpression extends ParsedOclExpression {

  /** Creates new ParsedIfExpression */
    public ParsedIfExpression(ParsedOclExpression ifExp,
                              ParsedOclExpression thenExp,
                              ParsedOclExpression elseExp)
    {
      this.ifExp   = ifExp;
      this.thenExp = thenExp;
      this.elseExp = elseExp;
    }

    private ParsedOclExpression ifExp   = null;
    private ParsedOclExpression thenExp = null;
    private ParsedOclExpression elseExp = null;

    public ParsedOclExpression getIfExp() {
        return ifExp;
    }

    public ParsedOclExpression getThenExp() {
        return thenExp;
    }

    public ParsedOclExpression getElseExp() {
        return elseExp;
    }

    public String toString() {
      String result = "if " + ifExp.toString() +
               " then " + thenExp.toString() +
               " else " + elseExp.toString() +
               " endif";
      return appendAppliedPropertyString(result);
    }

    public void arrangeOperators(){
      ifExp  .arrangeOperators();
      thenExp.arrangeOperators();
      elseExp.arrangeOperators();
      super  .arrangeOperators();
    }

    public void applyPriority(){
      ifExp  .applyPriority();
      thenExp.applyPriority();
      elseExp.applyPriority();
      super  .applyPriority();
    }
}
