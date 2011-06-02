package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorEndImpl;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.eclipse.uml2.uml.ConnectorEnd;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = FeatureExtractor.class,after = FeatureExtractor.class)
public class ConnectorEndExtractor extends AbstractExtractorFromEmf{
	@VisitBefore(matchSubclasses = true)
	public void visitConnectorEnd(ConnectorEnd ce){
		NakedConnectorEndImpl nce=new NakedConnectorEndImpl();
		nce.setPartWitPort((INakedProperty) getNakedPeer(ce.getPartWithPort()));
		nce.setRole((INakedProperty) getNakedPeer(ce.getRole()));
		initialize(nce,ce,ce.getOwner());
		populateMultiplicity(ce, nce);
	}
}
