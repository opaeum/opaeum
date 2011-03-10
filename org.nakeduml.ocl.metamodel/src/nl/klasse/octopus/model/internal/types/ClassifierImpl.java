/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.internal.analysis.ModelError;
import nl.klasse.octopus.modelVisitors.IPackageVisitor;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclErrContextImpl;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.library.StdlibBasic;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;


/** Implementation of IClassifier 
 * 
 * @author  Jos Warmer
 * @version $Id: ClassifierImpl.java,v 1.2 2008/01/19 13:42:11 annekekleppe Exp $
 */
public class ClassifierImpl extends NameSpaceImpl implements IClassifier{
    private   List<IClassifier>      		subClasses;			// list of subclasses
	private   List<IOclContext>     	 	inheritedExps;		// list of expressions on inherited features
	protected List<IAttribute> 			attrs;       			// list of attributes
    protected List<IOperation> 			opers;       			// list of operations
    protected List <IAttribute>			classAttrs;  			// list of classifier attributes
    protected List<IOperation> 			classOpers;  		// list of classifier operations
    protected List <IState>				states;      			// list of states
	protected List<IClassifier> 			generalizations; 	// the superclasses
	protected List<IInterface> 			interfaces; 			// the superclasses
    protected List<IAssociationEnd> 	navigations;			// list of associations this classifier partakes in
    protected List<IOclContext> 		defs;        			// list of ocl definitions
    protected List<IOclContext> 		invs;        			// list of ocl invariants
    protected String	stereotype;									// the stereotype
    protected IPackage  owner = null;

    /** Creates new IClassifier */
	public ClassifierImpl(String n) {
        super(n);
		subClasses	    = new ArrayList<IClassifier>();
		inheritedExps   = new ArrayList<IOclContext>();
        attrs       		= new ArrayList<IAttribute>();
        opers       		= new ArrayList<IOperation>();
        classAttrs  		= new ArrayList<IAttribute>();
        classOpers  	= new ArrayList<IOperation>();
        states      		= new ArrayList<IState>();
		generalizations = new ArrayList<IClassifier>();
		interfaces      	= new ArrayList<IInterface>();
        navigations     	= new ArrayList<IAssociationEnd>();
        defs        		= new ArrayList<IOclContext>();
        invs        		= new ArrayList<IOclContext>();
    }
    
/*******************************************************************************
 * The getter and setter methods
 *******************************************************************************/

	/* getters and setters for attrs */
    public boolean addAttribute( IAttribute p ) {
		if ( isPresent(p.getName())) {
			return false;
		} else {
			if (p.hasClassScope()) {
				classAttrs.add(p);
			} else {
				attrs.add( p );  
			}
	        ((AttributeImpl)p).setOwner(this);
		}
		return true;
    }

    public void removeAttribute( IAttribute p ) {
        attrs.remove( p );
    }

	public List<IAttribute> getAttributes( ) {
		return attrs;
	}

	public List<IAttribute> getAllAttributes( ) {
		List<IAttribute> result = new ArrayList<IAttribute>();
		Iterator it = this.getGeneralizations().iterator();
		while( it.hasNext() ){
			IClassifier gen = (IClassifier) it.next();
			result.addAll(gen.getAllAttributes());
		}
		result.addAll(attrs);

		return result;
	}

	/* getters and setters for opers */
    public void addOperation( IOperation p ) {
    	if (p.hasClassScope()) {
    		classOpers.add(p);
    	} else {
    		opers.add(p);
    	} 
        ((OperationImpl)p).setOwner(this);
    }

    public void removeOperation( IOperation p ) {
        opers.remove( p );
    }

    public List<IOperation> getOperations( ) {
        return opers;
    }
    
    /* getters and setters for classAttrs */
    public List<IAttribute> getClassAttributes( ) {
        return classAttrs;
    }
	
	/* getters and setters for classOpers */
    public List<IOperation> getClassOperations( ) {
        return classOpers;
    }
    
    /* getters and setters for states */
    public void addState( IState p ) {
        states.add( p );
        ((StateImpl)p).setOwner(this);
    }

