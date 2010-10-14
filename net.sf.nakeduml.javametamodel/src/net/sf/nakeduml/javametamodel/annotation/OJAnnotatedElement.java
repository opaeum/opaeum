package net.sf.nakeduml.javametamodel.annotation;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJPathName;
public interface OJAnnotatedElement {
	Set<OJAnnotationValue> getAnnotations();
	String toJavaString();
	OJAnnotationValue putAnnotation(OJAnnotationValue an);
	OJAnnotationValue removeAnnotation(OJPathName type);
	boolean addAnnotationIfNew(OJAnnotationValue value);
	
}
