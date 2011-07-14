package org.nakeduml.tree;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.environment.ContextRoot;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.rorotika.cm.core.CmApplication;
import com.rorotika.cm.core.Hierarchy;
import com.rorotika.cm.core.network.Group;
import com.rorotika.cm.core.network.Network;
import com.rorotika.cm.core.network.NetworkSoftwareVersion;

@Named("treeController")
@ConversationScoped
public class TreeController implements Serializable {

	private TreeNode root;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	@ContextRoot
	CmApplication cmApplication;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode(cmApplication.getName(), null);
		buildTree(root, cmApplication);
		// for (Group group : cmApplication.getGroup()) {
		// TreeNode groupNode = new DefaultTreeNode(group.getName(), root);
		// for (Network network : group.getNetwork()) {
		// TreeNode networkNode = new DefaultTreeNode(network.getName(),
		// groupNode);
		// for (NetworkSoftwareVersion nsv :
		// network.getNetworkSoftwareVersion()) {
		// TreeNode nsvNode = new DefaultTreeNode(nsv.getName(), networkNode);
		// }
		// }
		// }
	}

	private void buildTree(TreeNode parentNode, Hierarchy hierarchy) {
		TreeNode node = new DefaultTreeNode(((AbstractEntity) hierarchy).getName(), parentNode);
		for (Hierarchy h : hierarchy.getChildren()) {
			buildTree(node, h);
		}
	}

	public TreeNode getModel() {
		return root;
	}

}
