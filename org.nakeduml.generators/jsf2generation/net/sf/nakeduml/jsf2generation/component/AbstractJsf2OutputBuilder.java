package net.sf.nakeduml.jsf2generation.component;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.dummy.UIFragment;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementField;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDecorate;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsf2OutputBuilder extends AbstractJsf2ComponentBuilder {

	public AbstractJsf2OutputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}

	@Override
	public UIFragment createUIComponent() {
		UIFragment htmlFragment = new UIFragment();
		UIOutput uiOutput = new HtmlOutputText();
		setUpOutput(dc, pf, uiOutput);
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createReadonlyViewRenderedExpression(dc, pf), void.class);
		HtmlDecorate htmlDecorate = addDecoration(uiOutput, getEditTemplate(), ve, pf);
		htmlFragment.getChildren().add(htmlDecorate);
		return htmlFragment;
	}	
	
	private void setUpOutput(DomainClassifier dc, PropertyField pf, UIOutput uiOutput) {
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createPropertyValueExpression(dc, pf), Object.class);
		uiOutput.setValue(ve);
		setSettedAttributes(uiOutput, "value");
	}
	
	private String createReadonlyViewRenderedExpression(DomainClassifier dc, PropertyField pf) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (mayEditWithoutGroupOwnership(pf) || mayViewWithoutGroupOwnership(pf)) {
			eb.append("true");
		} else {
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.toString();
	}
	
	private boolean mayEditWithoutGroupOwnership(TypedElementField pf) {
		if (pf.getSecurityOnEdit() != null && !pf.getSecurityOnEdit().getRequiresGroupOwnership()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean mayViewWithoutGroupOwnership(TypedElementField pf) {
		if (pf.getSecurityOnView() != null && !pf.getSecurityOnView().getRequiresGroupOwnership()) {
			return true;
		} else {
			return false;
		}
	}	
}