    public void removeState( IState p ) {
        states.remove( p );
    }

    public List<IState> getStates( ) {
        return states;
    }

	public List<IState> getMainStates( ) {
		List<IState> result = new ArrayList<IState>();
		Iterator it = states.iterator();
		while (it.hasNext()){
			IState s = (IState) it.next();
			if (s.getEnclosingState() == null){
				result.add(s);
			}
		}
		return result;
	}
	   
    /* getters and setters for generalizations */
    public void addGeneralization( IClassifier c ) {
        generalizations.add( c );
        if (c instanceof ClassifierImpl) ((ClassifierImpl)c).addSubClass(this);
    }

    /**
	 * @param impl
	 */
	public void addSubClass(IClassifier impl) {
		subClasses.add(impl);		
	}

	public void removeGeneralization( IClassifier c ) {
        generalizations.remove( c );
		if (c instanceof ClassifierImpl) ((ClassifierImpl)c).removeSubClass(this);
    }

    /**
	 * @param impl
	 */
	public void removeSubClass(IClassifier impl) {
		subClasses.remove(impl);		
	}

	public List<IClassifier> getGeneralizations( ) {
        return generalizations;
    }

	/* getters and setters for interfaces */
	public void addInterface( IInterface c ) {
		interfaces.add( c );
		if (c instanceof InterfaceImpl) ((InterfaceImpl)c).addImplementingClass(this);
 	}

	public void removeInterface( IClassifier c ) {
		interfaces.remove( c );
		if (c instanceof InterfaceImpl) ((InterfaceImpl)c).removeImplementingClass(this);
	}

	public List<IInterface> getInterfaces() {
		return interfaces;
	}
	
	public List<IAssociationEnd> getAllNavigations( ) {
		Set<IAssociationEnd> result = new HashSet<IAssociationEnd>();
		result.addAll(navigations);
		Iterator it = this.getGeneralizations().iterator();
		while( it.hasNext() ){
			IClassifier gen = (IClassifier) it.next();
			result.addAll(gen.getAllNavigations());
		}
		return new ArrayList<IAssociationEnd>(result);
	}
    
    /* getters and setters for navigations */
    public List<IAssociationEnd> getNavigations() {
        return navigations;
    }

    public void removeNavigation(IAssociationEnd n) {
        navigations.remove(n);
    }

    public boolean addNavigation(IAssociationEnd n) {
		if ( isPresent(n.getName())) {
			return false;
		} else {
			navigations.add(n); 
			((AssociationEndImpl)n).setOwner(this);
		}
		return true;
    }
        
    /* getters and setters for invs */
    public void addInvariant( IOclContext p ) {
    	if (Check.ENABLED) Check.pre("ClassifierImpl.addInvariant: the context of '" + p.getName() + 
									 "' should be equal to '" + this.getName() + "'",
    								 p.getOwningModelElement().getModelElement() == this );   	
        invs.add( p );
    }

    public void removeInvariant( IOclContext p ) {
        invs.remove( p );
    }

    public List<IOclContext> getInvariants( ) {
        return invs;
    }
    
	/**
	 * Method setInvariants. Removes any existing invariants
	 * @param newList
	 */
	public void setInvariants(ArrayList<IOclContext> newList) {
    	if (Check.ENABLED) {
    		Iterator it = newList.iterator();
    		while( it.hasNext() ) {
    			Check.pre("ClassifierImpl.setInvariants: the context of all elements of newList should be equal to '" 
    			          + this.getName() + "'",
    					  ((IOclContext) it.next()).getOwningModelElement().getModelElement() == this ); 
    		}
    	}  	
		invs = newList;
	}
	    
    /* getters and setters for defs */
    public void addDefinition( IOclContext p ) {
    	if (Check.ENABLED) Check.pre("ClassifierImpl.addDefinition: the context of '" + p.getName() + 
									 "' should be equal to '" + this.getName() + "'",
    								 p.getOwningModelElement().getModelElement() == this );   	
        defs.add( p );
    }

