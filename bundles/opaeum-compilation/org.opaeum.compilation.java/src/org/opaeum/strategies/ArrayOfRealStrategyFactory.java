package org.opaeum.strategies;


import javax.persistence.Transient;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class ArrayOfRealStrategyFactory extends AbstractStrategyFactory {
	public static class MyFormatterStrategy implements FormatterStrategy{

		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.initializeResultVariable("null");
		}

		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("null");
		}
		
	}
	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedClass c,OJAnnotatedField f, Property p) {
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Transient.class.getName()));
			f.putAnnotation(type);
		}

	}

	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy {

		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner, OJBlock block, Property p) {
			return getDefaultStringValue();
		}

		@Override
		public String parseConfiguredValue(OJUtil ojUtil, OJAnnotatedClass owner, OJBlock block, Property p, String configuredValue) {
			return configuredValue;
		}

		@Override
		public String getDefaultStringValue() {
			return "new double[0]";
		}
	}
	public static class MyTestValueStrategy implements TestModelValueStrategy{

		@Override
		public String getDefaultStringValue(int count) {
			return "This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG blob";
		}

		
	}
	@SuppressWarnings("unchecked")
	public ArrayOfRealStrategyFactory() {
		super(MyJpaStrategy.class,MyConfigurableDataStrategy.class,MyTestValueStrategy.class,MyFormatterStrategy.class);
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("BinaryLargeObject");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory";
	}

}
