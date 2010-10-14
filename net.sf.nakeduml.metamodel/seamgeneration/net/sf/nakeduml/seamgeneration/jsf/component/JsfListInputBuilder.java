package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class JsfListInputBuilder extends AbstractJsfListInputBuilder implements IJsfComponentBuilder {
	public JsfListInputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlInputText();
	}
}
