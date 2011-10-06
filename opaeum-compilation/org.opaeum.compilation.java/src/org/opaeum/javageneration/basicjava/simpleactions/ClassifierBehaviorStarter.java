package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ClassifierBehaviorStarter extends SimpleNodeBuilder<INakedStartClassifierBehaviorAction>{
	public ClassifierBehaviorStarter(OpaeumLibrary oclEngine,INakedStartClassifierBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		ActionMap actionMap = new ActionMap(node);
		if(node.getTargetElement()!=null && node.getTargetElement().getNakedBaseType() instanceof INakedBehavioredClassifier){
			INakedBehavioredClassifier entity = (INakedBehavioredClassifier) node.getTargetElement().getNakedBaseType();
			if(entity.getClassifierBehavior() != null){
				block = super.buildLoopThroughTarget(operation, block, actionMap);
				block.addToStatements(actionMap.targetName()+".startClassifierBehavior()");
			}
		}
	}
}
