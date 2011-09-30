package org.opeum.javageneration.persistence;

import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

import org.opeum.java.metamodel.annotation.OJAnnotatedField;


public interface JpaStrategy extends ISimpleTypeStrategy{
	void annotate(OJAnnotatedField f, INakedProperty p);
}
