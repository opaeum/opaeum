/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.Collection;

import nl.klasse.octopus.modelVisitors.IPackageVisitor;


/** This interface represents a package.
 *  <p>
 *  Packages are organized in a hierarchy, by having subpackages.
 *  Packages can also import elements.
 * 
 * @author  Jos Warmer
 * @version $Id: IPackage.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface IPackage extends IModelElement, INameSpace {

  /** Returns the collection of all subpackages
   *  of this package. If there are no subpackages, an
   *  empty list is returned. This never returns <i>null</i>.
   * 
   * @return Collection[IPackage]
   */
  public Collection<IPackage> getSubpackages  ();

  /** Returns the parent of this package, i.e. this package is a subpackage of the parent.
   *  Returns <i>null</i> if this has no parent (it is a top-package).
   *  <p> Note that the internal implementation of a UML model always contains an
   *  extra top-package with name "_system".
   * @return IPackage
   */
  public INameSpace getParent();

  /** Returns the root of this package, the root package is the top-package
   *  of the package hierarchy.
   * @return IPackage
   */
  public IPackage getRoot();

  /** Returns the collection of all Classifiers which are
   *  owned by this package. This operation returns datatypes, enumeration 
   *  types, association classes, etc, but no interfaces. Use the operation
   *  <code>getInterfaces<\code> to obtain these. If there are no owned
   *  Classifiers, an empty List is returned. This never returns <i>null</i>.
   * 
   * @return Collection[IClassifier]
   */
  public Collection<IClassifier> getClassifiers();

  /** Returns the collection of all Interfaces which are
   *  owned by this package. If there are no owned Interfaces, an
   *  empty List is returned. This never returns <i>null</i>.
   * 
   * @return Collection[IInterface]
   */
  public Collection<IInterface> getInterfaces();

  /** Returns the collection of all Interfaces which are
   *  owned by this package. If there are no owned Interfaces, an
   *  empty List is returned. This never returns <i>null</i>.
   * 
   * @return Collection[IAssociation]
   */
  public Collection<IAssociation> getAssociations();
  
  /** Returns the collection of all elements imported
   *  by this package. If there are no imported elements, an
   *  empty List is returned. This never returns <i>null</i>.
   *  
   * @return Collection[IImportedElement].
   */
  public Collection<IImportedElement> getImports();
  
  /** The 'visitor' will do its work on any of the elements
   * in this IPackage instance.
   * 
   * @param visitor
   */
  public void accept(IPackageVisitor visitor);
   


}

