package org.opeum.strategies;

import org.opeum.javageneration.persistence.JpaStrategy;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.workspace.AbstractStrategyFactory;

import org.hibernate.annotations.Type;
import org.hibernate.type.TextType;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;

public class IdStrategyFactory extends AbstractStrategyFactory {

	public static class MyJpaStrategy implements JpaStrategy {

		@Override
		public void annotate(OJAnnotatedField f, INakedProperty p) {
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			type.putAttribute("type", TextType.class.getName());
			f.putAnnotation(type);
		}

	}

	@Override
	public boolean appliesTo(INakedSimpleType st){
		return false;
	}

}
