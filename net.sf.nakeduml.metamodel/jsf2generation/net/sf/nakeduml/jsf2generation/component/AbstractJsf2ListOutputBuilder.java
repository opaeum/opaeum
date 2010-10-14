package net.sf.nakeduml.jsf2generation.component;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.jboss.seam.ui.component.html.HtmlDecorate;

public abstract class AbstractJsf2ListOutputBuilder extends AbstractJsf2OutputBuilder {

	public AbstractJsf2ListOutputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	
	@Override
	protected String getEditTemplate() {
		return "/layout/editInsideTable.xhtml";
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return "objectVar";
	}
	
	@Override
	protected HtmlDecorate addDecoration(UIOutput uiOutput, String template, ValueExpression ve, PropertyField pf) {
		HtmlDecorate htmlDecorate = new HtmlDecorate();
		htmlDecorate.setId(retrieveDecorationId(uiOutput));
		htmlDecorate.setTemplate(template);
		setSettedAttributes(htmlDecorate, "id", "template");
		htmlDecorate.setValueExpression("rendered", ve);
		htmlDecorate.getChildren().add(uiOutput);
		addAjaxSupport(uiOutput);
		return htmlDecorate;
	}

}