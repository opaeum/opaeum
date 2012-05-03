package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedWriteVariableAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerVariableActionGenerator.class } , after = { TinkerVariableActionGenerator.class })
public class TinkerWriteVariableActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedWriteVariableAction.class })
	public void visitWriteVariableAction(INakedWriteVariableAction oa) {
		OJAnnotatedClass inputPinClass = findJavaClassForActivityNode(oa);
		implementWriteVariable(inputPinClass, oa);
		implementGetValue(inputPinClass, oa.getValue());
	}
	
	// TODO support multiplicity many
	private void implementWriteVariable(OJAnnotatedClass actionClass, INakedWriteVariableAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		OJAnnotatedOperation writeVariable = new OJAnnotatedOperation("writeVariable");
		TinkerGenerationUtil.addOverrideAnnotation(writeVariable);
		actionClass.addToOperations(writeVariable);
		writeVariable.addToParameters(new OJParameter("v", map.javaBaseTypePath()));
		writeVariable.getBody().addToStatements("this.getActivity()." + map.setter() + "(v)");
	}

}
