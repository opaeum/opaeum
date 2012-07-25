package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Pin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractBehaviorCaller<T extends CallBehaviorAction> extends AbstractCaller<T>{
	public AbstractBehaviorCaller(OpaeumLibrary oclEngine,T action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
		if(node.getBehavior()!=null){
			operationMap=OJUtil.buildOperationMap(node.getBehavior());
		}
	}
	protected abstract void maybeStartBehavior(OJAnnotatedOperation oper,OJBlock block,NakedStructuralFeatureMap resultMap);
	protected abstract NakedStructuralFeatureMap getResultMap();
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedOperationMap calledElement = OJUtil.buildOperationMap(node.getBehavior());
		if(calledElement == null){
			block.addToStatements("no behavior to call!");
		}else{
			if(EmfActionUtil.getReturnPin(node) != null || EmfBehaviorUtil.hasExecutionInstance(node.getBehavior())){
				if(EmfBehaviorUtil.isComplectSynchronousMethod(node.getBehavior())){
					// TODO store the results in the output pins
				}
				NakedStructuralFeatureMap resultMap = getResultMap();
				OJAnnotatedField resultField = expressor.buildResultVariable(operation, block, resultMap);
				OJBlock fs = block;
				if(node.getBehavior().getContext() == null && EmfBehaviorUtil.hasExecutionInstance(node.getBehavior())){
					resultField.setInitExp("new " + resultMap.javaBaseType() + "()");
					for(Pin p:node.getArguments()){
						NakedStructuralFeatureMap paramMap = OJUtil.buildStructuralFeatureMap(node.getBehavior(), EmfActionUtil.getLinkedTypedElement( p));
						fs.addToStatements(resultField.getName() + "." + paramMap.setter() + "(" + readPin(operation, fs, p) + ")");
					}
				}else{
					ActionMap actionMap = new ActionMap(node);
					fs = buildLoopThroughTarget(operation, block, actionMap);
					resultField.setInitExp(actionMap.targetName() + "." + operationMap.javaOperName() + "("
							+ populateArgumentPinsAndBuildArgumentString(operation, calledElement.isLongRunning(), node.getArguments()) + ")");
				}
				fs.addToLocals(resultField);
				if(shouldStoreMessageStructureOnProcess()){
					Behavior messageStructure = node.getBehavior();
					NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap( getLibrary().getEndToComposite( messageStructure).getOtherEnd());
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
		fs.addToStatements(actionMap.targetName() + "." + operationMap.javaOperName() + "("
				+ populateArgumentPinsAndBuildArgumentString(operation, EmfBehaviorUtil.isLongRunning(node.getBehavior()), node.getArguments()) + ")");
		return fs;
	}
	protected abstract boolean shouldStoreMessageStructureOnProcess();
	private boolean resultIsMany(NakedStructuralFeatureMap resultMap){
		boolean many = resultMap.isMany();
		if(EmfBehaviorUtil.hasMessageStructure(node)){
			many = false;
		}else if(!(EmfActionUtil.getReturnPin(node) == null ||  EmfActionUtil.getLinkedTypedElement(EmfActionUtil.getReturnPin(node)) == null)){
			many = EmfActionUtil.getReturnPin(node).isMultivalued();
		}
		return many;
	}
}