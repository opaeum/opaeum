package net.sf.nakeduml.jbpm.test;

import net.sf.nakeduml.ripper.RipperInitData;

import org.junit.Before;
import org.nakeduml.environment.Environment;

public class BaseTest {

	@Before
	public void beforeTest() {
		Environment.getInstance().reset();
	}
	
}
