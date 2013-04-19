/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.tools.common.Check;


/** @author  Jos Warmer
 * @version $Id: VariableDeclaration.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class VariableDeclaration implements IModelElement, IVariableDeclaration {

	private boolean 	  isIteratorVar = false;
    private String        varName        = null;
    private IClassifier   type           = null;
    private OclExpression initExpression = null;
    private PathName 	  pathName;		          // path of this element as Part1::part2::part3::part4

    /** Creates a new instance of VariableDeclaration
      */
    public VariableDeclaration(String name, IClassifier c) {
        if (Check.ENABLED) Check.pre("VariableDeclaration of type null", c != null);
        this.varName = name;
        this.type    = c;
    }
    public String getName() { return varName; }
    public void setName(String s) { varName = s; }
    public PathName getPathName(){
        return pathName;
    }    
    
    public void setPathName(PathName pn){
        pathName = pn;
    }
    
    public IClassifier getType() {
        return type;
    }

    public String toString() {
		String initStr = "";
		if (initExpression != null) initStr = " = " + initExpression.asOclString();
        return varName + " : " + (type == null ? "<null>" : type.getName()) + initStr;
    }

    /** Getter for property initExpression.
     * @return Value of property initExpression.
     */
    public OclExpression getInitExpression() {
        return initExpression;
    }

    /** Setter for property initExpression.
     * @param initExpression New value of property initExpression.
     */
    public void setInitExpression(OclExpression initExpression) {
        this.initExpression = initExpression;
    }

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(IClassifier type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public boolean isIteratorVar() {
		return isIteratorVar;
	}

	/**
	 * @param b
	 */
	public void setIteratorVar(boolean b) {
		isIteratorVar = b;
	}

}
