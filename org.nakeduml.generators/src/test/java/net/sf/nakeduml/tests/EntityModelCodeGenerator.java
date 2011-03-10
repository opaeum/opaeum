package net.sf.nakeduml.tests;


public class EntityModelCodeGenerator extends AbstractTestCodeGenerator{
	protected EntityModelCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		new EntityModelCodeGenerator("../nakedumltest/entitymodel", "testmodels").generateCodeForSingleModel("entitymodel.uml");
	}
}
