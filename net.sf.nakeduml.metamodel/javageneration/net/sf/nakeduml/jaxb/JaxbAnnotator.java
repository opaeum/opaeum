package net.sf.nakeduml.jaxb;

import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;


public class JaxbAnnotator {

	public static OJAnnotationValue addXmlTransient(OJAnnotatedOperation o){
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient"));
		o.addAnnotationIfNew(column);
		return column;
	}
}
