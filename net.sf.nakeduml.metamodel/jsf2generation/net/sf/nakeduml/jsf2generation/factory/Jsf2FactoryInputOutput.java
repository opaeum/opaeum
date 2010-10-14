package net.sf.nakeduml.jsf2generation.factory;

import java.io.IOException;
import java.util.Properties;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.IJsf2ComponentBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2BooleanBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2DateBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2EntityBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2EnumBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2InputBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2OuputBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class Jsf2FactoryInputOutput {
	private static Jsf2FactoryInputOutput factory;
	private Properties uiComponentProperties;
	private Jsf2FactoryInputOutput() {
		super();
		uiComponentProperties = new Properties();
		try {
			uiComponentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("type.mapper.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}
	public static Jsf2FactoryInputOutput instance() {
		if (factory==null) {
			factory = new Jsf2FactoryInputOutput();
		}
		return factory;
	}
	public IJsf2ComponentBuilder getJsf2InputOutputBuilder(DomainClassifier dc, PropertyField pf) {
		if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new Jsf2OuputBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new Jsf2DateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new Jsf2BooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new Jsf2EnumBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new Jsf2EntityBuilder(dc,pf);
		} else {
			return new Jsf2InputBuilder(dc,pf);
		}
	}
}
