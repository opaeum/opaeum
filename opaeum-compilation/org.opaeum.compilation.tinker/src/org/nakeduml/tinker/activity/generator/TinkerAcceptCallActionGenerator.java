package org.nakeduml.tinker.activity.generator;

import java.util.Arrays;
import java.util.Collections;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = {TinkerAcceptEventActionGenerator.class}, after = {TinkerAcceptEventActionGenerator.class})
public class TinkerAcceptCallActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedAcceptCallAction.class })
	public void visitAcceptCallActionBefore(INakedAcceptCallAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 		
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAcceptCallAction);
	}

	@VisitAfter(matchSubclasses = false, match = { INakedAcceptCallAction.class })
	public void visitAcceptCallActionAfter(INakedAcceptCallAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		OJConstructor constructor1 = actionClass.findConstructor(OJUtil.classifierPathname(oa.getActivity().getContext()));
		addCreateComponentConstructor(constructor1, actionClass, oa);
		addCopyEventToOutputPin(actionClass, oa);
		addGetReturnInformationOutputPin(actionClass, oa);
		addGetReplyAction(actionClass, oa);
		removeAcceptCallActionFromActivityClass(actionClass, oa);
		implementGetTriggers(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	private void addGetReturnInformationOutputPin(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		OJAnnotatedOperation getReturnInformationOutputPin = new OJAnnotatedOperation("getReturnInformationOutputPin");
		TinkerGenerationUtil.addOverrideAnnotation(getReturnInformationOutputPin);
		for (INakedOutputPin outputPin : oa.getOutput()) {
			if (outputPin.equals(((INakedAcceptCallAction) oa).getReturnInfo())) {
				getReturnInformationOutputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(outputPin));
				getReturnInformationOutputPin.getBody().addToStatements("return " + TinkerBehaviorUtil.outputPinGetterName(outputPin) + "()");
				break;
			}
		}
		actionClass.addToOperations(getReturnInformationOutputPin);
	}
	
	private void addGetReplyAction(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		OJAnnotatedOperation getReplyAction = new OJAnnotatedOperation("getReplyAction", TinkerBehaviorUtil.activityNodePathName(oa.getReplyAction()));
		TinkerGenerationUtil.addOverrideAnnotation(getReplyAction);
		getReplyAction.getBody().addToStatements("return getReturnInformationOutputPin().getReturnInformationInputPin().getAction()");
		actionClass.addToOperations(getReplyAction);
	}
	
	private void removeAcceptCallActionFromActivityClass(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa, getLibrary());
		OJAnnotatedClass activityClass = findJavaClass(oa.getActivity());
		OJField fieldToRemove = null;
		OJOperation internalAdderToRemove = null;
		OJOperation internalRemoverToRemove = null;
		OJOperation getter = null;
		OJOperation setter = null;
		for (OJField field : activityClass.getFields()) {
			if (field.getName().equals(map.fieldname())) {
				fieldToRemove = field;
				internalAdderToRemove = activityClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				internalRemoverToRemove = activityClass.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
				getter = activityClass.findOperation(map.getter(), Collections.emptyList());
				setter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaBaseTypePath()));
				break;
			}
		}
		activityClass.removeFromFields(fieldToRemove);
		activityClass.removeFromOperations(internalAdderToRemove);
		activityClass.removeFromOperations(internalRemoverToRemove);
		activityClass.removeFromOperations(getter);
		activityClass.removeFromOperations(setter);
	}



}
