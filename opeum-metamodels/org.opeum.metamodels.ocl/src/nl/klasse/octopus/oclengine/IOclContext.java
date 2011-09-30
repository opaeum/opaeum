/* (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.octopus.oclengine;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.OclUsageType;


/**
 * @author jos
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * @author jos
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/** This represents the context of an OCL expression.
 *
 * @author anneke
 * @version $Id: IOclContext.java,v 1.1 2006/03/01 19:13:34 jwarmer Exp $
 */
public interface IOclContext extends IModelElement {
	/** Returns the element to which the OCL expression is attached.
	 * 
	 * @return
	 */
	public IModelElementReference 	getOwningModelElement();

	/** Returns the operation that is defined in the OCL expression using the <code>
	 * def</code> construct.
	 * <p>Returns <code>null</code> if the OCL expression does not contain a <code>
	 * def</code>.
	 * 
	 * @return null or the defined operation
	 */
	public IOperation 				getDefinedOperation();

	/** Returns the attribute that is defined in the OCL expression using the <code>
	 * def</code> construct.
	 * <p>Returns <code>null</code> if the OCL expression does not contain a <code>
	 * def</code>.
	 * 
	 * @return null or the defined attribute.
	 */
	public IAttribute 				getDefinedAttribute();

	/** Returns true if the OCL expression defines an attribute or operation.
	 * @return true if the OCL expression is a <code>def</code>
	 */
	public boolean					isDefinition();

	/** The OCL expression itself. If the expression contains errors (<code>this.hasErrors()
	 * == true</code> this returns null. If there are no errors, the abstract syntax tree
	 * of the excpression is returned.
	 * 
	 * @return null if there are errors, the OCL expression itself otherwise.
	 */
	public IOclExpression 			getExpression();

	/** The role that the OCL plays. E.g. invariant, precondition, init expression, derivation rule, etc.
	 * @return The role of the expression.
	 */
	public OclUsageType				getType();
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IModelElement#getName()
	 */
	public String					getName();
	
	/** A String representation of the OCL expression. This representation is build from the
	 * abstract syntax tree of the expression and therefore not identical to the input string.
	 * <p>Note that this is the expression only, without its context.
	 * 
	 * @return The OCL expression as a String
	 */
	public String					getExpressionString();
	
	/** A String representation of the context and the OCL expression. This representation is build from the
	 * abstract syntax tree of the expression and therefore not identical to the input string.
	 * <p>Note that this is the expression, including the context.
	 * 
	 * @return The context and OCL expression as a String
	 */
	public String 					asOclString();
	
	/** Does the expression contain errors?
	 * @return true if there expression contains an error, false if the expression is correct.
	 */
	public boolean					hasErrors();

	/** The list of errormessages. Each elem,ent in the list is one error.
	 * If there are no errors (<code>hasErrors() == false</code>), returns an empty list.
	 * 
	 * @return A List<String> of error messages.
	 */
	public List						getErrors();
	
	/** The name of the source file of the context expression.
	 * 
	 * @return the name of the source file
	 */
	public String 					getFilename();
	
	/** The line number in the source file of the context expression.
	 * 
	 * @return the line number in the source file
	 */
	public int	 					getLine();
	
	/** The column number in the source file of the context expression.
	 * 
	 * @return  the column number in the source file
	 */
	public int	 					getColumn();
	
	/** A String representation of the context and the OCL expression. This representation is build from the
	 * abstract syntax tree of the expression. The syntax ofc the expression is the so-called Business Modeling
	 * syntax, as described in appendix C of the second edition of the book The Object Constraint
	 * Language.
	 * <p>Note that this is the expression, including the context.
	 * 
	 * @return The context and OCL expression as a String
	 */
	public String					asBMsyntax();
	
	/** Return a string representatyion of the role and the expression without the <code>context</code>
	 * 
	 * @return Role + expression as String
	 */
	public String					getTypeAndExpressionString();
}