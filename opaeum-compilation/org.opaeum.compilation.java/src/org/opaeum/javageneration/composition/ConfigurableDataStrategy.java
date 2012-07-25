package org.opaeum.javageneration.composition;

import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;


public interface ConfigurableDataStrategy extends ISimpleTypeStrategy{
	public String getDefaultStringValue();
	public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, Property p);
	public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, Property p, String configuredValue);

}
