package org.opaeum.javageneration.util;

import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;

public interface PropertyStrategy{
	public OJAnnotatedField addPersistentProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody);
	public OJAnnotatedField addTransientProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody);
}
