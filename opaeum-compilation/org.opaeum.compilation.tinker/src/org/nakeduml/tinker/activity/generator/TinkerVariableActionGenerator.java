package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedVariableAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerVariableActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedVariableAction.class })
	public void visitVariableAction(INakedVariableAction oa) {
		OJAnnotatedClass ojClass = findJavaClassForActivityNode(oa); 
		implementReadVariable(ojClass, oa);
	}
	
	// TODO support multiplicity many
	private void implementReadVariable(OJAnnotatedClass actionClass, INakedVariableAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		OJAnnotatedOperation getVariable = new OJAnnotatedOperation("getVariable");
		TinkerGenerationUtil.addOverrideAnnotation(getVariable);
		getVariable.setReturnType(map.javaBaseTypePath());
		getVariable.getBody().addToStatements("return this.getActivity()." + map.getter() + "()");
		actionClass.addToOperations(getVariable);
	}


}
