package net.sf.nakeduml.strategies;

import javax.persistence.Lob;

import net.sf.nakeduml.javageneration.TestValueStrategy;
import net.sf.nakeduml.javageneration.composition.ConfigurableDataStrategy;
import net.sf.nakeduml.javageneration.persistence.JpaStrategy;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.AbstractStrategyFactory;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

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

}
