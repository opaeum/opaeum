package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.environment.MethodInvocationHolder;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class OperationCaller extends AbstractCaller<INakedCallOperationAction>{
	public OperationCaller(NakedUmlLibrary oclEngine,INakedCallOperationAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getOperation() == null){
			block.addToStatements("no operation call!");
		}else{
			if(node.isSynchronous()){
				NakedStructuralFeatureMap resultMap = null;
				INakedPin returnPin = node.getReturnPin();
				ActionMap actionMap = new ActionMap(node);
				String firstArg = node.getOperation().isLongRunning() ? "context," : "";
				String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "(" + firstArg
						+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")";
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
				operation.getOwner().addToImports(new OJPathName(MethodInvocationHolder.class.getName()));
				fs.addToStatements("getOutgoingEvents()" + ".add(new MethodInvocationHolder(new  " + "(" + actionMap.targetName() + ","
						+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")");
			}
		}
	}
}
