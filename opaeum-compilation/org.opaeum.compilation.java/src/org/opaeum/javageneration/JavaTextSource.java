package org.opaeum.javageneration;

import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.textmetamodel.TextSource;

public class JavaTextSource implements TextSource{
	private OJElement javaSource;
	private JavaSourceKind kind=JavaSourceKind.NORMAL;
	public JavaTextSource(OJClassifier javaSource){
		super();
		this.setJavaSource(javaSource);
	}
	public JavaTextSource(JavaSourceKind kind,OJAnnotatedClass javaSource){
		super();
		this.kind=kind;
		this.setJavaSource(javaSource);
	}
	public JavaTextSource(OJAnnotatedPackageInfo p){
		this.setJavaSource(p);
	}
	public char[] toCharArray(){
		String string = null;
		switch(kind){
		case ABSTRACT_SUPERCLASS:
			string = ((OJAnnotatedClass) getJavaSource()).toAbstractSuperclassJavaString();
			break;
		case CONCRETE_IMPLEMENTATION:
			string = ((OJAnnotatedClass) getJavaSource()).toConcreteImplementationJavaString();
			break;
		case NORMAL:
			string =getJavaSource().toJavaString();
			break;
		}
		return string.toCharArray();
	}
	public boolean hasContent(){
		if(getJavaSource() instanceof OJAnnotatedPackageInfo){
			if(((OJAnnotatedPackageInfo) getJavaSource()).getAnnotations().isEmpty()){
				return false;
			}
		}
		return true;
	}
	public OJElement getJavaSource(){
		return javaSource;
	}
	public void setJavaSource(OJElement javaSource){
		this.javaSource = javaSource;
	}
}
