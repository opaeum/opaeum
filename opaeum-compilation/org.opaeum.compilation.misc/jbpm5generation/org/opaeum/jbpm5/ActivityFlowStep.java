package org.opaeum.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ConstraintsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StateType;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.ActivityNodeMap;

@StepDependency(phase = FlowGenerationPhase.class)
public class ActivityFlowStep extends AbstractFlowStep{
	private static final int ARTIFICIAL_JOIN_ID = 100000;
	private static final int ARTIFICIAL_FORK_ID = 200000;
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FINAL_NODE_ID = 400000;
	private static final int ARTIFICIAL_CHOICE_ID = 500000;
	private static final Integer INIT_NODE_ID = 600000;
	@VisitAfter(matchSubclasses = true)
	public void createRoot(Activity a){
		if(!EmfActivityUtil.isSimpleSynchronousMethod(a)){
			populateContainer(a);
		}
	}
	private int insertArtificialJoin(NodesType nodes,ConnectionsType connections,int i,ActivityNode state){
		Long joinId = EmfWorkspace.getOpaeumId( state) + ARTIFICIAL_JOIN_ID;
		addJoin(nodes, i, Jbpm5Util.getArtificialJoinName(state), joinId);
		createConnection(connections, joinId, EmfWorkspace.getOpaeumId( state));
		i++;
		targetIdMap.peek().put(state, joinId);
		return i;
	}
	private final int addFinalNode(NodesType nodes,ConnectionsType connections,int i,ControlNode state){
		String name = PersistentNameUtil.getPersistentName( state).getAsIs();
		Long nakedUmlId = EmfWorkspace.getOpaeumId( state);
		EndType addFinalNode = null;
		if((EmfElementFinder.getContainer(state) instanceof StructuredActivityNode)){
			addFinalNode = addFinalNode(nodes, i, name, nakedUmlId);
		}else{
			addActionNode(nodes, i, state);
			i++;
			Long finalNodeId = nakedUmlId + ARTIFICIAL_FINAL_NODE_ID;
			addFinalNode = addFinalNode(nodes, i, PersistentNameUtil.getPersistentName(state) + "_end", finalNodeId);
			this.createConnection(connections, nakedUmlId, finalNodeId);
		}
		if(state instanceof ActivityFinalNode){
			addFinalNode.setTerminate("true");
		}
		return i;
	}
	private void populateContainer(Namespace container){
		DocumentRoot root = super.createRoot(container);
		ProcessType process = root.getProcess();
		sourceIdMap.push(new HashMap<Element,Long>());
		targetIdMap.push(new HashMap<Element,Long>());
		NodesType nodesType = process.getNodes().get(0);
		ConnectionsType connections = process.getConnections().get(0);
		int i = 1;
		HashMap<SplitType,ActivityNode> choiceNodes = new HashMap<SplitType,ActivityNode>();
		Collection<ActivityNode> activityNodes = new HashSet<ActivityNode>(EmfActivityUtil.getActivityNodes(container));
		Collection<ActivityNode> effectiveStartNodes = getEffectiveStartNodes(container);
		// Remove the now redundant initial nodes
		activityNodes.removeAll(EmfActivityUtil.getStartNodes( container));
		// Effective Startnodes will be treated separately
		activityNodes.removeAll(effectiveStartNodes);
		Long startNodeId = EmfWorkspace.getOpaeumId(container) + ARTIFICIAL_START_NODE_ID;
		addInitialNode(nodesType, i, "artificial_start_for_" + PersistentNameUtil.getPersistentName( container).getAsIs(), startNodeId);
		i++;
		if(container instanceof Activity && EmfBehaviorUtil  .isProcess((Activity) container)){
			ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
			Long initNodeId = EmfWorkspace.getOpaeumId(container) + INIT_NODE_ID;
			setBounds(i, actionNode, initNodeId);
			createAction("init", actionNode.getAction(), true);
			actionNode.setName("init");
			nodesType.getActionNode().add(actionNode);
			i++;
			createConnection(connections, startNodeId, initNodeId);
			startNodeId = initNodeId;
		}
		if(effectiveStartNodes.size() > 1){
			// INsert artificial Fork;
			Long forkId = EmfWorkspace.getOpaeumId( container) + ARTIFICIAL_FORK_ID;
			addFork(nodesType, i, Jbpm5Util.getArtificialForkName(container), forkId);
			createConnection(connections, startNodeId, forkId);
			i++;
			startNodeId = forkId;
		}
		// Add connections from fork/startNode
		for(ActivityNode effectiveStartNode:effectiveStartNodes){
			i = addNode(nodesType, connections, i, choiceNodes, effectiveStartNode);
			Long targetId = targetIdMap.peek().get(effectiveStartNode);
			createConnection(connections, startNodeId, targetId);
		}
		// Add Nodes
		for(ActivityNode node:activityNodes){
			i = addNode(nodesType, connections, i, choiceNodes, node);
		}
		// Add connections
		for(ActivityEdge t:EmfActivityUtil.getEdges( container)){
			ActivityNode es = EmfActivityUtil.getEffectiveSource(t);
			Long sourceId = sourceIdMap.peek().get(es);
			Long targetId = targetIdMap.peek().get(EmfActivityUtil.getEffectiveTarget(t));
			if(sourceId != null && targetId != null){
				// Not all nodes manifest in jbpm nodes, e.g. initialNodes and
				// some ActivityParameterNodes
				if(t.getSource() instanceof OutputPin && EmfActionUtil.isExceptionPin((OutputPin) t.getSource())){
					// Bypass artificial forks and decisions
					createConnection(connections, EmfWorkspace.getOpaeumId( es), targetId);
				}else{
					createConnection(connections, sourceId, targetId);
				}
			}
		}
		for(Map.Entry<SplitType,ActivityNode> entry:choiceNodes.entrySet()){
			this.addConstraintsToSplit(entry.getKey(), EmfActivityUtil.getAllEffectiveOutgoing(entry.getValue()), true);
		}
	}
	private boolean requiresArtificialFinalNode(ActivityNode node){
		boolean isFinalNode = node instanceof FinalNode;
		boolean isOutputExpansionNode = node instanceof ExpansionNode && ((ExpansionNode) node).getRegionAsOutput()!=null;
		boolean hasExceptionHandler = node instanceof Action && ((Action) node).getHandlers().size() > 0;
		return (EmfActivityUtil.getAllEffectiveOutgoing(node).isEmpty() && !isFinalNode && !hasExceptionHandler) || isOutputExpansionNode;
	}
	private Collection<ActivityNode> getEffectiveStartNodes(Namespace container){
		Set<ActivityNode> results = new HashSet<ActivityNode>();
		Collection<ActivityNode> startNodes = EmfActivityUtil.getStartNodes( container);
		for(ActivityNode sn:startNodes){
			if(isInitialNode(sn)){
				Set<ActivityEdge> outging = EmfActivityUtil.getAllEffectiveOutgoing(sn);
				for(ActivityEdge edge:outging){
					if(dependsOnInitialNode(edge)){
						results.add(EmfActivityUtil.getEffectiveTarget(edge));
					}
				}
			}else{
				results.add(sn);
			}
		}
		return results;
	}
	/**
	 * Returns true if this node is dependent on the preceding InitialNode for its activation
	 * 
	 * @param edge
	 * @return
	 */
	private boolean dependsOnInitialNode(ActivityEdge edge){
		if(EmfActivityUtil.getEffectiveTarget(edge) instanceof ControlNode){
			ControlNode node = (ControlNode) EmfActivityUtil.getEffectiveTarget(edge);
			if(node instanceof MergeNode){
				return true;
			}
		}
		Set<ActivityEdge> in = EmfActivityUtil.getAllEffectiveIncoming(EmfActivityUtil.getEffectiveTarget(edge));
		for(ActivityEdge e:in){
			if(!isInitialNode(EmfActivityUtil.getEffectiveSource(e))){
				return false;
			}
		}
		return true;
	}
	private boolean isInitialNode(ActivityNode startNode){
		boolean isInitialNode = startNode instanceof InitialNode;
		boolean isInputParameter = startNode instanceof ActivityParameterNode
				&& EmfBehaviorUtil.isArgument( ((ActivityParameterNode) startNode).getParameter());
		boolean isInputExpansionNode = startNode instanceof ExpansionNode && ((ExpansionNode) startNode).getRegionAsInput() != null;
		return isInitialNode || isInputParameter || isInputExpansionNode;
	}
	private int addNode(NodesType nodesType,ConnectionsType connections,int i,HashMap<SplitType,ActivityNode> choiceNodes,ActivityNode node){
		if(!(node instanceof Pin)){
			if(EmfActivityUtil.isImplicitJoin( node)){
				i = insertArtificialJoin(nodesType, connections, i, node);
			}else{
				targetIdMap.peek().put(node, EmfWorkspace.getOpaeumId(node));
			}
			if(node instanceof ControlNode){
				ControlNode controlNode = (ControlNode) node;
				if(controlNode instanceof FlowFinalNode){
					i = addFinalNode(nodesType, connections, i, controlNode);
				}else if(controlNode instanceof ActivityFinalNode){
					i = addFinalNode(nodesType, connections, i, controlNode);
				}else if(controlNode instanceof ForkNode){
					addFork(nodesType, i, PersistentNameUtil.getPersistentName( node).toString(), EmfWorkspace.getOpaeumId(node));
				}else if(controlNode instanceof JoinNode){
					addJoin(nodesType, i, PersistentNameUtil.getPersistentName( node).getAsIs(), EmfWorkspace.getOpaeumId(node));
				}else if(controlNode instanceof MergeNode){
					addMerge(nodesType, i, PersistentNameUtil.getPersistentName( node).getAsIs(), EmfWorkspace.getOpaeumId(node));
				}else if(controlNode instanceof DecisionNode){
					choiceNodes.put(addChoice(nodesType, i, PersistentNameUtil.getPersistentName( node).toString(), EmfWorkspace.getOpaeumId(node)),
							node);
				}else{
					System.out.println(controlNode.eClass().getName() + " not supported");
				}
			}else if(node instanceof ActivityParameterNode
					&& ((ActivityParameterNode) node).getParameter().getDirection() == ParameterDirectionKind.RETURN_LITERAL){
				addActionNode(nodesType, i, node);
			}else if(node instanceof ExpansionNode && ((ExpansionNode) node).getRegionAsOutput() != null){
				addActionNode(nodesType, i, node);
			}else if(node instanceof Action){
				Action action = (Action) node;
				if(action instanceof AcceptEventAction){
					addWaitState(nodesType, i, (AcceptEventAction) node);
				}else if(EmfActionUtil.isEmbeddedTask(action )){
					//TODO async
					addWaitState(nodesType, i, (Action) node);
				}else if(action instanceof CallBehaviorAction && EmfBehaviorUtil.isProcess(((CallBehaviorAction) node).getBehavior())){
					//TODO async
					addWaitState(nodesType, i, (CallAction) node);
				}else if(action instanceof CallOperationAction && EmfBehaviorUtil.isLongRunning( ((CallOperationAction) node).getOperation())){
					addWaitState(nodesType, i, (CallOperationAction) node);
					//TODO async
				}else if(EmfActionUtil.hasExceptions( action)){
					addExceptionAwareState(nodesType, i, action);
				}else if(node instanceof ExpansionRegion){
					addWaitState(nodesType, i, (ExpansionRegion) node);
				}else if(node instanceof StructuredActivityNode){
					populateContainer((StructuredActivityNode) node);
				}else{
					addActionNode(nodesType, i, action);
				}
				Collection<ExceptionHandler> handlers = action.getHandlers();
				for(ExceptionHandler handler:handlers){
					createConnection(connections, EmfWorkspace.getOpaeumId( action), EmfWorkspace.getOpaeumId(handler.getHandlerBody()));
				}
			}else{
				System.out.println(node.getName() + ":" + node.getClass().getName() + " not supported yet");
			}
			i++;
			if(EmfActivityUtil.isImplicitFork( node)){
				i = insertArtificialFork(nodesType, connections, i, node);
			}else if(requiresArtificialDecision(node)){
				i = insertArtificialChoice(nodesType, choiceNodes, connections, i, node);
			}else{
				sourceIdMap.peek().put(node, EmfWorkspace.getOpaeumId(node));
			}
			if(requiresArtificialFinalNode(node)){
				addFinalNode(nodesType, i, "artificialFinalFor" + node.getName(), EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_FORK_ID);
				i++;
				createConnection(connections, EmfWorkspace.getOpaeumId(node), EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_FORK_ID);
			}
		}
		return i;
	}
	public boolean requiresArtificialDecision(ActivityNode node){
		return EmfActivityUtil.isImplicitDecision(node);
	}
	private StateType addWaitState(NodesType nodes,int i,Action task){
		StateType state = addState(nodes, i, PersistentNameUtil.getPersistentName( task).toString(), EmfWorkspace.getOpaeumId(task));
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		state.getOnEntry().add(onEntry);
		ActionMap map = ojUtil.buildActionMap(task);
		createAction(map.doActionMethod(), onEntry.getAction(), true);
		return state;
	}
	public void addExceptionAwareState(NodesType nodesType,int i,Action action){
		ActivityNodeMap map = ojUtil.buildActivityNodeMap(action);
		StateType state = addState(nodesType, i, PersistentNameUtil.getPersistentName( action).getAsIs(), EmfWorkspace.getOpaeumId(action));
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		createAction(map.doActionMethod(), onEntry.getAction(), true);
		state.getOnEntry().add(onEntry);
	}
	private int insertArtificialFork(NodesType nodesType,ConnectionsType connections,int i,ActivityNode node){
		Long forkId = EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_FORK_ID;
		addFork(nodesType, i, Jbpm5Util.getArtificialForkName(node), forkId);
		createConnection(connections, EmfWorkspace.getOpaeumId( node), forkId);
		i++;
		sourceIdMap.peek().put(node, EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_FORK_ID);
		return i;
	}
	private int insertArtificialChoice(NodesType nodesType,HashMap<SplitType,ActivityNode> choiceNodes,ConnectionsType connections,int i,
			ActivityNode node){
		Long forkId = EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_CHOICE_ID;
		SplitType split = addChoice(nodesType, i, Jbpm5Util.getArtificialChoiceName(node), forkId);
		createConnection(connections, EmfWorkspace.getOpaeumId(node), forkId);
		choiceNodes.put(split, node);
		i++;
		sourceIdMap.peek().put(node, EmfWorkspace.getOpaeumId(node) + ARTIFICIAL_CHOICE_ID);
		return i;
	}
	private void addActionNode(NodesType nodes,int i,ActivityNode node){
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode, EmfWorkspace.getOpaeumId(node));
		ActivityNodeMap map = ojUtil.buildActivityNodeMap(node);
		createAction(map.doActionMethod(), actionNode.getAction(), true);
		actionNode.setName(PersistentNameUtil.getPersistentName( node).getAsIs());
		nodes.getActionNode().add(actionNode);
	}
	private void addWaitState(NodesType nodes,int i,AcceptEventAction action){
		StateType state = addState(nodes, i, PersistentNameUtil.getPersistentName( action).toString(), EmfWorkspace.getOpaeumId(action));
		ActionMap map = ojUtil.buildActionMap(action);
		if(EmfEventUtil.requiresEventRequest( action) && EmfActivityUtil .getAllEffectiveIncoming(action).size() > 0){
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			state.getOnEntry().add(onEntry);
			createAction(map.doActionMethod(), onEntry.getAction(), true);
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			state.getOnExit().add(onExit);
			createAction(map.getCancelEventsMethod(), onExit.getAction(), false);
		}
	}

	protected void addConstraintsToSplit(SplitType split, Collection<? extends ActivityEdge> outgoing, boolean passContext) {
		ConstraintsType constraints = ProcessFactory.eINSTANCE.createConstraintsType();
		split.getConstraints().add(constraints);
		for (ActivityEdge t : outgoing) {
			ConstraintType constraint = ProcessFactory.eINSTANCE.createConstraintType();
			constraint.setDialect("mvel");
			Long toNodeId = this.targetIdMap.peek().get(EmfActivityUtil.getEffectiveTarget( t));
			constraint.setToNodeId(toNodeId + "");
			if (!EmfActivityUtil.hasGuard(t)) {
				constraint.setValue("return true;");
				constraint.setPriority("3");
			} else {
				if (t.getGuard()instanceof OpaqueExpression ) {
					String param = passContext ? "context" : "";
					constraint.setValue("return processObject." + Jbpm5Util.getGuardMethod(EmfActivityUtil.getEffectiveSource(t), t) + "(" + param + ");");
					constraint.setPriority("1");
				} else if (t.getGuard() instanceof LiteralBoolean) {
					constraint.setValue("return " + ((LiteralBoolean)t.getGuard()).booleanValue() + ";");
					constraint.setPriority("2");
				} else {
					constraint.setValue("return true;");
					constraint.setPriority("3");
				}
			}
			constraint.setToType("DROOLS_DEFAULT");
			constraint.setType("code");
			constraints.getConstraint().add(constraint);
		}
	}
}
