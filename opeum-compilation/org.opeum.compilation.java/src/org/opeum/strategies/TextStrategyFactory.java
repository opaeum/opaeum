package org.opeum.strategies;

import javax.persistence.Lob;

import org.opeum.javageneration.TestValueStrategy;
import org.opeum.javageneration.composition.ConfigurableDataStrategy;
import org.opeum.javageneration.persistence.JpaStrategy;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.workspace.AbstractStrategyFactory;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;

public class TextStrategyFactory extends AbstractStrategyFactory {
	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedField f, INakedProperty p) {
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Lob.class.getName()));
			f.putAnnotation(type);
		}

	}

	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy {

		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p) {
			return getDefaultStringValue();
		}

		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue) {
			return configuredValue;
		}

		@Override
		public String getDefaultStringValue() {
			return "This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob";
		}
	}
	public static class MyTestValueStrategy implements TestValueStrategy{

		@Override
		public String getDefaultValue() {
			return "\"This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob\"";
		}

		@Override
		public void transformClass(OJAnnotatedClass owner, OJBlock block) {
		}
		
	}
	public TextStrategyFactory() {
		super(MyJpaStrategy.class,MyConfigurableDataStrategy.class,MyTestValueStrategy.class);
	}
	@Override
	public boolean appliesTo(INakedSimpleType st){
		return st.getName().equals("Text");
	}

}
