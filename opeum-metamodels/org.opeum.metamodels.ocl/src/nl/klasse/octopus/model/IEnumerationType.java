/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.List;

/** IEnumerationType represents all enumeration types in OCL. 
 *  
 * @author  Jos Warmer
 * @version $Id: IEnumerationType.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface IEnumerationType extends IDataType {  

  /** Return the enumeration literal with the name <i>name</i>.
   *  Return null if no such elemnt exists.
   * 
   * @param name
   * @return IModelElement
   */
  public IModelElement lookupLiteral(String name);
  
  /** Get a list of all enumeration literals belonging to
   *  this enumeration type.
   * 
   * @return List of EnumerationLiteral;
   */
  public List<IEnumLiteral> getLiterals();
}
