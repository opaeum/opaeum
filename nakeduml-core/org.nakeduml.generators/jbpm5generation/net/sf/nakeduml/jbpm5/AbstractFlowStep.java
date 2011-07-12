package net.sf.nakeduml.jbpm5;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActivityNodeMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ConstraintsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.DynamicType;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class AbstractFlowStep extends VisitorAdapter<INakedElementOwner, INakedModelWorkspace> implements TransformationStep {
	public static final String JBPM_PROCESS_EXTENSION = "rf";
	protected TextWorkspace textWorkspace;
	protected INakedModelWorkspace workspace;
	protected Map<INakedElement, Integer> targetIdMap;
	protected Map<INakedElement, Integer> sourceIdMap;
	protected NakedUmlConfig config;
	private INakedRootObject currentModelOrProfile;


	@Override
	public void visitRecursively(INakedElementOwner o) {
		if(o instanceof INakedRootObject){
			this.currentModelOrProfile=(INakedRootObject) o;
		}
		super.visitRecursively(o);
	}

	public void initialize(NakedUmlConfig config, TextWorkspace textWorkspace, INakedModelWorkspace workspace) {
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
		this.config = config;
	}

	protected DocumentRoot createRoot(INakedBehavior behavior) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(JBPM_PROCESS_EXTENSION, new ProcessResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(ProcessPackage.eNS_URI, ProcessPackage.eINSTANCE);
		Resource r = resourceSet.createResource(URI.createFileURI("temp.rf"));
		DocumentRoot root = ProcessFactory.eINSTANCE.createDocumentRoot();
		root.setProcess(ProcessFactory.eINSTANCE.createProcessType());
		r.getContents().add(root);
		ProcessType process = root.getProcess();
		HeaderType header = ProcessFactory.eINSTANCE.createHeaderType();
		process.getHeader().add(header);
		String variableName = "processObject";
		String qualifiedJavaName = behavior.getMappingInfo().getQualifiedJavaName();
		VariablesType variables = ProcessFactory.eINSTANCE.createVariablesType();
		header.getVariables().add(variables);
		createVariable(variables, variableName, qualifiedJavaName);
		root.getProcess().getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		root.getProcess().getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		root.getProcess().setId(Jbpm5Util.generateProcessName(behavior));
		root.getProcess().setName(Jbpm5Util.generateProcessName(behavior));
		root.getProcess().setPackageName(behavior.getNameSpace().getMappingInfo().getQualifiedJavaName());
		root.getProcess().setVersion("" + workspace.getWorkspaceMappingInfo().getCurrentVersion());
		root.getProcess().setType("RuleFlow");
		
		OutputRoot outputRoot = config.getOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = OJUtil.packagePathname(behavior.getNameSpace()).getNames();
		names.add(behavior.getMappingInfo().getJavaName() + ".rf");
		or.findOrCreateTextFile(names, new EmfTextSource(r, "process"), outputRoot.overwriteFiles());
		return root;
	}
	protected SourceFolder getSourceFolder(OutputRoot outputRoot) {
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getIdentifier() : currentModelOrProfile
				.getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}

	protected void createVariable(VariablesType variables, String variableName, String qualifiedJavaName) {
		VariableType processObject = ProcessFactory.eINSTANCE.createVariableType();
		variables.getVariable().add(processObject);
		processObject.setName(variableName);
		TypeType processObjectType = ProcessFactory.eINSTANCE.createTypeType();
		processObjectType.setClassName(qualifiedJavaName);
		final String PKG = "drools";
		if (qualifiedJavaName.equals("java.lang.String")) {
			processObjectType.setName("org." + PKG + ".process.core.datatype.impl.type.StringDataType");
		} else if (qualifiedJavaName.equalsIgnoreCase("java.lang.Boolean")) {
			processObjectType.setName("org." + PKG + ".process.core.datatype.impl.type.BooleanDataType");
		} else if (qualifiedJavaName.equalsIgnoreCase("java.lang.Integer")) {
			processObjectType.setName("org." + PKG + ".process.core.datatype.impl.type.IntegerDataType");
		} else {
			processObjectType.setName("org." + PKG + ".process.core.datatype.impl.type.ObjectDataType");
		}
		processObject.getType().add(processObjectType);
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
	protected DynamicType createDynamicState(NodesType nodes, int i, String name, Integer nakedUmlId) {
		DynamicType flowState = ProcessFactory.eINSTANCE.createDynamicType();
		nodes.getDynamic().add(flowState);
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

	protected void addConstraintsToSplit(SplitType split, Collection<? extends GuardedFlow> outgoing, boolean passContext) {
		ConstraintsType constraints = ProcessFactory.eINSTANCE.createConstraintsType();
		split.getConstraints().add(constraints);
		for (GuardedFlow t : outgoing) {
			ConstraintType constraint = ProcessFactory.eINSTANCE.createConstraintType();
			constraint.setDialect("mvel");
			Integer toNodeId = this.targetIdMap.get(t.getEffectiveTarget());
			constraint.setToNodeId(toNodeId + "");
			if (t.getGuard() == null) {
				constraint.setValue("return true;");
				constraint.setPriority("3");
			} else {
				if (t.getGuard().isOclValue()) {
					String param = passContext ? "context" : "";
					constraint.setValue("return processObject." + Jbpm5Util.getGuardMethod(t) + "(" + param + ");");
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
		if (root instanceof INakedModelWorkspace) {
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		} else {
			return root.getOwnedElements();
		}
	}

	protected final ActionType createAction(String methodName, EList<ActionType> action, boolean passContext) {
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		String string = passContext ? "context" : "";
		entryAction.setValue("processObject." + methodName + "(" + string + ")");
		return entryAction;
	}
}
