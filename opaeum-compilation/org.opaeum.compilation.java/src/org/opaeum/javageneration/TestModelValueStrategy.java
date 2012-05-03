package org.opaeum.javageneration;

import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface TestModelValueStrategy  extends ISimpleTypeStrategy{
	public String getDefaultStringValue(int seed);
}
