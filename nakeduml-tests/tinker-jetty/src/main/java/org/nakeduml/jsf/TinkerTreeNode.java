package org.nakeduml.jsf;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class TinkerTreeNode extends DefaultTreeNode {

	private Long id;
	
	public TinkerTreeNode() {
		super();
	}

	public TinkerTreeNode(Long id, String display, TreeNode parent) {
		super(display, parent);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
