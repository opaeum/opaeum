/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IOclMessageType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.library.StdlibBasic;
import nl.klasse.octopus.stdlib.internal.library.StdlibCollections;
import nl.klasse.octopus.stdlib.internal.library.StdlibMessageTypes;
import nl.klasse.octopus.stdlib.internal.library.StdlibTupleTypes;
import nl.klasse.tools.common.Check;


/** @author  Jos Warmer
 * @version $Id: OclLibraryImpl.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 *
 * This class represents the OCL Standard Library. It makes sure it is 
 * properly initialized before use.
 */
public class OclLibraryImpl implements IOclLibrary {
	private StdlibCollections   myStdlibCollections     = null;
	private StdlibMessageTypes  myStdlibOclMessageTypes = null;
	private StdlibTupleTypes    myStdlibTuples          = null;

	public OclLibraryImpl() {
	    initialize();
	}

	public void initialize(){
	    StdlibBasic.initialize();
	    OclIterator.initialize();
	    myStdlibCollections = new StdlibCollections();
	    myStdlibCollections.initialize();
	    myStdlibTuples = new StdlibTupleTypes();
	    myStdlibTuples.initialize();
	    myStdlibOclMessageTypes = new StdlibMessageTypes();
	    myStdlibOclMessageTypes.initialize();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#reInitialize()
	 */
	public void reInitialize() {
	    myStdlibCollections.initialize();  
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#lookupStandardType(java.lang.String)
	 */
	public IClassifier lookupStandardType(String name) {
	      return StdlibBasic.getBasicType(name);
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#lookupCollectionType(nl.klasse.octopus.oclengine.modelinterface.CollectionMetaType, nl.klasse.octopus.oclengine.modelinterface.IClassifier)
	 */
	public ICollectionType lookupCollectionType(CollectionMetaType type, IClassifier elemType){
	      if (Check.ENABLED) Check.pre("lookupCollectionType: elemType is null", elemType != null);
	      ICollectionType result = myStdlibCollections.lookupType(type, elemType);
	      return result;
	}
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#lookupTupleType(java.util.List)
	 */
	public ITupleType lookupTupleType(List<IVariableDeclaration> analyzedParts){
      if (Check.ENABLED) Check.pre("lookupTupleType: analyzedParts is empty", !analyzedParts.isEmpty());
      ITupleType result = myStdlibTuples.lookupType(analyzedParts);
      return result;
	}
  
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#getReturnType(nl.klasse.octopus.oclengine.modelinterface.IClassifier, nl.klasse.octopus.oclengine.modelinterface.IOperation)
	 */
	public IClassifier getReturnType(IClassifier sourceType, IOperation op){
	  	if (Check.ENABLED) Check.pre("OclLibraryImpl.getReturnType: op is not 'asSet', 'allInstances', 'flatten', or '-'", 
	  	                             op.getName().equals("asSet") || op.getName().equals("flatten")
	  	                             || op.getName().equals("allInstances") || op.getName().equals("-"));
	
	  	IClassifier result = null;
	  	IClassifier originalResult = op.getReturnType();
	  	if (originalResult != StdlibBasic.getBasicType("DependsOnSourceType")) {
	  		result = originalResult;
	  	} else {
	  		if ( op.getName().equals("asSet") || op.getName().equals("allInstances") ){
		  		result = myStdlibCollections.lookupType(CollectionMetaType.SET, sourceType);
	  		}
	  		if ( op.getName().equals("flatten") ){
			    if (Check.ENABLED) Check.isTrue("OclLibraryImpl.getReturnType: sourceType should be a collection", 
			                                    sourceType instanceof ICollectionType);     
	  			IClassifier elType = ((ICollectionType)sourceType).getElementType();
	  			// TODO find out whether we need a deep or shallow flatten
	  			// this is a deep flatten
	  			while ( elType.isCollectionKind() ) {
	                elType = ((ICollectionType)elType).getElementType();
	  			} 
				result = myStdlibCollections.lookupType(((ICollectionType)sourceType).getMetaType(), elType);
	  		}			
	  		if ( op.getName().equals("-") ){
		  		result = sourceType;
	  		}
	  	}
	  	return result;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.stdlibinterface.IOclLibrary#getReturnType(java.util.List, nl.klasse.octopus.oclengine.modelinterface.IOperation)
	 */
	public IClassifier getReturnType(List<IClassifier> args, IOperation op){
		if (Check.ENABLED) Check.pre( "OclLibraryImpl.getReturnType: list 'args' should not be null", args != null);
		if (Check.ENABLED) Check.pre( "OclLibraryImpl.getReturnType: 'op' should not be null", op != null);
		if (Check.ENABLED) Check.pre( "OclLibraryImpl.getReturnType: list 'args' should contain Classifiers", 
		    						  Check.elementsOfType(args, IClassifier.class));
	  	IClassifier result = null;
	  	IClassifier originalResult = op.getReturnType();
	  	if (originalResult != StdlibBasic.getBasicType("DependsOnArgumentType")) {
	  		result = originalResult;
	  	} else {
	  		if (args.size() > 0 ) {
            	 result = (IClassifier) args.get(0);
	  		}
	  	}
	  	return result;
 	}
	/**
	 * @return
	 */
	public Collection getTupleTypes() {
		return myStdlibTuples.getTupleTypes();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.stdlib.IOclLibrary#lookupOclMessageType(nl.klasse.octopus.model.IOperation)
	 */
	public IOclMessageType lookupOclMessageType(IOperation op) {
		if (Check.ENABLED) Check.pre("lookupOclMessageType: operation is null", op != null);
		IOclMessageType result = myStdlibOclMessageTypes.lookupType(op);
		return result;
	}

}
