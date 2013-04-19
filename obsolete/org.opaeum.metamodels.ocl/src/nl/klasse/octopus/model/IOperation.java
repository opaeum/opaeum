/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;


import java.util.List;

/** The class IOperation represents an operation that is owned by a IClassifier.
 * 
 * @author  Jos Warmer
 * @version $Id: IOperation.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface IOperation extends IPackageableElement {

  /** Returns the collection of parameters.
   *  If there are no parameters, an empty list is returned.
   *  The return type is not considered a  parameter, although the
   *  UML 1.4 metamodel defines this as a parameter with 
   *  parameter-direction-kind = return.
   * 
   * @return List
   */
  public List<IParameter> getParameters();
  
  /** Returns a list containing the types of the parameters of this operation.
   * 
   * @return a list containing IClassifier instances
   */
  public List<IClassifier> getParamTypes();

  /** Returns the type of the operation.  
   *  Returns <i>null</i> if there is no return type.
   * 
   * 
   * @return IClassifier
   */
  public IClassifier getReturnType();

  /** Returns the classifier that owns this operation.
   *  This can _never_ return null.
   *  
   * @return IOperation
   */
  public IClassifier getOwner();

  /** Returns the signature of the operation as a String.
   * 
   * @return String
   */
  public String getSignature( );

  /** Returns true if the operation is a class operation.
   * 
   * @return boolean
   */
  public boolean hasClassScope( );

  /** Returns true if this operation is defined in an OCL 'def' statement.
   * 
   * @return boolean
   */
  public boolean isOclDef( );

  /** Returns true if this operation is an abstract operation.
   * 
   * @return boolean
   */
  public boolean isAbstract( );

  /** Returns true if this operation is an infix operation.
   * 
   * @return boolean
   */
  public boolean isInfix( );

  /** Returns true if this operation is a prefix operation.
   * 
   * @return boolean
   */
  public boolean isPrefix( );

	/** Returns the visibility of this operation.
	 * 
	 * @return
	 */
	public VisibilityKind getVisibility();
}