package net.sf.nakeduml.jbpm5;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.HeaderType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.ProcessType;
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
	TextWorkspace textWorkspace;
	private INakedModelWorkspace workspace;

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
		processObjectType.setName("org.jbpm.process.core.datatype.impl.type.ObjectDataType");
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

	protected final void createArtificialJoin(NodesType nodes, ConnectionsType connections, int i, INakedState state) {
		JoinType join = ProcessFactory.eINSTANCE.createJoinType();
		join.setType("2");
		join.setName("JoinFor" + state.getMappingInfo().getPersistentName());
		setBounds(i, join);
		nodes.getJoin().add(join);
		ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
		connection.setFrom(i + "");
		connection.setTo((i + 1) + "");
		connections.getConnection().add(connection);
	}



	protected final void addStartNode(NodesType nodes, int i, INakedElement state) {
		StartType node = ProcessFactory.eINSTANCE.createStartType();
		node.setName(state.getMappingInfo().getPersistentName().getAsIs());
		setBounds(i, node);
		nodes.getStart().add(node);
	}

	protected final void setBounds(int i, Object flowState) {
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(flowState.getClass()).getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals("id")) {
					pd.getWriteMethod().invoke(flowState, "" + i++);
				} else if (pd.getName().equals("height")) {
					pd.getWriteMethod().invoke(flowState, "50");
				} else if (pd.getName().equals("width")) {
					pd.getWriteMethod().invoke(flowState, "100");
				} else if (pd.getName().equals("x")) {
					pd.getWriteMethod().invoke(flowState, (i * 50) + "");
				} else if (pd.getName().equals("y")) {
					pd.getWriteMethod().invoke(flowState, (i * 100) + "");
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

	@Override
	public final Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		return root.getOwnedElements();
	}
}
