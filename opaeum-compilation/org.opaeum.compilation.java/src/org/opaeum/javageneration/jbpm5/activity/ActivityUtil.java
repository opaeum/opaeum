package org.opaeum.javageneration.jbpm5.activity;

import java.util.Collection;

import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;

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
