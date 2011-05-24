package org.nakeduml.tinker.composition;

import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;


public interface ConfigurableDataStrategy {
	public String getDefaultStringValue();
	public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p);
	public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue);

}
