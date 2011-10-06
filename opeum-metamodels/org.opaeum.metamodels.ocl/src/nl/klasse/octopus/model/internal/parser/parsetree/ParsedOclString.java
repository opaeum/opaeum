/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.parser.parsetree;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.types.ModelElementImpl;
import nl.klasse.octopus.oclengine.IModelElementReference;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.ModelElementReferenceImpl;
import nl.klasse.tools.common.Check;


public class ParsedOclString extends ModelElementImpl implements IOclContext {
      
	private OclUsageType		type				= OclUsageType.WRONGTYPE;	// the kind of expression held by this object
    private String              expressionString 	= null; // the string (entered by the user) which constitues the OCL expression
	private IModelElementReference	context			= null;	// the model element that holds this expression, e.g. a class or operation
	private String 				filename			= "";    //name of the input file that contains this context
	private int 				line				= 1;     //the line number of this context in the input file
	private int 				column				= 1;     //the column number of this context in the input file
	private IOperation			definedOperation	= null;
	private IAttribute			definedAttribute	= null;

    /** Creates new ParsedOclString */
    public ParsedOclString(String n, OclUsageType t) {
        super(n);
		if (Check.ENABLED) Check.pre("ParsedOclString constructor: type should be a valid type for an OCL expression",
				  OclUsageType.WRONGTYPE != t );
        type = t; 
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

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#isDefinition()
	 */
	public boolean isDefinition(){
		return false;
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
		System.out.println("ParsedOclString.asOclString should never be called");
		return "";
    } 
        
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getExpression()
	 */
	public IOclExpression getExpression() {
		System.out.println("ParsedOclString.getExpression should never be called");
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getDefinedAttribute()
	 */
	public IAttribute getDefinedAttribute() {
		return definedAttribute;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getDefinedOperation()
	 */
	public IOperation getDefinedOperation() {
		return definedOperation;
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#getErrors()
	 */
	public List getErrors() {
		return new ArrayList();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#hasErrors()
	 */
	public boolean hasErrors(){
		return true;
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
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.IOclContext#asBMsyntax()
	 */
	public String asBMsyntax(){
		System.out.println("ParsedOclString.asBMsyntax should never be called");
		return "";
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IOclContext#getTypeAndExpressionString()
	 */
	public String getTypeAndExpressionString() {
		System.out.println("ParsedOclString.getTypeAndExpressionString should never be called");
		return "";
	}
	
	public String toString() {
		return type + " File " + filename + ", line " + line + ", col " + column;
	}

	/**
	 * @return
	 */
	public IModelElementReference getContext() {
		return context;		
	}
}
