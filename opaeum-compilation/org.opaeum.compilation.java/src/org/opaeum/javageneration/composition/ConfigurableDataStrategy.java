package org.opaeum.javageneration.composition;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface ConfigurableDataStrategy extends ISimpleTypeStrategy{
	public String getDefaultStringValue();
	public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p);
	public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue);

}
