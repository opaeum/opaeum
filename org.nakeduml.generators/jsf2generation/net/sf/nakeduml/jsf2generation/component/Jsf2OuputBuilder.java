package net.sf.nakeduml.jsf2generation.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class Jsf2OuputBuilder extends AbstractJsf2OutputBuilder implements IJsf2ComponentBuilder  {

	public Jsf2OuputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}

	@Override
	public UIComponent createComponent() {
		return new HtmlOutputText();
	}

}
