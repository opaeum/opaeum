/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

import nl.klasse.octopus.expressions.internal.types.PathName;

/** The superinterface of all elements in the system. 
 *  The only thing that is really in common is that all elements
 *  have a name and a pathname.
 * 
 * @author  Jos Warmer
 * @version $Id: IModelElement.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IModelElement {

	/** Return the name of this element.
	 * 
	 * @return String
	 */
	public String getName();   
	
	/** Return the qualified name that exists in the OCL concrete syntax
	 *  as Part1::part2::part3::part4
	 * 
	 * @return String
	 */
	public PathName getPathName();
   
}
