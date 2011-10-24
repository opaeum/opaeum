/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.IState;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;

/**
 * The Environment class represents the environemnt in which an OCL
 * (sub)expression is to be evaluated. An environment cvan be nested and may
 * have a parent environment. It contains NamedElements that can be looked up.
 * 
 * @author Jos Warmer
 * @version $Id: Environment.java,v 1.2 2008/01/19 13:16:05 annekekleppe Exp $
 */
public class Environment implements INameSpace {
	private Collection<NamedElement> namedElements = new ArrayList<NamedElement>();
	private Environment parent = null;

	/**
	 * Creates a new instance of Environment
	 */
	public Environment() {
	}

	/**
	 * @param env
	 *            The new parent of this environment
	 */
	public void setParent(Environment env) {
		parent = env;
	}

	/**
	 * @return Environment returns the parent environment, or null if there is
	 *         none.
	 */
	public Environment getParent() {
		return parent;
	}

	public void addElement(String name, IModelElement elem, boolean imp) {
		if (Check.ENABLED)
			Check.pre("Environment::addElement name is null", name != null);
		if (Check.ENABLED)
			Check.pre("Environment::addElement element is null", elem != null);
		namedElements.add(new NamedElement(name, elem, imp));
	}

	/**
	 * Adds all elements from the classifier 'class' to this environemnt. In the
	 * OCL spec this results in a new environment, because Environment needs to
	 * be a datatype. This isn't needed here.
	 */
	public void addNamespace(IClassifier clas) {
		// TODO give other type of warning
		System.err.println("NOT IMPLEMENTED YET: Environment::addNameSpace()");
	}

	public void addPackageContents(INameSpace p) {
		if (p == null)
			return;
		for (IClassifier c : p.getClassifiers()) {
			addElement(c.getName(), c, false);
		}
		for (IImportedElement imp : p.getImports()) {
			if (!imp.isReference()) {
				addElement(imp.getName(), imp.getElement(), false);
			}
		}
		for (IPackage sub : p.getSubpackages()) {
			addElement(sub.getName(), sub, false);
		}
	}

	public void addStates(IClassifier c) {
		Iterator i = c.getStates().iterator();
		while (i.hasNext()) {
			IState s = (IState) i.next();
			addState(s);
		}
	}

	private void addState(IState s) {
		addElement(s.getName(), s, false);
		Iterator i = s.getSubstates().iterator();
		while (i.hasNext()) {
			IState subState = (IState) i.next();
			addState(subState);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////// Element Lookup: implements INameSpace
	// //////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.klasse.octopus.oclengine.modelinterface.INameSpace#lookup(nl.klasse
	 * .octopus.oclengine.general.PathName)
	 */
	public IModelElement lookup(PathName name) {
		IModelElement result = lookupLocal(name);
		if (result == null) {
			if (this.getParent() != null) {
				return this.getParent().lookup(name);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.klasse.octopus.oclengine.modelinterface.INameSpace#lookupOperation
	 * (nl.klasse.octopus.oclengine.general.PathName, java.util.List)
	 */
	public IOperation lookupOperation(PathName opName, List<IClassifier> parameters) {
		IOperation result = null;
		result = lookupLocalOperation(opName, parameters);
		if (result == null) {
			if (this.getParent() != null) {
				result = this.getParent().lookupOperation(opName, parameters);
			}
		}
		return result;
	}

	/**
	 * Try to find a NamedElement with name <i>name</i> in the local environment
	 * without checking the parent environment. If <i>name</i> is a pathname,
	 * the full path is searched.
	 */
	private IModelElement lookupLocal(PathName name) {
		if (name.isSingleName()) {
			return lookupLocalElement(name.getLast());
		}
		// / else ...
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.getName().equals(name.getFirst())) {
				if (ne.getElement() instanceof INameSpace) {
					// look for the rest of the path within this namespace
					IModelElement result = ((INameSpace) ne.getElement()).lookup(name.getTail());
					return result;
				}
			}
		}
		return null;
	}

	private IOperation lookupLocalOperation(PathName opName, List<IClassifier> parameters) {
		IOperation result = null;
		if (opName.isSingleName()) { // A loose operation in an environment does
										// not exist.
			return null;
		}
		// / else ...
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.getName().equals(opName.getFirst())) {
				if (ne.getElement() instanceof INameSpace) {
					// look for the rest of the path within this namespace
					// TODO check wether this needs to be a return statement
					result = ((INameSpace) ne.getElement()).lookupOperation(opName.getTail(), parameters);
				}
			}
		}
		return result;
	}

