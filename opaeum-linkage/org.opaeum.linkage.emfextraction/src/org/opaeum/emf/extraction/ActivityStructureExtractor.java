package org.opaeum.emf.extraction;

import java.util.Collection;

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
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActivityPartitionImpl;
import org.opaeum.metamodel.activities.internal.NakedActivityVariable;
import org.opaeum.metamodel.activities.internal.NakedExpansionRegionImpl;
import org.opaeum.metamodel.activities.internal.NakedStructuredActivityNodeImpl;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

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

	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if (e instanceof ExpansionRegion) {
			return new NakedExpansionRegionImpl();
		} else {
			return super.createElementFor(e, peerClass);
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitStructuredActivityNode(StructuredActivityNode emfNode,NakedStructuredActivityNodeImpl nakedNode ) {
		if(nakedNode instanceof NakedExpansionRegionImpl){
			((NakedExpansionRegionImpl) nakedNode).getInputElement().clear();
			((NakedExpansionRegionImpl) nakedNode).getOutputElement().clear();
			
		}
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

}
