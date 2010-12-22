package net.sf.nakeduml.strategies;

import net.sf.nakeduml.javageneration.TestValueStrategy;
import net.sf.nakeduml.javageneration.composition.ConfigurableDataStrategy;
import net.sf.nakeduml.javageneration.persistence.JpaStrategy;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.AbstractStrategyFactory;

import org.hibernate.annotations.Type;
import org.hibernate.type.TextType;

public class TextStrategyFactory extends AbstractStrategyFactory {
	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedField f, INakedProperty p) {
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			type.putAttribute("type", TextType.class.getName());
			f.putAnnotation(type);
		}

	}

	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy {

		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p) {
			return "This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob";
		}

		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p, String configuredValue) {
			return configuredValue;
		}
	}
	public static class MyTestValueStrategy implements TestValueStrategy{

		@Override
		public String getDefaultValue(OJAnnotatedClass owner, OJBlock block, INakedProperty p) {
			return "\"This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob\"";
		}
		
	}
	public TextStrategyFactory() {
		super(MyJpaStrategy.class,MyConfigurableDataStrategy.class,MyTestValueStrategy.class);
	}

}
