package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityPartitionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityVariable;

import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Variable;

@StepDependency(phase = EmfExtractionPhase.class, requires = { TypedElementExtractor.class }, after = { TypedElementExtractor.class })
public class ActivityStructureExtractor extends AbstractExtractorFromEmf {
	@VisitBefore
	public void visitVariable(Variable emfNode, NakedActivityVariable ae) {
		populateMultiplicityAndBaseType(emfNode, emfNode.getType(), ae);
	}


	@VisitBefore
	public void visitPartition(ActivityPartition emfPartition, NakedActivityPartitionImpl nakedPartition) {
		if (emfPartition.getRepresents() != null) {
			nakedPartition.setRepresents(getNakedPeer(emfPartition.getRepresents()));
		}
	}
}
