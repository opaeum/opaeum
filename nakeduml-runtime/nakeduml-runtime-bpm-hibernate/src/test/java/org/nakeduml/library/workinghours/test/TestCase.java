package org.nakeduml.library.workinghours.test;

import junit.framework.AssertionFailedError;

import org.junit.Assert;



public abstract class TestCase {

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
