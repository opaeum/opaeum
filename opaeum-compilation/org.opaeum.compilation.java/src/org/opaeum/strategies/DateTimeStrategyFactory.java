package org.opaeum.strategies;

import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.TestValueStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class DateTimeStrategyFactory extends AbstractStrategyFactory {
	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedField f, INakedProperty p) {
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName(Temporal.class.getName()));
			temporal.addEnumValue(new OJEnumValue(new OJPathName(TemporalType.class.getName()), "TIMESTAMP"));
			f.putAnnotation(temporal);
		}

	}

	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy {

		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p) {
			return getDefaultStringValue();
		}

		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue) {
			addSimpleDateFormat(owner,block);
			return "dateTimeFormat.parse(" + configuredValue + ")";
		}

		@Override
		public String getDefaultStringValue() {
			return "2002-10-10";
		}

	}
	public static class MyTestValueStrategy implements TestValueStrategy{

		@Override
		public String getDefaultValue() {
			return "dateTimeFormat.parse(\"2010-01-31\")";
		}

		@Override
		public void transformClass(OJAnnotatedClass owner, OJBlock block) {
			addSimpleDateFormat(owner,block);
		}
		
	}

	@SuppressWarnings("unchecked")
	public DateTimeStrategyFactory() {
		super(MyJpaStrategy.class,MyConfigurableDataStrategy.class,MyTestValueStrategy.class);
	}
	private static void addSimpleDateFormat(OJAnnotatedClass owner, OJBlock block) {
		owner.addToImports("java.text.SimpleDateFormat");

		List<OJField> locals = block.getLocals();
		boolean hasField = false;
		for (OJField f : locals) {
			if (f.getName().equals("dateTimeFormat")) {
				hasField = true;
				break;
			}
		}
		if (!hasField) {
			OJAnnotatedField dateTimeFormat = new OJAnnotatedField("dateTimeFormat", new OJPathName("java.text.SimpleDateFormat"));
			dateTimeFormat.setInitExp("new SimpleDateFormat(\"yyyy-MM-dd\")");
			block.addToLocals(dateTimeFormat);
		}
	}
	@Override
	public boolean appliesTo(INakedSimpleType st){
		return st.getName().equals("DateTime");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.DateTimeStrategyFactory";
	}

}
