package org.opaeum.javageneration.persistence;

import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface JpaStrategy extends ISimpleTypeStrategy{
	void annotate(OJAnnotatedClass owner, OJAnnotatedField f, Property p);
}
