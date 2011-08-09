package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.textmetamodel.TextSource;

import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJElement;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;


public class JavaTextSource implements TextSource{
	OJElement javaSource;
	public JavaTextSource(OJClassifier javaSource){
		super();
		this.javaSource = javaSource;
	}
	public JavaTextSource(OJPackage p){
		this.javaSource = p;
	}
	public char[] toCharArray(){
		String string = javaSource instanceof OJAnnotatedPackage ? ((OJAnnotatedPackage) javaSource).toPackageInfoString() : javaSource.toJavaString();
		return string.toCharArray();
	}
	public boolean hasContent(){
		if(javaSource instanceof OJAnnotatedPackage){
			if(((OJAnnotatedPackage) javaSource).getAnnotations().isEmpty()){
				return false;
			}
		}
		return true;
	}
}
