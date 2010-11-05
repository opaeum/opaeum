package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public class ObjectNodeExpressor {
	public String expressInputPinOrOutParam(OJBlock block, INakedObjectNode pin) {
		// Either an outputpin or parameterNode
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		INakedObjectNode feedingNode = pin.getFeedingNode();
		String call;
		if (feedingNode instanceof INakedOutputPin) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), feedingNode);
			call = map.umlName();
			call = retrieveFromExecutionInstanceIfNecessary(feedingNode, call);
		} else {
			call = feedingNode.getName();// From another type of object node
		}
		return surroundWithSelectionAndTransformation(call, edge);
	}

	protected String retrieveFromExecutionInstanceIfNecessary(INakedObjectNode feedingNode, String call) {
		if (feedingNode.getOwnerElement() instanceof INakedCallAction) {
			INakedCallAction callAction = (INakedCallAction) feedingNode.getOwnerElement();
			// Was the invoked element a task or process?
			// will be outputpin
			INakedOutputPin outputPin = (INakedOutputPin) feedingNode;
			if (callAction instanceof INakedOpaqueAction || BehaviorUtil.hasExecutionInstance(callAction.getCalledElement())) {
				INakedTypedElement p = (INakedTypedElement) outputPin.getLinkedTypedElement()==null?outputPin:outputPin.getLinkedTypedElement();
				if (callAction.getTargetElement() == null || callAction.getTargetElement().getNakedMultiplicity().isSingleObject()) {
					// Only one call, so retrieve the single result
					call = "get" + callAction.getMappingInfo().getJavaName().getCapped() + "().get"
							+ p.getMappingInfo().getJavaName().getCapped() + "()";
				} else {
					// TODO Multiple calls, so retrieve the result of the
					// last, or aggregate the results
					// NakedStructuralFeatureMap actionMap =
					// OJUtil.buildStructuralFeatureMap(callAction,
					// oclEngine.getOclLibrary());
					// ClassifierMap calledElement = new
					// NakedClassifierMap(callAction.getMessageStructure());
					// String local = calledElement.javaType() + " " +
					// actionMap.umlName();
					// String exp = "=(" + calledElement.javaType() + ")" +
					// actionMap.getter() + "().get(" + actionMap.getter()
					// + "().size())";
					// block.addToStatements(local + exp);
					call = "get" + callAction.getMappingInfo().getJavaName().getCapped() + "().iterator().next().get"
							+ p.getMappingInfo().getJavaName().getCapped() + "()";
				}
			}
		}
		return call;
	}

	protected String surroundWithSelectionAndTransformation(String expression, INakedObjectFlow edge) {
		if (edge.getSelection() != null) {
			expression = edge.getSelection().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if (edge.getTransformation() != null) {
			expression = edge.getTransformation().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		return expression;
	}

	public NakedStructuralFeatureMap maybeBuildResultVariable(OJAnnotatedOperation operation, OJBlock block, INakedPin returnPin) {
		NakedStructuralFeatureMap map = null;
		map = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
		OJField field = new OJField();
		field.setName(map.umlName());
		field.setType(map.javaTypePath());
		field.setInitExp(map.javaDefaultValue());
		block.addToLocals(field);
		operation.getOwner().addToImports(map.javaBaseTypePath());
		operation.getOwner().addToImports(map.javaDefaultTypePath());
		return map;
	}

	public String resultGetter(NakedStructuralFeatureMap resultMap) {
		return resultMap.umlName();
	}

	public String resultSetter(NakedStructuralFeatureMap resultMap, String call) {
		return resultMap.umlName() + "=" + call;
	}
}
