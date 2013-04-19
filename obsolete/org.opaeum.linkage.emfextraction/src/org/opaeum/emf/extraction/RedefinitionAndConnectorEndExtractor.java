package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityNode;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedState;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.internal.NakedActivityEdgeImpl;
import org.opaeum.metamodel.activities.internal.NakedActivityNodeImpl;
import org.opaeum.metamodel.components.internal.NakedConnectorEndImpl;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;
import org.opaeum.metamodel.statemachines.internal.NakedStateImpl;
import org.opaeum.metamodel.statemachines.internal.NakedTransitionImpl;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by Opaeum and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
		FeatureExtractor.class,ActivityEdgeExtractor.class,TransitionExtractor.class
},after = {
		FeatureExtractor.class,ActivityEdgeExtractor.class,TransitionExtractor.class
})
public class RedefinitionAndConnectorEndExtractor extends AbstractExtractorFromEmf{
	
	@Override
	protected Object resolvePeer(Element o,Class<?> peerClass){
		return getNakedPeer(o);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitConnectorEnd(ConnectorEnd ce,NakedConnectorEndImpl nce){
		nce.setPartWitPort((INakedProperty) getNakedPeer(ce.getPartWithPort()));
		nce.setRole((INakedProperty) getNakedPeer(ce.getRole()));
		populateMultiplicity(ce, nce);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(Property p){
		NakedPropertyImpl np = (NakedPropertyImpl) getNakedPeer(p);
		if(np != null){
			// Could be an extension end which is not represented in opaeum
			Set<INakedProperty> subsettedProperties = getRedefinedElements(p.getSubsettedProperties());
			np.setSubsettedProperties(subsettedProperties);
			Collection<INakedProperty> redefinedProperties = getRedefinedElements(p.getRedefinedProperties());
			np.setRedefinedProperties(redefinedProperties);
		}
	}
	@SuppressWarnings("unchecked")
	protected <T extends INakedElement>Set<T> getRedefinedElements(EList<? extends Element> subsettedElements){
		Set<T> result = new HashSet<T>();
		for(Element sp:subsettedElements){
			T nakedPeer = (T) getNakedPeer(sp);
			result.add(nakedPeer);
		}
		return result;
	}
	@VisitBefore()
	public void visitOperation(Operation eo,NakedOperationImpl no){
		Set<INakedOperation> redefinedOperations = getRedefinedElements(eo.getRedefinedOperations());
		no.setRedefinedOperations(redefinedOperations);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitActivityNode(ActivityNode en,NakedActivityNodeImpl nn){
		Set<INakedActivityNode> redefinedNodes = getRedefinedElements(en.getRedefinedNodes());
		nn.setRedefinedNodes(redefinedNodes);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEdge(ActivityEdge ee,NakedActivityEdgeImpl ne){
		Set<INakedActivityEdge> redefinedEdges = getRedefinedElements(ee.getRedefinedEdges());
		ne.setRedefinedEdges(redefinedEdges);
	}
	@VisitBefore()
	public void visitState(State es,NakedStateImpl ns){
		ns.setRedefinedState((INakedState) getNakedPeer(es.getRedefinedState()));
	}
	@VisitBefore()
	public void visitTransition(Transition et,NakedTransitionImpl nt){
		nt.setRedefinedTransition((INakedTransition) getNakedPeer(et.getRedefinedTransition()));
	}
}
