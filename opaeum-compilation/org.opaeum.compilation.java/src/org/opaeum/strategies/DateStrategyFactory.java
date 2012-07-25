package org.opaeum.strategies;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class DateStrategyFactory extends AbstractStrategyFactory{
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedField f,Property p){
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName(Temporal.class.getName()));
			temporal.addEnumValue(new OJEnumValue(new OJPathName(TemporalType.class.getName()), "DATE"));
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
			addSimpleDateFormat(owner, block);
			return "dateTimeFormat.parse(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "2002-10-10";
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
	public DateStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, DateTestModelValueStrategy.class);
	}
	private static void addSimpleDateFormat(OJAnnotatedClass owner,OJBlock block){
		owner.addToImports("java.text.SimpleDateFormat");
		List<OJField> locals = block.getLocals();
		boolean hasField = false;
		for(OJField f:locals){
			if(f.getName().equals("dateFormat")){
				hasField = true;
				break;
			}
		}
		if(!hasField){
			OJAnnotatedField dateTimeFormat = new OJAnnotatedField("dateFormat", new OJPathName("java.text.SimpleDateFormat"));
			dateTimeFormat.setInitExp("new SimpleDateFormat(\"yyyy-MM-dd\")");
			block.addToLocals(dateTimeFormat);
		}
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("DateTime");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.DateStrategyFactory";
	}
}
