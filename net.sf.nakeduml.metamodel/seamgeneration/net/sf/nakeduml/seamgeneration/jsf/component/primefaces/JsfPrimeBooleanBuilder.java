package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.IJsfComponentBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class JsfPrimeBooleanBuilder extends AbstractJsfPrimeInputBuilder implements IJsfComponentBuilder {
	public JsfPrimeBooleanBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlSelectBooleanCheckbox();
	}
	
}
