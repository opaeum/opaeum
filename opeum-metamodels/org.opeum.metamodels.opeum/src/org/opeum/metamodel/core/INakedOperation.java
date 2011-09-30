package org.opeum.metamodel.core;

import java.util.Collection;
import java.util.Set;

import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import nl.klasse.octopus.model.IOperation;

public interface INakedOperation extends IOperation,INakedPackageableElement,IParameterOwner{
	boolean isQuery();
	INakedClassifier getOwner();
	boolean hasReturnParameter();
	void setQuery(boolean b);
	boolean isStatic();
	void setStatic(boolean b);
	Set<? extends INakedBehavior> getMethods();
	void addMethod(INakedBehavior impl);
	// TODO move to code generation class
	boolean shouldEmulateClass();
	boolean isLongRunning();
	void setIsLongRunning(boolean b);
	INakedConstraint getBodyCondition();
	void initMessageStructure();
	INakedMessageStructure getMessageStructure();
	Collection<INakedClassifier> getRaisedExceptions();
	Collection<INakedOperation> getRedefinedOperations();
}