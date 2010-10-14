package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.Util;


/**
 * @author  anneke
 * @version $Id: OperationImpl.java,v 1.2 2008/01/19 13:33:33 annekekleppe Exp $
 */
public class OperationImpl extends ModelElementImpl implements  IOperation {
    private List<IParameter>       		params;         					// list of parameters 				
    private IClassifier 						returnType;    					// reference to the return type of this operation
    private Collection<IOclContext> 	pres;             	
    private Collection<IOclContext> 	posts;           	
    private IOclContext 					body;            
    private boolean	   						isOclDef	   		= false;
    private IClassifier						owner       		= null; 		// reference to the owner
    private boolean    						hasClassScope = false;
	private boolean	   						isAbstract    	= false; 	// true if this operation is an abstract operation
	private boolean	   						isInfix     		= false; 	// true if this operation is an infix operation
	private boolean	   						isPrefix    		= false; 	// true if this operation is a prefix operation

    /** Creates new OperationImpl */
	public OperationImpl(String n, List<IParameter> pars, IClassifier r) {
		super(n);
		if (Check.ENABLED) {
			Check.pre("OperationImpl constructor: all elements in 'pars' should be of type ParameterImpl", 
					  Check.elementsOfType(pars, ParameterImpl.class)); 
		}
		params     = pars;
		Iterator i = pars.iterator();
		while( i.hasNext() ){ ((ParameterImpl)i.next()).setOwner(this); }
		setReturnType(r);
		pres       = new ArrayList<IOclContext>();
		posts      = new ArrayList<IOclContext>();
	}

	public OperationImpl(String n) {
		super(n);
		pres       = new ArrayList<IOclContext>();
		posts      = new ArrayList<IOclContext>();
		params 	   = new ArrayList<IParameter>();
	}
	
	public void setParameters(List<IParameter> pars){
		if (Check.ENABLED) {
			Check.pre("OperationImpl.setParameters: all elements in 'pars' should be of type ParameterImpl", 
					  Check.elementsOfType(pars, ParameterImpl.class)); 
		}
		params     = pars;
		Iterator i = pars.iterator();
		while( i.hasNext() ){ ((ParameterImpl)i.next()).setOwner(this); }
	}

	public void addToParameters(IParameter par){
		params.add(par);
		((ParameterImpl)par).setOwner(this);
	}
	
