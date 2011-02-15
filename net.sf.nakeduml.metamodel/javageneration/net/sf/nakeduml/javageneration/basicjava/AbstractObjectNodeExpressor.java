package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class AbstractObjectNodeExpressor {
	private IOclLibrary oclLibrary;

	public AbstractObjectNodeExpressor(IOclLibrary oclLibrary) {
		this.oclLibrary = oclLibrary;
	}

	abstract public String expressInputPinOrOutParamOrExpansionNode(OJBlock block, INakedObjectNode pin);

	abstract public OJAnnotatedField maybeBuildResultVariable(OJAnnotatedOperation operation, OJBlock block, NakedStructuralFeatureMap map);

	public abstract String setterForSingleResult(NakedStructuralFeatureMap resultMap, String call);

	public abstract String getterForStructuredResults(NakedStructuralFeatureMap resultMap);

	protected String surroundWithSelectionAndTransformation(String expression, INakedObjectFlow edge) {
		if (edge.getSelection() != null) {
			expression = edge.getSelection().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if (edge.getTransformation() != null) {
			expression = edge.getTransformation().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if(edge.getSelection()==null && edge.getTransformation()==null){
			INakedObjectNode source=(INakedObjectNode) edge.getSource();
			INakedObjectNode target = (INakedObjectNode) edge.getTarget();
			if(target.getNakedMultiplicity().isMany() && source.getNakedMultiplicity().isMany() &&( source.isOrdered()!=target.isOrdered() || source.isUnique()!=target.isUnique())){
				NakedStructuralFeatureMap targetMap = OJUtil.buildStructuralFeatureMap(edge.getActivity(), target);
				expression="new " + targetMap.javaDefaultTypePath().getLast() + "<" + targetMap.javaDefaultTypePath().getElementTypes().get(0).getLast() +">(" + expression +")";
			}
		}
		return expression;
	}

	public String storeResults(NakedStructuralFeatureMap resultMap, String call, boolean isMany) {
		if (resultMap.isCollection()) {
			if (isMany) {
				call = getterForStructuredResults(resultMap) + ".addAll(" + call + ")";
			} else {
				call = getterForStructuredResults(resultMap) + ".add(" + call + ")";
			}
		} else {
			call = setterForSingleResult(resultMap, call);
		}
		return call;
	}

	protected String retrieveFromExecutionInstanceIfNecessary(INakedOutputPin feedingNode, String call) {
		if (feedingNode.getOwnerElement() instanceof INakedCallAction) {
			INakedCallAction callAction = (INakedCallAction) feedingNode.getOwnerElement();
			if (BehaviorUtil.hasMessageStructure(callAction)) {
				NakedStructuralFeatureMap pinMap=null; 			
				if(feedingNode.getLinkedTypedElement()==null){
					pinMap = OJUtil.buildStructuralFeatureMap(callAction.getActivity(), feedingNode,false);
				}else{
					pinMap = OJUtil.buildStructuralFeatureMap(callAction.getActivity(), feedingNode.getLinkedTypedElement());
				}
				NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(callAction, this.oclLibrary);
				call = getterForStructuredResults(actionMap);
				if (callAction.getTargetElement() == null || callAction.getTargetElement().getNakedMultiplicity().isSingleObject()) {
					// Only one call, so retrieve the single result
					return call + "." + pinMap.getter() + "()";
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
					call = call + ".iterator().next()." + pinMap.getter() + "()";
				}
			}
		}
		return call;
	}

	public String expressExceptionInput(OJBlock block, INakedObjectNode pin) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin);
		return   "(" + map.javaType() + ")e.getValue()";
	}
}