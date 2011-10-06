package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractNodeBuilder;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class SimpleNodeBuilder<E extends INakedActivityNode> extends AbstractNodeBuilder {
	protected E node;
	public SimpleNodeBuilder(OpaeumLibrary oclEngine, E action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, objectNodeExpressor);
		this.node = action;
	}

	public abstract void implementActionOn(OJAnnotatedOperation operation, OJBlock block);
}
