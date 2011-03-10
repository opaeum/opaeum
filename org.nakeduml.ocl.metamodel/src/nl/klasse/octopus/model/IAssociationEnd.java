/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** IAssociationEnd represents one end of an association. 
 *  <p>
 *  The type of an association-end takes the multiplicity into account.
 *  For example, when class A is associated with class B and the multiplicity at
 *  B is many, the type of that association end will be Set(B). The inherited operation
 *  <code>getType()<\code> will result in Set(B). The operation <code>getBaseType()<\code>
 *  will result in B.
 * 
 * @author  Jos Warmer
 * @version $Id: IAssociationEnd.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IAssociationEnd extends IStructuralFeature {

  /** Returns the type of the classifier at this end.
   * 
   * @return IClassifier
   */
  public IClassifier getBaseType();

  /** Returns true if this association end is navigable, false otherwise.
   * 
   * @return boolean
   */
  boolean isNavigable();
  /** Returns the association this end belongs to.
   * 
   * @return IAssociation
   */
  public IAssociation getAssociation();
}
