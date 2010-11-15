package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityPartitionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionRegionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;

import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.StructuredActivityNode;
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

	@VisitBefore(matchSubclasses = true)
	public void visitStructuredActivityNode(StructuredActivityNode emfNode) {
		if (emfNode instanceof ExpansionRegion) {
			NakedExpansionRegionImpl nakedRegion = new NakedExpansionRegionImpl();
			ExpansionRegion emfRegion = (ExpansionRegion) emfNode;
			super.initialize(nakedRegion, emfNode, resolveCorrectParent(emfNode));
//			for (ExpansionNode ie : emfRegion.getInputElements()) {
//				nakedRegion.getInputElement().add(populateExpansionNode(ie, new NakedExpansionNodeImpl()));
//			}
//			for (ExpansionNode oe : emfRegion.getOutputElements()) {
//				nakedRegion.getOutputElement().add(populateExpansionNode(oe, new NakedExpansionNodeImpl()));
//			}
		} else {
			NakedStructuredActivityNode nakedNode = new NakedStructuredActivityNode();
			super.initialize(nakedNode, emfNode, resolveCorrectParent(emfNode));
		}
		//TODO set partition
	}

	private Element resolveCorrectParent(ActivityNode emfNode) {
		// TODO check if this is necessary
		return emfNode.getInStructuredNode() == null ? emfNode.getActivity() : emfNode.getInStructuredNode();
	}

}
