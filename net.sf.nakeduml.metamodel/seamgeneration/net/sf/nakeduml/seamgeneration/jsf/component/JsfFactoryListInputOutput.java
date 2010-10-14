package net.sf.nakeduml.seamgeneration.jsf.component;

import java.io.IOException;
import java.util.Properties;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListPrimeDateBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListPrimeInputBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfPrimeBooleanBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfPrimeDateBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfPrimeEntityBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfPrimeEnumBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfPrimeSearchInputBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class JsfFactoryListInputOutput {
	private static JsfFactoryListInputOutput factory;
	private Properties uiComponentProperties;
	private JsfFactoryListInputOutput() {
		super();
		uiComponentProperties = new Properties();
		try {
			uiComponentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("type.mapper.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}
	public static JsfFactoryListInputOutput instance() {
		if (factory==null) {
			factory = new JsfFactoryListInputOutput();
		}
		return factory;
	}
	public IJsfComponentBuilder getJsfListInputOutputBuilder(DomainClassifier dc, PropertyField pf) {
		if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new JsfListOuputBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfListDateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfListBooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfListEnumBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfListEntityBuilder(dc,pf);
		} else {
			return new JsfListInputBuilder(dc,pf);
		}
	}
	
	public IJsfComponentBuilder getJsfListPrimeInputOutputBuilder(DomainClassifier dc, PropertyField pf) {
		if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new JsfListOuputBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfListPrimeDateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfListBooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfListEnumBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfListEntityBuilder(dc,pf);
		} else {
			return new JsfListPrimeInputBuilder(dc,pf);
		}
	}

	public IJsfComponentBuilder getJsfListSearchPrimeInputOutputBuilder(ClassifierUserInteraction ui, DomainClassifier dc, PropertyField pf) {
		if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfPrimeDateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfPrimeBooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfPrimeEnumBuilder(ui, dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfPrimeEntityBuilder(ui, dc,pf);
		} else {
			return new JsfPrimeSearchInputBuilder(ui, dc,pf);
		}
	}
	
}
