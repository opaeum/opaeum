package org.nakeduml.tinker.activity.maps;

import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;

public class TinkerActivityNodeMapFactory {

	public static TinkerStructuralFeatureMap getPinToActionAssociationMap(ConcreteEmulatedClassifier actionConcreteEmulatedClassifier, INakedPin pin) {
		ActivityNodeBridge pinBridge = new ActivityNodeBridge(actionConcreteEmulatedClassifier, pin, true);
		pinBridge.setComposite(true);
		ActivityNodeBridge actionBridge = new ActivityNodeBridge(actionConcreteEmulatedClassifier, pin.getAction(), false);
		pinBridge.setOtherEnd(actionBridge);
		return new TinkerStructuralFeatureMap(pinBridge);
	}

	public static TinkerStructuralFeatureMap getActionToPinAssociationMap(ConcreteEmulatedClassifier actionConcreteEmulatedClassifier, INakedPin pin) {
		ActivityNodeBridge actionBridge = new ActivityNodeBridge(actionConcreteEmulatedClassifier, pin.getAction(), false);
		ActivityNodeBridge pinBridge = new ActivityNodeBridge(actionConcreteEmulatedClassifier, pin, true);
		pinBridge.setComposite(true);
		actionBridge.setOtherEnd(pinBridge);
		return new TinkerStructuralFeatureMap(actionBridge);
	}

	public static TinkerStructuralFeatureMap getNodeToActivityAssociationMap(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedBehavior activity, INakedActivityNode node) {
//		ActivityNodeBridge bridge = new ActivityNodeBridge(concreteEmulatedClassifier, node, true);
//		bridge.setComposite(true);
		ActivityBridge activityBridge = new ActivityBridge(concreteEmulatedClassifier, activity, false);
//		bridge.setOtherEnd(activityBridge);
		return new TinkerStructuralFeatureMap(activityBridge);
	}

	public static TinkerStructuralFeatureMap getNodeToActivityAssociationMap(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedActivityNode node) {
		ActivityNodeBridge bridge = new ActivityNodeBridge(concreteEmulatedClassifier, node, true);
		bridge.setComposite(true);
		ActivityBridge activityBridge = new ActivityBridge(concreteEmulatedClassifier, node.getActivity(), false);
		bridge.setOtherEnd(activityBridge);
		return new TinkerStructuralFeatureMap(bridge);
	}

	public static TinkerStructuralFeatureMap getActivityToNodeAssociationMap(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedActivityNode node) {
		ActivityBridge activityBridge = new ActivityBridge(concreteEmulatedClassifier, node.getActivity(), false);
		ActivityNodeBridge bridge = new ActivityNodeBridge(concreteEmulatedClassifier, node, true);
		bridge.setComposite(true);
		activityBridge.setOtherEnd(bridge);
		return new TinkerStructuralFeatureMap(activityBridge);
	}

	public static TinkerStructuralFeatureMap get(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedActivityNode node1, INakedActivityNode otherEnd, boolean inverse) {
		ActivityNodeBridge bridge1 = new ActivityNodeBridge(concreteEmulatedClassifier, node1, inverse);
		ActivityNodeBridge bridge2 = new ActivityNodeBridge(concreteEmulatedClassifier, otherEnd, !inverse);
		bridge1.setOtherEnd(bridge2);
		return new TinkerStructuralFeatureMap(bridge1);
	}

	public static TinkerStructuralFeatureMap get(INakedPin node) {
		return TinkerActivityNodeMapFactory.getPinToActionAssociationMap(TinkerActivityNodeMapFactory.getEmulatedClasifier(node), node);
	}

	public static ConcreteEmulatedClassifier getEmulatedClasifier(INakedPin node) {
		return new ConcreteEmulatedClassifier(node.getActivity().getNameSpace(), node.getAction());
	}

	public static TinkerStructuralFeatureMap getPinVariableInActionAssociationMap(ConcreteEmulatedClassifier concreteEmulatedClassifier, INakedPin pin) {
		InputPinVariableBridge pinBridge = new InputPinVariableBridge(concreteEmulatedClassifier, pin, false);
		pinBridge.setIsUnique(true);
		return new TinkerStructuralFeatureMap(pinBridge);
	}

}
