/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.Collection;

import nl.klasse.octopus.expressions.IVariableDeclaration;


/** ITupleType represents all tuple types in OCL. The parts of a tupletype
 *  are represented by its Attributes. Therefore this type has no additional
 *  operations, all is inherited from IClassifier.
 * 
 * @author  Jos Warmer
 * @version $Id: ITupleType.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface ITupleType extends IDataType {  

	public Collection<IVariableDeclaration> getParts();

}
