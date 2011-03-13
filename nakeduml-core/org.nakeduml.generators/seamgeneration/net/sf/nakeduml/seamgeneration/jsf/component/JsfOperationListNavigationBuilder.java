package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;

import org.ajax4jsf.component.AjaxActionComponent;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.component.html.HtmlFragment;

import com.sun.faces.el.ELContextImpl;

public class JsfOperationListNavigationBuilder implements IJsfLinkBuilder {
	//TODO duplicated
	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private DomainClassifier dc;
	private OperationNavigation n;
	
	public JsfOperationListNavigationBuilder(DomainClassifier dc, OperationNavigation n) {
		this.dc=dc;
		this.n=n;
		
	}

	@Override
	public HtmlFragment createUILink() {
		HtmlFragment htmlFragment = new HtmlFragment();
		AjaxActionComponent viewLink = createComponent();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createOperationNavigationValueExpression(dc, n), void.class);
		viewLink.setValue(ve);
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(new ELContextImpl(null), createNavigationActionExpression(dc, n), void.class,
				new Class[] {});
		viewLink.setActionExpression(me);

		me = SeamExpressionFactory.INSTANCE.createMethodExpression(new ELContextImpl(null), createNavigationActionListernerExpression(dc, n), void.class,
				new Class[] {});
		ActionListener listener = new MethodExpressionActionListener(me);
		viewLink.addActionListener(listener);
		
		addParameter(viewLink, "navigateTo", n.getName());
		viewLink.setAjaxSingle(true);
		setSettedAttributes(viewLink, "value", "action", "ajaxSingle");
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createNavigationRenderedExpression(dc, n), void.class);
		HtmlDecorate htmlDecorate = addDecoration(viewLink, getDisplayTemplate(), ve, n);
		htmlFragment.getChildren().add(htmlDecorate);
		return htmlFragment;
	}	
	
	//TODO refactor, duplicated
	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	//TODO check duplication
	protected void addParameter(UICommand button, String name, String value) {
		UIParameter parameter = new UIParameter();
		parameter.setName(name);
		if (value.startsWith("#{")) {
			ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), value, Object.class);
			parameter.setValue(ve);
		} else {
			parameter.setValue(value);
		}
		setSettedAttributes(parameter, "name", "value");
		button.getChildren().add(parameter);
	}	
	
	@Override
	public AjaxActionComponent createComponent() {
		return new HtmlAjaxCommandLink();
	}
	
	protected HtmlDecorate addDecoration(UICommand uiCommand, String template, ValueExpression ve, OperationNavigation n) {
		HtmlDecorate htmlDecorate = new HtmlDecorate();
		htmlDecorate.setTemplate(template);
		setSettedAttributes(htmlDecorate, "template");
		htmlDecorate.setValueExpression("rendered", ve);
		htmlDecorate.getChildren().add(uiCommand);
		return htmlDecorate;
	}

	protected String createNavigationActionExpression(DomainClassifier dc, OperationNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append("objectVar");
		eb.append(".");
		eb.append(n.getName());
		eb.append("()");
		return eb.toString();
	}
	
	protected String createNavigationActionListernerExpression(DomainClassifier dc, OperationNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append("crudController.outjectCompositionOwner(objectVar)");
		return eb.toString();
	}	

	protected String createNavigationRenderedExpression(DomainClassifier dc, OperationNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (n.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("objectVar");
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append("objectVar");
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append("objectVar");
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.toString();
	}

	
	private String createUpdateRenderedExpression(DomainClassifier dc, String securedObject) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
					&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
					&& !dc.getSecurityOnView().getRequiresGroupOwnership()
					&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}		
		return eb.getString();
	}
	
	
	protected String createOperationNavigationValueExpression(DomainClassifier dc, OperationNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance(); 
		eb.append("messages['");
		eb.append(n.getName());
		eb.append("']");
		return eb.toString();
	}

	protected String getDisplayTemplate() {
		return "/layout/displayInsideTable.xhtml";
	}

}
