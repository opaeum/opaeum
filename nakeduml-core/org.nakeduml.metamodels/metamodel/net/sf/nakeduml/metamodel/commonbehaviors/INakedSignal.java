package net.sf.nakeduml.metamodel.commonbehaviors;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedProperty;
public interface INakedSignal extends INakedComplexStructure{
	List<INakedProperty> getArgumentParameters();
}