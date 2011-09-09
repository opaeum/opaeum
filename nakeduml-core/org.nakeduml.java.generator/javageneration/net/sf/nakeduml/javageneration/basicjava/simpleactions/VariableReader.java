package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class VariableReader extends SimpleNodeBuilder<INakedReadVariableAction> {
	public VariableReader(NakedUmlLibrary oclEngine, INakedReadVariableAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(result.getActivity(), result);
		expressor.buildResultVariable(operation, block, resultMap);
		NakedStructuralFeatureMap variableMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getVariable());
		String call=expressor.storeResults(resultMap, variableMap.umlName(), variableMap.isMany());
		block.addToStatements(call);
	}
}