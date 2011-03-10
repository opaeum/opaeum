package net.sf.nakeduml.jsf2generation.factory;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.IJsf2LinkBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2LinkBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2MenuLinkBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

public class Jsf2FactoryNavigation {
	private static Jsf2FactoryNavigation factory;
	private Jsf2FactoryNavigation() {
		super();
	}
	public static Jsf2FactoryNavigation instance() {
		if (factory==null) {
			factory = new Jsf2FactoryNavigation();
		}
		return factory;
	}
//	public IJsf2LinkBuilder getJsfLinkBuilder(DomainClassifier dc, PropertyNavigation n) {
//		return new Jsf2LinkBuilder(dc, n);
//	}
//	public IJsf2LinkBuilder getJsfMenuLinkBuilder(DomainClassifier dc, PropertyNavigation n, boolean forCreate) {
//		return new Jsf2MenuLinkBuilder(dc, n, forCreate);
//	}
}
