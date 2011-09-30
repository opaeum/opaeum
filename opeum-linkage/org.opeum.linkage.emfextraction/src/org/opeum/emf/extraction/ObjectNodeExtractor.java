package org.opeum.emf.extraction;

import java.util.List;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.Parameter;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.internal.NakedExpansionNodeImpl;
import org.opeum.metamodel.activities.internal.NakedObjectNodeImpl;
import org.opeum.metamodel.activities.internal.NakedParameterNodeImpl;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedParameter;
import org.opeum.metamodel.core.internal.NakedMultiplicityImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = {FeatureExtractor.class,ActivityStructureExtractor.class},after = {
		FeatureExtractor.class,ActivityStructureExtractor.class})
public class ObjectNodeExtractor extends CommonBehaviorExtractor{
	@VisitBefore
	public void visitActivityParameterNode(ActivityParameterNode emfNode,NakedParameterNodeImpl nakedNode){
		Parameter emfParameter = emfNode.getParameter();
		nakedNode.setParameter((INakedParameter) getNakedPeer(emfParameter));
	}
	@VisitBefore
	public void visitCentralBufferNode(CentralBufferNode emfNode,NakedObjectNodeImpl nakedNode){
	}
	@VisitBefore
	public void visitExpansionNode(ExpansionNode emfNode,NakedExpansionNodeImpl non){
		non.setMultiplicity(new NakedMultiplicityImpl("0", "*"));
		non.setBaseType((INakedClassifier) getNakedPeer(emfNode.getType()));
		non.setIsOrdered(true);
		non.setIsUnique(false);
		List<INakedExpansionNode> expansionNodes = non.getExpansionRegion().getInputElement();
		if(emfNode.getRegionAsInput()==null){
			expansionNodes = non.getExpansionRegion().getOutputElement();
		}else{
			expansionNodes = non.getExpansionRegion().getInputElement();
		}
		expansionNodes.add(non);

	}
}
