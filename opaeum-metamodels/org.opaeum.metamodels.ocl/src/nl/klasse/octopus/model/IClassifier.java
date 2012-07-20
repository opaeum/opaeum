/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;


/** IClassifier is the root of the type hierarchy. Each type is a subtype
 *  of IClassifier.  An IClassifier object owns attributes, operations, 
 *  associationEnds and states.
 *  <p>
 *  IClassifier also contains several lookup operations to find elements
 *  within the classifier.
 * 
 * @author  Jos Warmer
 * @version $Id: IClassifier.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface IClassifier extends IPackageableElement, INameSpace {

  /** Return a possibly empty collection of Attributes
   *  owned directly by this classifier.
   * 
   * @return Set[IAttribute]
   */
  public List<IAttribute> getAttributes  ( );
 

  /** Return a possibly empty collection of Operations
   *  owned by this classifier.
   * 
   * @return Set[IOperation]
   */
  public List<IOperation> getOperations  ( );



  /** Return a possibly empty collection of Generalizations
   *  of this classifier.
   * 
   * @return Set[IClassifier]
   */
  public List<IClassifier> getGeneralizations  ( );

  /** Return a possibly empty collection of subclassifiers
   *  of this classifier.
   * 
   * @return Set[IClassifier]
   */
  public Collection<IClassifier> getSubClasses();
  
  /** Return a possibly empty collection of Interfaces
   *  implemented by this classifier.
   * 
   * @return Set[Interface]
   */
  public List<IInterface> getInterfaces  ( );
  
  /** Return a possibly empty collection of States
   *  owned by this classifier.
   * 
   * @return Set[IState]
   */
  public List<IState> getStates  ( );
 

  /** Return the attribute with the name <i>attName</i>.
   *  Return null if no such attribute exists.
   *  Result will never be a class attribute.
   *  Supertypes will also be searched.
   * 
   * @param attName
   * @return IAttribute
   */
  public IAttribute findAttribute(String attName);

  /** Return the class attribute with the name <i>attName</i>.
   *  Return null if no such attribute exists.
   *  Result will never be an instance attribute
   *  Supertypes will also be searched.
   * 
   * @param attName
   * @return IAttribute
   */
  public IAttribute findClassAttribute(String attName);

  /** Return the IOperation with the name <i>opName</i> and parameters <i>parameters</i>.
   *  Return null if no such IOperation exists.
   *  Supertypes will also be searched.
   * 
   * @param opName
   * @param parameters: list of IClassifier
   * @return IOperation
   */
public IOperation findOperation(String opName, List<IClassifier> paramTypes);

  /** Return the IOperation with the name <i>opName</i> and parameters <i>parameters</i>.
   *  Return null if no such IOperation exists.
   *  Supertypes will also be searched.
   * 
   * @param opName
   * @param parameters: list of IClassifier
   * @return IOperation
   */
  public IOperation findClassOperation(String opName, List<IClassifier> paramTypes );

  /** Return the IAssociationEnd with the name <i>assName</i>.
   *  Return null if no such IAssociationEnd exists.
   *  Supertypes will also be searched.
   * 
   * @param assName
   * @return IAssociationEnd
   */
  public IAssociationEnd findAssociationEnd(String assName);

  /** Return the IAssociationClass with the name <i>assName</i>, if it is
   *  connected to this IClassifier instance.
   *  Return null if no such IAssociationClass exists.
   *  Supertypes will also be searched.
   * 
   * @param name
   * @return IAssociationClass
   */
  public IAssociationClass findAssociationClass(String name);
  
  /** Return the IState with the name <i>path</i>.
   *  Return null if no such IState exists.
   *  Supertypes will also be searched.
   * 
   * @param path
   * @return IState
   */
  public IState findState(PathName path);

  /** Returns true if this classifier instance conforms to <i>c</i>.
   * 
   * @param c
   * @return boolean
   */
  public boolean conformsTo(IClassifier c) ;

  /** Returns true if this classifier is a collection type, false
   *  otherwise.
   * 
   * @return boolean
   */
  public boolean isCollectionKind();

  /** Returns the package that owns this classifier.
   *  This can _never_ return null.
   *  
   * @return INameSpace
   */
   public INameSpace getOwner();
   
	/** Add an attribute that is defined in an OCL 'def' statement
	 * 
	 * @param attr
	 */
	public void addOclDefAttribute(IAttribute attr);
	
	/** Add an operation that is defined in an OCL 'def' statement
	 * 
	 * @param oper
	 */
	public void addOclDefOperation(IOperation oper);

	/** Returns the type that is the common supertype of this instance and
	 * <code>otherType</code> or <code>null</code> if none can be found.
	 * 
	 * @param otherType the type of which the common supertype should be found
	 * @return null or the common supertype
	 */
	public IClassifier findCommonSuperType(IClassifier otherType);

	/**
	 * @return Returns the isAbstract.
	 */
	public boolean isAbstract();

	/**
	 * @param isAbstract The isAbstract to set.
	 */
	public void setIsAbstract(boolean isAbstract);



}