    public void removeDefinition( IOclContext p ) {
        defs.remove( p );
    }

    public List<IOclContext> getDefinitions( ) {
        return defs;
    } 

	/**
	 * Method setDefinitions. Removes any existing definitions
	 * @param newList
	 */
	public void setDefinitions(ArrayList<IOclContext> newList) {
    	if (Check.ENABLED) {
    		Iterator it = newList.iterator();
    		while( it.hasNext() ) {
    			Check.pre("ClassifierImpl.setDefinitions: the context of all elements of newList should be equal to '" 
    					  + this.getName() + "'",
    					  ((IOclContext) it.next()).getOwningModelElement().getModelElement() == this ); 
    		}
    	} 
   		defs = newList;
	}

    public List<IOclContext> getAllOclExpressions() {
        List<IOclContext> result = new ArrayList<IOclContext>();
		if (!invs.isEmpty()) result.addAll( invs );
		if (!defs.isEmpty()) result.addAll( defs );
        Iterator e = attrs.iterator();
        while ( e.hasNext() ) {
            AttributeImpl sub = (AttributeImpl) e.next();
            result.addAll( sub.getAllOclExpressions() );
        }
        e = navigations.iterator();
        while ( e.hasNext() ) {
            AssociationEndImpl sub = (AssociationEndImpl) e.next();
            result.addAll( sub.getAllOclExpressions() );
        }        
        e = opers.iterator();
        while ( e.hasNext() ) {
            OperationImpl sub = (OperationImpl) e.next();
            result.addAll( sub.getAllOclExpressions() );
        }
        return result;
    }

    /* getter and setter methods for ocl defined elements */
	/** Add an attribute that is defined in an OCL 'def' statement
	 * 
	 * @param attr
	 */
	public void addOclDefAttribute(IAttribute attr){
		if (attr.isOclDef()) {
			addAttribute(attr);
		} 
	}
	
	/** Add an operation that is defined in an OCL 'def' statement
	 * 
	 * @param oper
	 */
	public void addOclDefOperation(IOperation oper){
		if (oper.isOclDef()) {
			addOperation(oper);
		} 
	}
	
	/**
	 * Method removeOclDefAttribute removes an attribute that is defined in an OCL 'def' statement.
	 * @param definedElement
	 */
	public void removeOclDefAttribute(IAttribute attr){
		if (attr.isOclDef()) {
			removeAttribute(attr);
		} 
	}

	/**
	 * Method removeOclDefOperation removes an operation that is defined in an OCL 'def' statement.
	 * @param definedElement
	 */
	public void removeOclDefOperation(IOperation oper){
		if (oper.isOclDef()) {
			removeOperation(oper);
		} 
	}
		    
    /* getter and setter methods for owner */
  public IPackage getOwner() {
    return owner;
  }

  public void setOwner(IPackage p) {
    owner = p;
  }

  public PathName getPathName(){
	  PathName newPath = new PathName();
	  if (getOwner() != null) {
		  newPath = getOwner().getPathName().getCopy();
		  newPath.addString(getName());
	  } else {
		  newPath = new PathName(getName());
	  }
	  return newPath;
  }    
   
	/* getter and setter methods for OCL expressions on inherited features */
	public void addInheritedExp(IOclContext cont) {
		inheritedExps.add(cont);
	}

	public List<IOclContext> getInheritedExps() {
		return inheritedExps;
	}
	
	/**
	 * Method removeOclDefOperation removes an operation that is defined in an OCL 'def' statement.
	 * @param definedElement
	 */
	public void removeInheritedExp(IOclContext cont){
		inheritedExps.remove(cont);
	}
		    

/***************************************************************************
 * Methods that implement simple requests for information on this IClassifier
 ***************************************************************************/

    public boolean conformsTo(IClassifier c) {
        return Conformance.conformsTo(this, c);
    }
    
    public boolean isCollectionKind() {
      return false;
    }

    public boolean isReference() {
        return false;
    }
    
