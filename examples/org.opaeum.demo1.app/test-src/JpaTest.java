import junit.framework.TestCase;

import org.opaeum.demo1.util.Demo1Environment;

public class JpaTest extends TestCase{
	public void testIt(){
		Demo1Environment.INSTANCE.createConversationalPersistence();
	}
}
