package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlSelectManyMenu;

import org.primefaces.component.uiajax.UIAjax;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.component.IJsfComponentBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class JsfPrimeEntityBuilder extends AbstractJsfPrimeInputBuilder implements IJsfComponentBuilder {
	
	private ClassifierUserInteraction ui;
	
	public JsfPrimeEntityBuilder(ClassifierUserInteraction ui, DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
		this.ui = ui;
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlSelectManyMenu();
	}
	
	protected String getEditRenderedRoot(DomainClassifier dc) {
		if (ui.getUserInteractionKind()!=UserInteractionKind.CREATE || ui.getOriginatingPropertyNavigation()==null) {
			return NameConverter.decapitalize(dc.getName());
		} 
		return NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName() +"_"+dc.getName());
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

	@Override
	protected String getEvent() {
		return "onchange";
	}
	
}
