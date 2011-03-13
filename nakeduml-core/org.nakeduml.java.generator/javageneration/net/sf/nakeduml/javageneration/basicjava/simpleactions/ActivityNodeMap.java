package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.metamodel.activities.INakedActivityNode;


public class ActivityNodeMap {
	private INakedActivityNode node;

	public ActivityNodeMap(INakedActivityNode node) {
		super();
		this.node = node;
	}
	public String doActionMethod(){
		return "do"+node.getMappingInfo().getJavaName().getCapped();
	}

}
