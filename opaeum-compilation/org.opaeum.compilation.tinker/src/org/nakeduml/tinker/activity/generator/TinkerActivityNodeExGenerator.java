package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class, 
	TinkerActivityParameterNodeGenerator.class,
	TinkerInputPinGenerator.class, 
	TinkerOutputPinGenerator.class, 
	TinkerValuePinGenerator.class,
	TinkerReadVariableActionGenerator.class,
	TinkerAddVariableValueAction.class,
	TinkerAddStructuralFeatureValueActionGenerator.class,
	TinkerReplyActionGenerator.class,
	TinkerOpaqueActionGenerator.class,
	TinkerCallBehaviorActionGenerator.class,
	TinkerCallOperationActionGenerator.class,
	TinkerSendSignalActionGenerator.class,
	TinkerCreateObjectActionGenerator.class,
	TinkerAcceptEventActionGenerator.class,
	TinkerAcceptCallActionGenerator.class,
	TinkerControlNodeGenerator.class,
	TinkerActivityEdgeGenerator.class}, after = { TinkerActivityNodeGenerator.class,TinkerImplementNodeStep.class })
public class TinkerActivityNodeExGenerator extends AbstractTinkerActivityNodeGenerator {

	// public OpaeumLibrary getLibrary() {
	// return workspace.getOpaeumLibrary();
	// }

	// private void addGetInFlow(OJClass actionClass, INakedActivityNode oa) {
	// OJAnnotatedOperation getInFlow = new OJAnnotatedOperation("getInFlow");
	//
	// INakedActivityEdge outEdge = oa.getIncoming().iterator().next();
	// OJPathName path =
	// OJUtil.packagePathname(outEdge.getNameSpace()).getCopy();
	// path.addToNames(NameConverter.capitalize(outEdge.getName()));
	// getInFlow.setReturnType(path);
	//
	// for (INakedActivityEdge edge : oa.getIncoming()) {
	// getInFlow.getBody().addToStatements("return " +
	// TinkerBehaviorUtil.edgeGetter(edge) + "()");
	// }
	// actionClass.addToImports(new OJPathName("java.util.Arrays"));
	// actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
	// actionClass.addToOperations(getInFlow);
	// }

}
