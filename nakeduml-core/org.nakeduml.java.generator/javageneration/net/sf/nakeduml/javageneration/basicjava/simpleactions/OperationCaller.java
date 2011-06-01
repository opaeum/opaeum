package net.sf.nakeduml.javageneration.basicjava.simpleactions;


import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class OperationCaller extends AbstractCaller<INakedCallOperationAction>{
	public OperationCaller(IOclEngine oclEngine,INakedCallOperationAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getOperation() == null){
			block.addToStatements("no operation call!");
		}else{
			NakedStructuralFeatureMap resultMap = null;
			INakedPin returnPin = node.getReturnPin();
			ActionMap actionMap = new ActionMap(node);
			String firstArg=		node.getOperation().isLongRunning()?"context,":"";

			String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "(" + firstArg+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments())
					+ ")";
			if(BehaviorUtil.hasMessageStructure(node)){
				resultMap = OJUtil.buildStructuralFeatureMap(node, oclEngine.getOclLibrary());
			}else if(returnPin != null){
				resultMap = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
			}
			OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
			if(resultMap != null){
				expressor.maybeBuildResultVariable(operation, block, resultMap);
				boolean many = resultMap.isMany();
				if(!(returnPin == null || returnPin.getLinkedTypedElement() == null || BehaviorUtil.hasMessageStructure(node))){
					many = returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany();
				}
				call = expressor.storeResults(resultMap, call, many);
			}
			fs.addToStatements(call);
		}
	}
}
