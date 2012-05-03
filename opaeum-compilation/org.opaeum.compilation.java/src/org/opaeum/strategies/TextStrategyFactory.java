package org.opaeum.strategies;

import javax.persistence.Lob;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

public class TextStrategyFactory extends AbstractStrategyFactory{
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedField f,INakedProperty p){
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Lob.class.getName()));
			f.putAnnotation(type);
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,INakedProperty p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner,OJBlock block,INakedProperty p,String configuredValue){
			return configuredValue;
		}
		@Override
		public String getDefaultStringValue(){
			return "This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob";
		}
	}
	public static class MyTestValueStrategy implements TestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			return "This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob";
		}
	}
	@SuppressWarnings("unchecked")
	public TextStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, MyTestValueStrategy.class);
	}
	@Override
	public boolean appliesTo(INakedSimpleType st){
		return st.getName().equals("Text");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory";
	}
}
