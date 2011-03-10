package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class JsfInputBuilder extends AbstractJsfInputBuilder implements IJsfComponentBuilder {
	
	private ClassifierUserInteraction ui; 
	
	public JsfInputBuilder(ClassifierUserInteraction ui, DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
		this.ui = ui;
	}
	@Override
	public UIComponent createComponent() {
		return new HtmlInputText();
	}
	
	protected String getEditRenderedRoot(DomainClassifier dc) {
		if (ui.getUserInteractionKind()!=UserInteractionKind.CREATE || ui.getOriginatingPropertyNavigation()==null) {
			return NameConverter.decapitalize(dc.getName());
		} 
		return NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName() +"_"+dc.getName());
	}
	

}
