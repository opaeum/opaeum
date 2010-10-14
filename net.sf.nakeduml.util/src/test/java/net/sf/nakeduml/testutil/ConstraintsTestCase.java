package net.sf.nakeduml.testutil;
import java.util.Collection;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import net.sf.nakeduml.util.Constrained;
public class ConstraintsTestCase extends TestCase {
	protected void assertNoOfInvariantsFailed(int number, Constrained object) {
		int i = object.getFailedInvariants().size();
		if (number != i) {
			throw new AssertionFailedError(number + " invariant were expected to fail but " + i + " have failed");
		}
	}
	protected void assertInvariantHasFailed(String invariantName, Constrained object) {
		for(String error:object.getFailedInvariants()){
			if (error.indexOf(invariantName) > 0) {
				return;
			}
		}
		throw new AssertionFailedError("Invariant '" + invariantName + "' was expected to fail but did not");
	}
	protected void assertEmpty(String name, Collection coll) {
		if (!coll.isEmpty()) {
			throw new AssertionFailedError("The collection '" + name + "' was expected to be empty but was not");
		}
	}
	protected void assertNotEmpty(String name, Collection coll) {
		if (coll.isEmpty()) {
			throw new AssertionFailedError("The collection '" + name + "' was expected not to be empty but was empty");
		}
	}
	protected void assertPreconditionHasFailed(String preconditionName, AssertionError error) {
		if (error.getMessage().indexOf(preconditionName) == -1) {
			throw new AssertionFailedError("The precondition '" + preconditionName
					+ "' was expected not to fail but did not");
		}
	}
	protected void assertPreconditionHasNotFailed(String preconditionName, AssertionError error) {
		if (error.getMessage().indexOf(preconditionName) == -1) {
			throw new AssertionFailedError("The precondition '" + preconditionName
					+ "' was expected not to fail but did not");
		}
	}
	protected void failPrecondition(String preconditionName) {
		throw new AssertionFailedError("The precondition '" + preconditionName
				+ "' was expected not to fail but did not");
	}
	protected void assertInvariantHasNotFailed(String invariantName, Constrained object) {
		for(String error:object.getFailedInvariants()){
			if (error.indexOf(invariantName) > 0) {
				throw new AssertionFailedError("Invariant '" + invariantName + "' has failed");
			}
		}
	}
	
	public void testNada() {
		
	}
	
}
