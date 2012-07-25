package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ClassifierBehaviorStarter extends SimpleNodeBuilder<StartClassifierBehaviorAction>{
	public ClassifierBehaviorStarter(OpaeumLibrary oclEngine,StartClassifierBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		ActionMap actionMap = new ActionMap(node);
		Classifier targetType = EmfActionUtil.getTargetType(node);
		if(targetType instanceof BehavioredClassifier){
			BehavioredClassifier entity = (BehavioredClassifier) targetType;
			if(entity.getClassifierBehavior() != null){
				block = super.buildLoopThroughTarget(operation, block, actionMap);
				block.addToStatements(actionMap.targetName()+".startClassifierBehavior()");
			}
		}
	}
}
