package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class BehaviorCaller extends AbstractBehaviorCaller<INakedCallBehaviorAction>{
	public BehaviorCaller(OpaeumLibrary oclEngine,INakedCallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	protected void maybeStartBehavior(OJAnnotatedOperation oper,OJBlock block,NakedStructuralFeatureMap resultMap){
		if(node.getBehavior().isProcess()){
			block.addToStatements(resultMap.fieldname() + ".execute()");
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
