package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

public class JsfListOuputBuilder extends AbstractJsfListOutputBuilder implements IJsfComponentBuilder  {

	public JsfListOuputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}

	@Override
	public UIComponent createComponent() {
		return new HtmlOutputText();
	}

}
