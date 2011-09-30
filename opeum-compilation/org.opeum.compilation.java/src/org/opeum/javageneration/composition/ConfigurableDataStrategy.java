package org.opeum.javageneration.composition;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface ConfigurableDataStrategy extends ISimpleTypeStrategy{
	public String getDefaultStringValue();
	public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p);
	public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue);

}
