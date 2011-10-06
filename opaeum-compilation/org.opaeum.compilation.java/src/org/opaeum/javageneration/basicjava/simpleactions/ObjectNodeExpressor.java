package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	public ObjectNodeExpressor(OpaeumLibrary oclLibrary){
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
