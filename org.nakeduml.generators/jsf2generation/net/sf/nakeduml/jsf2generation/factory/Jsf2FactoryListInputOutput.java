package net.sf.nakeduml.jsf2generation.factory;

import java.io.IOException;
import java.util.Properties;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.IJsf2ComponentBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListBooleanBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListDateBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListEntityBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListEnumBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListInputBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListOuputBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class Jsf2FactoryListInputOutput {
	private static Jsf2FactoryListInputOutput factory;
	private Properties uiComponentProperties;
	private Jsf2FactoryListInputOutput() {
		super();
		uiComponentProperties = new Properties();
		try {
			uiComponentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("type.mapper.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}
	public static Jsf2FactoryListInputOutput instance() {
		if (factory==null) {
			factory = new Jsf2FactoryListInputOutput();
		}
		return factory;
	}
	public IJsf2ComponentBuilder getJsfListInputOutputBuilder(DomainClassifier dc, PropertyField pf) {
		if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new Jsf2ListOuputBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new Jsf2ListDateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new Jsf2ListBooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new Jsf2ListEnumBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new Jsf2ListEntityBuilder(dc,pf);
		} else {
			return new Jsf2ListInputBuilder(dc,pf);
		}
	}
}
