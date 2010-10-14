package net.sf.nakeduml.seamgeneration.jsf.component;

import java.io.IOException;
import java.util.Properties;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class JsfFactoryInputOutput {
	private static JsfFactoryInputOutput factory;
	private Properties uiComponentProperties;
	private JsfFactoryInputOutput() {
		super();
		uiComponentProperties = new Properties();
		try {
			uiComponentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("type.mapper.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}
	public static JsfFactoryInputOutput instance() {
		if (factory==null) {
			factory = new JsfFactoryInputOutput();
		}
		return factory;
	}
	public IJsfComponentBuilder getJsfInputOutputBuilder(ClassifierUserInteraction ui, DomainClassifier dc, PropertyField pf) {
		if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new JsfOuputBuilder(ui, dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfDateBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfBooleanBuilder(dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfEnumBuilder(ui, dc,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfEntityBuilder(ui, dc,pf);
		} else {
			return new JsfInputBuilder(ui, dc,pf);
		}
	}
}
