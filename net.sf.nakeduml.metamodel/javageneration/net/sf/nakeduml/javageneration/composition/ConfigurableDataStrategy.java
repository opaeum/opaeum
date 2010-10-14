package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface ConfigurableDataStrategy {
	public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p);
	public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue);

}
