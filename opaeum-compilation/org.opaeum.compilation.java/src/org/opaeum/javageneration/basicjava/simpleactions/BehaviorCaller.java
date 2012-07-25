package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class BehaviorCaller extends AbstractBehaviorCaller<CallBehaviorAction>{
	public BehaviorCaller(OpaeumLibrary oclEngine,CallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	protected void maybeStartBehavior(OJAnnotatedOperation oper,OJBlock block,NakedStructuralFeatureMap resultMap){
		if(EmfBehaviorUtil.isProcess(node.getBehavior())){
			block.addToStatements(resultMap.fieldname() + ".execute()");
		}
	}
	protected NakedStructuralFeatureMap getResultMap(){
		NakedStructuralFeatureMap resultMap = null;
		if(EmfBehaviorUtil.hasExecutionInstance(node.getBehavior())){
			resultMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		}else{
			resultMap = OJUtil.buildStructuralFeatureMap(getContainingActivity(), EmfActionUtil.getReturnPin( node), true);
		}
		return resultMap;
	}
	@Override
	protected boolean shouldStoreMessageStructureOnProcess(){
		return node.getBehavior().getContext() == null && EmfBehaviorUtil.hasExecutionInstance(node.getBehavior());
	}
}
