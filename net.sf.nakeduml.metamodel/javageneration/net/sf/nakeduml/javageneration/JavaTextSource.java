package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.textmetamodel.TextSource;

public class JavaTextSource implements TextSource {
	public static final String TEST_SRC = "test-src";
	public static final String GEN_SRC = "gen-src";
	public static final String JPA_ROOT = "jpaRoot";

	OJElement javaSource;

	public JavaTextSource(OJClassifier javaSource) {
		super();
		this.javaSource = javaSource;
	}

	public JavaTextSource(OJPackage p) {
		this.javaSource = p;
	}

	public char[] toCharArray() {
		String string = javaSource instanceof OJAnnotatedPackage? ((OJAnnotatedPackage)javaSource).toPackageInfoString():javaSource.toJavaString();
		return string.toCharArray();
	}

	public boolean hasContent(){
		if(javaSource instanceof OJAnnotatedPackage){
			if(((OJAnnotatedPackage)javaSource).getAnnotations().isEmpty()){
				return false;
			}
		}
		return true;
	}
}
