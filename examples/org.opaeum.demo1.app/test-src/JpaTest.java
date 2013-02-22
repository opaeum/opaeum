import org.opeum.demo1.util.Demo1Environment;

import junit.framework.TestCase;

public class JpaTest extends TestCase{
	public void testIt(){
		Demo1Environment.INSTANCE.createConversationalPersistence();
	}
}