    public String getBasename() {
      return getName();
    }
    
    public String toString() {
        return getName();
    }

    public String toStringVerbose() {
        String result = "class (" + this.getClass().getName() + ") "  + getName() + StringHelpers.newLine;

        result = result + "attributes" + StringHelpers.newLine;
        Iterator iter = getAttributes().iterator();
        while( iter.hasNext() ){
            result = result + "    " + iter.next().toString() + StringHelpers.newLine;
        }
		iter = getClassAttributes().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString() + StringHelpers.newLine;
		}
		
		result = result + "navigations" + StringHelpers.newLine;
		iter = getNavigations().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString() + StringHelpers.newLine;
		}

        result = result + "operations" + StringHelpers.newLine;
		iter = getOperations().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString()  + StringHelpers.newLine;
		}
		iter = getClassOperations().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString()  + StringHelpers.newLine;
		}

		result = result + "states" + StringHelpers.newLine;
		iter = getStates().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString()  + StringHelpers.newLine;
		}

		result = result + "invariants" + StringHelpers.newLine;
		iter = getInvariants().iterator();
		while( iter.hasNext() ){
			result = result + "    " + iter.next().toString()  + StringHelpers.newLine;
		}

        result = result + "definitions" + StringHelpers.newLine;
        iter = getDefinitions().iterator();
        while( iter.hasNext() ){
            result = result + "    " + iter.next().toString()  + StringHelpers.newLine;
        }
        return result;
    }
    
	private boolean isPresent(String n) {
		if ( findLocalAttribute(n) != null || findLocalAssociationEnd(n) != null) {
			return true;
		} else {
			return false;
		}
	}


