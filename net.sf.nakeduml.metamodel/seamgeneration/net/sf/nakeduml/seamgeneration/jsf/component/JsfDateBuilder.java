package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.richfaces.component.html.HtmlCalendar;

public class JsfDateBuilder extends AbstractJsfInputBuilder implements IJsfComponentBuilder {
	public JsfDateBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlCalendar();
	}
	
}
