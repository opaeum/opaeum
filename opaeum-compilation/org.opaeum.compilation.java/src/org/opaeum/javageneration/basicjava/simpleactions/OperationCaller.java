package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Pin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class OperationCaller extends AbstractCaller<CallOperationAction>{
	public OperationCaller(OpaeumLibrary oclEngine,CallOperationAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
		if(node.getOperation()!=null){
			operationMap=OJUtil.buildOperationMap(node.getOperation());
		}

	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getOperation() == null){
			block.addToStatements("no operation call!");
		}else{
			StringBuilder args = populateArgumentPinsAndBuildArgumentString(operation, EmfBehaviorUtil.isLongRunning( node.getOperation()), node.getArguments());
			if(node.isSynchronous()){
				NakedStructuralFeatureMap resultMap = null;
				Pin returnPin = EmfActionUtil.getReturnPin( node);
				ActionMap actionMap = new ActionMap(node);
				String call = actionMap.targetName() + "." + operationMap.javaOperName() + "(" + args + ")";
				if(EmfBehaviorUtil.hasMessageStructure(node)){
					resultMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
				}else if(returnPin != null){
					resultMap = OJUtil.buildStructuralFeatureMap(getContainingActivity(), returnPin, true);
				}
				OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
				if(resultMap != null){
					expressor.buildResultVariable(operation, block, resultMap);
					if(EmfBehaviorUtil.hasMessageStructure(node)){
						// Such operations allways return a single instanceof the message Structure
						call = expressor.storeResults(resultMap, call, false);
					}else if(!(returnPin == null || EmfActionUtil.getLinkedTypedElement( returnPin) == null)){
						boolean many = ((MultiplicityElement) EmfActionUtil.getLinkedTypedElement( returnPin)).isMultivalued();
						call = expressor.storeResults(resultMap, resultMap.fieldname() + "=" + call, many);
						// We want the call to populate the pin variable for postconditions
					}
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
