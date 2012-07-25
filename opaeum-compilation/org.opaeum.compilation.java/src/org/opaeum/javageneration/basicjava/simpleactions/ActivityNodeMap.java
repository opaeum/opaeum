package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.ActivityNode;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;


public class ActivityNodeMap {
	private ActivityNode node;

	public ActivityNodeMap(ActivityNode node) {
		super();
		this.node = node;
	}
	public String doActionMethod(){
		return "do"+NameConverter.capitalize(node.getName()) + EmfWorkspace.getOpaeumId(node);
	}

}
