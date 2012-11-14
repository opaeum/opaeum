package org.opaeum.strategies;

import javax.persistence.Basic;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.basicjava.AttributeInJava;
import org.opaeum.javageneration.basicjava.AttributeStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.jaxb.JaxbAnnotator;
import org.opaeum.jaxb.JaxbStrategy;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.runtime.environment.Environment;

public class LocaleStrategyFactory extends AbstractStrategyFactory{
	public static class MyJaxbStrategy implements JaxbStrategy{

		@Override
		public void annotatedField(OJAnnotatedField oper){
			JaxbAnnotator.addXmlTransient(oper);
			
		}
		
	}
	public static class MyFormatterStrategy implements FormatterStrategy{
		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.getOwner().addToImports(Environment.class.getName());
			parse.initializeResultVariable("value==null||value.length()==0?null:Environment.getLocale(value)");
		}
		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("value==null?\"\":value.toString()");
		}
	}
	public static class MyAttributeStrategy implements AttributeStrategy{
		@Override
		public void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap property){
			owner.addToImports(Environment.class.getName());
			a.field.setType(new OJPathName("String"));
			owner.addToImports(Environment.class.getName());
			a.getter.initializeResultVariable(a.field.getName() + "==null?null:Environment.getLocale(" + a.field.getName() + ")");
			if(a.internalAdder == null){
				a.setter.getBody().getStatements().clear();
				a.setter.getBody().addToStatements("this."+a.field.getName()+"=" + a.field.getName() + "==null?null:"+ a.field.getName() + ".toString()");
			}else{
				a.internalAdder.getBody().getStatements().clear();
				a.internalAdder.getBody().addToStatements(
						"this." + a.field.getName() + " = " + a.field.getName() + "==null?null:" + a.field.getName() + ".toString()");
			}
			a.internalRemover.getBody().getStatements().clear();
			a.internalRemover.getBody().addToStatements("this." + a.field.getName() + " = null");
		}
	}
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedClass c,OJAnnotatedField f,Property p){
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName(Basic.class.getName()));
			f.putAnnotation(temporal);
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,Property p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner,OJBlock block,Property p,String configuredValue){
			owner.addToImports(Environment.class.getName());
			return "Environment.getLocale(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "en_ZA";
		}
	}
	public static class DateTestModelValueStrategy implements TestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			switch(seed % 3){
			case 1:
				return "en_ZA";
			case 2:
				return "en_US";
			}
			return "en_GB";
		}
	}
	@SuppressWarnings("unchecked")
	public LocaleStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, DateTestModelValueStrategy.class, MyFormatterStrategy.class,
				MyAttributeStrategy.class, MyJaxbStrategy.class);
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("Locale");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.LocaleStrategyFactory";
	}
}
