package net.sf.nakeduml.metamodel.activities;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public interface INakedActivityVariable extends INakedTypedElement{

	INakedActivity getActivity();
}
