package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class SimpleActionBridge extends Jbpm5ActionBuilder<INakedActivityNode> {
	private SimpleNodeBuilder<?> delegate;

	public SimpleActionBridge(NakedUmlLibrary oclEngine, INakedActivityNode node, SimpleNodeBuilder<?> delegate) {
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
