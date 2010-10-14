package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.el.ValueExpression;
import javax.faces.component.UICommand;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.ajax4jsf.component.AjaxActionComponent;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
import org.jboss.seam.ui.component.html.HtmlDecorate;

public class JsfListNavigationBuilder extends AbstractJsfLinkBuilder {

	public JsfListNavigationBuilder(DomainClassifier dc, PropertyNavigation n) {
		super(dc, n);
	}

	@Override
	public AjaxActionComponent createComponent() {
		return new HtmlAjaxCommandLink();
	}
	
	@Override
	protected HtmlDecorate addDecoration(UICommand uiCommand, String template, ValueExpression ve, PropertyNavigation n) {
		HtmlDecorate htmlDecorate = new HtmlDecorate();
		htmlDecorate.setTemplate(template);
		setSettedAttributes(htmlDecorate, "template");
		htmlDecorate.setValueExpression("rendered", ve);
		htmlDecorate.getChildren().add(uiCommand);
		return htmlDecorate;
	}

	@Override
	protected String createNavigationActionExpression(DomainClassifier dc, PropertyNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (n.getResultingUserInteraction().getUserInteractionKind()==UserInteractionKind.LIST) {
			eb.append(n.getResultingUserInteraction().getName());
			return eb.getString();
		} else {
			eb.append("crudController.outjectCompositionOwner(");
			eb.append("objectVar.");
			eb.append(n.getProperty().getName());
			eb.append(")");
			return eb.toString();
		}
	}

	@Override
	protected String createNavigationRenderedExpression(DomainClassifier dc, PropertyNavigation n) {
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
		
//		eb.append(" and ");
//		String expression = createUpdateRenderedExpression(
//				n.getResultingUserInteraction().getClassifier(),
//				NameConverter.decapitalize(n.getName())
//			);
//		if (!expression.equals("true") && !expression.equals("false")) {
//			eb.append(" objectVar.");
//		}
//		eb.append(expression);
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
	
	
	@Override
	protected String createNavigationValueExpression(DomainClassifier dc, PropertyNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance(); 
		if (n.getResultingUserInteraction().getUserInteractionKind()==UserInteractionKind.LIST) {
			eb.append("'");
			eb.append(n.getName());
			eb.append("'");
		} else {
			eb.append("objectVar");
			eb.append(".");
			eb.append(n.getRepresentedElement().getName());
			eb.append(".name");
		}
		return eb.toString();
	}

	@Override
	protected String getDisplayTemplate() {
		return "/layout/displayInsideTable.xhtml";
	}

}