/***************************************************************************
 * Methods that search for model elements contained by this IClassifier
 ***************************************************************************/

    public IAttribute findAttribute(String attName) {
		IAttribute result = findLocalAttribute(attName);
		if (result != null) return result;
        // now try the superclasses
        Iterator it = this.getGeneralizations().iterator();
        while( it.hasNext() ){
            IClassifier gen = (IClassifier) it.next();
            result = ((ClassifierImpl)gen).findAttribute(attName);
            if( result != null ){
                return result;
            }
        }
		// now try the implemented interfaces
		it = this.getInterfaces().iterator();
		while( it.hasNext() ){
			IInterface gen = (IInterface) it.next();
			result = ((InterfaceImpl)gen).findAttribute(attName);
			if( result != null ){
				return result;
			}
		}
        return null;
    }
    
	public IAttribute findLocalAttribute(String attName) {
		IAttribute result = null;
		Iterator iter = getAttributes().iterator();
		while( iter.hasNext() ){
			result = (AttributeImpl)iter.next();
			if( result.getName().equals(attName) ){
				return result;
			}
		}
		return null;
	}

    public IAssociationEnd findAssociationEnd(String assName) {
		IAssociationEnd result = findLocalAssociationEnd(assName);
		if (result != null) return result;
        // now try the superclasses
        Iterator it = this.getGeneralizations().iterator();
        while( it.hasNext() ){
            IClassifier gen = (IClassifier) it.next();
            result = (AssociationEndImpl) gen.findAssociationEnd(assName);
            if( result != null ){
                return result;
            }
        }
		// now try the implemented interfaces
		it = this.getInterfaces().iterator();
		while( it.hasNext() ){
			IInterface gen = (IInterface) it.next();
			result = ((InterfaceImpl)gen).findAssociationEnd(assName);
			if( result != null ){
				return result;
			}
		}
        return null;
    }
    
	public IAssociationEnd findLocalAssociationEnd(String assName) {
		IAssociationEnd result = null;
		Iterator iter = getNavigations().iterator();
		while( iter.hasNext() ){
		    result = (AssociationEndImpl)iter.next();
		    if( result.getName().equals(assName) ){
		        return result;
		    }
		}
		return null;
	}
   
	public IAssociationClass findAssociationClass(String assName) {
		IAssociationClass result = findLocalAssociationClass(assName);
		if (result != null) return result;
		// now try the superclasses
		Iterator it = this.getGeneralizations().iterator();
		while( it.hasNext() ){
			IClassifier gen = (IClassifier) it.next();
			result = (IAssociationClass) gen.findAssociationClass(assName);
			if( result != null ){
				return result;
			}
		}
		// now try the implemented interfaces
		it = this.getInterfaces().iterator();
		while( it.hasNext() ){
			IInterface gen = (IInterface) it.next();
			result = ((InterfaceImpl)gen).findAssociationClass(assName);
			if( result != null ){
				return result;
			}
		}
		return null;
	}
    
	public IAssociationClass findLocalAssociationClass(String assName) {
		IAssociation result = null;
		Iterator iter = getNavigations().iterator();
		while( iter.hasNext() ){
			IAssociationEnd end = (AssociationEndImpl)iter.next();
			result = end.getAssociation();
			if (result.getName().equals(assName) && result.isClass()){
				return (IAssociationClass) result;
			}
		}
		return null;
	}
   
    /**
	 * @param string
	 * @return
	 */
	protected IModelElement lookupLocal(String name) {
		IModelElement result = this.findAttribute(name);
		if (result == null) result = this.findAssociationEnd(name);
		if (result == null) {
			result = this.findClassAttribute(name);
		}
		if (result == null) {
			result = this.findLocalState(name);
		}
		// TODO implement the search for inner classes
//		this.findLocalClass(name);
		return result;
	}

	private IState findLocalState(String name) {
		StateImpl state = null;
		Iterator it = getStates().iterator();
		while ( it.hasNext() ){
			StateImpl s = (StateImpl) it.next();	
			if (s.getName().equals(name)) return s;			
		}
		return state;
	}

	private IAttribute findLocalClassAttribute(String name) {
		IAttribute result = null;
		Iterator it = getClassAttributes().iterator();
		while( it.hasNext() ){
			AttributeImpl att = (AttributeImpl) it.next();
			if( att.getName().equals( name) ){
				result = att;
			}
		}
		return result;
	}


	public IAttribute findClassAttribute(String attName) {
		IAttribute result = findLocalClassAttribute(attName);
		if (result != null) return result;
		// now try the superclasses
		Iterator it = this.getGeneralizations().iterator();
		while( it.hasNext() ){
			IClassifier gen = (IClassifier) it.next();
			result = ((ClassifierImpl)gen).findClassAttribute(attName);
			if( result != null ){
				return result;
			}
		}
		// now try the implemented interfaces
		it = this.getInterfaces().iterator();
		while( it.hasNext() ){
			IInterface gen = (IInterface) it.next();
			result = ((InterfaceImpl)gen).findClassAttribute(attName);
			if( result != null ){
				return result;
			}
		}
		return null;
	}

	public IOperation findOperation(String opName, List<IClassifier> argumentTypes) {
    	if (Check.ENABLED) Check.pre("ClassifierImpl.lookupOperation: opName should not be null",
    								 opName != null );
    	if (Check.ENABLED) Check.pre("ClassifierImpl.lookupOperation: argumentTypes should hold Classifiers",
    								 Check.elementsOfType(argumentTypes, IClassifier.class) );
		IOperation result = findLocalOperation(opName, argumentTypes);
		if (result != null ) return result;
        // now try the superclasses
        Iterator gens = this.getGeneralizations().iterator();
        while( gens.hasNext() ){
            IClassifier gen = (IClassifier) gens.next();
            result = (IOperation)gen.findOperation(opName, argumentTypes);
            if( result != null ){
                return result;
            }
        }
		// now try the interfaces
		Iterator intfs = this.getInterfaces().iterator();
		while( intfs.hasNext() ){
			IInterface intf = (IInterface) intfs.next();
			result = (IOperation)intf.findOperation(opName, argumentTypes);
			if( result != null ){
				return result;
			}
		}        
		// If all fails, try OclAny
        result = StdlibBasic.getBasicType(IOclLibrary.OclAnyTypeName).findOperation(opName, argumentTypes);
        return result;
    }
    
	public IOperation findLocalOperation(String opName, List<IClassifier> argumentTypes) {
		IOperation result = null;
		Iterator  iter   = getOperations().iterator();
		while( iter.hasNext() ){
		    result = (OperationImpl)iter.next();
		    if( result.getName().equals(opName) ){
		        if( Conformance.argumentsConformTo(argumentTypes, result) ){
		            return result;
		        }
		    }
		}
		return null;
	}

    public IOperation findClassOperation(String opName, List<IClassifier> argumentTypes) {
        IOperation result = null;
        Iterator  iter   = getClassOperations().iterator();
        while( iter.hasNext() ){
            result = (OperationImpl)iter.next();
            if( result.getName().equals(opName) ){
                if( Conformance.argumentsConformTo(argumentTypes, result) ){
                    return result;
                }
            }
        }
        // now try the superclasses 				 
        Iterator gens = this.getGeneralizations().iterator();
        while( gens.hasNext() ){
            IClassifier gen = (IClassifier) gens.next();
            result = (IOperation)gen.findClassOperation(opName, argumentTypes);
            if( result != null ){
                return result;
            }
        }
		// now try the interfaces
		Iterator intfs = this.getInterfaces().iterator();
		while( intfs.hasNext() ){
			IInterface intf = (IInterface) intfs.next();
			result = (IOperation)intf.findClassOperation(opName, argumentTypes);
			if( result != null ){
				return result;
			}
		}        
        // If all fails, try OclAny
        result = StdlibBasic.getBasicType(IOclLibrary.OclAnyTypeName).findOperation(opName, argumentTypes);
        return result;
    }
	/**
	 * Method lookupState.
	 * @param string : a simple name
	 * @return StateImpl
	 */
	public IState findLocalState(PathName path) {
		StateImpl state = null;
		Iterator it = states.iterator();
		while ( it.hasNext() ){
			StateImpl s = (StateImpl) it.next();	
			if (s.getStatePath().equals(path)) return s;			
		}
		return state;
	}
	
	public IState findState(PathName path) {
		IState result = findLocalState(path);
		if (result == null) {
			// try the superclasses 				 
			Iterator gens = this.getGeneralizations().iterator();
			while( gens.hasNext() ){
				IClassifier gen = (IClassifier) gens.next();
				result = (IState)gen.findState(path);
				if( result != null ){
					return result;
				}
			}
		}
		return result;		
	}

