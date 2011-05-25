package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.ActionFeatureBridge;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public final class Jbpm5ObjectNodeExpressor extends AbstractObjectNodeExpressor {
	public Jbpm5ObjectNodeExpressor(IOclEngine oclEngine) {
		super(oclEngine.getOclLibrary());
	}

	@Override
	public OJAnnotatedField maybeBuildResultVariable(OJAnnotatedOperation operation, OJBlock block, NakedStructuralFeatureMap resultMap) {
		if (inStructuredActivity(getOriginal(resultMap)) && resultMap.isMany()) {
			// Setup collection
			OJAnnotatedField outPinVar = new OJAnnotatedField(resultMap.umlName(), resultMap.javaTypePath());
			block.addToLocals(outPinVar);
			outPinVar.setInitExp("context.getVariable(\"" + resultMap.umlName() + "\")");
			OJIfStatement ifNull = new OJIfStatement(resultMap.umlName() + "==null", resultMap.umlName() + "="
					+ resultMap.javaDefaultValue());
			block.addToStatements(ifNull);
			ifNull.getThenPart().addToStatements("context.setVariable(\"" + resultMap.umlName() + "\"," + resultMap.umlName() + ")");
			operation.getOwner().addToImports(resultMap.javaBaseTypePath());
			operation.getOwner().addToImports(resultMap.javaDefaultTypePath());
			return outPinVar;
		}
		return null;
	}

	private boolean inStructuredActivity(INakedElement node) {
		if (node instanceof INakedExpansionNode) {
			return ((INakedExpansionNode) node).getExpansionRegion().getOwnerElement() instanceof INakedStructuredActivityNode;
		} else if (node instanceof INakedPin) {
			return ((INakedPin) node).getAction().getOwnerElement() instanceof INakedStructuredActivityNode;
		} else if (node instanceof INakedAction) {
			return ((INakedAction) node).getOwnerElement() instanceof INakedStructuredActivityNode;
		} else if (node instanceof INakedActivityVariable) {
			return ((INakedActivityVariable) node).getOwnerElement() instanceof INakedStructuredActivityNode;
		} else {
			return node.getOwnerElement() instanceof INakedStructuredActivityNode;
		}
	}

	@Override
	public String getterForStructuredResults(NakedStructuralFeatureMap resultMap) {
		if (inStructuredActivity(getOriginal(resultMap))) {
			// Variable has been retrieved from
			// context in maybeBuildResultVariable
			return resultMap.umlName();
		} else {
			return resultMap.getter() + "()";
		}
	}

	// TODO ugly but effective.
	private INakedElement getOriginal(NakedStructuralFeatureMap resultMap) {
		if (resultMap.getProperty() instanceof ActionFeatureBridge) {
			return ((ActionFeatureBridge) resultMap.getProperty()).getAction();
		} else {
			TypedElementPropertyBridge property = (TypedElementPropertyBridge) resultMap.getProperty();
			INakedTypedElement original = (INakedTypedElement) property.getOriginal();
			return original;
		}
	}

	@Override
	public String setterForSingleResult(NakedStructuralFeatureMap resultMap, String call) {
		if (inStructuredActivity(getOriginal(resultMap))) {
			return "context.setVariable(\"" + resultMap.umlName() + "\"," + call + ")";
		} else {
			return resultMap.setter() + "(" + call + ")";
		}
	}

	public String expressInputPinOrOutParamOrExpansionNode(OJBlock block, INakedObjectNode pin) {
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		INakedObjectNode feedingNode = pin.getFeedingNode();
		NakedStructuralFeatureMap feedingMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode, true);
		String call = feedingMap.getter() + "()";
		// Parameter or top level ExpansionNode
		if (feedingNode instanceof INakedOutputPin) {
			INakedOutputPin outputPin = (INakedOutputPin) feedingNode;
			if (outputPin.getAction().getOwnerElement() instanceof INakedExpansionRegion) {
				call = "(" + feedingMap.javaType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
			}
			call = retrieveFromExecutionInstanceIfNecessary(outputPin, call);
		} else if (feedingNode instanceof INakedExpansionNode) {
			INakedExpansionNode expansionNode = (INakedExpansionNode) feedingNode;
			INakedExpansionRegion region = (INakedExpansionRegion) expansionNode.getOwnerElement();
			if (expansionNode.isInputElement()) {
				call = "(" + feedingMap.javaBaseType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
			} else if (region.getOwnerElement() instanceof INakedStructuredActivityNode) {
				call = "(" + feedingMap.javaType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
			} else {
				call = feedingMap.getter() + "()";
			}
		}
		return surroundWithSelectionAndTransformation(call, edge);
	}

	@Override
	protected String retrieveFromExecutionInstanceIfNecessary(INakedOutputPin feedingNode, String call) {
		if (feedingNode.getAction().getOwnerElement() instanceof INakedActivity) {
			call = super.retrieveFromExecutionInstanceIfNecessary(feedingNode, call);
		} else if (feedingNode.getAction() instanceof INakedCallAction) {
			//
			INakedCallAction callAction = (INakedCallAction) feedingNode.getAction();
			if (BehaviorUtil.hasMessageStructure(callAction)) {
				INakedMessageStructure message = callAction.getMessageStructure();
				NakedClassifierMap messageMap = new NakedClassifierMap(message);
				NakedStructuralFeatureMap featureMap = null;
				if (feedingNode.getLinkedTypedElement() == null) {
					featureMap = OJUtil.buildStructuralFeatureMap(message, feedingNode, false);
				} else {
					featureMap = OJUtil.buildStructuralFeatureMap(message, feedingNode.getLinkedTypedElement());
				}
				// Owned by StructuredActivityNode
				call = "((" + messageMap.javaType() + ")context.getVariable(\"" + callAction.getName() + "\"))." + featureMap.getter()
						+ "()";
			}
		} else {
		}
		return call;
	}

	protected String initForResultVariable(NakedStructuralFeatureMap map) {
		return map.getter() + "()";
	}


}