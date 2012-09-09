package org.opaeum.strategies;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.basicjava.AttributeInJava;
import org.opaeum.javageneration.basicjava.AttributeStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class CurrencyStrategyFactory extends AbstractStrategyFactory{
	public static class MyFormatterStrategy implements FormatterStrategy{

		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.initializeResultVariable("value==null||value.length()==0?null:Currency.getInstance(value)");
			
		}

		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("value==null?\"\":value.getCurrencyCode()");
			
		}
		
	}
	public static class MyAttributeStrategy implements AttributeStrategy{
		@Override
		public void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap property){
			a.field.setType(new OJPathName("String"));
			a.getter.initializeResultVariable("Currency.getInstance(" + a.field.getName() + ")");
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
			return "java.util.Currency.getInstance(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "USD";
		}
	}
	public static class DateTestModelValueStrategy implements TestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			switch(seed%3){
			case 1:
				return "USD";
			case 2:
				return "ZAR";
			case 3:
				return "GBP";
			}
			return "EUR";
		}
	}
	@SuppressWarnings("unchecked")
	public CurrencyStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, DateTestModelValueStrategy.class, MyFormatterStrategy.class);
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("DateTime");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.CurrencyStrategyFactory";
	}
}
