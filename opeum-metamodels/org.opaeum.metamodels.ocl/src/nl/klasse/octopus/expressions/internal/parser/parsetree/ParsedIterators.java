package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.tools.common.Util;


/** @author Jos Warmer
 * @version $Id: ParsedIterators.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedIterators {

	private List<ParsedVariableDeclaration>	iterators 		= new ArrayList<ParsedVariableDeclaration>();
    private ParsedVariableDeclaration 			resultVar 	= null;

    /** Creates a new instance of ParsedIterators
      */
    public ParsedIterators(List<ParsedVariableDeclaration> its, ParsedVariableDeclaration var) {
        this.iterators = its;
        this.resultVar = var;
    }

    public ParsedVariableDeclaration getResult()
    {
        return resultVar;
    }

    public List<ParsedVariableDeclaration>  getIterators()
    {
        return iterators;
    }

    public String toString() {
      String result = Util.collectionToString(iterators, "&& ");
      if( resultVar != null ){
        result = result + "; \n" + resultVar.toString() ;
      }
      return result;
    }

    public void arrangeOperators(){
      Iterator i = iterators.iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).arrangeOperators();
      }
      resultVar.arrangeOperators();
    }

    public void applyPriority(){
      Iterator i = iterators.iterator();
      while( i != null && i.hasNext() ){
        ( (ParsedVariableDeclaration) i.next() ).applyPriority();
      }
      resultVar.applyPriority();
    }
}
