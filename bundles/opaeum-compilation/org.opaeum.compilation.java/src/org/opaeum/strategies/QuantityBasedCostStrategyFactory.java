package org.opaeum.strategies;

import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.basicjava.AttributeInJava;
import org.opaeum.javageneration.basicjava.AttributeStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.strategies.DateStrategyFactory.DateTestModelValueStrategy;

public class QuantityBasedCostStrategyFactory extends AbstractStrategyFactory{
	public static class MyAttributeStrategy implements AttributeStrategy{

		@Override
		public void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap property){
		}
		
	}
	public static class MyFormattingStrategy implements FormatterStrategy
	{

		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.initializeResultVariable("value==null||value.length()==0?null:new QuantityBasedCost(value)");
			
		}

		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("value==null?\"\":value.toString()");
			
		}
	}
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedClass c, OJAnnotatedField f,Property p){
			OJAnnotationValue embedded = new OJAnnotationValue(new OJPathName(Embedded.class.getName()));
			f.putAnnotation(embedded);
			OJAnnotationValue overrides = new OJAnnotationValue(new OJPathName(AttributeOverrides.class.getName()));
			f.putAnnotation(overrides);
			NameWrapper persistentName = PersistentNameUtil.getPersistentName(p);
			overrides.addAnnotationValue(JpaUtil.createOverride("costToCompany", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("date", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("resourceIdentifier", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("resourceClassIdentifier", persistentName));
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,Property p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner,OJBlock block,Property p,String configuredValue){
			return "new QuantityBasedCost(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "10 business days";
		}
	}
	public static class MyTestValueStrategy extends DateTestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			return seed + " " + BusinessTimeUnit.values()[seed % BusinessTimeUnit.values().length];
		}
	}
	@SuppressWarnings("unchecked")
	public QuantityBasedCostStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, MyTestValueStrategy.class,MyFormattingStrategy.class,MyAttributeStrategy.class);
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("Duration");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.QuantityBasedCostStrategyFactory";
	}
}
