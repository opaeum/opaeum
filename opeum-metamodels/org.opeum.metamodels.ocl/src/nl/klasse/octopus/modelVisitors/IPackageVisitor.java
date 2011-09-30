/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.modelVisitors;

import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.IState;

/** This interfaces defines a visitor for a Package.
 * The <i>accept(IPackageVisitor vis)</I> methods are defined in PackageImpl 
 * and ClassifierImpl.
 * <p>
 * An implementation of the IPackageVisitor can tailor the visit to go through
 * the Classes, Associations, Operations, etc. by defining the operations
 * <i>visitXXX</i>. If they return true the corresponding items are visited,
 * otherwise they are not.
 *
 * @author  Jos Warmer
 * @version $Id: IPackageVisitor.java,v 1.1 2006/03/01 19:13:27 jwarmer Exp $
 */
public interface IPackageVisitor {

	/**
	 * @param p
	 */
	public void package_Before(IPackage p);
	/**
	 * @param p
	 */
	public void package_After (IPackage p);
	
	/**
	 * @param c
	 */
	public void class_Before (IClassifier c);
	/**
	 * @param c
	 */
	public void class_After  (IClassifier c);
	
	/**
	 * @param c
	 */
	public void interface_Before (IInterface c);
	/**
	 * @param c
	 */
	public void interface_After  (IInterface c);
	
	/**
	 * @param o
	 */
	public void operation_Before(IOperation o);
	/**
	 * @param o
	 */
	public void operation_After (IOperation o);
	
	/**
	 * @param o
	 */
	public void parameter(IParameter o);
	/**
	 * @param a
	 */
	public void attribute(IAttribute a);
	/**
	 * @param s
	 */
	public void state    (IState     s);
	
	/**
	 * @param a
	 */
	public void association(IAssociation a);
	/**
	 * @param assend
	 */
	public void navigation(IAssociationEnd assend);
	
	/**
	 * @return
	 */
	public boolean visitClasses();
	/**
	 * @return
	 */
	public boolean visitInterfaces();
	/**
	 * @return
	 */
	public boolean visitAttributes();
	/**
	 * @return
	 */
	public boolean visitOperations();
	/**
	 * @return
	 */
	public boolean visitParameters();
	/**
	 * @return
	 */
	public boolean visitStates();
	/**
	 * @return
	 */
	public boolean visitAssociations();
	/**
	 * @return
	 */
	public boolean visitNavigations();
}

