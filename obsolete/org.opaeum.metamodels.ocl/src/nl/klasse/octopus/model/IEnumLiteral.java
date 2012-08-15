/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** IEnumLiteral represents an enumeration literal.
 *  The <i>name</i> attribute inherited from IModelElement is enough to keep the
 *  String value of the literal. 
 *  The owner is the enumeration type to which this literal belongs.
 * 
 * @author  Jos Warmer
 * @version $Id: IEnumLiteral.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IEnumLiteral extends IModelElement {

  /** return the enumeration type that owns this literal.
   * 
   * @return IEnumerationType
   */
  public IEnumerationType getEnumeration();
  
}
