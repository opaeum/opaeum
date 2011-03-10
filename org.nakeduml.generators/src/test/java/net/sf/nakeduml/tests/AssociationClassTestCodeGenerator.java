package net.sf.nakeduml.tests;


public class AssociationClassTestCodeGenerator extends AbstractTestCodeGenerator{
	protected AssociationClassTestCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		new AssociationClassTestCodeGenerator("../nakedumltest/assocationclasstests", "testmodels/").generateCodeForSingleModel("AssociationClassTests.uml");
	}
}
