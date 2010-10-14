package net.sf.nakeduml.testutil;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
public class TestCase extends junit.framework.TestCase {

	public static void assertEquals(double expected, double found, double precision) {
		if (Double.isInfinite(expected)) {
			if (!Double.isInfinite(found)) {
				throw new AssertionFailedError("Expected " + expected + " but found " + found);
			}
		} else if (Double.isNaN(expected)) {
			if (!Double.isNaN(found)) {
				throw new AssertionFailedError("Expected " + expected + " but found " + found);
			}
		} else {
			Assert.assertEquals(expected, found, precision);
		}
	}
	
	public void testNada() {
		
	}
}
