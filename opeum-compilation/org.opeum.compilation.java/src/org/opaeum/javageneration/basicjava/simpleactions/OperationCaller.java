package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedCallOperationAction;
import org.opeum.metamodel.activities.INakedPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class OperationCaller extends AbstractCaller<INakedCallOperationAction>{
	public OperationCaller(OpeumLibrary oclEngine,INakedCallOperationAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getOperation() == null){
			block.addToStatements("no operation call!");
		}else{
			StringBuilder args = populateArgumentPinsAndBuildArgumentString(operation, node.getOperation().isLongRunning(), node.getArguments());
			if(node.isSynchronous()){
				NakedStructuralFeatureMap resultMap = null;
				INakedPin returnPin = node.getReturnPin();
				ActionMap actionMap = new ActionMap(node);
				String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "(" + args + ")";
				if(BehaviorUtil.hasMessageStructure(node)){
					resultMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
				}else if(returnPin != null){
					resultMap = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
				}
				OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
				if(resultMap != null){
					expressor.buildResultVariable(operation, block, resultMap);
					boolean many = resultMap.isMany();
					if(!(returnPin == null || returnPin.getLinkedTypedElement() == null || BehaviorUtil.hasMessageStructure(node))){
						many = returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany();
					}
					call = expressor.storeResults(resultMap, call, many);
				}
				fs.addToStatements(call);
			}else{
				ActionMap actionMap = new ActionMap(node);
				OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
				OJPathName handler = EventUtil.handlerPathName(node.getOperation());
				operation.getOwner().addToImports(handler);
				if(args.length() > 0){
					args.append(",");
				}
				fs.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + actionMap.targetName() + ",new " + handler.getLast() + "(" + args + "false)))");
			}
		}
	}
}
