package net.sf.nakeduml.tests;


public class AuditTest extends AbstractTestCodeGenerator {

	protected AuditTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		AuditTest auditTest = new AuditTest("../nakedumltest/audit","testmodels/audit");
		auditTest.generateCodeForSingleModel("AuditTests.uml");
		auditTest.generateIntegrationCode();
	}


}
