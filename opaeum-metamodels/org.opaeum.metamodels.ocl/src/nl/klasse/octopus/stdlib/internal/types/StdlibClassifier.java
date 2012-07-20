/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;


/** @author  Jos Warmer
 * @version $Id: StdlibClassifier.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 */
public class StdlibClassifier implements IClassifier {
    private String name;            // name of this element
	private PathName pathName;		// path of this element as Part1::part2::part3::part4
	protected List<IAttribute> attrs;       // list of attributes
	protected List<IOperation> opers;       // list of operations
	protected List<IAttribute> classAttrs;  // list of classifier attributes
	protected List<IOperation> classOpers;  // list of classifier operations
	protected List<IAssociationEnd> assocs;      // list of associations
	protected List<IClassifier> generalizations; // the superclasses
	protected IPackage   owner = null;
//	protected Collection states;      // list of states
//	protected Collection navigations;

    /** Creates new IClassifier */
    public StdlibClassifier(String n) {
        name = n;
        attrs       = new ArrayList<IAttribute>();
        opers       = new ArrayList<IOperation>();
        classAttrs  = new ArrayList<IAttribute>();
        classOpers  = new ArrayList<IOperation>();
        assocs      = new ArrayList<IAssociationEnd>();
        generalizations = new ArrayList<IClassifier>();
        pathName = new PathName(name);
//		states      = new ArrayList();
//      navigations = new ArrayList();
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

    public boolean isCollectionKind() {
    	return false;
    }

    public void addAttribute(IAttribute p) {
    	attrs.add(p);
		((AttributeImpl)p).setOwner(this);
    }

    public void removeAttribute( IAttribute p ) {
        attrs.remove( p );
    }

    public List<IAttribute> getAttributes( ) {
        return attrs;
    }



    public List<IAttribute> getClassAttributes( ) {
        return classAttrs;
    }

    public void addGeneralization( IClassifier c ) {
        generalizations.add( c );
    }

    public void removeGeneralization( IClassifier c ) {
        generalizations.remove( c );
    }

    public List<IClassifier> getGeneralizations( ) {
        return generalizations;
    }

    public void addAssociation( IAssociationEnd p ) {
        assocs.add( p );
    }

    public void removeAssociation( IAssociationEnd p ) {
        assocs.remove( p );
    }

    public void addOperation( IOperation p ) {
        opers.add( p );
        if( p instanceof StdlibOperation ){
            ((StdlibOperation)p).setOwner(this);
        } else {
            System.out.println("StdlibClassifier::addOperation: wrong type of operation !!!!!!!!!!!!!!!");;
        }
    }

    public void removeOperation( IOperation p ) {
        opers.remove( p );
    }

    public List<IOperation> getOperations( ) {
        return opers;
    }

    public void addClassOperation( StdlibOperation p ) {
        classOpers.add( p );
        p.setOwner(this);
    }

    public void removeClassOperation( IOperation p ) {
        classOpers.remove( p );
    }

    public List<IOperation> getClassOperations( ) {
        return classOpers;
    }

    public void addState( IState p ) {
        if (Check.ENABLED) Check.pre("StdlibClasifier::addState should not be called", false);
    }

    public void removeState( IState p ) {
        if (Check.ENABLED) Check.pre("StdlibClasifier::removeState should not be called", false);
    }

    public List<IState> getStates( ) {
        return new ArrayList<IState>(1);
    }

    public String toString() {
        return getName();
    }
    

    public String getBasename() {
      return getName();
    }

    public boolean isReference() {
        return false;
    }

    public IAttribute findAttribute(String attName) {
		IAttribute result = findLocalAttribute(attName);
		if (result != null) return result;
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
        return null;
    }

    /** Lookup the modelelement with the name <I>path</I> inside this classifier.
     * The result can be a class attribute/ooperation.
     ** return null if no such element exists.
     **/
    public IModelElement lookup(PathName path) {
//        System.out.println("IClassifier::lookup " + path.toString());
        if( path.isSingleName() ){
            String name = path.getLast();
/*
            Iterator it = getClassAttributes();
            while( it.hasNext() ){
                AttributeImpl att = (AttributeImpl) it.next();
                if( att.getName().equals( name) ){
                    return att;
                }
            }
*/

            Iterator it = getClassOperations().iterator();
            while( it.hasNext() ){
                IOperation op = (IOperation ) it.next();
                if( op.getName().equals( name) ){
                    return op;
                }
            }
        }
        return null;
    }

    public boolean conformsTo(IClassifier c) {
        return Conformance.conformsTo(this, c);
    }

    public List<IAssociationEnd> getNavigations() {
        return new ArrayList<IAssociationEnd>(1);
    }

    public void removeNavigation(IAssociationEnd n) {
        if (Check.ENABLED) Check.pre("StdlibClasifier::removeNavigation should not be called", false);
//        navigations.remove(n);
    }

    public void addNavigation(IAssociationEnd n) {
        if (Check.ENABLED) Check.pre("StdlibClasifier::addNavigation should not be called", false);
//        navigations.add(n);
    }

    /** No class operations for standard types.
     * 
     * @see nl.klasse.octopus.oclengine.modelinterface.IClassifier#lookupOperation(String, List)
     */
    public IOperation findClassOperation(String opName, List<IClassifier> argumentTypes) {
        return null;
    }
    
    public IOperation findOperation(String opName, List<IClassifier> argumentTypes) {
        IOperation result = null;
        Iterator iter = getOperations().iterator();
        while( iter.hasNext() ){
            result = (IOperation)iter.next();
            if( result.getName().equals(opName) ){
                if( Conformance.argumentsConformTo(argumentTypes, result) ) {
                    return result;
                } else {
                	//TBD: betere foutmelding
                    //System.out.println("Found operation " + result.toString() + " but parameters do not match");
                }
            }
        }
        result = null;
        // now try the superclasses
        Iterator gens = this.getGeneralizations().iterator();
        while( gens.hasNext() ){
            IClassifier gen = (IClassifier) gens.next();
            result = (StdlibOperation)gen.findOperation(opName, argumentTypes);
            if( result != null ){
                return result;
            }
        }
        result = null;
        // If all fails, try OclAny
        IClassifier oclAny = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);
        if( oclAny != this ) {
            result = oclAny.findOperation(opName, argumentTypes);
        }
        return result;
    }

