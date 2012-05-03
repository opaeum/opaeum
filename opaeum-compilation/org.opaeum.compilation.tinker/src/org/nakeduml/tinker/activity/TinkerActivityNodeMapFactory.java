package org.nakeduml.tinker.activity;

import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public class TinkerActivityNodeMapFactory {

	public static TinkerStructuralFeatureMap get(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedPin node) {
		TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, new PinBridge(concreteEmulatedClassifier, node));
		bridge.setComposite(true);
		bridge.isComposite();
		return new TinkerStructuralFeatureMap(bridge);
	}

}
