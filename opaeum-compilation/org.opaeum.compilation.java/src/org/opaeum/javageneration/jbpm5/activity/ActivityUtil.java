package org.opaeum.javageneration.jbpm5.activity;

import java.util.Collection;

import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;

public class ActivityUtil{
	public static String getCollectionExpression(INakedExpansionNode node){
		return "getCollection" + node.getName() + "On" + node.getExpansionRegion().getName();
	}
	public static void setupVariables(OJAnnotatedOperation oper,INakedActivityNode node){
		if(node instanceof INakedStructuredActivityNode){
			Collection<INakedActivityVariable> variables = ((INakedStructuredActivityNode) node).getVariables();
			for(INakedActivityVariable var:variables){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), var);
				OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
				field.setInitExp("(" + map.javaType() + ")context.getVariable(\"" + map.fieldname() + "\")");
				oper.getOwner().addToImports(map.javaTypePath());
				oper.getBody().addToLocals(field);
			}
		}
		if(node.getOwnerElement() instanceof INakedActivityNode){
			setupVariables(oper, (INakedActivityNode) node.getOwnerElement());
		}
	}
	public static boolean flowsInStructuredNode(INakedObjectFlow flow){
		if(flow.getTarget() instanceof INakedExpansionNode){
			INakedExpansionNode target = (INakedExpansionNode) flow.getTarget();
			if(target.isOutputElement()){
				return true;
			}else{
				return target.getExpansionRegion().getOutputElement() instanceof INakedActivity;
			}
		}else if(flow.getTarget() instanceof INakedPin){
			INakedPin pin = (INakedPin) flow.getTarget();
			return pin.getAction().getOwnerElement() instanceof INakedStructuredActivityNode;
		}else {
			return false;
		}
	}
}
