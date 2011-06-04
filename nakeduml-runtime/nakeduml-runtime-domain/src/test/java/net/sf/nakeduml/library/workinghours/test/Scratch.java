package net.sf.nakeduml.library.workinghours.test;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Scratch{
	{
		UmlNodeInstance f = null;
		NodeInstanceContainer nodeInstanceContainer = (NodeInstanceContainer) f.getNodeInstanceContainer();
		NodeContainer nodeContainer=(NodeContainer) nodeInstanceContainer.getNodeContainer();
		nodeInstanceContainer.getNodeInstance(nodeContainer.getNode(1)).trigger(f, Node.CONNECTION_DEFAULT_TYPE);
		WorkflowProcess p=null;
	}
}
