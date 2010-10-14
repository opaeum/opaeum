package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import javax.faces.component.UIComponent;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.IJsfComponentBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.primefaces.component.calendar.Calendar;

public class JsfPrimeDateBuilder extends AbstractJsfPrimeInputBuilder implements IJsfComponentBuilder {
	public JsfPrimeDateBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	@Override
	public UIComponent createComponent() {
		return new Calendar();
	}
	
}
