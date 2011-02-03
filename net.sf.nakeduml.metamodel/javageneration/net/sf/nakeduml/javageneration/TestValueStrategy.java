package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface TestValueStrategy {
	public String getDefaultValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p);
	public String getDefaultValue();
}
