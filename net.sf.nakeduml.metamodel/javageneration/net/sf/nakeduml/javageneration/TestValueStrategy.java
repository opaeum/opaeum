package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;

public interface TestValueStrategy {
	public void transformClass(OJAnnotatedClass owner, OJBlock block);
	public String getDefaultValue();
}
