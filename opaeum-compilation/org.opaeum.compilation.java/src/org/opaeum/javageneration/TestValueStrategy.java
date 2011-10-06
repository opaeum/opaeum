package org.opaeum.javageneration;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface TestValueStrategy  extends ISimpleTypeStrategy{
	public void transformClass(OJAnnotatedClass owner, OJBlock block);
	public String getDefaultValue();
}
