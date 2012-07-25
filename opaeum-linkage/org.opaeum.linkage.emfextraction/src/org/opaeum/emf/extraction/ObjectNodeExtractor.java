package org.opaeum.emf.extraction;

import java.util.List;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedParameter;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.internal.NakedExpansionNodeImpl;
import org.opaeum.metamodel.activities.internal.NakedObjectNodeImpl;
import org.opaeum.metamodel.activities.internal.NakedParameterNodeImpl;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;

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
		non.setBaseType((INakedClassifier) getNakedPeer(emfNode.getType()));
		non.setIsOrdered(true);
		non.setIsUnique(false);
		List<INakedExpansionNode> expansionNodes = non.getExpansionRegion().getInputElement();
		if(emfNode.getRegionAsInput()==null){
			expansionNodes = non.getExpansionRegion().getOutputElement();
			non.setMultiplicity(new NakedMultiplicityImpl("0", "*"));
		}else{
			expansionNodes = non.getExpansionRegion().getInputElement();
			non.setMultiplicity(new NakedMultiplicityImpl("1", "1"));
		}
		expansionNodes.add(non);

	}
}
