package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.internal.NakedExceptionHandlerImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityEdgeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedControlNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectFlowImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ObjectFlow;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,StructuralFeatureActionExtractor.class,VariableActionExtractor.class,AcceptEventActionExtractor.class
},after = {
		ActivityControlNodeExtractor.class,ObjectNodeExtractor.class,ActionExtractor.class,StructuralFeatureActionExtractor.class,VariableActionExtractor.class,AcceptEventActionExtractor.class
})
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
	@VisitBefore
	public void visitExceptionHandler(ExceptionHandler h){
		if(h.getHandlerBody() != null && h.getExceptionInput() != null){
			INakedExceptionHandler nakedHandler = new NakedExceptionHandlerImpl();
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
	}
	private void initializeEdge(ActivityEdge ae,INakedClassifier nc,INakedActivityEdge nae){
		initialize(nae, ae, ae.getOwner());
		INakedValueSpecification guard = getValueSpecification(nae, ae.getGuard(), OclUsageType.BODY);
		if(guard != null){
			nae.setGuard(guard);
			guard.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		}
		nae.setSource(getNode(ae.getSource()));
		nae.setTarget(getNode(ae.getTarget()));
		INakedValueSpecification weight = getValueSpecification(nae, ae.getWeight(), OclUsageType.BODY);
		if(weight != null){
			weight.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			nae.setWeight(weight);
		}
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
		INakedActivityNode node = (INakedActivityNode) getNakedPeer(emfNode);
		if(node == null){
			if(emfNode instanceof Action){
				node = new NakedOpaqueActionImpl(){
					@Override
					public Collection<INakedOutputPin> getOutput(){
						return Collections.emptySet();
					}
				};
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
