package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.tools.common.Util;

/**
 *
 * @author  jos
 * @version $Id: ParsedName.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class ParsedName extends ParsedElement {

    private Token rolename = null;	// used to store an additional rolename, 
                 // as in references to navigations to and from the same class
	private Token token  = null;
    private List<Token>  tokens = new ArrayList<Token>();

    /** Creates new ParsedName */
    public ParsedName(Token t) {
        token = t;
        tokens.add(t);
        startAt(token);
        endAt(token);
    }

    public void addToken(Token t){
      token = t;
      tokens.add(t);
    }

    public Token getBaseToken(){
      return token;
    }

    public PathName toPathName() {
      PathName result = null;

      Iterator i = tokens.iterator();
      while( i.hasNext() ){
        Token t = (Token) i.next();
        if( result == null ){
          result = new PathName(t.image);
        } else {
          result.addString(t.image);
        }
      }
      return result;
    }


    public String toString() {
      String result = Util.collectionToString(tokens, "::");
      return result; //  + "[" + getStartColumn() + "@" + getEndColumn() + "]";
    }

	/**
	 * @param rolename
	 */
	public void setRolename(Token rolename) {
		this.rolename = rolename;		
	}

	/**
	 * @return
	 */
	public String getRolename() {
		if (rolename != null){
			return rolename.image;
		}
		return "";
	}

}
