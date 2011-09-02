package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.internal.NakedExceptionHandlerImpl;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityEdgeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedControlNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectFlowImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ObjectFlow;

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
			INakedExceptionHandler neh = (INakedExceptionHandler) e;
			if(neh.getHandlerBody() != null && neh.getExceptionInput() != null){
				return new NakedExceptionHandlerImpl();
			}else{
				return null;
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
		initialize(nakedHandler, h, h.getProtectedNode());
		nakedHandler.setExceptionInput((INakedObjectNode) getNakedPeer(h.getExceptionInput()));
		nakedHandler.setHandlerBody((INakedAction) getNakedPeer(h.getHandlerBody()));
		EList<Classifier> types = h.getExceptionTypes();
		Collection<INakedClassifier> nakedTypes = new ArrayList<INakedClassifier>();
		for(Classifier classifier:types){
			nakedTypes.add((INakedClassifier) getNakedPeer(classifier));
		}
		nakedHandler.setExceptionTypes(nakedTypes);
	}
	private void initializeEdge(ActivityEdge ae,INakedClassifier nc,INakedActivityEdge nae){
		nae.setSource(getNode(ae.getSource()));
		nae.setTarget(getNode(ae.getTarget()));
	}
	/**
	 * Interim solution to ensure that a node is always guarranteed. If none is found to be built yet, this method creates an opaqy action
	 * or control node.
	 * 
	 * @param emfNode
	 * @return
	 */
	@SuppressWarnings("serial")
	private INakedActivityNode getNode(ActivityNode emfNode){
		if(emfNode == null){
			return null;// Most likely being deleted
		}else{
			INakedActivityNode node = (INakedActivityNode) getNakedPeer(emfNode);
			if(node == null){
				if(emfNode instanceof Action){
					throw new IllegalStateException("Action " +emfNode + " not loaded");
				}else{
					NakedControlNodeImpl cnode = new NakedControlNodeImpl();
					cnode.setControlNodeType(ControlNodeType.MERGE_NODE);
					node = cnode;
				}
				initialize(node, emfNode, emfNode.getOwner());
			}
			return node;
		}
	}
}
