package net.sf.nakeduml.jbpm.test;

import javax.inject.Inject;

import net.sf.nakeduml.ripper.JbpmWebTest;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SimpleSyncWebTest extends JbpmWebTest {
	@Inject
	private SimpleSyncController processController;
	
	@Test
	public void test() {
		Assert.assertTrue(  processController.testProcessAndTestForJOINNODE1andSHIP() );
		Assert.assertTrue(  processController.doOperationTestAndTestForActivityFinalNode1() );			
	}

}