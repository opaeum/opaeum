package nl.klasse.octopus.expressions.internal.parser.parsetree;

import nl.klasse.octopus.expressions.internal.parser.javacc.Token;
/**
 * @author  jos
 * @version $Id: ParsedVariableDeclaration.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public class ParsedVariableDeclaration extends ParsedElement {

    /** Holds value of property name. */
    private ParsedName name;

    /** Holds value of property initExpression. */
    private ParsedOclExpression initExpression;

    /** Holds value of property type. */
    private ParsedType type;

    /** Creates new ParsedVariableDeclaration */
    public ParsedVariableDeclaration(ParsedName var, ParsedType type, ParsedOclExpression init) {
        this.name = var;
        this.type = type;
        this.initExpression = init;
    }

    public void applyPriority(){
      if( initExpression != null ) { initExpression.applyPriority(); }
    }

    public void arrangeOperators(){
      if( initExpression != null ) { initExpression.arrangeOperators(); }
    }

    /** Getter for property name.
     * @return Value of property name.
     */
    public ParsedName getName() {
        return name;
    }

    /** Setter for property name.
     * @param name New value of property name.
     */
    public void setName(ParsedName name) {
        this.name = name;
    }

    /** Getter for property initExpression.
     * @return Value of property initExpression.
     */
    public ParsedOclExpression getInitExpression() {
        return initExpression;
    }

    /** Setter for property initExpression.
     * @param initExpression New value of property initExpression.
     */
    public void setInitExpression(ParsedOclExpression initExpression) {
        this.initExpression = initExpression;
    }

    /** Getter for property type.
     * @return Value of property type.
     */
    public ParsedType getType() {
        return type;
    }

    /** Setter for property type.
     * @param type New value of property type.
     */
    public void setType(ParsedType type) {
        this.type = type;
    }

    public String toString(){
      String result = name.toString();
      if( type != null ) {
        result = result + " : " + type.toString();
      }
      if( initExpression != null ){
        result = result + " = " + initExpression.toString();
      }
      return result;
    }

    // ---------------------- Token management ---------------------
    public Token getStart()
    {
        return name.getStart();
    }
    public Token getEnd()
    {
        if( initExpression != null ){
            return initExpression.getEnd();
        } else if ( type != null ){
            return type.getEnd();
        } else {
            return name.getEnd();
        }
    }

}
