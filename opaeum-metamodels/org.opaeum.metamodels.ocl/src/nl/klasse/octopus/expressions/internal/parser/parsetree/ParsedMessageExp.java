package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.ArrayList;
import java.util.List;

/** This class represents a parsed OclMessageExp
 * @version $Id: ParsedMessageExp.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedMessageExp extends ParsedOclExpression {

	public static final int DAKJE           = 0;
	public static final int DOUBLE_DAKJE    = 1;
	public static final int NO_VALUE        = 2;

	public static final String[] dakjeText = { "^", "^^", "UNKNOWN_DAKJE" };

	private ParsedName           			propertyName = null;
	private int                  				messageKind  	= NO_VALUE;
	private List<ParsedOclExpression> arguments    	= null;
	private ParsedOclExpression  		target       		= null;

    /** Creates new ParsedMessageExp */
    public ParsedMessageExp(){ }

    public void setTarget(ParsedOclExpression  exp){
      target = exp;
    }

    public ParsedOclExpression  getTarget(){
      return target;
    }

    public void setArguments(List<ParsedOclExpression> args){
        arguments = args;
    }

    public List<ParsedOclExpression> getArguments(){
		if( arguments == null ) {
			arguments = new ArrayList<ParsedOclExpression>();
		}
		return arguments;
	}

    public ParsedOclExpression getArgument(int index){
      if( arguments.size() <= index ) {
        return null;
      } else {
        return (ParsedOclExpression ) arguments.get(index);
      }
    }

	public void setPropertyName(ParsedName name) {
		propertyName = name;
	}

	public ParsedName getPropertyName() {
		return propertyName;
	}

    public String toString() {
      String s = target.toString() + dakjeText[messageKind] + propertyName.toString();

      return this.appendAppliedPropertyString( s);
    }
	/**
	 * @return
	 */
	public int getMessageKind() {
		return messageKind;
	}

	/**
	 * @param i
	 */
	public void setMessageKind(int i) {
		messageKind = i;
	}

}
