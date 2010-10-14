package net.sf.nakeduml.jsf2generation.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class Jsf2ListInputBuilder extends AbstractJsf2ListInputBuilder implements IJsf2ComponentBuilder {
	public Jsf2ListInputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlInputText();
	}
}