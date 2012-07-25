package org.opaeum.jbpm5;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.visitor.TextFileGeneratingVisitor;

public class AbstractFlowStep extends TextFileGeneratingVisitor  implements ITransformationStep {
	public static final String JBPM_PROCESS_EXTENSION = "rf";
	protected Stack<Map<Element, Long>> targetIdMap=new Stack<Map<Element,Long>>();
	protected Stack<Map<Element, Long>> sourceIdMap=new Stack<Map<Element,Long>>();
	protected OpaeumConfig config;


	public void initialize(OpaeumConfig config, TextWorkspace textWorkspace, EmfWorkspace workspace) {
		super.textWorkspace = textWorkspace;
		super.workspace = workspace;
		super.config = config;
		super.textFiles=new HashSet<TextOutputNode>();
	}

	protected DocumentRoot createRoot(NamedElement behavior) {
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
		String qualifiedJavaName = OJUtil.classifierPathname(behavior).toJavaString();
		VariablesType variables = ProcessFactory.eINSTANCE.createVariablesType();
		header.getVariables().add(variables);
		createVariable(variables, variableName, qualifiedJavaName);
		root.getProcess().getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		root.getProcess().getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		root.getProcess().setId(Jbpm5Util.generateProcessName(behavior));
		root.getProcess().setName(Jbpm5Util.generateProcessName(behavior));
		root.getProcess().setPackageName(OJUtil.packagePathname(behavior.getNamespace()).toJavaString());
		root.getProcess().setVersion("" + workspace.getWorkspaceMappingInfo().getVersion().toVersionString());
		root.getProcess().setType("RuleFlow");
		List<String> names = OJUtil.packagePathname(behavior.getNamespace()).getCopy().getNames();
		names.add(behavior.getName() + ".rf");
		TextFile textFile = createTextPath(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE,names);
		textFile.setTextSource(new EmfTextSource(r, "process"));
		return root;
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

	protected final void addStartNode(NodesType nodes, int i, Element state) {
		StartType node = ProcessFactory.eINSTANCE.createStartType();
		node.setName(PersistentNameUtil.getPersistentName( state).getAsIs());
		setBounds(i, node, EmfWorkspace.getOpaeumId(state));
		nodes.getStart().add(node);
	}

	protected final void setBounds(int i, Object flowState, Long nakedUmlId) {
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


	protected JoinType addJoin(NodesType nodes, int i, String name, Long nakedUmlId) {
		JoinType join = addJoinType(nodes, i, name, nakedUmlId);
		join.setType("1");
		return join;
	}

	protected JoinType addMerge(NodesType nodes, int i, String name, Long nakedUmlId) {
		JoinType join = addJoinType(nodes, i, name, nakedUmlId);
		join.setType("2");
		return join;
	}

	private JoinType addJoinType(NodesType nodes, int i, String name, Long nakedUmlId) {
		JoinType join = ProcessFactory.eINSTANCE.createJoinType();
		join.setName(name);
		setBounds(i, join, nakedUmlId);
		nodes.getJoin().add(join);
		return join;
	}

	protected CompositeType createCompositeState(NodesType nodes, int i, String name, Long nakedUmlId) {
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
	protected DynamicType createDynamicState(NodesType nodes, int i, String name, Long nakedUmlId) {
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

	protected EndType addFinalNode(NodesType nodes, int i, String name, Long nakedUmlId) {
		EndType endNode = ProcessFactory.eINSTANCE.createEndType();
		endNode.setName(name);
		endNode.setTerminate("false");
		nodes.getEnd().add(endNode);
		setBounds(i, endNode, nakedUmlId);
		return endNode;
	}

	protected StateType addState(NodesType nodes, int i, String name, Long nakedUmlId) {
		StateType node = ProcessFactory.eINSTANCE.createStateType();
		node.setName(name);
		nodes.getState().add(node);
		setBounds(i, node, nakedUmlId);
		return node;
	}

	protected SplitType addFork(NodesType nodes, int i, String name, Long nakedUmlId) {
		SplitType split = addSplitType(nodes, i, name, nakedUmlId);
		split.setType("1");
		return split;
	}

	protected SplitType addChoice(NodesType nodes, int i, String name, Long nakedUmlId) {
		SplitType split = addSplitType(nodes, i, name, nakedUmlId);
		split.setType("2");
		return split;
	}

	private SplitType addSplitType(NodesType nodes, int i, String name, Long nakedUmlId) {
		SplitType split = ProcessFactory.eINSTANCE.createSplitType();
		nodes.getSplit().add(split);
		split.setName(name);
		setBounds(i, split, nakedUmlId);
		return split;
	}

	protected final void createConnection(ConnectionsType connections, Long node1, Long node2) {
		ConnectionType startConn = ProcessFactory.eINSTANCE.createConnectionType();
		startConn.setFromType("DROOLS_DEFAULT");
		startConn.setFrom("" + node1);
		startConn.setTo("" + node2);
		connections.getConnection().add(startConn);
	}

	protected StartType addInitialNode(NodesType nodesType, int i, String name, Long nakedUmlId) {
		StartType node1 = ProcessFactory.eINSTANCE.createStartType();
		node1.setName(name);
		setBounds(i, node1, nakedUmlId);
		nodesType.getStart().add(node1);
		return node1;
	}


	@Override
	public final Collection<Element> getChildren(Element root) {
		if (root instanceof ModelWorkspace) {
			return new ArrayList<Element>(((ModelWorkspace) root).getGeneratingModelsOrProfiles());
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

	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
}
