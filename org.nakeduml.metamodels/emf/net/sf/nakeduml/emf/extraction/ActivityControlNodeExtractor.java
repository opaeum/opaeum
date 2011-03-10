package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.internal.NakedControlNodeImpl;

import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;

@StepDependency(phase = EmfExtractionPhase.class,requires = {TypedElementExtractor.class,ActivityStructureExtractor.class},after = {
		TypedElementExtractor.class,ActivityStructureExtractor.class})
public class ActivityControlNodeExtractor extends CommonBehaviorExtractor{
	@VisitBefore
	public void visitInitialNode(InitialNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.INITIAL_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitActivityFinalNode(ActivityFinalNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.ACTIVITY_FINAL_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitFlowFinalNode(FlowFinalNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.FLOW_FINAL_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitJoinNode(JoinNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.JOIN_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitForkNode(ForkNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.FORK_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitDecisionNode(DecisionNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.DECISION_NODE);
		assignPartition(nakedNode, emfNode);
	}
	@VisitBefore
	public void visitMergeNode(MergeNode emfNode,NakedControlNodeImpl nakedNode){
		nakedNode.setControlNodeType(ControlNodeType.MERGE_NODE);
		assignPartition(nakedNode, emfNode);
	}
	private void assignPartition(INakedActivityNode node,ActivityNode emfNode){
		if(emfNode.getInPartitions().size() == 1){
			node.setInPartition(((INakedActivityPartition) getNakedPeer((emfNode.getInPartitions().get(0)))));
		}
	}
}
