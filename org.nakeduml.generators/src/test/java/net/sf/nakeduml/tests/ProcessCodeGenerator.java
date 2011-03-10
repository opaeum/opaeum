package net.sf.nakeduml.tests;


public class ProcessCodeGenerator extends AbstractTestCodeGenerator{
	protected ProcessCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		new ProcessCodeGenerator("../nakedumltest/processmodel", "testmodels/").generateCodeForSingleModel("processmodel.uml");
	}
}
