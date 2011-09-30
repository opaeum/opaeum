package org.opeum.tests;


public class CMDataGeneratorTest extends AbstractTestCodeGenerator {
	protected CMDataGeneratorTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new CMDataGeneratorTest("../opeumtest/cmdatagenerationtests", "/usr/share/cm-data/cm-model").transformDirectory();
	}


}
