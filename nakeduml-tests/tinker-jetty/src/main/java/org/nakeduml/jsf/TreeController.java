package org.nakeduml.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.environment.ContextRoot;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.primefaces.component.behavior.ajax.AjaxBehavior;
import org.primefaces.component.tree.Tree;
import org.primefaces.component.tree.UITreeNode;
import org.primefaces.model.TreeNode;
import org.tinker.Angel;
import org.tinker.Demon;
import org.tinker.God;
import org.tinker.Universe;

@Named("treeController")
@ConversationScoped
public class TreeController implements Serializable {

	TreeNode root;
	TreeNode selectedNode;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	@ContextRoot
	God god;
	@Inject
	ContextController contextController;
	Tree tree;

	@PostConstruct
	public void init() {
		root = new TinkerTreeNode(-1L, "root", null);
		buildTree(root, god);
		buildTreeComponent();
	}
	
	public void setTree(Tree tree) {
		this.tree= tree; 
	}
	
	public Tree getTree() {
		return tree;
	}	

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public void onNodeSelect() {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        TinkerTreeNode tinkerTreeNode = (TinkerTreeNode)selectedNode;
		AbstractEntity abstractEntity = db.instantiateClassifier(tinkerTreeNode.getId());
        contextController.setContextObject(abstractEntity);
    } 	

	private void buildTree(TreeNode root, God god) {
		TreeNode godNode = new TinkerTreeNode(god.getId(), god.getName(), root);
		for (Universe universe : god.getUniverse()) {
			TreeNode universeNode = new TinkerTreeNode(universe.getId(), universe.getName(), godNode);
		}
		for (Demon demon : god.getDemon()) {
			TreeNode universeNode = new TinkerTreeNode(demon.getId(), demon.getName(), godNode);
		}
		for (Angel angel : god.getAngels()) {
			TreeNode universeNode = new TinkerTreeNode(angel.getId(), angel.getName(), godNode);
		}
	}
	
	private void buildTreeComponent() {
		tree = new Tree();
		ExpressionFactory f = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		tree.setValueExpression("value", f.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{treeController.root}", TreeNode.class));
		tree.setValueExpression("selection", f.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{treeController.selectedNode}", TreeNode.class));
		tree.setVar("node");
		tree.setSelectionMode("single");
		
		UITreeNode uiTreeNode = new UITreeNode();
		tree.getChildren().add(uiTreeNode);
		
		AjaxBehavior ajaxBehavior = new AjaxBehavior();
		ajaxBehavior.setListener(f.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), "#{treeController.onNodeSelect}", null, new Class<?>[]{}));
		ajaxBehavior.setUpdate("messages,:centerForm:contextDataTablePanelGroupId");
		tree.addClientBehavior("select", ajaxBehavior);
		
		HtmlOutputText outputText = new HtmlOutputText();
		uiTreeNode.getChildren().add(outputText);
		outputText.setValueExpression("value", f.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{node}", String.class));		
	}

}
