package org.opaeum.javageneration.jbpm5.activity;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = JavaTransformationPhase.class,requires = ActivityProcessImplementor.class,after = ActivityProcessImplementor.class)
public class ActivityNodeEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(Activity c){
		if(!EmfActivityUtil.isSimpleSynchronousMethod(c)){
			OJEnum e = super.buildOJEnum(c, false);
			nodes(e, c, c);
		}
	}
	private void nodes(OJEnum e,Namespace c,Classifier msg){
		Collection<ActivityNode> activityNodes = EmfActivityUtil.getActivityNodes( c);
		for(ActivityNode n:activityNodes){
			String parentLiteral;
			if(EmfBehaviorUtil.isRestingNode(n) || n instanceof Action || (n instanceof ExpansionNode && ((ExpansionNode) n).getRegionAsOutput()!=null)){
				if(getEnclosingElement(n) == null){
					parentLiteral = "null";
				}else{
					Namespace container = EmfActivityUtil.getNearestNodeContainer((StructuredActivityNode)c);
					OJPathName parentState = OJUtil.classifierPathname(container).getCopy();
					parentState.replaceTail(parentState.getLast() + "State");
					e.addToImports(parentState);
					parentLiteral = parentState.getLast() + "." + Jbpm5Util.stepLiteralName(getEnclosingElement(n));
				}
				buildLiteral(n, e, parentLiteral);
			}
			if(n instanceof StructuredActivityNode){
				StructuredActivityNode san = (StructuredActivityNode) n;
				OJEnum e2 = super.buildOJEnum(getLibrary().getMessageStructure( san), false);
				nodes(e2, san, getLibrary().getMessageStructure(san));
			}
		}
	}
	@Override
	protected NamedElement getEnclosingElement(NamedElement s){
		ActivityNode node = (ActivityNode) s;
		return node.getInStructuredNode();
	}
	@Override
	protected Collection<Trigger> getOperationTriggers(Element step){
		Collection<Trigger> result = new ArrayList<Trigger>();
		if(step instanceof AcceptEventAction){
			AcceptEventAction a = (AcceptEventAction) step;
			for(Trigger t:a.getTriggers()){
				if(t.getEvent() instanceof Operation){
					result.add(t);
				}
			}
		}
		return result;
	}
}
