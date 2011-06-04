package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedScreenFlowTaskBuilder extends PotentialTaskActionBuilder<INakedEmbeddedScreenFlowTask>{
	private EmbeddedScreenFlowTaskCaller delegate;
	public EmbeddedScreenFlowTaskBuilder(IOclEngine oclEngine,INakedEmbeddedScreenFlowTask node){
		super(oclEngine, node);
		this.delegate=new EmbeddedScreenFlowTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public boolean isTask(){
		return true;
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper){
		delegate.implementActionOn(oper, oper.getBody());
	}
}
