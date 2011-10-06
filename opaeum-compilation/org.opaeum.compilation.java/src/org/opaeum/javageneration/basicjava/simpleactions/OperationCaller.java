package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class OperationCaller extends AbstractCaller<INakedCallOperationAction>{
	public OperationCaller(OpaeumLibrary oclEngine,INakedCallOperationAction action,AbstractObjectNodeExpressor expressor){
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
