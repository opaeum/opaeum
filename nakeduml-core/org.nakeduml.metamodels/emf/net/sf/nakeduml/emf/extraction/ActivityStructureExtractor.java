package net.sf.nakeduml.emf.extraction;

import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityPartitionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionRegionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedStructuredActivityNodeImpl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Variable;

@StepDependency(phase = EmfExtractionPhase.class, requires = { FeatureExtractor.class }, after = { FeatureExtractor.class })
public class ActivityStructureExtractor extends AbstractActionExtractor {
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
		NakedStructuredActivityNodeImpl nakedNode = null;
		if (emfNode instanceof ExpansionRegion) {
			nakedNode = new NakedExpansionRegionImpl();
		} else {
			nakedNode = new NakedStructuredActivityNodeImpl();
		}
		super.initialize(nakedNode, emfNode, resolveCorrectParent(emfNode));
		super.initAction(emfNode, nakedNode);
		Collection<INakedInputPin> input = populatePins(emfNode.getActivity(), getInputs(emfNode));
		nakedNode.setInput(input);
		Collection<INakedOutputPin> output = populatePins(emfNode.getActivity(),getOutputs(emfNode));
		nakedNode.setOutput(output);
	}

	private EList<InputPin> getInputs(StructuredActivityNode emfNode) {
		EList<ActivityNode> nodes = emfNode.getNodes();
		EList<InputPin> input = new BasicEList<InputPin>();
		for (ActivityNode node : nodes) {
			if(node instanceof InputPin && node.getOwner() == emfNode){
				input.add((InputPin) node);
			}
		}
		return input;
	}
	private EList<OutputPin> getOutputs(StructuredActivityNode emfNode) {
		EList<ActivityNode> nodes = emfNode.getNodes();
		EList<OutputPin> input = new BasicEList<OutputPin>();
		for (ActivityNode node : nodes) {
			if(node instanceof OutputPin && node.getOwner() == emfNode){
				input.add((OutputPin) node);
			}
		}
		return input;
	}

	private Element resolveCorrectParent(ActivityNode emfNode) {
		// TODO check if this is necessary
		return emfNode.getInStructuredNode() == null ? emfNode.getActivity() : emfNode.getInStructuredNode();
	}
}
