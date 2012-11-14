package org.opaeum.jaxb;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;

public class JaxbAnnotator{
	public static void addXmlTransient(OJAnnotatedField o){
		if(!o.isTransient()){
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient"));
			o.addAnnotationIfNew(column);
		}
	}
}
