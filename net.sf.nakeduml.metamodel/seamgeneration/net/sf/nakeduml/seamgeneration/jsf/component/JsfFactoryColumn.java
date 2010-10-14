package net.sf.nakeduml.seamgeneration.jsf.component;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.IJsfPropertyFieldPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.IJsfPropertyNavigationPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListBigDecimalPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListBooleanPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListEnumPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListNavigationPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListObjectPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListPrimeDateColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListStringPrimeColumnBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.primefaces.JsfListStringPrimeWritableColumnBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class JsfFactoryColumn {
	private static JsfFactoryColumn factory;
	private JsfFactoryColumn() {
		super();
	}
	public static JsfFactoryColumn instance() {
		if (factory==null) {
			factory = new JsfFactoryColumn();
		}
		return factory;
	}
	public IJsfPropertyFieldColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, PropertyField pf, boolean filter) {
		if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfListDateColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfListBooleanColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.math.BigDecimal")) {
			return new JsfListBigDecimalColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfListEnumColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfListObjectColumnBuilder(ui,pf);
		} else if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new JsfListStringColumnBuilder(ui,pf);
		} else {
			return new JsfListStringFilterColumnBuilder(ui,pf,filter);
		}
	}
	public IJsfPropertyFieldPrimeColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new JsfListPrimeDateColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new JsfListBooleanPrimeColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.math.BigDecimal")) {
			return new JsfListBigDecimalPrimeColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new JsfListEnumPrimeColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new JsfListObjectPrimeColumnBuilder(ui,pf);
		} else if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new JsfListStringPrimeColumnBuilder(ui,pf);
		} else {
			return new JsfListStringPrimeWritableColumnBuilder(ui,pf);
		}
	}
	public IJsfPropertyNavigationColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, PropertyNavigation pn) {
		return new JsfListNavigationColumnBuilder(ui,pn);
	}
	public IJsfPropertyNavigationPrimeColumnBuilder getJsfPrimeColumnBuilder(ClassifierUserInteraction ui, PropertyNavigation pn) {
		return new JsfListNavigationPrimeColumnBuilder(ui,pn);
	}
	public IJsfPropertyNavigationColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, OperationNavigation pn) {
		return new JsfOperationListNavigationColumnBuilder(ui,pn);
	}	
}
