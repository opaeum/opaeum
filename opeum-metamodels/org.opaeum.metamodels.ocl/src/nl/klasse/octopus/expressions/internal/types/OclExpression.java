
/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressionVisitors.AstWalker;
import nl.klasse.octopus.expressionVisitors.AstWalkerBackwards;
import nl.klasse.octopus.expressionVisitors.internal.AstToBMsyntax;
import nl.klasse.octopus.expressionVisitors.internal.AstToString;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;


/**
 *
 * @author anneke
 * @version $Id: OclExpression.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
/**
 * OclExpression is the topmost metaclass in the OCL metamodel
 *
 *  @author  Jos Warmer
 * @version $Id: OclExpression.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class OclExpression implements IModelElement, IOclExpression {
    private PropertyCallExp appliedProperty = null;
    private IClassifier     type            = null;
    private boolean			isImplicit		= false; // indicates whether this ast-node is inserted by the OclEngine
    												 // or was entered by the user.
    private PathName 		pathName;		// path of this element as Part1::part2::part3::part4
    private String name = "";

    public OclExpression(){
        name = "<unnamed>";
    }
    
    public OclExpression(String name){
        this.name = name ;
    }

	public String getName() { 
		return name; 
	}
	
	public void setName(String s) { 
		name = s; 
	}
    
	public PathName getPathName(){
		return pathName;
	}    
    
	public void setPathName(PathName pn){
		pathName = pn;
	}   
    
    public void setAppliedProperty(PropertyCallExp p) {
        appliedProperty = p;
        if (p != null) p.setSource(this);
    }
    
    public PropertyCallExp getAppliedProperty() {
        return appliedProperty;
    }

    /** returns the last property call. E.g in a.b->c(..).b()
     * it will return the property call <i>b</i>. If the OCL
     * expression has no applied property it returns null.
     *
     */
    public PropertyCallExp getLastAppliedProperty() {
        PropertyCallExp result = appliedProperty;
        if( result != null ){
            while( result.getAppliedProperty() != null ){
                result = result.getAppliedProperty();
            }
        }
        return result;
    }   

	/**
	 * Method getNodeType returns the type of this node in the
	 * abstract syntax tree. E.g. when this object holds the information 
	 * on the 'if-then-else' part of the expression: 
	 * (if true then a else b).someProperty.attr
	 * , then this method will return the type of 'a' and 'b' (both should 
	 * have the same type). The method getExpressionType on the other hand 
	 * would return the type of 'attr'.
	 * @return IClassifier The returned type
	 */
    public IClassifier getNodeType() {
        return type;
    }
    
    /** Setter for property type.
     * @param type New value of property type.
     */
    public void setNodeType(IClassifier type) {
        this.type = type;
    }

	/**
	 * Method getExpressionType returns the type of the complete expression
	 * taking into account any applied properties. E.g. when this object 
	 * holds the information on the 'if-then-else' part of the expression: 
	 * (if true then a else b).someProperty.attr
	 * , then this method will return the type of 'attr'.
	 * The method getNodeType on the other hand would return the type
	 * of 'a' and 'b' (both should have the same type).
	 * @return IClassifier The returned type
	 */
	public IClassifier getExpressionType() {
	    if( this.getLastAppliedProperty() == null ) {
		    return this.getNodeType(); 
	    } else {
		    return this.getLastAppliedProperty().getNodeType() ;
	    }
	}
  
	public String asBMString() {
		AstToBMsyntax bm = new AstToBMsyntax();
		bm.setStart( true );
		AstWalkerBackwards w = new AstWalkerBackwards();
		return (String) w.walk(this, bm);
	}

	public String asOclString() {
		AstToString i = new AstToString ();
		AstWalker walker = new AstWalker();
		return (String) walker.walk(this, i);
	}

	/**
	 * Returns true if this ast-node was inserted by the OclEngine to complete
	 * the AST. It was not present in the user-edited input to the OclEngine.
	 * @return
	 */
	public boolean isImplicit() {
		return isImplicit;
	}

	/**
	 * @param b
	 */
	public void setImplicit(boolean b) {
		isImplicit = b;
	}

}

