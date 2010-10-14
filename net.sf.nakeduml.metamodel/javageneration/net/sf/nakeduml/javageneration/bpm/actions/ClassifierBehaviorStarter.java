package net.sf.nakeduml.javageneration.bpm.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleActionBuilder;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ClassifierBehaviorStarter extends SimpleActionBuilder<INakedStartClassifierBehaviorAction>{
	public ClassifierBehaviorStarter(IOclEngine oclEngine,INakedStartClassifierBehaviorAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		ActionMap actionMap = new ActionMap(node);
		if(actionMap.targetBaseType() instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) actionMap.targetBaseType();
			if(entity.getClassifierBehavior() != null){
				block = super.buildLoopThroughTarget(operation, block, actionMap);
				block.addToStatements(actionMap.targetName()+".startClassifierBehavior()");
			}
		}
	}
}
