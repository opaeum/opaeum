package org.opeum.javageneration.jbpm5.activity;

import java.util.Collection;

import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedActivityVariable;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedStructuredActivityNode;

import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

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
