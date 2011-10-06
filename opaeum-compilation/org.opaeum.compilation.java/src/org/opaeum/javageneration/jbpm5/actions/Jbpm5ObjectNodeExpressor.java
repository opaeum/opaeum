package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.ActionFeatureBridge;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public final class Jbpm5ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	// oyoyoyoyoy Jbpm
	public static final String EXCEPTION_FIELD = "currentException";
	@Override
	public boolean pinsAvailableAsVariables(){
		return true;
	}
	public Jbpm5ObjectNodeExpressor(OpaeumLibrary l){
		super(l);
	}
	@Override
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		OJAnnotatedField outPinVar = new OJAnnotatedField(resultMap.umlName(), resultMap.javaTypePath());
		block.addToLocals(outPinVar);
		if(inStructuredActivity(getOriginal(resultMap)) && resultMap.isMany()){
			// Setup collection
			outPinVar.setInitExp("context.getVariable(\"" + resultMap.umlName() + "\")");
			OJIfStatement ifNull = new OJIfStatement(resultMap.umlName() + "==null", resultMap.umlName() + "=" + resultMap.javaDefaultValue());
			block.addToStatements(ifNull);
			ifNull.getThenPart().addToStatements("context.setVariable(\"" + resultMap.umlName() + "\"," + resultMap.umlName() + ")");
			operation.getOwner().addToImports(resultMap.javaBaseTypePath());
			operation.getOwner().addToImports(resultMap.javaDefaultTypePath());
			return outPinVar;
		}
		return outPinVar;
	}
	private boolean inStructuredActivity(INakedElement node){
		if(node instanceof INakedExpansionNode){
			return ((INakedExpansionNode) node).getExpansionRegion().getOwnerElement() instanceof INakedStructuredActivityNode;
		}else if(node instanceof INakedPin){
			return ((INakedPin) node).getAction().getOwnerElement() instanceof INakedStructuredActivityNode;
		}else if(node instanceof INakedAction){
			return ((INakedAction) node).getOwnerElement() instanceof INakedStructuredActivityNode;
		}else if(node instanceof INakedActivityVariable){
			return ((INakedActivityVariable) node).getOwnerElement() instanceof INakedStructuredActivityNode;
		}else{
			return node.getOwnerElement() instanceof INakedStructuredActivityNode;
		}
	}
	@Override
	public String getterForStructuredResults(NakedStructuralFeatureMap resultMap){
		if(inStructuredActivity(getOriginal(resultMap))){
			// Variable has been retrieved from
			// context in maybeBuildResultVariable
			return resultMap.umlName();
		}else{
			return resultMap.getter() + "()";
		}
	}
	// TODO ugly but effective.
	private INakedElement getOriginal(NakedStructuralFeatureMap resultMap){
		if(resultMap.getProperty() instanceof ActionFeatureBridge){
			return ((ActionFeatureBridge) resultMap.getProperty()).getAction();
		}else{
			TypedElementPropertyBridge property = (TypedElementPropertyBridge) resultMap.getProperty();
			INakedTypedElement original = (INakedTypedElement) property.getOriginal();
			return original;
		}
	}
	@Override
	public String setterForSingleResult(NakedStructuralFeatureMap resultMap,String call){
		if(inStructuredActivity(getOriginal(resultMap))){
			return "context.setVariable(\"" + resultMap.umlName() + "\"," + call + ")";
		}else{
			return resultMap.setter() + "(" + call + ")";
		}
	}
	public String expressInputPinOrOutParamOrExpansionNode(OJBlock block,INakedObjectNode pin){
		if(pin.getIncoming().isEmpty()){
			return "null";
		}else{
			INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
			INakedObjectNode feedingNode = pin.getFeedingNode();
			NakedStructuralFeatureMap feedingMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode, true);
			String call = feedingMap.getter() + "()";
			// Parameter or top level ExpansionNode
			if(feedingNode instanceof INakedOutputPin){
				INakedOutputPin outputPin = (INakedOutputPin) feedingNode;
				if(outputPin.getAction().getOwnerElement() instanceof INakedExpansionRegion){
					call = "(" + feedingMap.javaType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
				}
				call = retrieveFromExecutionInstanceIfNecessary(outputPin, call);
			}else if(feedingNode instanceof INakedExpansionNode){
				INakedExpansionNode expansionNode = (INakedExpansionNode) feedingNode;
				INakedExpansionRegion region = (INakedExpansionRegion) expansionNode.getOwnerElement();
				if(expansionNode.isInputElement()){
					call = "(" + feedingMap.javaBaseType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
				}else if(region.getOwnerElement() instanceof INakedStructuredActivityNode){
					call = "(" + feedingMap.javaType() + ")context.getVariable(\"" + feedingMap.umlName() + "\")";
				}else{
					call = feedingMap.getter() + "()";
				}
			}
			return surroundWithSelectionAndTransformation(call, edge);
		}
	}
	@Override
	protected String retrieveFromExecutionInstanceIfNecessary(INakedOutputPin feedingNode,String call){
		if(BehaviorUtil.hasMessageStructure(feedingNode.getAction())){
			INakedAction callAction = (INakedAction) feedingNode.getAction();
			NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(callAction, this.library);
			if(feedingNode.getAction().getOwnerElement() instanceof INakedActivity){
				call = actionMap.getter() + "()";
			}else{
				call = "((" + actionMap.javaBaseType() + ")context.getVariable(\"" + callAction.getName() + "\"))";
			}
			call = super.retrieveFromExecutionInstanceIfNecessary(feedingNode, call);
		}
		return call;
	}
	protected String initForResultVariable(NakedStructuralFeatureMap map){
		return map.getter() + "()";
	}
	public String expressExceptionInput(OJBlock block,INakedObjectNode pin){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
		return "(" + map.javaType() + ")" + EXCEPTION_FIELD;
	}
}