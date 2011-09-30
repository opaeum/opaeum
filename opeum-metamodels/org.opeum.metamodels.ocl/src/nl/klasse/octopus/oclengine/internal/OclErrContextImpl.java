/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.oclengine.internal;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedDefinition;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOclExpression;
import nl.klasse.octopus.expressions.internal.types.PathName;
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
 * @author  anneke
 * @version $Id: OclErrContextImpl.java,v 1.2 2008/01/19 13:31:10 annekekleppe Exp $
 */
public class OclErrContextImpl extends ModelElementImpl implements IOclContext {
      
	private OclUsageType		type				= OclUsageType.WRONGTYPE;	// the kind of expression held by this object
    private String              expressionString 	= null; // the string (entered by the user) which constitues the OCL expression
	private IModelElementReference	context			= null;	// the model element that holds this expression, e.g. a class or operation
    private ParsedDefinition    definitionTree   	= null; // The parse tree that holds the information on 'def:'
	private List				errors			 	= new ArrayList();
	private String 				filename			= "";    //name of the input file that contains this context
	private int 				line				= 1;     //the line number of this context in the input file
	private int 				column				= 1;     //the column number of this context in the input file
	private ParsedOclExpression parsetree			= null;  //if the expression has been parsed correctly and errors are found during analysis
															 //this holds the parsed expression
	private IOperation			definedOperation	= null;
	private IAttribute			definedAttribute	= null;

    /** Creates new OclErrContextImpl */
    public OclErrContextImpl(String n, OclUsageType t, IModelElementReference context) {
        super(n);
		if (Check.ENABLED) Check.pre("OclErrContextImpl constructor: type should be a valid type for an OCL expression",
				  OclUsageType.WRONGTYPE != t );
        type = t; 
        this.context = context;
    }

	public OclErrContextImpl(String n, OclUsageType t, IClassifier clss, IModelElement elem) {
		super(n);
		if (Check.ENABLED) Check.pre("OclErrContextImpl constructor: type should be a valid type for an OCL expression",
				  OclUsageType.WRONGTYPE != t );
		type = t; 
		context = new ModelElementReferenceImpl(clss, elem);
	}
	
    /**
	 * @param s
	 */
	public void setExpressionString( String s ){
        expressionString = s;
    }  
    
    /* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getExpressionString()
	 */
	public String getExpressionString( ){
        if( expressionString != null ){
            return expressionString;
        } else {
        	return "";
        }
    } 

	public boolean isDefinition(){
		return definitionTree != null;
	}
      
    /**
	 * @param clss
	 * @param elem
	 */
	public void setContext( IClassifier clss, IModelElement elem ){
        context = new ModelElementReferenceImpl(clss, elem);
    }  
    
    /* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getOwningModelElement()
	 */
	public IModelElementReference getOwningModelElement( ){
        return context;
    }  
    
    /* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getType()
	 */
	public OclUsageType getType( ){
        return type;
    } 
    
    /* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#asOclString()
	 */
	public String asOclString() {
		String result = "";
		if (context != null ) {
			result = "context " + context.toString() + StringHelpers.newLine;
		} else {
			result = "context UNKNOWN" + StringHelpers.newLine;
		}
		result = result + this.getTypeAndExpressionString();
        return result;
    } 
        
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getExpression()
	 */
	public IOclExpression getExpression() {
		System.out.println("OclErrContextImpl.getExpression should never be called");
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getDefinedAttribute()
	 */
	public IAttribute getDefinedAttribute() {
		return definedAttribute;
	}

	public void setDefinedAttribute(IAttribute definedAttribute) {
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedAttribute: definedVar should not be null",
				  definedAttribute != null);
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedAttribute: the type of this OclContextImpl instance is not " +
				  OclUsageType.DEF.toString(),
				  type == OclUsageType.DEF);
		this.definedAttribute = definedAttribute;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getDefinedOperation()
	 */
	public IOperation getDefinedOperation() {
		return definedOperation;
	}

	public void setDefinedOperation(IOperation definedOperation) {
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedOperation: definedOperation should not be null",
				  definedOperation != null);
		if (Check.ENABLED) Check.pre("OclContextImpl.setDefinedOperation: the type of this OclContextImpl instance is not " +
				  OclUsageType.DEF.toString(),
				  type == OclUsageType.DEF);
		this.definedOperation = definedOperation;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getErrors()
	 */
	public List getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 * @param errors The errors to set
	 */
	public void setErrors(List errors) {
		this.errors = errors;
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#hasErrors()
	 */
	public boolean hasErrors(){
		return !errors.isEmpty();
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
	/**
	 * Returns the parsetree.
	 * @return ParsedOclExpression
	 */
	public ParsedOclExpression getParsetree() {
		return parsetree;
	}

	/**
	 * Sets the parsetree.
	 * @param parsetree The parsetree to set
	 */
	public void setParsetree(ParsedOclExpression parsetree) {
		this.parsetree = parsetree;
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#asBMsyntax()
	 */
	public String asBMsyntax(){
		String newLine = StringHelpers.newLine;
		// context keyword
		String contextStr = "CONTEXT ";
		if (this.getOwningModelElement() != null){
			// package path
			PathName path = this.getOwningModelElement().getModelElement().getPathName().getCopy();
			// remove the last (to be replaced by complete signature)
			path = path.getHead(); 
			contextStr = contextStr + path.toString() + "::";
			// signature of context
			if (this.getOwningModelElement() instanceof IOperation){
				contextStr = contextStr + ((IOperation) this.getOwningModelElement()).getSignature();
			} else if ( this.getOwningModelElement() instanceof IAttribute ) {
				contextStr = contextStr + ((IAttribute) this.getOwningModelElement()).getSignature();
			} else {
				contextStr = contextStr + this.getOwningModelElement().getModelElement().getName();
			} 
		} else {
			contextStr = contextStr + "UNKNOWN_CONTEXT";
		}
		contextStr = contextStr + newLine +
			"-- WARNING: this expression has not been analysed correctly and " + newLine +
			"-- can therefore not be shown in Business Modeling Syntax.";
		// type of usage/role of ocl expression		
		if ( this.getType() != null) {	
			contextStr = contextStr + newLine + this.getType().toString().toUpperCase();
			} else {
			contextStr = contextStr + "UNKNOWN_EXPRESSION_TYPE";
		}
		// name of this expression, if present
		if (this.getName().length() != 0 ){
			contextStr = contextStr + " " + this.getName();
		} 
		contextStr = contextStr + " : ";
						   
		// the defined element, if any
		if (this.getType() != null && this.getType() == OclUsageType.DEF){
			if (this.getDefinedAttribute() != null)
				contextStr = contextStr + " " + this.getDefinedAttribute().getSignature() + " = " ;
			if (this.getDefinedOperation() != null)
				contextStr = contextStr + " " + this.getDefinedOperation().getSignature() + " = ";			
		}
		// the expression itself, which is not correctly analysed
		String expString = newLine + expressionString; 
		return contextStr + expString;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IOclContext#getTypeAndExpressionString()
	 */
	public String getTypeAndExpressionString() {
		String result = type.toString();
		if (getName().length() > 0) result = result + " " + getName();
		result = result + " : ";
		if (type == OclUsageType.DEF) {
			if (definedOperation != null) result = result + definedOperation.toString() + " ";
			if (definedAttribute != null) result = result + definedAttribute.toString() + " ";
		}
		result = result + expressionString;
		return result;
	}
	
	public String toString() {
		return type + " File " + filename + ", line " + line + ", col " + column;
	}
}
