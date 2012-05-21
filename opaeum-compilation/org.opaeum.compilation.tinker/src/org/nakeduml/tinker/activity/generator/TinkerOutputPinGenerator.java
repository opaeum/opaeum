package org.nakeduml.tinker.activity.generator;

import java.util.Arrays;
import java.util.Collections;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ActivityNodeBridge;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedOutputPin;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerPinGenerator.class }, after = { TinkerPinGenerator.class })
public class TinkerOutputPinGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedOutputPin.class })
	public void visitOutputPins(INakedOutputPin oa) {

		OJAnnotatedClass outputPinClass = findJavaClassForActivityNode(oa);
		
		boolean implementGetReturnInformationInputPin = false;
		OJPathName superClass;
		if (oa.getAction() instanceof INakedAcceptCallAction && ((INakedAcceptCallAction) oa.getAction()).getReturnInfo().equals(oa)) {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneReturnInformationOutputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyReturnInformationOutputPinPathName.getCopy();
			}
			implementGetReturnInformationInputPin = true;
		} else {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy();
			}
		}
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		outputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		outputPinClass.setSuperclass(superClass);

		implementCopyTokensToStart(outputPinClass);

		removeOutputPinfromActivityClass(oa);

		if (implementGetReturnInformationInputPin) {
			implementGetReturnInformationInputPin(outputPinClass, oa);
		}
	}

	private void implementCopyTokensToStart(OJAnnotatedClass outputPinClass) {
		OJAnnotatedOperation copyTokensToStart = new OJAnnotatedOperation("copyTokensToStart");
		TinkerGenerationUtil.addOverrideAnnotation(copyTokensToStart);
		copyTokensToStart.getBody().addToStatements("setStarts(getOutTokens())");
		outputPinClass.addToOperations(copyTokensToStart);
	}

	private void removeOutputPinfromActivityClass(INakedOutputPin oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa, true);
		OJAnnotatedClass activityClass = findJavaClass(oa.getActivity());
		OJOperation clearToRemove = null;
		OJOperation adderAllToRemove = null;
		OJOperation removeAllToRemove = null;
		OJField fieldToRemove = null;
		OJOperation adderToRemove = null;
		OJOperation removerToRemove = null;
		OJOperation internalAdderToRemove = null;
		OJOperation internalRemoverToRemove = null;
		OJOperation getter = null;
		OJOperation setter = null;
		OJOperation manySetter = null;
		for (OJField field : activityClass.getFields()) {
			if (field.getName().equals(map.fieldname())) {
				fieldToRemove = field;
				clearToRemove = activityClass.findOperation(map.clearer(), Collections.emptyList());
				removeAllToRemove = activityClass.findOperation(map.removeAll(), Arrays.asList(map.javaTypePath()));
				adderAllToRemove = activityClass.findOperation(map.allAdder(), Arrays.asList(map.javaTypePath()));
				adderToRemove = activityClass.findOperation(map.adder(), Arrays.asList(map.javaBaseTypePath()));
				removerToRemove = activityClass.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
				internalAdderToRemove = activityClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				internalRemoverToRemove = activityClass.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
				getter = activityClass.findOperation(map.getter(), Collections.emptyList());
				setter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaBaseTypePath()));
				manySetter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
				break;
			}
		}
		activityClass.removeFromOperations(removeAllToRemove);
		activityClass.removeFromOperations(clearToRemove);
		activityClass.removeFromFields(fieldToRemove);
		activityClass.removeFromOperations(adderToRemove);
		activityClass.removeFromOperations(adderAllToRemove);
		activityClass.removeFromOperations(removerToRemove);
		activityClass.removeFromOperations(internalAdderToRemove);
		activityClass.removeFromOperations(internalRemoverToRemove);
		activityClass.removeFromOperations(getter);
		activityClass.removeFromOperations(setter);
		activityClass.removeFromOperations(manySetter);
	
		OJAnnotatedOperation clearCache = (OJAnnotatedOperation) activityClass.findOperation("clearCache", Collections.emptyList());
		clearCache.getBody().removeFromStatements(clearCache.getBody().findStatement(map.fieldname()));
	
	}

	private void implementGetReturnInformationInputPin(OJAnnotatedClass outputPinClass, INakedOutputPin oa) {
		OJAnnotatedOperation getReturnInformationInputPin = new OJAnnotatedOperation("getReturnInformationInputPin");
		INakedActivityEdge outFlow = oa.getOutgoing().iterator().next();
		INakedActivityNode target = outFlow.getTarget();
		getReturnInformationInputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(target));
		getReturnInformationInputPin.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(outFlow) + "().getTarget()");
		outputPinClass.addToOperations(getReturnInformationInputPin);
	}

}
