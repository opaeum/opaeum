package org.opeum.jaxb;

import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;



public class JaxbAnnotator {

	public static OJAnnotationValue addXmlTransient(OJAnnotatedOperation o){
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient"));
		o.addAnnotationIfNew(column);
		return column;
	}
}
