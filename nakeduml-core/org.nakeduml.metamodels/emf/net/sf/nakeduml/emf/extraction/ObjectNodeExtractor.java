package net.sf.nakeduml.emf.extraction;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedParameterNodeImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.Parameter;

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
