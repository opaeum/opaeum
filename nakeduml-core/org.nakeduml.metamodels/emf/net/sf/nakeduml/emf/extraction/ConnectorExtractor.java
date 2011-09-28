package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.components.NakedConnectorKind;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorImpl;

import org.eclipse.uml2.uml.Connector;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
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
