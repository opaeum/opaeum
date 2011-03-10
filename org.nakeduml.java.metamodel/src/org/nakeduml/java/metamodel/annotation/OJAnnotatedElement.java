package org.nakeduml.java.metamodel.annotation;
import java.util.Set;

import org.nakeduml.java.metamodel.OJPathName;

public interface OJAnnotatedElement {
	Set<OJAnnotationValue> getAnnotations();
	String toJavaString();
	OJAnnotationValue putAnnotation(OJAnnotationValue an);
	OJAnnotationValue removeAnnotation(OJPathName type);
	boolean addAnnotationIfNew(OJAnnotationValue value);
	OJAnnotationValue findAnnotation(OJPathName ojPathName);
	
}
