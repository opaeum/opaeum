package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityEdgeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedControlNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectFlowImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ObjectFlow;

@StepDependency(phase = EmfExtractionPhase.class,requires =  {ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,
	StructuralFeatureActionExtractor.class,VariableActionExtractor.class},after = {ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,
			StructuralFeatureActionExtractor.class,VariableActionExtractor.class})
public class ActivityEdgeExtractor extends CommonBehaviorExtractor{
	@VisitBefore
	public void visitObjectFlow(ObjectFlow f){
		Activity activity = getActivity(f);
		INakedClassifier nc = getNearestContext(activity);
		NakedObjectFlowImpl nakedObjectFlow = new NakedObjectFlowImpl();
		initializeEdge(f, nc, nakedObjectFlow);
		nakedObjectFlow.setTransformation((INakedBehavior) getNakedPeer(f.getTransformation()));
		nakedObjectFlow.setSelection((INakedBehavior) getNakedPeer(f.getSelection()));
	}
	@VisitBefore
	public void visitControlFlow(ControlFlow f){
		Activity activity = getActivity(f);
		INakedClassifier nc = getNearestContext(activity);
		initializeEdge(f, nc, new NakedActivityEdgeImpl());
	}
	private void initializeEdge(ActivityEdge ae,INakedClassifier nc,INakedActivityEdge nae){
		initialize(nae, ae, ae.getActivity());
		INakedValueSpecification guard = getValueSpecification((INakedBehavior) getNakedPeer(ae.getActivity()), ae.getGuard(),
				OclUsageType.BODY);
		if(guard != null){
			nae.setGuard(guard);
			guard.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		}
		nae.setSource(getNode(ae.getSource()));
		nae.setTarget(getNode(ae.getTarget()));
		INakedValueSpecification weight = getValueSpecification(nc, ae.getWeight(), OclUsageType.BODY);
		if(weight != null){
			weight.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			nae.setWeight(weight);
		}
	}
	/**
	 * Interim solution to ensure that a node is always guarranteed. If none is found to be built yet, this method creates an opaqy action or
	 * control node.
	 * 
	 * @param emfNode
	 * @return
	 */
	private INakedActivityNode getNode(ActivityNode emfNode){
		INakedActivityNode node = (INakedActivityNode) getNakedPeer(emfNode);
		if(node == null){
			if(emfNode instanceof Action){
				node = new NakedOpaqueActionImpl();
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