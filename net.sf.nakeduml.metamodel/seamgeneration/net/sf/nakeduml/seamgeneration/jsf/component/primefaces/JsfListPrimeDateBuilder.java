package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.AbstractJsfListInputBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.IJsfComponentBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.uiajax.UIAjax;

public class JsfListPrimeDateBuilder extends AbstractJsfListInputBuilder implements IJsfComponentBuilder {
	public JsfListPrimeDateBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new Calendar();
	}
	@Override
	protected String getEvent() {
		return "onchanged";
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void addAjaxSupport(UIOutput uiOutput) {
		UIAjax uiAjax = new UIAjax();
		uiAjax.setEvent(getEvent());
		uiAjax.setUpdate(retrieveDecorationId(uiOutput));
		setSettedAttributes(uiAjax, "event", "update");
		uiOutput.getChildren().add(uiAjax);
		List<String> attributes = (List<String>)uiOutput.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove(getEvent());
	}	
	
}