  public IPackage getOwner() {
    return owner;
  }

  public void setOwner(IPackage p) {
    owner = p;
  }

  public IClassifier getElementType() {
    return null;
  }
  	/** Add an attribute that is defined in an OCL 'def' statement
	 * 
	 * @param attr
	 */
	public void addOclDefAttribute(IAttribute attr){
	}
	
	/** Add an operation that is defined in an OCL 'def' statement
	 * 
	 * @param oper
	 */
	public void addOclDefOperation(IOperation oper){
	}
	
	/**
	 * Method removeOclDefAttribute removes an attribute that is defined in an OCL 'def' statement.
	 * @param attr
	 */
	public void removeOclDefAttribute(IAttribute attr){
	}

	/**
	 * Method removeOclDefOperation removes an operation that is defined in an OCL 'def' statement.
	 * @param oper
	 */
	public void removeOclDefOperation(IOperation oper){
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IClassifier#lookupState(nl.klasse.octopus.oclengine.general.PathName)
	 */
	public IState findState(PathName path) {
		return null;
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IClassifier#getInterfaces()
	 */
	public List<IInterface> getInterfaces() {
		return new ArrayList<IInterface>();
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IClassifier#getVisibility()
	 */
	public VisibilityKind getVisibility() {
		return VisibilityKind.PUBLIC;
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.INameSpace#lookupOperation(nl.klasse.octopus.oclengine.general.PathName, java.util.List)
	 */
	public IOperation lookupOperation(PathName path, List<IClassifier> types) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#findClassAttribute(java.lang.String)
	 */
	public IAttribute findClassAttribute(String attName) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#findAssociationClass(java.lang.String)
	 */
	public IAssociationClass findAssociationClass(String name) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#findCommonSuperType(nl.klasse.octopus.model.IClassifier)
	 */
	public IClassifier findCommonSuperType(IClassifier otherType) {
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


	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#getSubClasses()
	 */
	public Collection<IClassifier> getSubClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#getIsAbstract()
	 */
	public boolean isAbstract() {
		return false;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#setIsAbstract(boolean)
	 */
	public void setIsAbstract(boolean isAbstract) {
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IClassifier#getStereotype()
	 */
	public String getStereotype() {
		return "";
	}

	@Override
	public Collection<IImportedElement> getImports() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IClassifier> getClassifiers() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IPackage> getSubpackages() {
		return Collections.emptySet();
	}
}