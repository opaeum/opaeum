/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.VisibilityKind;


/**
 * StdlibOperation : 
 */
public class StdlibOperation implements IOperation {
    private String  	name;            			// name of this element
	private boolean 	isClassOperation = false;	//
	private boolean 	isInfix 		 = false;	//	
	private boolean 	isPrefix 		 = false;	//
	private List<IParameter>        params			 = new ArrayList<IParameter>();	// list of parameters
	private IClassifier returnType		 = null;   			// reference to the return type of this operation
	private IClassifier owner       	 = null; 	// reference to the owner
	private PathName pathName;		// path of this element as Part1::part2::part3::part4

    /** Creates new StdlibOperation */
    public StdlibOperation(String n, IClassifier returnType) {
        name 		= n;
        params     	= new ArrayList<IParameter>();
        this.returnType = returnType;
    }

	/** Creates new StdlibOperation */
	public StdlibOperation(String n, IClassifier returnType, boolean isPrefix) {
		name 		= n;
		params     	= new ArrayList<IParameter>();
		this.isPrefix = isPrefix;
		this.returnType = returnType;
	}
	
	/** Creates new StdlibOperation */
    public StdlibOperation(String n, String parName, IClassifier parType, IClassifier returnType) {
        name 		= n;
        params     	= new ArrayList<IParameter>(1);
        StdlibParameter par = new StdlibParameter(parName, parType);
        par.setOwner(this);
        params.add( par );
        this.returnType = returnType;
    }
	
	/** Creates new StdlibOperation */
	public StdlibOperation(String n, String parName, IClassifier parType, IClassifier returnType, boolean isInfix) {
		name 		= n;
		params     	= new ArrayList<IParameter>(1);
		StdlibParameter par = new StdlibParameter(parName, parType);
		par.setOwner(this);
		params.add( par );
		this.isInfix = isInfix;
		this.returnType = returnType;
	}
	/** Creates new StdlibOperation */
    public StdlibOperation(String n, String parName, IClassifier parType,
                                     String parName2, IClassifier parType2, IClassifier returnType) {
        name = n;
        params     = new ArrayList<IParameter>(2);
        StdlibParameter par =  new StdlibParameter(parName, parType);
        par.setOwner(this);
        params.add( par );
        par =  new StdlibParameter(parName2, parType2);
        par.setOwner(this);
        params.add( par );
        this.returnType = returnType;
    }
    
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	public PathName getPathName(){
		return pathName;
	}        
	public void setPathName(PathName pn){
		pathName = pn;
	}
    public List<IParameter> getParameters() {
        return params;
    }
    
	/**
	 * Method getParamTypes.
	 * @return List
	 */
	public List<IClassifier> getParamTypes() {
		List<IClassifier> result = new ArrayList<IClassifier>();
		Iterator it = params.iterator();
		while( it.hasNext() ){
			StdlibParameter par = (StdlibParameter) it.next();
			result.add( par.getType() );	
		}
		return result;
	}
	
    public IClassifier getReturnType() {
        return returnType;
    }

    public void setReturnType(IClassifier c) {
        returnType = c;
    }

    public IClassifier getOwner() {
        return owner;
    }

    public void setOwner(IClassifier c) {
        owner = c;
    }

    public String getSignature( ) {
        if( returnType == null ){
            return getName() + "( " + collectionToString(params, ", ") + " )" ;
        } else {
            return getName() + "( " + collectionToString(params, ", ") + " )"  +": " + returnType.getName();
        }
    }

    public String toString() {
        if( returnType == null ){
            return getName() + "( " + collectionToString(params, ", ") + " )" ;
        } else {
            return getName() + "( " + collectionToString(params, ", ") + " ): " + returnType.getName();
        }
    }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IOperation#isClassOperation()
	 */
	public boolean hasClassScope() {
		return isClassOperation;
	}

	/**
	 * Sets the isClassOperation.
	 * @param isClassOperation The isClassOperation to set
	 */
	public void setIsClassOperation(boolean isClassOperation) {
		this.isClassOperation = isClassOperation;
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IOperation#isOclDef()
	 */
	public boolean isOclDef( ){
		return false;
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IOperation#isInfix()
	 */
	public boolean isInfix() {
		return isInfix;
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IOperation#isPrefix()
	 */
	public boolean isPrefix() {
		return isPrefix;
	}

	/** A helper operation that should be somewhere else
	  */
	 public String collectionToString(Collection coll, String separator)
	 {
		 String result = "";
		 if( coll != null ){
			 Iterator i = coll.iterator();
			 while( i.hasNext() ){
				 result = result + i.next().toString();
				 if( i.hasNext() ) result = result + separator;
			 }
		 }
		 return result;
	 }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IOperation#getVisibility()
	 */
	public VisibilityKind getVisibility() {
		return VisibilityKind.PUBLIC;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IOperation#isAbstract()
	 */
	public boolean isAbstract() {
		return false;
	}
}
