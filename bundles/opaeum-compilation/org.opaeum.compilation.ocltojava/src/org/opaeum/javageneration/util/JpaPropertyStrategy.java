package org.opaeum.javageneration.util;

import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;

public class JpaPropertyStrategy extends PojoPropertyStrategy{
	@Override
	public OJAnnotatedField addTransientProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		OJAnnotatedField result = super.addTransientProperty(ojClass, name, type, withBody);
		result.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		return result;
	}
}
