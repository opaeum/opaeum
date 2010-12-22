package net.sf.nakeduml.jbpm5;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ConstraintsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.HeaderType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.drools.drools._5._0.process.TypeType;
import org.drools.drools._5._0.process.VariableType;
import org.drools.drools._5._0.process.VariablesType;
import org.drools.drools._5._0.process.util.ProcessResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class FlowGenerationStep extends VisitorAdapter<INakedElementOwner, INakedModelWorkspace> implements TransformationStep {
	protected TextWorkspace textWorkspace;
	private INakedModelWorkspace workspace;
	protected Map<INakedElement, Integer> targetIdMap;
	protected Map<INakedElement, Integer> sourceIdMap;

	public void initialize(TextWorkspace textWorkspace, INakedModelWorkspace workspace) {
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
	}

	protected DocumentRoot createRoot(INakedBehavior behavior) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("rf", new ProcessResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(ProcessPackage.eNS_URI, ProcessPackage.eINSTANCE);
		Resource r = resourceSet.createResource(URI.createFileURI("temp.rf"));
		DocumentRoot root = ProcessFactory.eINSTANCE.createDocumentRoot();
		root.setProcess(ProcessFactory.eINSTANCE.createProcessType());
		r.getContents().add(root);
		ProcessType process = root.getProcess();
		HeaderType header = ProcessFactory.eINSTANCE.createHeaderType();
		process.getHeader().add(header);
		VariablesType variables = ProcessFactory.eINSTANCE.createVariablesType();
		header.getVariables().add(variables);
		VariableType processObject = ProcessFactory.eINSTANCE.createVariableType();
		variables.getVariable().add(processObject);
		processObject.setName("processObject");
		TypeType processObjectType = ProcessFactory.eINSTANCE.createTypeType();
		processObjectType.setClassName(behavior.getMappingInfo().getQualifiedJavaName());
		processObjectType.setName("org.drools.process.core.datatype.impl.type.ObjectDataType");
		processObject.getType().add(processObjectType);
		root.getProcess().getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		root.getProcess().getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		root.getProcess().setId(BpmUtil.generateProcessName(behavior));
		root.getProcess().setName(BpmUtil.generateProcessName(behavior));
		root.getProcess().setPackageName(behavior.getNameSpace().getMappingInfo().getQualifiedJavaName());
		root.getProcess().setVersion("" + workspace.getWorkspaceMappingInfo().getCurrentVersion());
		root.getProcess().setType("RuleFlow");
		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(PropertiesSource.GEN_RESOURCE);
		List<String> names = new ArrayList<String>();
		addNames(behavior.getNameSpace(), names);
		names.add(behavior.getName() + ".rf");
		or.findOrCreateTextFile(names, new EmfTextSource(r, "process"));
		return root;
	}

	protected final void addStartNode(NodesType nodes, int i, INakedElement state) {
		StartType node = ProcessFactory.eINSTANCE.createStartType();
		node.setName(state.getMappingInfo().getPersistentName().getAsIs());
		setBounds(i, node, state.getMappingInfo().getNakedUmlId());
		nodes.getStart().add(node);
	}

	protected final void setBounds(int i, Object flowState, int nakedUmlId) {
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(flowState.getClass()).getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals("id")) {
					pd.getWriteMethod().invoke(flowState, "" + nakedUmlId);
				} else if (pd.getName().equals("height")) {
					pd.getWriteMethod().invoke(flowState, "50");
				} else if (pd.getName().equals("width")) {
					pd.getWriteMethod().invoke(flowState, "100");
				} else if (pd.getName().equals("x")) {
					pd.getWriteMethod().invoke(flowState, (i * 50) + "");
				} else if (pd.getName().equals("y")) {
					pd.getWriteMethod().invoke(flowState, (i * 50) + "");
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	void addNames(INakedNameSpace ns, List<String> path) {
		if (ns != null) {
			addNames(ns.getNameSpace(), path);
			path.add(ns.getName().toLowerCase());
		}
	}

	protected JoinType addJoin(NodesType nodes, int i, String name, Integer nakedUmlId) {
		JoinType join = addJoinType(nodes, i, name, nakedUmlId);
		join.setType("1");
		return join;
	}

	protected JoinType addMerge(NodesType nodes, int i, String name, Integer nakedUmlId) {
		JoinType join = addJoinType(nodes, i, name, nakedUmlId);
		join.setType("2");
		return join;
	}

	private JoinType addJoinType(NodesType nodes, int i, String name, Integer nakedUmlId) {
		JoinType join = ProcessFactory.eINSTANCE.createJoinType();
		join.setName(name);
		setBounds(i, join, nakedUmlId);
		nodes.getJoin().add(join);
		return join;
	}

	protected CompositeType createCompositeState(NodesType nodes, int i, String name, Integer nakedUmlId) {
		CompositeType flowState = ProcessFactory.eINSTANCE.createCompositeType();
		nodes.getComposite().add(flowState);
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		flowState.setName(name);
		setBounds(i, flowState, nakedUmlId);
		flowState.setHeight("500");
		flowState.setWidth("500");
		return flowState;
	}

	protected EndType addFinalNode(NodesType nodes, int i, String name, int nakedUmlId) {
		EndType endNode = ProcessFactory.eINSTANCE.createEndType();
		endNode.setName(name);
		endNode.setTerminate("false");
		nodes.getEnd().add(endNode);
		setBounds(i, endNode, nakedUmlId);
		return endNode;
	}

	protected StateType addState(NodesType nodes, int i, String name, Integer nakedUmlId) {
		StateType node = ProcessFactory.eINSTANCE.createStateType();
		node.setName(name);
		nodes.getState().add(node);
		setBounds(i, node, nakedUmlId);
		return node;
	}

	protected SplitType addFork(NodesType nodes, int i, String name, Integer nakedUmlId) {
		SplitType split = addSplitType(nodes, i, name, nakedUmlId);
		split.setType("1");
		return split;
	}

	protected SplitType addChoice(NodesType nodes, int i, String name, Integer nakedUmlId) {
		SplitType split = addSplitType(nodes, i, name, nakedUmlId);
		split.setType("2");
		return split;
	}

	private SplitType addSplitType(NodesType nodes, int i, String name, Integer nakedUmlId) {
		SplitType split = ProcessFactory.eINSTANCE.createSplitType();
		nodes.getSplit().add(split);
		split.setName(name);
		setBounds(i, split, nakedUmlId);
		return split;
	}

	protected final void createConnection(ConnectionsType connections, int node1, int node2) {
		ConnectionType startConn = ProcessFactory.eINSTANCE.createConnectionType();
		startConn.setFromType("DROOLS_DEFAULT");
		startConn.setFrom("" + node1);
		startConn.setTo("" + node2);
		connections.getConnection().add(startConn);
	}

	protected StartType addInitialNode(NodesType nodesType, int i, String name, int nakedUmlId) {
		StartType node1 = ProcessFactory.eINSTANCE.createStartType();
		node1.setName(name);
		setBounds(i, node1, nakedUmlId);
		nodesType.getStart().add(node1);
		return node1;
	}

	protected void addConstraintsToSplit(SplitType split, Collection<? extends GuardedFlow> outgoing) {
		ConstraintsType constraints = ProcessFactory.eINSTANCE.createConstraintsType();
		split.getConstraints().add(constraints);
		for (GuardedFlow t : outgoing) {
			ConstraintType constraint = ProcessFactory.eINSTANCE.createConstraintType();
			constraint.setDialect("mvel");
			constraint.setToNodeId(this.targetIdMap.get(t.getTarget()) + "");
			if (t.getGuard() == null) {
				constraint.setValue("return true;");
				constraint.setPriority("3");
			} else {
				if (t.getGuard().isOclValue()) {
					constraint.setValue("return processObject." + BpmUtil.getGuardMethod(t) + "(context);");
					constraint.setPriority("1");
				} else if (t.getGuard().getValue() instanceof Boolean) {
					constraint.setValue("return " + t.getGuard().getValue() + ";");
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

	@Override
	public final Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		return root.getOwnedElements();
	}
}
