package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectMany;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.EntityConverter;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.jboss.seam.ui.component.html.HtmlSelectItems;
import org.jboss.seam.ui.converter.EnumConverter;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsfInputBuilder extends AbstractJsfComponentBuilder {

	public AbstractJsfInputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}

	@Override
	public HtmlFragment createUIComponent() {
		HtmlFragment htmlFragment = new HtmlFragment();

		UIInput uiInput = (UIInput) createComponent();
		setUpInput(dc, pf, uiInput);

		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createEditPropertyRenderedExpression(dc, pf),
				void.class);
		HtmlDecorate htmlDecorate = addDecoration(uiInput, getEditTemplate(), ve, pf);

		uiInput.setId(this.dc.getName() + "_" + this.pf.getName());
		setSettedAttributes(uiInput, "id");

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

	protected UIInput setUpInput(DomainClassifier dc, PropertyField pf, UIInput uiInput) {
		if (uiInput instanceof UISelectMany) {
			UISelectMany uiSelectMany = (UISelectMany) uiInput;
			HtmlSelectItems htmlSelectItems = new HtmlSelectItems();
			StringBuilder sb = new StringBuilder();
			if (pf.getTypedElement().getType().getClassifierKind() == ClassifierKind.ENTITY
					|| pf.getTypedElement().getType().getClassifierKind() == ClassifierKind.INTERFACE) {
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
		uiInput.setRequired(isRequired(pf));
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createPropertyValueExpression(dc, pf), Object.class);
		uiInput.setValue(ve);
		setSettedAttributes(uiInput, "value", "required");
		return uiInput;
	}

	protected boolean isRequired(PropertyField pf) {
		return pf.getParticipationKind() == TypedElementParticipationKind.REQUIRED;
	}

	protected String createEditPropertyRenderedExpression(DomainClassifier dc, PropertyField pf) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnEdit().getRequiredRoles().isEmpty()) {
			eb.append(rolesToString(pf.getSecurityOnEdit().getRequiredRoles()));
		} else {
			if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
				eb.append("true");
			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isGroupOwnershipValid(nakedUser)");
			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isUserOwnershipValid(nakedUser)");
			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
				eb.append(getEditRenderedRoot(dc));
				eb.append(".isUserOwnershipValid(nakedUser)");
			}
			if (!pf.getSecurityOnEdit().getRequiredRoles().isEmpty()) {
				eb.append(" and ");
				eb.append(rolesToString(pf.getSecurityOnEdit().getRequiredRoles()));
			}
		}
		return eb.toString();
	}

	private String createViewPropertyRenderedExpression(DomainClassifier dc, PropertyField pf) {
		boolean editRequiresRoles = !pf.getSecurityOnEdit().getRequiredRoles().isEmpty();
		
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnView().getRequiredRoles().isEmpty()) {
			eb.append(rolesToString(pf.getSecurityOnView().getRequiredRoles()));
		} else {
			if (editRequiresRoles) {
				eb.append("not ");
				eb.append(rolesToString(pf.getSecurityOnEdit().getRequiredRoles()));
				eb.append(" and ");
				if (pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
				} else if (pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isGroupOwnershipValid(nakedUser)");
				} else if (!pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
				} else {
					eb.append("true");
				}
			} else {
				if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("not ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isGroupOwnershipValid(nakedUser)");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("not ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("not ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("not ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
					eb.append(" and ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isGroupOwnershipValid(nakedUser)");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("not ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isUserOwnershipValid(nakedUser)");
					eb.append(" and ");
					eb.append(getEditRenderedRoot(dc));
					eb.append(".isGroupOwnershipValid(nakedUser)");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
						&& pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
					eb.append("false");
				}
			}
		}
		return eb.toString();
	}
	
//	private String createViewPropertyRenderedExpression2(DomainClassifier dc, PropertyField pf) {
//		boolean editRequiresRoles = pf.getSecurityOnEdit().getRequiredRoles().isEmpty();
//		
//		ExpressionBuilder eb = ExpressionBuilder.instance();
//		if (!pf.getSecurityOnView().getRequiredRoles().isEmpty()) {
//			eb.append(rolesToString(pf.getSecurityOnView().getRequiredRoles()));
//		} else {
//
//			if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& !pf.getSecurityOnView().getRequiresGroupOwnership() && !pf.getSecurityOnView().getRequiresUserOwnership()) {
//			} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()
//					&& pf.getSecurityOnView().getRequiresGroupOwnership() && pf.getSecurityOnView().getRequiresUserOwnership()) {
//			}
//		}
//		return eb.toString();
//	}
	

	private String rolesToString(Set<String> roles) {
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (String role : roles) {
			cnt++;
			sb.append("s:hasRole('");
			sb.append(role);
			sb.append("')");
			if (cnt != roles.size()) {
				sb.append(" and ");
			}
		}
		return sb.toString();
	}

}
