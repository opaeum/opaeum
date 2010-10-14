package net.sf.nakeduml.jsf2generation.component;

import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectMany;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.dummy.UIFragment;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.EntityConverter;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.component.html.HtmlSelectItems;
import org.jboss.seam.ui.converter.EnumConverter;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsf2InputBuilder extends AbstractJsf2ComponentBuilder {

	public AbstractJsf2InputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	
	@Override
	public UIFragment createUIComponent() {
		UIFragment htmlFragment = new UIFragment();
		UIInput uiInput = (UIInput)createComponent();
		setUpInput(dc, pf, uiInput);
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createEditPropertyRenderedExpression(dc, pf), void.class);
		HtmlDecorate htmlDecorate = addDecoration(uiInput, getEditTemplate(), ve, pf);
		htmlFragment.getChildren().add(htmlDecorate);
		
		UIOutput uiOutput = new HtmlOutputText();
		setUpOutputForInput(dc, pf, uiOutput);
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createViewPropertyRenderedExpression(dc, pf), void.class);
		htmlDecorate = addDecoration(uiOutput, getEditTemplate(), ve, pf);
		htmlFragment.getChildren().add(htmlDecorate);
		return htmlFragment;
	}
	
	private void setUpOutputForInput(DomainClassifier dc, PropertyField pf, UIOutput uiOutput) {
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createPropertyValueExpression(dc, pf), Object.class);
		uiOutput.setValue(ve);
		setSettedAttributes(uiOutput, "value");
	}
	
	private UIInput setUpInput(DomainClassifier dc, PropertyField pf, UIInput uiInput) {
		if (uiInput instanceof UISelectMany) {
			UISelectMany uiSelectMany = (UISelectMany) uiInput;
			HtmlSelectItems htmlSelectItems = new HtmlSelectItems();
			StringBuilder sb = new StringBuilder();
			if (pf.getTypedElement().getType().getClassifierKind() == ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind() == ClassifierKind.INTERFACE) {
				sb.append("#{");
				sb.append(getEditRenderedRoot(dc));
				sb.append(".");
				sb.append(pf.getTypedElement().getName());
				sb.append("SourcePopulation}");
				uiSelectMany.setConverter(new EntityConverter());
			} else if (pf.getTypedElement().getType().getClassifierKind() == ClassifierKind.ENUMERATION) {
				sb.append("#{");
				sb.append(pf.getTypedElement().getName());
				sb.append("Factory}");
				uiSelectMany.setConverter(new EnumConverter());
			} else {
				// TODO
			}
			ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), sb.toString(), Object.class);
			htmlSelectItems.setValueExpression("value", ve);
			htmlSelectItems.setVar(pf.getTypedElement().getName());
			htmlSelectItems.setLabel("#{" + pf.getTypedElement().getName() + ".name}");
			htmlSelectItems.setNoSelectionLabel("Please select");
			setSettedAttributes(htmlSelectItems, "value", "var", "label", "noSelectionLabel");
			uiSelectMany.getChildren().add(htmlSelectItems);
		}
		uiInput.setRequired(pf.getParticipationKind() == TypedElementParticipationKind.REQUIRED);
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createPropertyValueExpression(dc, pf), Object.class);
		uiInput.setValue(ve);
		setSettedAttributes(uiInput, "value");
		return uiInput;
	}
	
	private String createEditPropertyRenderedExpression(DomainClassifier dc, PropertyField pf) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
//			if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresGroupOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isGroupOwnershipValid(nakedUser)");
//			} else {
//				eb.append("true");
//			}
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
//			if (!dc.getSecurityOnEdit().getRequiresUserOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isUserOwnershipValid(nakedUser)");
//			} else {
//				eb.append("true");
//			}
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
//			if (!dc.getSecurityOnEdit().getRequiresUserOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isUserOwnershipValid(nakedUser)");
//			} else {
//				eb.append("true");
//			}
		}
		if (!pf.getSecurityOnEdit().getRequiredRoles().isEmpty()) {
			eb.append(" and ");
			eb.append(rolesToString(pf.getSecurityOnEdit().getRequiredRoles()));
		}
		return eb.toString();
	}

	private String createViewPropertyRenderedExpression(DomainClassifier dc, PropertyField pf) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
			eb.append(" and ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
			eb.append(" and ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		}
		if (!pf.getSecurityOnView().getRequiredRoles().isEmpty()) {
			eb.append(" and ");
			eb.append(rolesToString(pf.getSecurityOnView().getRequiredRoles()));
		}
		return eb.toString();
	}

	private String rolesToString(Set<String> roles) {
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (String role : roles) {
			cnt++;
			sb.append("s:hasRole('");
			sb.append(role);
			sb.append("')");
			if (cnt!=roles.size()) {
				sb.append(" and ");
			}
		}
		return sb.toString();
	}
	
}
