package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ClassifierBehaviorStarter extends SimpleNodeBuilder<INakedStartClassifierBehaviorAction>{
	public ClassifierBehaviorStarter(IOclEngine oclEngine,INakedStartClassifierBehaviorAction action,AbstractObjectNodeExpressor expressor){
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
