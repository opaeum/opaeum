package net.sf.nakeduml.seamgeneration.jsf.component;

import javax.faces.component.UIViewRoot;

import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;

public class JsfFactoryAccordion {
	private static JsfFactoryAccordion factory;
	private JsfFactoryAccordion() {
		super();
	}
	public static JsfFactoryAccordion instance() {
		if (factory==null) {
			factory = new JsfFactoryAccordion();
		}
		return factory;
	}
	
	public JsfAccordianPanelBuilder getJsfAccordianPanelBuilder(UIViewRoot jsfMenu) {
		return new JsfAccordianPanelBuilder(jsfMenu);
	}
	
}
