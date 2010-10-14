package net.sf.nakeduml.metamodel.activities;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
public interface INakedValuePin extends INakedInputPin, INakedElementOwner {
	INakedValueSpecification getValue();
	void setValue(INakedValueSpecification value);
}
