package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedSingleScreenTaskBuilder extends PotentialTaskActionBuilder<INakedEmbeddedSingleScreenTask>{
	EmbeddedSingleScreenTaskCaller delegate;
	public EmbeddedSingleScreenTaskBuilder(IOclEngine oclEngine,INakedEmbeddedSingleScreenTask node){
		super(oclEngine, node);
		delegate = new EmbeddedSingleScreenTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!node.isSynchronous()){
			delegate.implementActionOn(operation, operation.getBody());
		} // build task variable
	}
	@Override
	public boolean isTask(){
		return true;
	}
}
