package org.opeum.tests;


public class InterfaceTestCodeGenerator extends AbstractTestCodeGenerator{
	protected InterfaceTestCodeGenerator(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new InterfaceTestCodeGenerator("../opeumtest/interfacetests", "testmodels").generateCodeForSingleModel("InterfaceTests.uml");
	}


}
