package net.sf.nakeduml.jbpm.test;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.util.BaseTest;

@RunWith(Arquillian.class)
public class SimpleSyncWebTest extends BaseTest {
	@Inject
	private SimpleSyncController processController;
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";
	
	@Test
	public void test() {
		Assert.assertTrue(  processController.testProcessAndTestForJOINNODE1andSHIP() );
		Assert.assertTrue(  processController.doOperationTestAndTestForActivityFinalNode1() );			
	}

}