package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public abstract class AbstractBehaviorCaller<T extends INakedCallBehaviorAction> extends AbstractCaller<T>{
	public AbstractBehaviorCaller(NakedUmlLibrary oclEngine,T action,AbstractObjectNodeExpressor objectNodeExpressor){
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
					fs = callBehavior(operation, block);
				}
				fs.addToLocals(resultField);
				if(shouldStoreMessageStructureOnProcess()){
					INakedMessageStructure messageStructure = node.getMessageStructure();
					NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(messageStructure.getEndToComposite().getOtherEnd());
					fs.addToStatements(featureMap.adder() + "(" + resultField.getName() + ")");
				}
				maybeStartBehavior(operation, fs, resultMap);
				fs.addToStatements(expressor.storeResults(resultMap, resultMap.umlName(), resultIsMany(resultMap)));
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
				+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")");
		return fs;
	}
	protected abstract boolean shouldStoreMessageStructureOnProcess();
	private boolean resultIsMany(NakedStructuralFeatureMap resultMap){
		boolean many = resultMap.isMany();
		if(BehaviorUtil.hasMessageStructure(node) && node.getTargetElement()!=null){
			many = node.getTargetElement().getNakedMultiplicity().isMany();
		}else if(!(node.getReturnPin() == null || node.getReturnPin().getLinkedTypedElement() == null)){
			many = node.getReturnPin().getNakedMultiplicity().isMany();
		}
		return many;
	}
}