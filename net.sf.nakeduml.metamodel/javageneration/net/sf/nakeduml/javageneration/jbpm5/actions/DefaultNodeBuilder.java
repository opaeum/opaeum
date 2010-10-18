package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import nl.klasse.octopus.oclengine.IOclEngine;

public class DefaultNodeBuilder extends JbpmActionBuilder<INakedActivityNode>{

	public DefaultNodeBuilder(IOclEngine oclEngine,INakedActivityNode node){
		super(oclEngine, node);
	}

	@Override
	public void implementActionOn(OJOperation oper){
		
	}
}
