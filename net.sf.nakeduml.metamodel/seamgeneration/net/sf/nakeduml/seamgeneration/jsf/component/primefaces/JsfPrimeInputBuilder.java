package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.component.IJsfComponentBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class JsfPrimeInputBuilder extends AbstractJsfPrimeInputBuilder implements IJsfComponentBuilder {
	
	private ClassifierUserInteraction ui; 
	
	public JsfPrimeInputBuilder(ClassifierUserInteraction ui, DomainClassifier dc, PropertyField pf) {
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
