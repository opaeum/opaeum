package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.activities.INakedInputPin;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerPinGenerator.class }, after = { TinkerPinGenerator.class })
public class TinkerInputPinGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitAfter(matchSubclasses = false, match = { INakedInputPin.class })
	public void visitInputPins(INakedInputPin oa) {
		OJAnnotatedClass inputPinClass = findJavaClassForActivityNode(oa);

		OJPathName superClass;
		if (oa.getAction() instanceof INakedReplyAction && ((INakedReplyAction) oa.getAction()).getReturnInfo().equals(oa)) {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneReturnInformationInputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyReturnInformationInputPinPathName.getCopy();
			}
		} else {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneInputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyInputPinPathName.getCopy();
			}
		}
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		inputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		inputPinClass.setSuperclass(superClass);
	}

}
