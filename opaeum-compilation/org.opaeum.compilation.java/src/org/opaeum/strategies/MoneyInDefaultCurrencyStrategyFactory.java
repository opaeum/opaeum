package org.opaeum.strategies;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
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
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.runtime.environment.Environment;

public class MoneyInDefaultCurrencyStrategyFactory extends AbstractStrategyFactory{
	public static class MyFormatterStrategy implements FormatterStrategy{
		@Override
		public void implementParse(OJAnnotatedOperation parse){
			addFormat(parse);
			parse.initializeResultVariable("null");
			OJTryStatement tryParse = new OJTryStatement();
			parse.getBody().addToStatements(tryParse);
			tryParse.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
			tryParse.getCatchPart().addToStatements("result=null");
			tryParse.getTryPart().addToStatements("result = (value==null||value.length()==0?null:format.parse(value).doubleValue())");
		}
		private void addFormat(OJAnnotatedOperation parse){
			OJAnnotatedField format = new OJAnnotatedField("format", new OJPathName(NumberFormat.class.getName()));
			parse.getBody().addToLocals(format);
			format.setInitExp("NumberFormat.getInstance()");
			parse.initializeResultVariable("null");
		}
		@Override
		public void implementFormat(OJAnnotatedOperation format){
			addFormat(format);
			format.getBody().addToStatements("result =value==null?\"\":format.format(value)");
		}
	}
	public static class MyAttributeStrategy implements AttributeStrategy{
		@Override
		public void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap property){
			owner.addToImports(new OJPathName("org.opaeum.runtime.costing.CurrencyMismatchException"));
			owner.addToImports(new OJPathName(Environment.class.getName()));
			String fieldName = a.field.getName() + "Currency";
			owner.addToFields(new OJAnnotatedField(fieldName, new OJPathName("String")));
			String cond = fieldName + "!=null && !" + fieldName + ".equals(Environment.getInstance().getDefaultCurrency())";
			String throwIt = "throw new CurrencyMismatchException(" + fieldName + ",Environment.getInstance().getDefaultCurrency())";
			if(a.internalAdder != null){
				a.internalAdder.getBody().getStatements().add(0, new OJIfStatement(cond, throwIt));
			}else{
				a.setter.getBody().getStatements().add(0, new OJIfStatement(cond, throwIt));
			}
		}
	}
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedClass c,OJAnnotatedField f,Property p){
			OJAnnotatedField currency = (OJAnnotatedField) c.findField(f.getName() + "Currency");
			currency.putAnnotation(new OJAnnotationValue(new OJPathName(Basic.class.getName())));
			OJAnnotationValue col = new OJAnnotationValue(new OJPathName(Column.class.getName()));
			col.putAttribute("name", PersistentNameUtil.getPersistentName(p) + "_currency");
			currency.putAnnotation(col);
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,Property p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner,OJBlock block,Property p,String configuredValue){
			addCUrrencyFormat(owner, block);
			return "currencyFormat.parse(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "1000";
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
	public MoneyInDefaultCurrencyStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, DateTestModelValueStrategy.class, MyAttributeStrategy.class,
				MyFormatterStrategy.class);
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
			block.addToStatements("format.setCurrency(Environment.getInstance().getDefaultCurrency())");
			block.addToLocals(dateTimeFormat);
		}
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("MoneyInDefaultCurrency");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.MoneyInDefaultCurrencyStrategyFactory";
	}
}
