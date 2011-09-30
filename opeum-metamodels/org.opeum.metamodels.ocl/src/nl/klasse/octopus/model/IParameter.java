/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** A IParameter is a name-type combination that represents a parameter
 *  of an IOperation.
 * 
 * @author  Jos Warmer
 * @version $Id: IParameter.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IParameter extends IModelElement {
    
  /** Returns the type of the parameter.
   * 
   * @return IClassifier
   */
  IClassifier getType();

  /** Returns the operation that owns this parameter.
   *  This can _never_ return null.
   *  
   * @return IOperation
   */
  IOperation getOwner();
  
  /** Return the direction of the parameter.
   * 
   * @return int A constant from ParameterDirectionKind.
   */
  public ParameterDirectionKind getDirection();

}