    public List<IParameter> getParameters() {
        return params;
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

	public PathName getPathName(){
		PathName newPath = new PathName();
		if (owner != null) {
			newPath = owner.getPathName().getCopy();
			newPath.addString(getName());
		} else {
			newPath = new PathName(getName());
		}
		return newPath;
	}    

    public String getSignature( ) {
    	String result = "";
    	String paramstr = Util.collectionToString(params, ", ");
    	if (paramstr.length() > 0) {
    		result = getVisibility() + " " + getName() + "( " + paramstr + " )" ;
    	} else {
			result = getVisibility() + " " + getName() + "()";
    	}
        if( returnType == null ){
            return result;
        } else {
            return result  + " : " + returnType.getName();
        }
    }

    public void addPreCondition( IOclContext p ) {
         pres.add( p );
    }

    public void removePreCondition( IOclContext p ) {
        pres.remove( p );
    }

    public Collection<IOclContext> getPreConditions( ) {
        return pres;
    }

    public void addPostCondition( IOclContext p ) {
        posts.add( p );
    }

    public void removePostCondition( IOclContext p ) {
        posts.remove( p );
    }

    public Collection<IOclContext> getPostConditions( ) {
        return posts;
    }

    public List<IOclContext> getAllOclExpressions() {
        List<IOclContext> result = new ArrayList<IOclContext>();
        result.addAll(pres);
        result.addAll(posts);
//		result.add(body);
        return result;
    }

    public String toString() {
		String prefix = "";
		if (this.hasClassScope()) {
			prefix = "$ ";
		}
        if( returnType == null ){
            return prefix + getName() + "( " + Util.collectionToString(params, ", ") + " )" ;
        } else {
            return prefix + getName() + "( " + Util.collectionToString(params, ", ") + " ): " + returnType.getName();
        }
    }

    /** Create a deep copy of this object.
     */
//    public Object deepCopy() {
//        OperationImpl result = new OperationImpl(getName(), this.params, this.returnType);
//        result.params = new ArrayList();
//        // pre- and postconditions are not copied yet
////        Util.deepCopyCollection(this.pres  , result.pres);
////        Util.deepCopyCollection(this.posts , result.posts);
//        Util.deepCopyCollection(this.params, result.params);
//        result.preNumber  = this.preNumber;
//        result.postNumber = this.postNumber;
//        result.setOwner(this.owner);
//        return result;
//    }

	/**
	 * Returns the isClassOperation.
	 * @return boolean
	 */
	public boolean hasClassScope() {
		return hasClassScope;
}
	/**
	 * Sets the isClassOperation.
	 * @param hasClassScope The isClassOperation to set
	 */
	public void setHasClassScope(boolean hasClassScope) {
		this.hasClassScope = hasClassScope;
	}

	/**
	 * Method setPostconditions. Overrides any exiting postconditions.
	 * @param newList
	 */
	public void setPostconditions(ArrayList<IOclContext> newList) {
		posts = newList;
	}

	/**
	 * Method setPreconditions. Overrides any exiting preconditions.
	 * @param newList
	 */
	public void setPreconditions(ArrayList<IOclContext> newList) {
		pres = newList;
	}

	/**
	 * Returns the isOclDef.
	 * @return boolean
	 */
	public boolean isOclDef() {
		return isOclDef;
	}

	/**
	 * Sets the isOclDef.
	 * @param isOclDef The isOclDef to set
	 */
	public void setIsOclDef(boolean isOclDef) {
		this.isOclDef = isOclDef;
	}

	/**
	 * Returns the body.
	 * @return OclContext
	 */
	public IOclContext getBodyExpression() {
		return body;
	}

	/**
	 * Sets the body.
	 * @param body The body to set
	 */
	public void setBodyExpression(IOclContext body) {
		this.body = body;
	}

	/**
	 * Sets the body.
	 * @param body The body to set
	 */
	public void removeBodyExpression(IOclContext body) {
		if ( this.body == body ) this.body = null;
	}

	/**
	 * Method getParamTypes.
	 * @return List
	 */
	public List<IClassifier> getParamTypes() {
		List<IClassifier> result = new ArrayList<IClassifier>();
		Iterator it = params.iterator();
		while( it.hasNext() ){
			ParameterImpl par = (ParameterImpl) it.next();
			result.add( par.getType() );	
		}
		return result;
	}
	/**
	 * @return
	 */
	public boolean isInfix() {
		return isInfix;
	}

	/**
	 * @return
	 */
	public boolean isPrefix() {
		return isPrefix;
	}

	/** If this operation has only one parameter, then isInfix may be set to true.
	 * 
	 * @param b
	 */
	public void setInfix(boolean b) {
		if (b == true && this.params.size() == 1) isInfix = b;
		if (b == false) isInfix = b;
	}

	/**
	 * @param b
	 */
	public void setPrefix(boolean b) {
		isPrefix = b;
	}

	/**
	 * @return
	 */
	public OperationImpl getCopy() {
		OperationImpl copy = new OperationImpl(this.getName(),this.getParameters(), this.getReturnType());
		copy.setBodyExpression(this.getBodyExpression());
		copy.setFilename(this.getFilename());
		copy.setInfix(this.isInfix());
		copy.setHasClassScope(this.hasClassScope);
		copy.setIsOclDef(this.isOclDef);
		copy.setLineAndColumn(this.getLine(), this.getColumn());
//		copy.setName();
		copy.setOwner(this.getOwner());
		copy.setPostconditions((ArrayList<IOclContext>) this.getPostConditions());
		copy.setPrefix(this.isPrefix());
//		copy.setReturnType();
		copy.setVisibility(this.getVisibility());
		return copy;
	}

	/**
	 * @return Returns the isAbstract.
	 */
	public boolean isAbstract() {
		return isAbstract;
	}
	/**
	 * @param isAbstract The isAbstract to set.
	 */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
}
