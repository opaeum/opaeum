package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedScreenFlowTaskBuilder extends AbstractCallActionBuilder<INakedEmbeddedScreenFlowTask>{
	public EmbeddedScreenFlowTaskBuilder(OpaeumLibrary oclEngine,INakedEmbeddedScreenFlowTask node){
		super(oclEngine, node,new EmbeddedScreenFlowTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
