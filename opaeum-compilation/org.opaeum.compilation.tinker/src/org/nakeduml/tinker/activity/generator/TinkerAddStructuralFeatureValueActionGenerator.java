package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAddStructuralFeatureValueAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerWriteStructuralFeatureActionGenerator.class } , after = { TinkerWriteStructuralFeatureActionGenerator.class })
public class TinkerAddStructuralFeatureValueActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedAddStructuralFeatureValueAction.class })
	public void visitAddStructuralFeatureValueAction(INakedAddStructuralFeatureValueAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAddStructuralFeatureValueAction.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		implementGenerics(actionClass, oa);
		implementAddStructuralFeatureValueAction(actionClass, oa);
	}
	
	private void implementGenerics(OJAnnotatedClass actionClass, INakedAddStructuralFeatureValueAction oa) {
		NakedStructuralFeatureMap mapObject = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getObject(), true);
		NakedStructuralFeatureMap mapFeature = OJUtil.buildStructuralFeatureMap(oa.getFeature());
		actionClass.getSuperclass().addToGenerics(mapFeature.javaBaseTypePath());
		actionClass.getSuperclass().addToGenerics(mapObject.javaBaseTypePath());
	}
	
	private void implementAddStructuralFeatureValueAction(OJAnnotatedClass actionClass, INakedAddStructuralFeatureValueAction oa) {
		OJAnnotatedOperation writeStructuralFeature = new OJAnnotatedOperation("writeStructuralFeature");
		TinkerGenerationUtil.addOverrideAnnotation(writeStructuralFeature);
		NakedStructuralFeatureMap mapObject = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getObject(), true);
		writeStructuralFeature.addParam("o", mapObject.javaBaseTypePath());
		NakedStructuralFeatureMap mapValue = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getValue(), true);
		writeStructuralFeature.addParam("v", mapValue.javaBaseTypePath());

		NakedStructuralFeatureMap mapFeature = OJUtil.buildStructuralFeatureMap(oa.getFeature());

		if (mapFeature.isOne()) {
			writeStructuralFeature.getBody().addToStatements("o." + mapFeature.setter() + "(v)");
		} else {
			// TODO take insertAt into account is ordered
			writeStructuralFeature.getBody().addToStatements("o." + mapFeature.adder() + "(v)");
		}
		actionClass.addToOperations(writeStructuralFeature);
	}

}
