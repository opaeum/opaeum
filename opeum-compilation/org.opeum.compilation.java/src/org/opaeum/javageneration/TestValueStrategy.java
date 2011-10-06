package org.opeum.javageneration;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface TestValueStrategy  extends ISimpleTypeStrategy{
	public void transformClass(OJAnnotatedClass owner, OJBlock block);
	public String getDefaultValue();
}
