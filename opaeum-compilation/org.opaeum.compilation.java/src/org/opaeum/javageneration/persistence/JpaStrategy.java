package org.opaeum.javageneration.persistence;

import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface JpaStrategy extends ISimpleTypeStrategy{
	void annotate(OJAnnotatedField f, INakedProperty p);
}
