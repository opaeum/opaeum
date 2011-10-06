package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedCallBehaviorAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class BehaviorCaller extends AbstractBehaviorCaller<INakedCallBehaviorAction>{
	public BehaviorCaller(OpeumLibrary oclEngine,INakedCallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	protected void maybeStartBehavior(OJAnnotatedOperation oper,OJBlock block,NakedStructuralFeatureMap resultMap){
		if(node.getBehavior().isProcess() && !node.isSynchronous()){
			block.addToStatements(resultMap.umlName() + ".execute()");
		}else{
			//JBPM will start the process
		}
	}
	protected NakedStructuralFeatureMap getResultMap(){
		NakedStructuralFeatureMap resultMap = null;
		if(BehaviorUtil.hasExecutionInstance(node.getBehavior())){
			resultMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		}else{
			resultMap = OJUtil.buildStructuralFeatureMap(node.getReturnPin().getActivity(), node.getReturnPin(), true);
		}
		return resultMap;
	}
	@Override
	protected boolean shouldStoreMessageStructureOnProcess(){
		return node.getBehavior().getContext() == null && BehaviorUtil.hasExecutionInstance(node.getBehavior());
	}
}
