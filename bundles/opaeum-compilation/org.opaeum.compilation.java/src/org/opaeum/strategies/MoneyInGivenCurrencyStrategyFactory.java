package org.opaeum.strategies;

import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.basicjava.AttributeInJava;
import org.opaeum.javageneration.basicjava.SimpleTypeAttributeStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class MoneyInGivenCurrencyStrategyFactory extends AbstractStrategyFactory{
	public static class MyFormatterStrategy implements FormatterStrategy{
		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.initializeResultVariable("value==null||value.length()==0?null:new MoneyInGivenCurrency(value)");
		}
		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("value==null?\"\":value.toString()");
		}
	}
	public static class MyAttributeStrategy implements SimpleTypeAttributeStrategy{
		@Override
		public void applyTo(OJUtil ojUtil,OJAnnotatedClass owner,AttributeInJava a, PropertyMap property){
		}
	}
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedClass c,OJAnnotatedField f,Property p){
			OJAnnotationValue embedded = new OJAnnotationValue(new OJPathName(Embedded.class.getName()));
			f.putAnnotation(embedded);
			OJAnnotationValue overrides = new OJAnnotationValue(new OJPathName(AttributeOverrides.class.getName()));
			f.putAnnotation(overrides);
			NameWrapper persistentName = PersistentNameUtil.getPersistentName(p);
			overrides.addAnnotationValue(JpaUtil.createOverride("amount", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("currency", persistentName));
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,Property p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJUtil ojUtil,OJAnnotatedClass owner,OJBlock block,Property p, String configuredValue){
			addCUrrencyFormat(owner, block);
			OJAnnotatedField field = new OJAnnotatedField("split", new OJPathName("String"));
			block.addToLocals(field);
			field.setInitExp(configuredValue + ".split(\"\\\\ \")");
			return "currencyFormat.parse(split[0])";
		}
		@Override
		public String getDefaultStringValue(){
			return "1000 ZAR";
		}
	}
	public static class DateTestModelValueStrategy implements TestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			int year = Calendar.getInstance().get(Calendar.YEAR) - seed;
			int month = ((Calendar.getInstance().get(Calendar.MONTH) + seed) % 12) + 1;
			int day = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + seed) % 28;
			return year + "-" + month + "-" + day;
		}
	}
	@SuppressWarnings("unchecked")
	public MoneyInGivenCurrencyStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, DateTestModelValueStrategy.class, MyAttributeStrategy.class,MyFormatterStrategy.class);
	}
	private static void addCUrrencyFormat(OJAnnotatedClass owner,OJBlock block){
		owner.addToImports("java.text.NumberFormat");
		List<OJField> locals = block.getLocals();
		boolean hasField = false;
		for(OJField f:locals){
			if(f.getName().equals("format")){
				hasField = true;
				break;
			}
		}
		if(!hasField){
			OJAnnotatedField dateTimeFormat = new OJAnnotatedField("format", new OJPathName("java.text.NumberFormat"));
			dateTimeFormat.setInitExp("NumberFormat.getCurrencyInstance()");
			block.addToStatements("format.setCurrency(Currency.getInstance().getDefaultCurrency(split[1]))");
			block.addToLocals(dateTimeFormat);
		}
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("MoneyInGivenCurrency");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.MoneyInGivenCurrencyStrategyFactory";
	}
}
