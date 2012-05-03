package org.nakeduml.tinker.activity.generator;

import java.util.Arrays;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerReplyActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedReplyAction.class })
	public void visitReplyAction(INakedReplyAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerReplyActionPathName.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		// addBlockingQueue(actionClass, oa);
		// if (!oa.getReplyValues().isEmpty()) {
		// addGetReply(actionClass, oa);
		// } else {
		// System.out.println();
		// }
		putReplyInBlockingQueue(actionClass, oa);
		addAddToInputPinVariable(actionClass, oa);
	}

	private void putReplyInBlockingQueue(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));

				OJOperation adder = actionClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				adder.getBody().addToStatements(TinkerBehaviorUtil.tinkerOperationBlockingQueue.getLast() + ".INSTANCE.put(getUid(), " + map.fieldname() + ")");
				actionClass.addToImports(TinkerBehaviorUtil.tinkerOperationBlockingQueue);

				break;
			}
		}

	}

	private void addGetReply(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		OJAnnotatedOperation getReply = new OJAnnotatedOperation("getReply");
		TinkerGenerationUtil.addOverrideAnnotation(getReply);

		getReply.getBody().addToStatements("return " + TinkerBehaviorUtil.tinkerOperationBlockingQueue.getLast() + ".INSTANCE.take(getUid())");
		actionClass.addToImports(TinkerBehaviorUtil.tinkerOperationBlockingQueue);

		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
				getReply.setReturnType(map.javaBaseTypePath());
				break;
			}
		}

		actionClass.addToOperations(getReply);
	}

	private void addBlockingQueue(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		OJField synchronousField = new OJField();
		synchronousField.setName("replyQueue");
		synchronousField.setType(new OJPathName("java.util.concurrent.BlockingQueue"));
		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
				synchronousField.getType().addToGenerics(map.javaBaseTypePath());
				synchronousField.setInitExp("new ArrayBlockingQueue<" + map.javaBaseTypePath().getLast() + ">(1)");
				break;
			}
		}
		actionClass.addToFields(synchronousField);
		actionClass.addToImports(new OJPathName("java.util.concurrent.ArrayBlockingQueue"));
		actionClass.addToImports(new OJPathName("java.util.concurrent.BlockingQueue"));
	}

}
