package org.opaeum.javageneration.jbpm5.activity;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.javageneration.util.OJUtill;

public class ActivityUtil{
	OJUtill ojUtil;
	public ActivityUtil(OJUtill ojUtil){
		super();
		this.ojUtil = ojUtil;
	}
	public static String getCollectionExpression(ExpansionNode node){
		return "getCollection" + node.getName() + "On" + EmfActivityUtil.getExpansionRegion( node).getName();
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
