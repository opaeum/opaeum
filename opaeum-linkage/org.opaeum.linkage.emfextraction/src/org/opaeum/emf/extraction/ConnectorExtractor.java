package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Connector;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.components.NakedConnectorKind;
import org.opaeum.metamodel.components.internal.NakedConnectorImpl;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by Opaeum and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = RedefinitionAndConnectorEndExtractor.class,after = RedefinitionAndConnectorEndExtractor.class)
public class ConnectorExtractor extends AbstractExtractorFromEmf{
	@VisitBefore(matchSubclasses = true)
	public void visitConnector(Connector c,NakedConnectorImpl nce ){
		nce.setEnd1((INakedConnectorEnd) getNakedPeer(c.getEnds().get(0)));
		nce.setEnd2((INakedConnectorEnd) getNakedPeer(c.getEnds().get(1)));
		switch(c.getKind()){
		case ASSEMBLY_LITERAL:
			nce.setKind(NakedConnectorKind.ASSEMBLY);
			break;
		case DELEGATION_LITERAL:
			nce.setKind(NakedConnectorKind.DELEGATION);
			break;
		}
	}
}
