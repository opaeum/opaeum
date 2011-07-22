/** (c) Copyright 2003 by Klasse Objecten
 */
package nl.klasse.octopus.oclengine.internal;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.types.ModelElementImpl;
import nl.klasse.octopus.oclengine.IModelElementReference;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;


/**
 *
 * @author anneke
 * @version $Id: OclContextImpl.java,v 1.1 2006/03/01 19:13:33 jwarmer Exp $
 */
public class OclContextImpl extends ModelElementImpl implements IOclContext {
	private OclUsageType type			= OclUsageType.WRONGTYPE;
	private String expressionByUser 	= null; // the string (entered by the user) which constitues the OCL expression
												// used for error messages only
	private IOclExpression expression 	= null;
	private IModelElementReference context = null;
	private String name					= "";
	private IAttribute definedAttribute	= null;
	private IOperation definedOperation	= null;
	private String filename				= "";    //name of the input file that contains this context
	private int line					= 1;     //the line number of this context in the input file
	private int column					= 1;     //the column number of this context in the input file
	
	public OclContextImpl(String n, OclUsageType t, IModelElementReference cont, IOclExpression exp){
		super(n);
		if (Check.ENABLED) Check.pre("OclContextImpl constructor: type " + t + " of " + n + " should be a valid type for an OCL expression",
				  OclUsageType.WRONGTYPE != t );
		if (Check.ENABLED) Check.pre("OclContextImpl constructor: context should not be null",
				  cont != null);
		if (Check.ENABLED) Check.pre("OclContextImpl constructor: context should be of type IClassifier, IOperation, IAttribute or IAssociationEnd",
				   (cont.getModelElement() instanceof IModelElement || 
				    cont.getModelElement() instanceof IOperation  || 
				    cont.getModelElement() instanceof IAttribute  || 
				    cont.getModelElement() instanceof IAssociationEnd));
		if (Check.ENABLED) Check.pre("OclContextImpl constructor: exp should not be null",
				  exp != null);
		type = t;
		name = n;
		context = cont;
		expression = exp;
	}
	/**
	 * Returns the context.
	 * @return IModelElement
	 */
	public IModelElementReference getOwningModelElement() {
		return context;
	}
	
	public boolean isDefinition(){
		return definedAttribute != null || definedOperation != null;
	}

	/**
	 * Returns the definedOperation.
	 * @return IOperation
	 */
	public IOperation getDefinedOperation() {
		return definedOperation;
	}

	/**
	 * Returns the definedVar.
	 * @return IAttribute
	 */
	public IAttribute getDefinedAttribute() {
		return definedAttribute;
	}

	/**
	 * Returns the expression.
	 * @return OclExpression
	 */
	public IOclExpression getExpression() {
		return expression;
	}

	/**
	 * Returns the type.
	 * @return int
	 */
	public OclUsageType getType() {
		return type;
	}

