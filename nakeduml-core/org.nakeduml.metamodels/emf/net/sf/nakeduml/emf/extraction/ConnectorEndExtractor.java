package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorEndImpl;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;

import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Property;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = FeatureExtractor.class,after = FeatureExtractor.class)
public class ConnectorEndExtractor extends AbstractExtractorFromEmf{
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
			//Could be an extension end which is not represented in nakeduml
			for(Property sp:p.getSubsettedProperties()){
				INakedProperty nakedPeer = (INakedProperty) getNakedPeer(sp);
				if(nakedPeer==null){
					System.out.println();
				}
				np.getSubsettedProperties().add(nakedPeer);
			}
			for(Property sp:p.getRedefinedProperties()){
				INakedProperty nakedPeer = (INakedProperty) getNakedPeer(sp);
				if(nakedPeer==null){
					System.out.println();
				}
				np.getRedefinedProperties().add(nakedPeer);
			}
		}
	}
}
