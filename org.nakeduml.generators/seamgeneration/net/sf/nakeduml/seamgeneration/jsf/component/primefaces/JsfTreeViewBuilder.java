package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.seamgeneration.jsf.component.AbstractJsfComponentBuilder;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.tree.Tree;
import org.primefaces.component.tree.UITreeNode;

import com.sun.faces.el.ELContextImpl;

public class JsfTreeViewBuilder extends AbstractJsfComponentBuilder {

	public JsfTreeViewBuilder() {
	}
	
	protected UIComponent createComponent() {
		return new Tree();
	}

	@Override
	public HtmlFragment createUIComponent() {
		HtmlFragment htmlFragment = new HtmlFragment();
		Tree tree = (Tree)createComponent();
		
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), "#{rootHierarchy}", void.class);
		tree.setValueExpression("value", ve);
		tree.setVar("node");
		setSettedAttributes(tree, "value", "var");
		
		UITreeNode treeNode = new UITreeNode();
		
//		HtmlOutputText htmlOutputText = new HtmlOutputText();
//		htmlOutputText.setValue("#{node.name}");
//		setSettedAttributes(htmlOutputText, "value");
//		treeNode.getChildren().add(htmlOutputText);

		CommandLink commandLink = new CommandLink();
		commandLink.setValue("#{node.name}");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(new ELContextImpl(null), "#{crudController.outjectCompositionOwner(var)}", void.class,
				new Class[] {});
		commandLink.setActionExpression(me);		
		setSettedAttributes(commandLink, "value", "actionExpression");
		treeNode.getChildren().add(commandLink);
		
		tree.getChildren().add(treeNode);
		
		htmlFragment.getChildren().add(tree);
		return htmlFragment;
	}

}
