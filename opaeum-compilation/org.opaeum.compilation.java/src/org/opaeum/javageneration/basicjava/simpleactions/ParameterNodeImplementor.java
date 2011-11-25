package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ParameterNodeImplementor extends SimpleNodeBuilder<INakedParameterNode>{
	public ParameterNodeImplementor(OpaeumLibrary oclEngine,INakedParameterNode action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(!node.getParameter().getDirection().equals(ParameterDirectionKind.IN) && node.getIncoming().size() > 0){
			// consume input token where necessary
			String call = super.expressor.expressInputPinOrOutParamOrExpansionNode(block, node);
			String pathToActivity = getPathToActivity();
			if(node.getParameter().isResult()){
				NakedStructuralFeatureMap resultMap;
				if(node.getParameter().getLinkedParameter() == null){
					resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getParameter());
				}else{
					resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getParameter().getLinkedParameter());
				}
				if(node.getParameter().isException()){
					// TODO JBPM exception handling
					// oper.getBody().addToStatements("processInstance.getRootToken().end()");
					OJPathName pathName = OJUtil.classifierPathname(node.getNakedBaseType());
					operation.getOwner().addToImports(pathName);
					operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
					if(node.getActivity().isLongRunning() || node.getActivity().getOwnerElement() instanceof INakedTransition
							|| node.getActivity().getOwnerElement() instanceof INakedState){
						block.addToStatements(expressor.storeResults(resultMap, call, node.getFeedingNode().getNakedMultiplicity().isMany()));
						NakedOperationMap map = OJUtil.buildOperationMap(node.getActivity());
						OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
						block.addToLocals(callBackListener);
						callBackListener.setInitExp(pathToActivity + "getCallingProcessObject()");
						OJIfStatement ifNotNull = new OJIfStatement("callbackListener!=null");
						block.addToStatements(ifNotNull);
						ifNotNull.getThenPart().addToStatements(
								"callbackListener." + map.exceptionOperName(node.getParameter()) + "(" + pathToActivity + "getCallingNodeInstanceUniqueId()," + call
										+ ",this)");
					}else if(BehaviorUtil.hasExecutionInstance(node.getActivity())){
						block.addToStatements(expressor.storeResults(resultMap, call, node.getFeedingNode().getNakedMultiplicity().isMany()));
					}else{
						block.addToStatements("throw new ExceptionHolder(this,\"" + node.getParameter().getName() + "\"," + call + ")");
					}
				}else{
					if(BehaviorUtil.hasExecutionInstance(node.getActivity()) || node.getActivity().getOwnerElement() instanceof INakedTransition
							|| node.getActivity().getOwnerElement() instanceof INakedState){
						block.addToStatements(pathToActivity + expressor.storeResults(resultMap, call, node.getFeedingNode().getNakedMultiplicity().isMany()));
					}else{
						block.addToStatements("result= " + call);
					}
				}
			}
		}
	}
}
