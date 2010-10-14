package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;
import nl.klasse.tools.common.Util;


/** @author  Jos Warmer
 * @version $Id: ParsedLetExp.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedLetExp extends ParsedOclExpression {

    private List<ParsedVariableDeclaration>	variables = null;
    private ParsedOclExpression  				in        	= null;

    /** Creates a new instance of ParsedLetExp
      */
    public ParsedLetExp(List<ParsedVariableDeclaration> vars, ParsedOclExpression in) {
      this.variables = vars;
      this.in        = in;
    }

    public Iterator getVariables(){
        return variables.iterator();
    }

    public ParsedOclExpression getIn() {
        return in;
    }

    public String toString() {
      String result = "let " + Util.collectionToString(variables, ",\n")
                      + " in " + in.toString() ;
      return this.appendAppliedPropertyString(result);
    }

    public void arrangeOperators(){
      Iterator i = variables.iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).arrangeOperators();
      }
      in   .arrangeOperators();
      super.arrangeOperators();
    }

    public void applyPriority(){
      Iterator i = variables.iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).applyPriority();
      }
      in   .applyPriority();
      super.applyPriority();
    }

    // --------------------- Token management --------------------
    // TODO find out wether we can remove this method
    public Token getEnd()
    {
        return in.getEnd();
    }

}
