package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ControlNodeBuilder extends SimpleNodeBuilder<INakedControlNode> {

	public ControlNodeBuilder(IOclEngine oclEngine, INakedControlNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		// TODO Auto-generated method stub
		
	}

}
