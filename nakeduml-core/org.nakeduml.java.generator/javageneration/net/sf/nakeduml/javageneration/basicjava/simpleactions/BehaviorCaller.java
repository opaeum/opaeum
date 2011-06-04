package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class BehaviorCaller extends AbstractBehaviorCaller<INakedCallBehaviorAction>{
	public BehaviorCaller(IOclEngine oclEngine,INakedCallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	protected void maybeStartBehavior(OJAnnotatedOperation oper, OJBlock block,NakedStructuralFeatureMap resultMap){
		if(node.getBehavior().isProcess() && !node.isSynchronous()){
			block.addToStatements(resultMap.umlName() + ".execute()");
		}
	}
	protected NakedStructuralFeatureMap getResultMap(){
		NakedStructuralFeatureMap resultMap = null;
		if(BehaviorUtil.hasExecutionInstance(node.getBehavior())){
			resultMap = OJUtil.buildStructuralFeatureMap(node, oclEngine.getOclLibrary());
		}else{
			resultMap = OJUtil.buildStructuralFeatureMap(node.getReturnPin().getActivity(), node.getReturnPin(),true);
		}
		return resultMap;
	}

}
