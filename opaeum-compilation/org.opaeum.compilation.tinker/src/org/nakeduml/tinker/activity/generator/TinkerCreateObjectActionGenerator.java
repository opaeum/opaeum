package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerCreateObjectActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedCreateObjectAction.class })
	public void visitCreateObjectAction(INakedCreateObjectAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 		
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerCreateObjectAction.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		implementGenerics(actionClass, oa);
		implementCreateObject(actionClass, oa);
		implementGetResult(actionClass, oa.getResult());
	}
	
	private void implementGenerics(OJAnnotatedClass actionClass, INakedCreateObjectAction oa) {
		NakedClassifierMap map = OJUtil.buildClassifierMap(oa.getClassifier());
		actionClass.addToImports(map.javaTypePath());
		actionClass.getSuperclass().addToGenerics(map.javaTypePath());
	}
	
	private void implementCreateObject(OJAnnotatedClass actionClass, INakedCreateObjectAction oa) {
		NakedClassifierMap map = OJUtil.buildClassifierMap(oa.getClassifier());
		OJAnnotatedOperation createObject = new OJAnnotatedOperation("createObject");
		TinkerGenerationUtil.addOverrideAnnotation(createObject);
		createObject.setReturnType(map.javaTypePath());
		createObject.getBody().addToStatements("return new " + map.javaTypePath().getLast() + "(true)");
		actionClass.addToOperations(createObject);
	}


}
