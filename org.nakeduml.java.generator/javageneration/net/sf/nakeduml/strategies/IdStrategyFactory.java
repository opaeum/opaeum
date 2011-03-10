package net.sf.nakeduml.strategies;

import net.sf.nakeduml.javageneration.persistence.JpaStrategy;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.AbstractStrategyFactory;

import org.hibernate.annotations.Type;
import org.hibernate.type.TextType;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

public class IdStrategyFactory extends AbstractStrategyFactory {

	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedField f, INakedProperty p) {
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			type.putAttribute("type", TextType.class.getName());
			f.putAnnotation(type);
		}

	}

}
