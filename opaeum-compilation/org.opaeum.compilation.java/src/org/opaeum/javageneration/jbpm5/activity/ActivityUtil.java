package org.opaeum.javageneration.jbpm5.activity;

import java.util.Collection;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;

public class ActivityUtil{
	public static String getCollectionExpression(ExpansionNode node){
		return "getCollection" + node.getName() + "On" + EmfActivityUtil.getExpansionRegion( node).getName();
	}
	public static void setupVariables(OJAnnotatedOperation oper,ActivityNode node){
		if(node instanceof StructuredActivityNode){
			Collection<Variable> variables = ((StructuredActivityNode) node).getVariables();
			for(Variable var:variables){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(node), var);
				OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
				field.setInitExp("(" + map.javaType() + ")context.getVariable(\"" + map.fieldname() + "\")");
				oper.getOwner().addToImports(map.javaTypePath());
				oper.getBody().addToLocals(field);
			}
		}
		if(node.getOwner() instanceof ActivityNode){
			setupVariables(oper, (ActivityNode) node.getOwner());
		}
	}
	public static boolean flowsInStructuredNode(ObjectFlow flow){
		if(flow.getTarget() instanceof ExpansionNode){
			ExpansionNode target = (ExpansionNode) flow.getTarget();
			if(target.getRegionAsOutput()!=null){
				return true;
			}else{
				return EmfElementFinder.getContainer(EmfActivityUtil.getExpansionRegion(target)) instanceof Activity;
			}
		}else if(flow.getTarget() instanceof Pin){
			Pin pin = (Pin) flow.getTarget();
			return pin.getOwner().getOwner() instanceof StructuredActivityNode;
		}else {
			return false;
		}
	}
}
