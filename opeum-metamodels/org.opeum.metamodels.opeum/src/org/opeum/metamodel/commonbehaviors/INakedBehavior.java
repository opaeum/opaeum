package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.IParameterOwner;

/**
 * From an Octopus perspective, any Behavior could possibly be called from an
 * ocl expression
 * 
 * @author ampie
 * 
 */
public interface INakedBehavior extends INakedMessageStructure, IParameterOwner, INakedBehavioredClassifier,ICompositionParticipant {
	@Deprecated
	/**
	 * is required for the following methods to be combined:
	 * <br>INameSpace IClassifier.getOwner() 
	 * <br>INakedNameSpace INakedClassifier.getOwner() 
	 * <br>IClassifier IOperation.getOwner() 
	 * Returns the nearest context of the behaviour if present.
	 * To find the nearest owning NameSpace, use getNameSpace() instead
	 */
	INakedBehavioredClassifier getOwner();

	void setSpecification(INakedOperation impl);

	INakedOperation getSpecification();

	boolean isClassifierBehavior();

	boolean isProcess();


}
