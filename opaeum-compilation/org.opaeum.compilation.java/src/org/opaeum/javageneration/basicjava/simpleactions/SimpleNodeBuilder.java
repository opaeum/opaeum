package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractNodeBuilder;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class SimpleNodeBuilder<E extends ActivityNode> extends AbstractNodeBuilder {
	protected E node;
	public SimpleNodeBuilder(OpaeumLibrary oclEngine, E action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, objectNodeExpressor);
		this.node = action;
	}

	public abstract void implementActionOn(OJAnnotatedOperation operation, OJBlock block);

	protected String getPathToActivity(){
		StringBuilder sb = new StringBuilder();
		ActivityNode an = node;
		while(!(an.getOwner() instanceof Activity)){
			sb.append("getNodeContainer().");
		}
		String pathToActivity = sb.toString();
		return pathToActivity;
	}
	public Activity getContainingActivity(){
		return EmfActivityUtil.getContainingActivity(node);
	}

}
