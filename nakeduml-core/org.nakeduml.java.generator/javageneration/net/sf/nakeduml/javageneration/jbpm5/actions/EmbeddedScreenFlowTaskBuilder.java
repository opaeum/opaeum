package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedScreenFlowTaskBuilder extends AbstractCallActionBuilder<INakedEmbeddedScreenFlowTask>{
	public EmbeddedScreenFlowTaskBuilder(NakedUmlLibrary oclEngine,INakedEmbeddedScreenFlowTask node){
		super(oclEngine, node,new EmbeddedScreenFlowTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