/***************************************************************************
 * Methods for visiting this IClassifier
 ***************************************************************************/

  public void accept(IPackageVisitor vis ) {
    vis.class_Before(this);
	if( vis.visitAttributes() ){
	  Iterator it = this.getAttributes().iterator();
	  while( it.hasNext() ){
		AttributeImpl att = (AttributeImpl) it.next();
		vis.attribute(att);
	  }
	  it = this.getClassAttributes().iterator();
	  while( it.hasNext() ){
		AttributeImpl att = (AttributeImpl) it.next();
		vis.attribute(att);
	  }
	}
	if( vis.visitNavigations() ){
	  Iterator it = this.navigations.iterator();
	  while( it.hasNext() ){
		AssociationEndImpl assend = (AssociationEndImpl) it.next();
		vis.navigation(assend);
	  }
	}
    if( vis.visitOperations() ){
      Iterator it = this.getOperations().iterator();
      while( it.hasNext() ){
        OperationImpl op = (OperationImpl) it.next();
        vis.operation_Before(op);
        if( vis.visitParameters() ){
          Iterator params = op.getParameters().iterator();
          while( params.hasNext() ){
            vis.parameter((ParameterImpl) params.next());
          }
        }
        vis.operation_After(op);
      }
      it = this.getClassOperations().iterator();
      while( it.hasNext() ){
        OperationImpl op = (OperationImpl) it.next();
        vis.operation_Before(op);
        if( vis.visitParameters() ){
          Iterator params = op.getParameters().iterator();
          while( params.hasNext() ){
            vis.parameter((ParameterImpl) params.next());
          }
        }
		vis.operation_After(op);
      }
    }
    if( vis.visitStates() ){
      Iterator it = this.getStates().iterator();
      while( it.hasNext() ){
        vis.state((StateImpl) it.next());
      }
    }
	vis.class_After(this);
  }
  
