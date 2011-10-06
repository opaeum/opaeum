package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.metamodel.activities.INakedActivityNode;


public class ActivityNodeMap {
	private INakedActivityNode node;

	public ActivityNodeMap(INakedActivityNode node) {
		super();
		this.node = node;
	}
	public String doActionMethod(){
		return "do"+node.getMappingInfo().getJavaName().getCapped() + node.getMappingInfo().getOpeumId();
	}

}
