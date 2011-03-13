package net.sf.nakeduml.seamgeneration.jsf.component;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

public class JsfFactoryNavigation {
	private static JsfFactoryNavigation factory;
	private JsfFactoryNavigation() {
		super();
	}
	public static JsfFactoryNavigation instance() {
		if (factory==null) {
			factory = new JsfFactoryNavigation();
		}
		return factory;
	}
	public IJsfLinkBuilder getJsfLinkBuilder(DomainClassifier dc, PropertyNavigation n) {
		return new JsfLinkBuilder(dc, n);
	}
	public IJsfLinkBuilder getJsfMenuLinkBuilder(DomainClassifier dc, PropertyNavigation n, boolean forCreate) {
		return new JsfMenuLinkBuilder(dc, n, forCreate);
	}
	public IJsfLinkBuilder getJsfMenuTooManyLinkBuilder(DomainClassifier dc, PropertyNavigation n) {
		return new JsfMenuTooManyLinkBuilder(dc, n);
	}
	
	public IJsfLinkBuilder getJsfOperationMenuLinkBuilder(DomainClassifier dc, OperationNavigation n) {
		return new JsfOperationMenuLinkBuilder(dc, n);
	}
	
}
