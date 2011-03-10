/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.internal.analysis.NamedElement;
import nl.klasse.octopus.expressions.internal.types.PathName;


/** An INameSpace represents a namespace, that may contain elements that can
 * be found by name, or by name plus parameters in case of operations.
 * <p>An element in a namespace can also be a namespace.
 * 
 * @version $Id: INameSpace.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public interface INameSpace {
	/** Return the model element within this namespace that is refered to by 'path'.
	 * Note that the first string in 'path' (path.getFirst()) must be visible locally
	 * in this namespace.
	 * @param path: the name of the model element, e.g. pack1::classA::attr3, or 
	 * class1::stateS::subStateS, or attr2, or assocRoleName45, etc.
	 * @return the found model element
	 */
	public IModelElement lookup(PathName path);
	/** Return the operation within this namespace that is refered to by 'path'
	 * and has parameter types that conform to the IClassifiers in list 'types'.
	 * Note that the first string in 'path' (path.getFirst()) must be visible locally
	 * in this namespace.
	 * @param path: the name of the operation, e.g. pack1::pack2::classX::oper23, or oper4
	 * @param types: a list of IClassifier instances that represent the types of the arguments
	 * to the operation we search for. 
	 * @return the found operation, otherwise null
	 */
	public IOperation lookupOperation(PathName path, List<IClassifier> types);
	public Collection<IImportedElement> getImports();
	public Collection<IClassifier> getClassifiers();
	public Collection<IPackage> getSubpackages();
}
