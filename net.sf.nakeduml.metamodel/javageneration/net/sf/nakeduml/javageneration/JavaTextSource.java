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
	public static final String NAKED_PROJECT_ROOT = "nakedProjectRoot";
	public static final String NAKED_PROJECT_EAR_ROOT = "nakedProjectEarRoot";
	public static final String NAKED_PROJECT_WAR_ROOT = "nakedProjectWarRoot";
	public static final String NAKED_PROJECT_EJB_ROOT = "nakedProjectEjbRoot";

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
