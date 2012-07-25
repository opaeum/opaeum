package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.ActivityNode;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class SimpleActionBridge extends Jbpm5ActionBuilder<ActivityNode> {
	private SimpleNodeBuilder<?> delegate;

	public SimpleActionBridge(OpaeumLibrary oclEngine, ActivityNode node, SimpleNodeBuilder<?> delegate) {
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
