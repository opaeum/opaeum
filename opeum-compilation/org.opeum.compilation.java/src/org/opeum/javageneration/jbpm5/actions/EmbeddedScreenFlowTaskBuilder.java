package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedScreenFlowTaskBuilder extends AbstractCallActionBuilder<INakedEmbeddedScreenFlowTask>{
	public EmbeddedScreenFlowTaskBuilder(OpeumLibrary oclEngine,INakedEmbeddedScreenFlowTask node){
		super(oclEngine, node,new EmbeddedScreenFlowTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
