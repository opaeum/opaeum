package org.opaeum.javageneration.basicjava;

import java.util.Set;

import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.textmetamodel.TextSource;

public class JavaStringTextSource implements TextSource{
	private String implementationCodeFor;
	public JavaStringTextSource(String implementationCodeFor){
		this.implementationCodeFor = implementationCodeFor;
	}
	@Override
	public char[] toCharArray(){
		return implementationCodeFor.toCharArray();
	}
	@Override
	public boolean hasContent(){
		return true;
	}
	public void renameAll(Set<OJPathName> affectedClasses,String suffix){
		implementationCodeFor = OJElement.replaceAll(implementationCodeFor, affectedClasses, suffix);
	}
}