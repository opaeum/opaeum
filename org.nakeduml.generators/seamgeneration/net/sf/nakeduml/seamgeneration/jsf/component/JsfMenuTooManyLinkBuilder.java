package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.el.ValueExpression;
import javax.faces.component.UICommand;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.ajax4jsf.component.AjaxActionComponent;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
import org.jboss.seam.ui.component.html.HtmlDecorate;

public class JsfMenuTooManyLinkBuilder extends AbstractJsfLinkBuilder {

	
	public JsfMenuTooManyLinkBuilder(DomainClassifier dc, PropertyNavigation n) {
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
			eb.append("crudController.outjectCompositionOwnerOfTooMany(");
			eb.append(NameConverter.decapitalize(dc.getName()));
			eb.append(", '");
			eb.append(NameConverter.decapitalize(n.getResultingUserInteraction().getClassifier().getQualifiedImplementationType()));
			eb.append("'");
			eb.append(")");
		}
		return eb.toString();
	}

	@Override
	protected String createNavigationRenderedExpression(DomainClassifier dc, PropertyNavigation n) {
		
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (n.getResultingUserInteraction().getUserInteractionKind()==UserInteractionKind.EDIT) {
			eb.append("(not (");
			eb.append(NameConverter.decapitalize(dc.getName()));
			eb.append(".");
			eb.append(NameConverter.decapitalize(n.getName()));
			eb.append(" eq null)) and ");
		}
		
		eb.append("true");
		
//		if (n.getSecurityOnView().getRequiresUserOwnership()) {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isUserOwnershipValidFor");
//			eb.append(NameConverter.capitalize(n.getProperty().getName()));
//			eb.append("(nakedUser)");			
//		} else if (n.getSecurityOnView().getRequiresGroupOwnership()) {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isGroupOwnershipValidFor");
//			eb.append(NameConverter.capitalize(n.getProperty().getName()));
//			eb.append("(nakedUser)");			
//		} else if (!n.getSecurityOnView().getRequiresGroupOwnership()) {
//			eb.append("true");
//		} else {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isGroupOwnershipValidFor");
//			eb.append(NameConverter.capitalize(n.getProperty().getName()));
//			eb.append("(nakedUser)");			
//		}
		return eb.toString();
	}
	
	@Override
	protected String createNavigationValueExpression(DomainClassifier dc, PropertyNavigation n) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append("messages['");
		eb.append(n.getName());
		eb.append("']");
		return eb.toString();
	}

	@Override
	protected String getDisplayTemplate() {
		return "/layout/menu-item.xhtml";
	}
	
}
