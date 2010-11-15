package net.sf.nakeduml.javageneration.jbpm5.activity;

import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;

public class ActivityUtil {
	public static final OJPathName PROCESS_CONTEXT = new OJPathName("org.drools.runtime.process.ProcessContext");

	public static String getCollectionExpression(INakedExpansionNode node) {
		return "getCollection" + node.getName() +"On"+ node.getExpansionRegion().getName();
	}
}
