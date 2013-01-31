package org.opaeum.emf.extraction;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ControlNodeType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityNode;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.ActivityValidationRule;
import org.opaeum.metamodel.actions.internal.NakedExceptionHandlerImpl;
import org.opaeum.metamodel.activities.internal.NakedActivityEdgeImpl;
import org.opaeum.metamodel.activities.internal.NakedControlNodeImpl;
import org.opaeum.metamodel.activities.internal.NakedObjectFlowImpl;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,StructuralFeatureActionExtractor.class,VariableActionExtractor.class,
		AcceptEventActionExtractor.class
},after = {
		ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,StructuralFeatureActionExtractor.class,VariableActionExtractor.class,
		AcceptEventActionExtractor.class
})
public class ActivityEdgeExtractor extends CommonBehaviorExtractor{
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof ExceptionHandler){
			ExceptionHandler neh = (ExceptionHandler) e;
			if(neh.getHandlerBody() != null && neh.getExceptionInput() != null){
				return new NakedExceptionHandlerImpl();
			}else{
				return null;
			}
		}else if(e instanceof ActivityEdge){
			ActivityNode source = ((ActivityEdge) e).getSource();
			if(source == null || ((ActivityEdge) e).getTarget() == null){
				getErrorMap().putError(getId(e), ActivityValidationRule.ACTIVITY_EDGE_BROKEN);
				return null;
			}else{
				return super.createElementFor(e, peerClass);
			}
		}else{
			return super.createElementFor(e, peerClass);
		}
	}
	@VisitBefore
	public void visitObjectFlow(ObjectFlow f,NakedObjectFlowImpl nakedObjectFlow){
		Activity activity = getActivity(f);
		INakedClassifier nc = getNearestContext(activity);
		initializeEdge(f, nc, nakedObjectFlow);
		nakedObjectFlow.setTransformation((INakedBehavior) getNakedPeer(f.getTransformation()));
		nakedObjectFlow.setSelection((INakedBehavior) getNakedPeer(f.getSelection()));
	}
	@VisitBefore
	public void visitControlFlow(ControlFlow f,NakedActivityEdgeImpl nce){
		Activity activity = getActivity(f);
		INakedClassifier nc = getNearestContext(activity);
		initializeEdge(f, nc, nce);
	}
	@VisitBefore
	public void visitExceptionHandler(ExceptionHandler h,NakedExceptionHandlerImpl nakedHandler){
		nakedHandler.setExceptionInput((INakedObjectNode) getNakedPeer(h.getExceptionInput()));
		nakedHandler.setHandlerBody((INakedAction) getNakedPeer(h.getHandlerBody()));
		EList<Classifier> types = h.getExceptionTypes();
		Collection<INakedClassifier> nakedTypes = new ArrayList<INakedClassifier>();
		for(Classifier classifier:types){
			nakedTypes.add((INakedClassifier) getNakedPeer(classifier));
		}
		nakedHandler.setExceptionTypes(nakedTypes);
	}
	private void initializeEdge(ActivityEdge ae,INakedClassifier nc,NakedActivityEdgeImpl nae){
		INakedActivityNode source = getNode(ae.getSource());
		INakedActivityNode target = getNode(ae.getTarget());
		// Workaround: Remember that ActivityEdge.ownedElements does not include the guard or weight, so the default deletion logic does not
		// work
		if(ae.getGuard() == null){
			nae.setGuard(null);
		}
		if(ae.getWeight() == null){
			nae.setWeight(null);
		}
		if(!nae.isMarkedForDeletion()){
			if((source == null || target == null)){
				throw new IllegalStateException(ae.getSource() + "<-" + ae + "->" + ae.getTarget());
			}else{
				nae.setIndexInOutgoing(ae.getSource().getOutgoings().indexOf(ae));
				nae.setSource(source);
				nae.setTarget(target);
			}
		}
	}
	/**
	 * Interim solution to ensure that a node is always guarranteed. If none is found to be built yet, this method creates an opaqy action
	 * or control node.
	 * 
	 * @param emfNode
	 * @return
	 */
	private INakedActivityNode getNode(ActivityNode emfNode){
		if(emfNode == null){
			return null;// Most likely being deleted
		}else{
			INakedActivityNode node = (INakedActivityNode) getNakedPeer(emfNode);
			if(node == null){
				if(emfNode instanceof Action){
					// could be deleting the edge
				}else{
					NakedControlNodeImpl cnode = new NakedControlNodeImpl();
					cnode.setControlNodeType(ControlNodeType.MERGE_NODE);
					node = cnode;
					initialize(node, emfNode, emfNode.getOwner());
				}
			}
			return node;
		}
	}
}