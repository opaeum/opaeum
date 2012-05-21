package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedValuePin;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerPinGenerator.class }, after = { TinkerPinGenerator.class })
public class TinkerValuePinGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitAfter(matchSubclasses = false, match = { INakedValuePin.class })
	public void visitValuePins(INakedValuePin oa) {
		OJAnnotatedClass valuePinClass = findJavaClassForActivityNode(oa);
		OJPathName superClass;
		if (oa.getNakedMultiplicity().isOne()) {
			superClass = TinkerBehaviorUtil.tinkerOneValuePinPathName.getCopy();
		} else {
			superClass = TinkerBehaviorUtil.tinkerManyValuePinPathName.getCopy();
		}
		valuePinClass.setSuperclass(superClass);
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		valuePinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));

		addGetValue(valuePinClass, oa);
//		addGetContextObject(valuePinClass, oa.getActivity().getContext());
	}

	private void addGetValue(OJAnnotatedClass inputPinClass, INakedValuePin oa) {
		OJAnnotatedOperation getValue = new OJAnnotatedOperation("getValue");
		TinkerGenerationUtil.addOverrideAnnotation(getValue);
		getValue.setReturnType(OJUtil.classifierPathname(oa.getNakedBaseType()));
		String expressValue = ValueSpecificationUtil.expressValue(getValue, oa.getValue(), oa.getAction().getContext(), oa.getType());
		getValue.getBody().addToStatements("return " + expressValue);
		inputPinClass.addToOperations(getValue);
	}

}
