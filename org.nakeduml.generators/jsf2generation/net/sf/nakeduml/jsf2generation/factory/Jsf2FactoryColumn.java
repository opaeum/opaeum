package net.sf.nakeduml.jsf2generation.factory;

import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.jsf2generation.component.IJsf2PropertyFieldColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.IJsf2PropertyNavigationColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListBigDecimalColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListBooleanColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListDateColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListEnumColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListNavigationColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListObjectColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListStringColumnBuilder;
import net.sf.nakeduml.jsf2generation.component.Jsf2ListStringFilterColumnBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

public class Jsf2FactoryColumn {
	private static Jsf2FactoryColumn factory;
	private Jsf2FactoryColumn() {
		super();
	}
	public static Jsf2FactoryColumn instance() {
		if (factory==null) {
			factory = new Jsf2FactoryColumn();
		}
		return factory;
	}
	public IJsf2PropertyFieldColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.util.Date")) {
			return new Jsf2ListDateColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.lang.Boolean")) {
			return new Jsf2ListBooleanColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getQualifiedImplementationType().equals("java.math.BigDecimal")) {
			return new Jsf2ListBigDecimalColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENUMERATION) {
			return new Jsf2ListEnumColumnBuilder(ui,pf);
		} else if (pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.ENTITY || pf.getTypedElement().getType().getClassifierKind()==ClassifierKind.INTERFACE) {
			return new Jsf2ListObjectColumnBuilder(ui,pf);
		} else if (pf.getParticipationKind()==TypedElementParticipationKind.READONLY) {
			return new Jsf2ListStringColumnBuilder(ui,pf);
		} else {
			return new Jsf2ListStringFilterColumnBuilder(ui,pf);
		}
	}
	public IJsf2PropertyNavigationColumnBuilder getJsfColumnBuilder(ClassifierUserInteraction ui, PropertyNavigation pn) {
		return new Jsf2ListNavigationColumnBuilder(ui,pn);
	}
}
