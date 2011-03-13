package net.sf.nakeduml.jaxb;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;



public class JaxbAnnotator {

	public static OJAnnotationValue addXmlTransient(OJAnnotatedOperation o){
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient"));
		o.addAnnotationIfNew(column);
		return column;
	}
}
