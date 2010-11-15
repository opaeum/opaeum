package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractActionBuilder;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import nl.klasse.octopus.oclengine.IOclEngine;

public abstract class SimpleActionBuilder<E extends INakedActivityNode> extends AbstractActionBuilder {
	protected E node;
	public SimpleActionBuilder(IOclEngine oclEngine, E action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, objectNodeExpressor);
		this.node = action;
	}

	public abstract void implementActionOn(OJAnnotatedOperation operation, OJBlock block);
}
