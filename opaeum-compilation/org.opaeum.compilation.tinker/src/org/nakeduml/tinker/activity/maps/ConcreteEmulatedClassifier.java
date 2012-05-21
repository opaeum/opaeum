package org.nakeduml.tinker.activity.maps;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.internal.emulated.EmulatedClassifier;

public class ConcreteEmulatedClassifier extends EmulatedClassifier {
	private static final long serialVersionUID = -7051187260422402462L;

	public ConcreteEmulatedClassifier(INakedNameSpace owner, INakedElement element) {
		super(owner, element);
	}

	@Override
	public String getName() {
		if (this.element instanceof INakedPin) {
			return TinkerBehaviorUtil.pinPathName((INakedPin) this.element);
		} else {
			return super.getName();
		}
	}
}
