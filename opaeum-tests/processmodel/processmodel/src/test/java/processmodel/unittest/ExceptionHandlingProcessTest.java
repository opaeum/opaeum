package processmodel.unittest;

import org.testng.annotations.Test;

import processmodel.processes.ExceptionHandlingProcess;
import processmodel.processes.ExceptionHandlingProcessState;

public class ExceptionHandlingProcessTest {
	@Test
	public void testIt() {
		ExceptionHandlingProcess process = new ExceptionHandlingProcess();
		process.execute();
		assert process.isStepActive(ExceptionHandlingProcessState.ACCEPTEVENTACTION);
	}
}
