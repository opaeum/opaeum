package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class SimpleActionBridge extends Jbpm5ActionBuilder<INakedActivityNode> {
	private SimpleNodeBuilder<?> delegate;

	public SimpleActionBridge(OpeumLibrary oclEngine, INakedActivityNode node, SimpleNodeBuilder<?> delegate) {
		super(oclEngine, node);
		this.delegate = delegate;
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation oper) {
		if (delegate != null) {
			delegate.implementActionOn(oper, oper.getBody());
		}
	}
}
