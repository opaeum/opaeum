package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.richfaces.component.html.HtmlCalendar;

public class JsfListDateBuilder extends AbstractJsfListInputBuilder implements IJsfComponentBuilder {
	public JsfListDateBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlCalendar();
	}
	@Override
	protected String getEvent() {
		return "onchanged";
	}
	
}
