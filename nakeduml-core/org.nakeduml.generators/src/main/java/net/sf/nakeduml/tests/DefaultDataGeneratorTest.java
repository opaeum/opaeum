package net.sf.nakeduml.tests;


public class DefaultDataGeneratorTest extends AbstractTestCodeGenerator {

	protected DefaultDataGeneratorTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		DefaultDataGeneratorTest dataGenerationTest = new DefaultDataGeneratorTest("../nakedumltest/datageneration","testmodels/datageneration");
		dataGenerationTest.generateCodeForSingleModel("DataGenerationTests.uml");
		dataGenerationTest.generateIntegrationCode();
	}


}
