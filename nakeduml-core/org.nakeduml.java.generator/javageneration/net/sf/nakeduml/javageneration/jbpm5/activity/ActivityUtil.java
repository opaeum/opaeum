package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;

import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ActivityUtil {

	public static String getCollectionExpression(INakedExpansionNode node) {
		return "getCollection" + node.getName() +"On"+ node.getExpansionRegion().getName();
	}

	public static void setupVariables(OJAnnotatedOperation oper, INakedActivityNode node) {
		if (node instanceof INakedStructuredActivityNode) {
			Collection<INakedActivityVariable> variables = ((INakedStructuredActivityNode) node).getVariables();
			for (INakedActivityVariable var : variables) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), var);
				OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
				field.setInitExp("(" + map.javaType() + ")context.getVariable(\"" + map.umlName() + "\")");
				oper.getOwner().addToImports(map.javaTypePath());
				oper.getBody().addToLocals(field);
			}
		}
		if (node.getOwnerElement() instanceof INakedActivityNode) {
			setupVariables(oper, (INakedActivityNode) node.getOwnerElement());
		}
	}
}
