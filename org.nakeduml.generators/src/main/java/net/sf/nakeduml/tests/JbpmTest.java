package net.sf.nakeduml.tests;

import java.io.File;

public class JbpmTest extends AbstractTestCodeGenerator{
	protected JbpmTest(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		JbpmTest jbpmTest = null;
		File f = new File("org.nakeduml.generators/testmodels/jbpm");
		if(f.exists()){
			jbpmTest = new JbpmTest("nakedumltest/jbpmtestintegration", "org.nakeduml.generators/testmodels/jbpm");
		}else{
			jbpmTest = new JbpmTest("../nakedumltest/jbpmtestintegration", "testmodels/jbpm");
		}
		jbpmTest.generateCodeForSingleModel("jbpm.uml");
		jbpmTest.generateIntegrationCode();
	}
}
