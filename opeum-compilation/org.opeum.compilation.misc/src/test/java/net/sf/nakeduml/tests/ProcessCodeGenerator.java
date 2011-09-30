package org.opeum.tests;


public class ProcessCodeGenerator extends AbstractTestCodeGenerator{
	protected ProcessCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		new ProcessCodeGenerator("../opeumtest/processmodel", "testmodels/").generateCodeForSingleModel("processmodel.uml");
	}
}
