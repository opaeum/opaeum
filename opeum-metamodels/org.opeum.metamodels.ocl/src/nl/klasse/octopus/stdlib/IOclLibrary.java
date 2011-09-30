/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib;

import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IOclMessageType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.ITupleType;


/**
 * @author  Jos Warmer
 * @version $Id: IOclLibrary.java,v 1.2 2008/01/19 13:31:10 annekekleppe Exp $
 */
public interface IOclLibrary {
	
	static public String StringTypeName 	= "String";
	static public String RealTypeName 		= "Real";
	static public String IntegerTypeName 	= "Integer";
	static public String BooleanTypeName 	= "Boolean";
	static public String OclVoidTypeName 	= "OclVoid";
	static public String OclAnyTypeName 	= "OclAny";
	static public String OclTypeTypeName 	= "OclType";
	static public String OclStateTypeName 	= "OclState";
    
	/** Reintializes the library, that is, all types of the form
	 *  Collection[TYPE] are removed. Should be used when the model
	 *  containing the TYPEs is seriously changed.	 * 
	 */
	public void reInitialize();
  
	/** Get the return type for operation 'op', given that 'sourceType'
     *  is the classifier on which 'op' is applied.
     * 
     *  This operation is neccesary for operations, whose return type
     *  is dependent upon the classifier to which it is applied. For instance,
     *  the 'asSet' and 'flatten' operations.
	 * @param sourceType: the classifier on t=which the operation 'op' is applied.
	 * @param op: the operation for which the return type should be found.
	 * @return IClassifier: the classifier that is the return type of 'op' applied on 'sourceType'.
	 */ 
  	public IClassifier getReturnType(IClassifier sourceType, IOperation op);

    /** Get the return type for operation 'op', given that 'sourceType'
     *  is the classifier on which 'op' is applied.
     * 
     *  This operation is neccesary for operations, whose return type
     *  is dependent upon the arguments to which it is applied. For instance,
     *  the 'oclAsType' operation.
	 * @param args: the list of types of the arguments applied to 'op'. 
	 * @param op: the operation for which the return type should be found.
	 * @return IClassifier: the classifier that is the return type of 'op' applied on 'sourceType'.
	 */
	public IClassifier getReturnType(List<IClassifier> args, IOperation op);

	/** Returns the ITupleType that corresponds with the ordered list of
	 *  VariableDeclarations 'parts'. Each name and type of the VariableDeclarations
	 * should correspond to the TupleParts in the TupleType, in the correct order.
	 * 
	 * @param parts: list of VariableDeclarations.
	 * @return ITupleType: the corresponding TupleType.
	 */
	public ITupleType lookupTupleType(List<IVariableDeclaration> parts);
	
	/** Returns the standard type with <i>name</i> in the OCL Library.
	 * 
	 * @param name The name of the type, e.g. 'Integer', 'String', ...
	 * @return IClasifier The standard type found, or null if no such standard type exists.
	 */
	public IClassifier lookupStandardType(String name);

	/** Returns the collectionType that holds elements of type 'elemType'. The kind of 
	 *  collection, i.e. Set, Bas, OrderedSet, or Sequence, is determined by 'type'.
	 * 
	 * @param type: the CollectionMetaType that indicates the kind of collection to look for.
	 * @param elemType: the type of the elements of the collection to be returned.
	 * @return
	 */
	public ICollectionType lookupCollectionType(CollectionMetaType type, IClassifier elemType);
	
	public IOclMessageType lookupOclMessageType(IOperation op);
}
