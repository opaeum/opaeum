package net.sf.nakeduml.seamgeneration.jsf.component;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfTreeViewBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

public class JsfFactoryHierarchy {
	private static JsfFactoryHierarchy factory;
	private JsfFactoryHierarchy() {
		super();
	}
	public static JsfFactoryHierarchy instance() {
		if (factory==null) {
			factory = new JsfFactoryHierarchy();
		}
		return factory;
	}
	
	public IJsfComponentBuilder getJsfTreeViewBuilder() {
		return new JsfTreeViewBuilder();
	}	
}
