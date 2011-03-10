package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class JsfListBooleanBuilder extends AbstractJsfListInputBuilder implements IJsfComponentBuilder {
	public JsfListBooleanBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlSelectBooleanCheckbox();
	}
}