/***************************************************************************
 * Methods that merge another IClassifier into this one
 ***************************************************************************/  

	/**
	 * Method mergeWith merges this class with <i>merge</i>
	 * this should be done for the other owned elements as well.
	 * @param merge
	 */
	public void mergeWith(ClassifierImpl merge, List<ModelError> errors) {
    	mergeAttributes(merge, errors);		
    	mergeOperations(merge, errors);		
    	mergeClassAttributes(merge, errors);		
    	mergeClassOperations(merge, errors);		
    	mergeNavigations(merge, errors);		
		mergeGeneralizations(merge, errors);
		mergeStates(merge, errors);
    	mergeInvariants(merge, errors);		
    	mergeDefinitions(merge, errors);		
	}

    private void mergeAttributes(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getAttributes().iterator();
    	while( i.hasNext() ){
    		AttributeImpl mergeAttr  = (AttributeImpl) i.next();
    		if( !isPresent(mergeAttr.getName())  ) {
    		    this.addAttribute(mergeAttr);
    		} else {
				errors.add( new ModelError(mergeAttr.getFilename(), mergeAttr.getLine(), mergeAttr.getColumn(),
							"Error in merging attributes of " + this.getName() + ": " 
							+ " attribute '" + mergeAttr.getName() + "' already exists"));
    		}	
    	}
    }
    
    private void mergeOperations(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getOperations().iterator();
    	while( i.hasNext() ){
    		OperationImpl mergeOper  = (OperationImpl) i.next();
    		OperationImpl exists = (OperationImpl) this.findLocalOperation(mergeOper.getName(), mergeOper.getParamTypes());
    		if( exists == null ) {
    		    this.addOperation(mergeOper);
    		} else {
				errors.add( new ModelError(mergeOper.getFilename(), mergeOper.getLine(), mergeOper.getColumn(),
							"Error in merging operations of " + this.getName() + ": " 
							+ " operation '" + exists.getSignature() + "' already exists"));
    		}
    	}
    }

    private void mergeClassAttributes(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getClassAttributes().iterator();
    	while( i.hasNext() ){
    		AttributeImpl mergeAttr  = (AttributeImpl) i.next();
    		AttributeImpl exists = (AttributeImpl) this.findLocalAttribute(mergeAttr.getName());
    		if( exists == null ) {
    		    this.addAttribute(mergeAttr);
    		} else {
				errors.add( new ModelError(mergeAttr.getFilename(), mergeAttr.getLine(), mergeAttr.getColumn(),
							"Error in merging class attributes of " + this.getName() + ": " 
							 + " class attribute '" + exists.getName() + "' already exists"));
    		}	
    	}
    }
    
    private void mergeClassOperations(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getClassOperations().iterator();
    	while( i.hasNext() ){
    		OperationImpl mergeOper  = (OperationImpl) i.next();
    		OperationImpl exists = (OperationImpl) this.findClassOperation(mergeOper.getName(), mergeOper.getParamTypes());
    		if( exists == null ) {
    		    this.addOperation(mergeOper);
    		} else {
				errors.add( new ModelError(mergeOper.getFilename(), mergeOper.getLine(), mergeOper.getColumn(),
							"Error in merging class operations of " + this.getName() + ": " 
							+ " class operation '" + exists.getSignature() + "' already exists"));
    		}	
    	}
    }
    
    private void mergeNavigations(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getNavigations().iterator();
    	while( i.hasNext() ){
    		AssociationEndImpl mergeAttr  = (AssociationEndImpl) i.next();
    		if( !isPresent(mergeAttr.getName())  ) {
    		    this.addNavigation(mergeAttr);
    		} else {
				errors.add( new ModelError(mergeAttr.getFilename(), mergeAttr.getLine(), mergeAttr.getColumn(),
							"Error in merging navigations of " + this.getName() + ": " 
							+ " association end '" + mergeAttr.getName() + "' already exists"));
    		}	
    	}
    }
        
    private void mergeInvariants(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getInvariants().iterator();
    	while( i.hasNext() ){
    		IOclContext mergeCont  = (IOclContext) i.next();
    		if (mergeCont instanceof OclErrContextImpl) {
    			((OclErrContextImpl) mergeCont).setContext(this, this);	
	   		    this.addInvariant(mergeCont);
    		} else {
				errors.add( new ModelError(mergeCont.getFilename(), mergeCont.getLine(), mergeCont.getColumn(),
							"Error in merging invariants of " + this.getName() ));
    		}	
    	}
    }
       
    private void mergeDefinitions(ClassifierImpl merge, List<ModelError> errors){
    	Iterator i = merge.getDefinitions().iterator();
    	while( i.hasNext() ){
    		IOclContext mergeCont  = (IOclContext) i.next();
    		if (mergeCont instanceof OclErrContextImpl) {
    			((OclErrContextImpl) mergeCont).setContext(this, this);	
	   		    this.addDefinition(mergeCont);
    		} else {
				errors.add( new ModelError(mergeCont.getFilename(), mergeCont.getLine(), mergeCont.getColumn(),
							"Error in merging definitions of " + this.getName() ));
    		}	
    	}
    }
    
	private void mergeStates(ClassifierImpl merge, List<ModelError> errors){
		Iterator i = merge.getStates().iterator();
		while( i.hasNext() ){
			StateImpl mergeState  = (StateImpl) i.next();
			if( findLocalState(mergeState.getName()) == null ) {
				this.addState(mergeState);
			} else {
				errors.add( new ModelError(mergeState.getFilename(), mergeState.getLine(), mergeState.getColumn(),
							"Error in merging states of " + this.getName() + ": " 
							+ " state '" + mergeState.getName() + "' already exists"));
			}				
		}
	}

	private void mergeGeneralizations(ClassifierImpl merge, List<ModelError> errors){
		Iterator i = merge.getGeneralizations().iterator();
		while( i.hasNext() ){
			IClassifier mergeSuperCls  = (IClassifier) i.next();
			if( !this.getGeneralizations().contains(mergeSuperCls) ) {
				this.addGeneralization(mergeSuperCls);
			} else {
				errors.add( new ModelError(merge.getFilename(), merge.getLine(), merge.getColumn(),
							"Error in merging generalizations of " + this.getName() + ": " 
							+ " superclass '" + mergeSuperCls.getName() + "' already present"));
			}	
		}
	}

	/**
	 * @return
	 */
	public Collection<IClassifier> getSubClasses() {
		return Collections.unmodifiableList(subClasses);	
	}
    
	public IClassifier findCommonSuperType(IClassifier otherType){
		IClassifier result = null;
		if ( this.conformsTo(otherType) ) {
			result = otherType;
		} else if ( otherType.conformsTo(this) ) {
			result = this;
		}
		if (result == null) {
			Iterator it = this.getGeneralizations().iterator();
			while (it.hasNext()) {
				IClassifier supr = (IClassifier) it.next();
				result = supr.findCommonSuperType(otherType);
			}
		}
		return result;
	}

	/**
	 * @return
	 */
	public InterfaceImpl getFacade() {
		Iterator it = this.getInterfaces().iterator();
		while (it.hasNext()) {
			InterfaceImpl intf = (InterfaceImpl) it.next();
			if (intf.isFacade()) return intf;
		}
		return null;
	}

	private boolean isAbstract = false;

	/**
	 * @return Returns the isAbstract.
	 */
	public boolean getIsAbstract() {
		return isAbstract;
	}

	/**
	 * @param isAbstract The isAbstract to set.
	 */
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	/**
	 * @return Returns the stereotype.
	 */
	public String getStereotype() {
		return stereotype;
	}
	/**
	 * @param stereotype The stereotype to set.
	 */
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
}