	/**
	 * Sets the definedOperation.
	 * @param definedOperation The definedOperation to set
	 */
	public void setDefinedOperation(IOperation definedOperation) {
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedOperation: definedOperation should not be null",
				  definedOperation != null);
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedOperation: the type of this OclContextImpl instance is not " +
		          OclUsageType.DEF.toString(),
				  type == OclUsageType.DEF);
		this.definedOperation = definedOperation;
	}

	/**
	 * Sets the definedVar.
	 * @param definedVar The definedVar to set
	 */
	public void setDefinedAttribute(IAttribute definedAttribute) {
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedAttribute: definedVar should not be null",
				  definedAttribute != null);
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedAttribute: the type of this OclContextImpl instance is not " +
		          OclUsageType.DEF.toString(),
				  type == OclUsageType.DEF);
		this.definedAttribute = definedAttribute;
	}
	
	public String asOclString() {
		return "context " + context.toString() + 
			   StringHelpers.newLine + this.getTypeAndExpressionString();
	}
	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public String getExpressionString() {
		return expression.asOclString();
	}
	
	public PathName getPathName(){
		PathName result = context.getModelElement().getPathName();
		result.addString("context" + getName());
		return result;
	}
	
	public boolean hasErrors(){
		return false;
	}
	
	public List	getErrors() {
		return new ArrayList();
	}
	
	/**
	 * Method setLineAndColumn.
	 * @param i
	 * @param i1
	 */
	public void setLineAndColumn(int line, int col) {
		this.line = line;
		this.column = col;
	}

	/**
	 * Returns the filename.
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 * @param filename The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * Returns the column.
	 * @return int
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the line.
	 * @return int
	 */
	public int getLine() {
		return line;
	}

	public String asBMsyntax() {
		String newLine = StringHelpers.newLine;
		// context keyword
		String contextStr = "CONTEXT ";
		// package path
		contextStr = contextStr + getCorrectPath();
		// signature of context
		if (this.getOwningModelElement() instanceof IOperation){
			contextStr = contextStr + ((IOperation) this.getOwningModelElement()).getSignature();
		} else if ( this.getOwningModelElement() instanceof IAttribute ) {
			contextStr = contextStr + ((IAttribute) this.getOwningModelElement()).getSignature();
		} else {
			contextStr = contextStr + this.getOwningModelElement().getModelElement().getName();
		} 
		// type of usage/role of ocl expression			
		contextStr = contextStr + newLine + this.getType().toString().toUpperCase();
		// name of this expression, if present
		if (this.getName().length() != 0 ){
			contextStr = contextStr + " " + this.getName();
		} 
		contextStr = contextStr + " : ";
		// the defined element, if any
		if (this.getType() == OclUsageType.DEF){
			if (this.getDefinedAttribute() != null)
				contextStr = contextStr + " " + this.getDefinedAttribute().getSignature() + " = " ;
			if (this.getDefinedOperation() != null)
				contextStr = contextStr + " " + this.getDefinedOperation().getSignature() + " = ";			
		}
		// the expression itself
		String expString = "";
		if (expression != null) {
			expString = (String) expression.asBMString();
		}
		return contextStr + expString;
	}

	private String getCorrectPath() {
		String result = "";
		PathName path = this.getOwningModelElement().getModelElement().getPathName().getCopy();
		// remove the last (to be replaced by complete signature)
		if (path != null) path = path.getHead(); 
		result = path.toString() + "::";
		return result;
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IOclContext#getTypeAndExpressionString()
	 */
	public String getTypeAndExpressionString() {
		String result = type.toString();
		if (name.length() > 0) result = result + " " + name;
		result = result + " : ";
		if (type == OclUsageType.DEF) {
			if (definedOperation != null) result = result + definedOperation.toString() + " ";
			if (definedAttribute != null) result = result + definedAttribute.toString() + " ";
		}
		result = result + expressionByUser;
		return result;
		
	}
	/**
	 * @param string
	 */
	public void setExpressionByUser(String string) {
		expressionByUser = string;
	}

	public OclContextImpl copyExpOfDef(){
		OclContextImpl nw = null;
		if (this.definedAttribute != null){
			nw = new OclContextImpl(this.name, OclUsageType.DERIVE, this.context, this.getExpression());
		}
		if (this.definedOperation != null){
			nw = new OclContextImpl(this.name, OclUsageType.BODY, this.context, this.getExpression());	
		}
		nw.expressionByUser = this.expressionByUser;
		nw.filename = this.filename;	
		nw.line = this.line;	
		nw.column = this.column;	
		return nw;
	}
	
	public String toString(){
		// the defined element, if any
		String xx = "";
		if (this.getType() == OclUsageType.DEF){
			if (this.getDefinedAttribute() != null)
				xx = this.getDefinedAttribute().getSignature() + " = " ;
			if (this.getDefinedOperation() != null)
				xx = this.getDefinedOperation().getSignature() + " = ";			
		}

		return type +  " " + name + ": " + xx;
	}
}
