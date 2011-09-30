package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class ClassifierBehaviorStarter extends SimpleNodeBuilder<INakedStartClassifierBehaviorAction>{
	public ClassifierBehaviorStarter(OpeumLibrary oclEngine,INakedStartClassifierBehaviorAction action,AbstractObjectNodeExpressor expressor){
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