	/**
	 * Find a named element called 'name' in this environment without checking
	 * the parents.
	 **/
	private IModelElement lookupLocalElement(String name) {
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.getName().equals(name)) {
				return ne.getElement();
			}
		}
		return null;
	}

	/**
	 * Find a named element called 'name' in this environment and all of its
	 * parents.
	 **/
	public IClassifier lookupClassifier(PathName name) {
		IModelElement hit = lookup(name);
		if ((hit != null) && (hit instanceof IClassifier)) {
			return (IClassifier) hit;
		}
		return null;
	}

	// private IOperation lookupOperationInPackage(IPackage p, PathName opName,
	// List<IClassifier> parameters) {
	// //TODO check whether we need this method
	// IOperation result = null;
	// // TODO No nested classes supported
	// if( opName.getTail().isSingleName() ){
	// Iterator it = p.getClassifiers().iterator();
	// while( it.hasNext() ){
	// IClassifier c = (IClassifier) it.next();
	// result = c.findOperation(opName.getLast(), parameters);
	// if( result != null) {
	// return result;
	// }
	// }
	// } else {
	// // Not found in a IClassifier, lets try the subpackages
	// Iterator it = p.getSubpackages().iterator();
	// while( it.hasNext() ){
	// IPackage sub = (IPackage) it.next();
	// result = lookupOperationInPackage(sub, opName.getTail(), parameters);
	// if( result != null) {
	// return result;
	// }
	// }
	// }
	// return result;
	// }
	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////// Implicit Element Lookup
	// ////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * Find <i>name</i> as an implicit attribute in thgis environment. This
	 * means that the environment must contain a NAmedElement of type
	 * VariableDeclaration that is labeled as 'may be implicit', that has an
	 * attribute named <i>name<i>.
	 */
	public IAttribute lookupImplicitAttribute(String name) {
		IAttribute result = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				result = ((IVariableDeclaration) (ne.getElement())).getType().findAttribute(name);
				if (result != null) {
					return result;
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitAttribute(name);
		}
		return null;
	}

	/**
	 * Find the variable that has <i>name</i> as an implicit attribute in this
	 * environment.
	 */
	public IVariableDeclaration lookupImplicitSourceForAttribute(String name) {
		IAttribute found = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				found = ((IVariableDeclaration) (ne.getElement())).getType().findAttribute(name);
				if (found != null) {
					return (IVariableDeclaration) (ne.getElement());
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitSourceForAttribute(name);
		}
		return null;
	}

	public IAssociationEnd lookupImplicitAssociationEnd(String name) {
		IAssociationEnd result = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				result = ((IVariableDeclaration) (ne.getElement())).getType().findAssociationEnd(name);
				if (result != null) {
					return result;
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitAssociationEnd(name);
		}
		return null;
	}

	public IVariableDeclaration lookupImplicitSourceForAssociationEnd(String name) {
		IAssociationEnd found = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				found = ((IVariableDeclaration) (ne.getElement())).getType().findAssociationEnd(name);
				if (found != null) {
					return (IVariableDeclaration) ne.getElement();
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitSourceForAssociationEnd(name);
		}
		return null;
	}

	public IAssociationClass lookupImplicitAssociationClass(String name) {
		IAssociationClass result = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				result = ((IVariableDeclaration) (ne.getElement())).getType().findAssociationClass(name);
				if (result != null) {
					return result;
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitAssociationClass(name);
		}
		return null;
	}

	public IVariableDeclaration lookupImplicitSourceForAssociationClass(String name) {
		IAssociationClass found = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				found = ((IVariableDeclaration) (ne.getElement())).getType().findAssociationClass(name);
				if (found != null) {
					return (IVariableDeclaration) ne.getElement();
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitSourceForAssociationEnd(name);
		}
		return null;
	}

	public IOperation lookupImplicitOperation(String name, List<IClassifier> argumentTypes) {
		IOperation result = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				result = ((IVariableDeclaration) (ne.getElement())).getType().findOperation(name, argumentTypes);
				if (result != null) {
					return result;
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitOperation(name, argumentTypes);
		}
		return null;
	}

	public IState lookupImplicitState(PathName name) {
		IState result = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				result = ((IVariableDeclaration) (ne.getElement())).getType().findState(name);
				if (result != null) {
					return result;
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitState(name);
		}
		return null;
	}

	public IVariableDeclaration lookupImplicitSourceForOperation(String name, List<IClassifier> argumentTypes) {
		IOperation found = null;
		Iterator elems = namedElements.iterator();
		while (elems.hasNext()) {
			NamedElement ne = (NamedElement) elems.next();
			if (ne.mayBeImplicit()) {
				found = ((IVariableDeclaration) (ne.getElement())).getType().findOperation(name, argumentTypes);
				if (found != null) {
					return (IVariableDeclaration) (ne.getElement());
				}
			}
		}
		if (this.getParent() != null) {
			return this.getParent().lookupImplicitSourceForOperation(name, argumentTypes);
		}
		return null;
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////// Environment Creation
	// ////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * Create a new Enviroment for the context <i>owner</i>. This is used e.g.
	 * for invariants on type <i>owner</i>
	 */
	static public Environment createEnvironment(INameSpace p, IClassifier owner) {
		if (Check.ENABLED)
			Check.pre("Environment::createEnvironment: Owner may not be null", owner != null);
		if (Check.ENABLED) {
			// Disabled Check - INakedBehaviour.getOwner() returns null where such an owner is not a INakedClassifier
			// Check.pre("Environment::createEnvironment: package incorrect for package "
			// + p.toString() + " class: " + owner.getName(),
			// owner.getOwner() == p);
		}
		Environment parent = new Environment();
		parent.addPackageContents(getRoot(p));
		Environment env = new Environment();
		env.addPackageContents(p);
		env.setParent(parent);
		env.addElement("self", new VariableDeclaration("self", owner), true);
		env.addStates(owner);
		return env;
	}

	private static INameSpace getRoot(INameSpace p) {
		if (p instanceof IPackage) {
			return ((IPackage) p).getRoot();
		} else {
			return getRoot(((IClassifier) p).getOwner());
		}
	}

	/**
	 * Create a new Enviroment for the context <i>op</i> of class <i>owner</i>.
	 * This is used for preconditions on operations of type <i>owner</i>
	 */
	static public Environment createPreEnvironment(INameSpace p, IClassifier owner, IOperation op) {
		if (Check.ENABLED)
			Check.pre("Environment::createPreEnvironment: package incorrect", owner.getOwner() == p);
		if (Check.ENABLED)
			Check.pre("Environment::createPreEnvironment: classifier incorrect", Conformance.conformsTo(owner, op.getOwner()));
		Environment env = createEnvironment(p, owner);
		Iterator iter = op.getParameters().iterator();
		while (iter.hasNext()) {
			IParameter param = (IParameter) iter.next();
			env.addElement(param.getName(), new VariableDeclaration(param.getName(), param.getType()), false);
		}
		return env;
	}

	/**
	 * Create a new Enviroment for the context <i>op</i> of class <i>owner</i>.
	 * This is used for postconditions on operations of type <i>owner</i>
	 */
	static public Environment createPostEnvironment(INameSpace p, IClassifier owner, IOperation op) {
		if (Check.ENABLED)
			Check.pre("Environment::createPostEnvironment: package incorrect", owner.getOwner() == p);
		if (Check.ENABLED)
			Check.pre("Environment::createPostEnvironment: classifier incorrect", Conformance.conformsTo(owner, op.getOwner()));
		Environment env = createPreEnvironment(p, owner, op);
		if (op.getReturnType() != null) {
			env.addElement("result", new VariableDeclaration("result", op.getReturnType()), false);
		}
		return env;
	}

	public String toString() {
		String result = "";
		Iterator i = namedElements.iterator();
		while (i.hasNext()) {
			NamedElement ne = (NamedElement) i.next();
			IModelElement me = ne.getElement();
			result = result + ne.getName() + " : " + ((me == null) ? "null" : me.getName()) + StringHelpers.newLine;
		}
		return result;
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

	public void replaceElement(String string,IModelElement messageStructure, boolean imp){
		Iterator<NamedElement> iterator = namedElements.iterator();
		while(iterator.hasNext()){
			if(iterator.next().getName().equals(string)){
				iterator.remove();
			}
		}
		addElement(string, messageStructure, imp);
		
	}
}
