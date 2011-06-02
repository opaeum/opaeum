package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class BehaviorCaller extends AbstractCaller<INakedCallBehaviorAction>{
	public BehaviorCaller(IOclEngine oclEngine,INakedCallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getCalledElement() == null){
			block.addToStatements("no behavior to call!");
		}else{
			NakedStructuralFeatureMap resultMap = null;
			INakedPin returnPin = node.getReturnPin();
			ActionMap actionMap = new ActionMap(node);
			NakedClassifierMap messageMap = new NakedClassifierMap(node.getMessageStructure());
			if(node.getReturnPin() != null || BehaviorUtil.hasExecutionInstance(node.getBehavior())){
				OJAnnotatedField resultField=new OJAnnotatedField(node.getName(), messageMap.javaTypePath());
				OJBlock fs = block;
				if(node.getBehavior().getContext() == null){
				}else{
					fs = buildLoopThroughTarget(operation, block, actionMap);
					fs.addToStatements(actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
							+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")");
				}
				block.addToLocals(resultField);
				if(BehaviorUtil.hasExecutionInstance(node.getBehavior())){
					resultMap = OJUtil.buildStructuralFeatureMap(node, oclEngine.getOclLibrary());
				}else{
					resultMap = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
				}
				expressor.maybeBuildResultVariable(operation, block, resultMap);
				boolean many = resultMap.isMany();
				if(!(returnPin == null || returnPin.getLinkedTypedElement() == null || BehaviorUtil.hasMessageStructure(node))){
					many = returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany();
				}
				if(node.getBehavior().isProcess() && !node.isSynchronous()){
					block.addToStatements(resultField.getName() + ".execute()");
				}
				fs.addToStatements(expressor.storeResults(resultMap, resultField.getName(), many));
			}else{
				OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
				fs.addToStatements(actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
						+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")");
			}
		}
	}
}
