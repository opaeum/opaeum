package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ObjectNodeExpressor extends AbstractObjectNodeExpressor {
	public ObjectNodeExpressor(IOclLibrary oclLibrary) {
		super(oclLibrary);
	}

	public final String expressControlNode(OJBlock block, INakedControlNode controlNode) {
		// Either an outputpin or parameterNode
		INakedObjectFlow edge = (INakedObjectFlow)controlNode.getIncoming().iterator().next();
		INakedObjectNode feedingNode = (INakedObjectNode) edge.getSource();
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(controlNode.getActivity(), feedingNode);
		String call = map.umlName();// ParameterNode or top level output
									// pin or expansion node
		if (feedingNode instanceof INakedOutputPin) {
			call = retrieveFromExecutionInstanceIfNecessary((INakedOutputPin) feedingNode, call);
		}
		return surroundWithSelectionAndTransformation(call, edge);
	}
	
	public final String expressInputPinOrOutParamOrExpansionNode(OJBlock block, INakedObjectNode pin) {
		// Either an outputpin or parameterNode
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		INakedObjectNode feedingNode = pin.getFeedingNode();
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), feedingNode);
		String call = map.umlName();// ParameterNode or top level output
									// pin or expansion node
		if (feedingNode instanceof INakedOutputPin) {
			call = retrieveFromExecutionInstanceIfNecessary((INakedOutputPin) feedingNode, call);
		}
		return surroundWithSelectionAndTransformation(call, edge);
	}

	public OJAnnotatedField maybeBuildResultVariable(OJAnnotatedOperation operation, OJBlock block, NakedStructuralFeatureMap map) {
		OJAnnotatedField field = new OJAnnotatedField();
		field.setName(map.umlName());
		field.setType(map.javaTypePath());
		field.setInitExp(map.javaDefaultValue());
		block.addToLocals(field);
		operation.getOwner().addToImports(map.javaBaseTypePath());
		operation.getOwner().addToImports(map.javaDefaultTypePath());
		return field;
	}

	public String getterForStructuredResults(NakedStructuralFeatureMap resultMap) {
		// Variable has been created in maybeBuildResultVariable
		return resultMap.umlName();
	}

	public String setterForSingleResult(NakedStructuralFeatureMap resultMap, String call) {
		return resultMap.umlName() + "=" + call;
	}
}
