package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityEdgeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityNodeImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorEndImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.internal.NakedStateImpl;
import net.sf.nakeduml.metamodel.statemachines.internal.NakedTransitionImpl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
		FeatureExtractor.class,ActivityEdgeExtractor.class,TransitionExtractor.class
},after = {
		FeatureExtractor.class,ActivityEdgeExtractor.class,TransitionExtractor.class
})
public class RedefinitionAndConnectorEndExtractor extends AbstractExtractorFromEmf{
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
			// Could be an extension end which is not represented in nakeduml
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
