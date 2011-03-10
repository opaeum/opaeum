package net.sf.nakeduml.tests;


public class InterfaceTestCodeGenerator extends AbstractTestCodeGenerator{
	protected InterfaceTestCodeGenerator(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new InterfaceTestCodeGenerator("../nakedumltest/interfacetests", "testmodels").generateCodeForSingleModel("InterfaceTests.uml");
	}


}
