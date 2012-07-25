package org.eclipse.uml2.uml;

import nl.klasse.octopus.model.IPackage;

public interface INakedPackage extends INakedNameSpace, IPackage {
	INakedNameSpace getParent();

	boolean isRootPackage();

	void setMappedImplementationPackage(String generatedName);

	String getMappedImplementationPackage();

	CodeGenerationStrategy getCodeGenerationStrategy();

	void setCodeGenerationStrategy(CodeGenerationStrategy none);

	boolean isSchema();


}