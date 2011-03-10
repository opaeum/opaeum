package net.sf.nakeduml.javageneration;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;


public interface TestValueStrategy {
	public void transformClass(OJAnnotatedClass owner, OJBlock block);
	public String getDefaultValue();
}
