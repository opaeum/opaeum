package org.nakeduml.tinker.activity;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.internal.emulated.EmulatedClassifier;

public class ConcreteEmulatedClassifier extends EmulatedClassifier {
	private static final long serialVersionUID = -7051187260422402462L;

	public ConcreteEmulatedClassifier(INakedNameSpace owner, INakedElement element) {
		super(owner, element);
	}
}
