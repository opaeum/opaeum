package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.activities.INakedObjectFlow;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	public ObjectNodeExpressor(OpeumLibrary oclLibrary){
		super(oclLibrary);
	}
	public boolean pinsAvailableAsVariables(){
		return false;
	}
	public final String expressInputPinOrOutParamOrExpansionNode(OJBlock block,INakedObjectNode pin){
		// Either an outputpin or parameterNode
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		INakedObjectNode feedingNode = pin.getFeedingNode();
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), feedingNode);
		String call = map.umlName();// ParameterNode or top level output
									// pin or expansion node
		if(feedingNode instanceof INakedOutputPin){
			call = retrieveFromExecutionInstanceIfNecessary((INakedOutputPin) feedingNode, call);
		}
		return surroundWithSelectionAndTransformation(call, edge);
	}
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField(map.umlName(),map.javaTypePath());
		field.setInitExp(map.javaDefaultValue());
		block.addToLocals(field);
		operation.getOwner().addToImports(map.javaBaseTypePath());
		operation.getOwner().addToImports(map.javaDefaultTypePath());
		return field;
	}
	public String getterForStructuredResults(NakedStructuralFeatureMap resultMap){
		// Variable has been created in maybeBuildResultVariable
		return resultMap.umlName();
	}
	public String setterForSingleResult(NakedStructuralFeatureMap resultMap,String call){
		return resultMap.umlName() + "=" + call;
	}
}
