/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** IAttribute represents an attribute, which is always owned by
 *  an IClassifier instance. Attributes may be of any type, including collection types.
 * 
 * @author  Jos Warmer
 * @version $Id: IAttribute.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IAttribute extends IStructuralFeature {

  /** Returns the classifier that owns this attribute.
   *  This can _never_ return null.
   *  
   * @return IClassifier
   */
  public IClassifier getOwner();

  /** Returns true if this attribute is defined in an OCL 'def' statement.
   * 
   * @return boolean
   */
  public boolean isOclDef( );
}
   
  