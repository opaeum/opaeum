package org.opaeum.jaxb;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;



public class JaxbAnnotator {

	public static OJAnnotationValue addXmlTransient(OJAnnotatedOperation o){
		if(o==null){
			System.out.println();
		}
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient"));
		o.addAnnotationIfNew(column);
		return column;
	}
}
