package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractBehaviorCaller<T extends INakedCallBehaviorAction> extends AbstractCaller<T>{
	public AbstractBehaviorCaller(OpaeumLibrary oclEngine,T action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	protected abstract void maybeStartBehavior(OJAnnotatedOperation oper,OJBlock block,NakedStructuralFeatureMap resultMap);
	protected abstract NakedStructuralFeatureMap getResultMap();
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getCalledElement() == null){
			block.addToStatements("no behavior to call!");
		}else{
			if(node.getReturnPin() != null || BehaviorUtil.hasExecutionInstance(node.getBehavior())){
				if(node.getBehavior() instanceof INakedActivity && ((INakedActivity) node.getBehavior()).getActivityKind() == ActivityKind.COMPLEX_SYNCHRONOUS_METHOD){
					// TODO store the results in the output pins
				}
				NakedStructuralFeatureMap resultMap = getResultMap();
				OJAnnotatedField resultField = expressor.buildResultVariable(operation, block, resultMap);
				OJBlock fs = block;
				if(node.getBehavior().getContext() == null && BehaviorUtil.hasExecutionInstance(node.getBehavior())){
					resultField.setInitExp("new " + resultMap.javaBaseType() + "()");
					for(INakedPin p:node.getArguments()){
						NakedStructuralFeatureMap paramMap = OJUtil.buildStructuralFeatureMap(node.getBehavior(), p.getLinkedTypedElement());
						fs.addToStatements(resultField.getName() + "." + paramMap.setter() + "(" + readPin(operation, fs, p) + ")");
					}
				}else{
					ActionMap actionMap = new ActionMap(node);
					fs = buildLoopThroughTarget(operation, block, actionMap);
					resultField.setInitExp(actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
							+ populateArgumentPinsAndBuildArgumentString(operation, node.getCalledElement().isLongRunning(), node.getArguments()) + ")");
				}
				fs.addToLocals(resultField);
				if(shouldStoreMessageStructureOnProcess()){
					INakedMessageStructure messageStructure = node.getMessageStructure();
					NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(messageStructure.getEndToComposite().getOtherEnd());
					fs.addToStatements(featureMap.adder() + "(" + resultField.getName() + ")");
				}
				maybeStartBehavior(operation, fs, resultMap);
				fs.addToStatements(expressor.storeResults(resultMap, resultMap.fieldname(), resultIsMany(resultMap)));
			}else{
				callBehavior(operation, block);
			}
		}
	}
	public OJBlock callBehavior(OJAnnotatedOperation operation,OJBlock block){
		OJBlock fs;
		ActionMap actionMap = new ActionMap(node);
		fs = buildLoopThroughTarget(operation, block, actionMap);
		fs.addToStatements(actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
				+ populateArgumentPinsAndBuildArgumentString(operation, node.getCalledElement().isLongRunning(), node.getArguments()) + ")");
		return fs;
	}
	protected abstract boolean shouldStoreMessageStructureOnProcess();
	private boolean resultIsMany(NakedStructuralFeatureMap resultMap){
		boolean many = resultMap.isMany();
		if(BehaviorUtil.hasMessageStructure(node)){
			many = false;
		}else if(!(node.getReturnPin() == null || node.getReturnPin().getLinkedTypedElement() == null)){
			many = node.getReturnPin().getNakedMultiplicity().isMany();
		}
		return many;
	}
}