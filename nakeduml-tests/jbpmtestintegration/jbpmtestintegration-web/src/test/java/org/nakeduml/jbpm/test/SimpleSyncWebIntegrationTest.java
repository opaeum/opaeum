package org.nakeduml.jbpm.test;

import javax.inject.Inject;


import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.ripper.JbpmWebTest;

@RunWith(Arquillian.class)
public class SimpleSyncWebIntegrationTest extends JbpmWebTest {
	@Inject
	private SimpleSyncController processController;
	
	@Test
	public void test() {
		Assert.assertTrue(  processController.testProcessAndTestForJOINNODE1andSHIP() );
		Assert.assertTrue(  processController.doOperationTestAndTestForActivityFinalNode1() );			
	}

}