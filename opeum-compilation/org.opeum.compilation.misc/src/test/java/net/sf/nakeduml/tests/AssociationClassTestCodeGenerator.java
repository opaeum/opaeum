package org.opeum.tests;


public class AssociationClassTestCodeGenerator extends AbstractTestCodeGenerator{
	protected AssociationClassTestCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		new AssociationClassTestCodeGenerator("../opeumtest/assocationclasstests", "testmodels/").generateCodeForSingleModel("AssociationClassTests.uml");
	}
}
