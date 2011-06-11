package org.tinker.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestEmbeddedSimpleTypes extends BaseLocalDbTest {

	@Test
	public void testNumbers() {
		God god = new God();
		god.setName("THEGOD");
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		Set<Integer> numbers = new HashSet<Integer>();
		numbers.add(one);
		numbers.add(two);
		numbers.add(three);
		god.setMultiNumbers(numbers);
		assertEquals(3, god.getMultiNumbers().size());
		god.addToMultiNumbers(4);
		assertEquals(4, god.getMultiNumbers().size());
		god.removeFromMultiNumbers(1);
		assertEquals(3, god.getMultiNumbers().size());
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (Integer i : god.getMultiNumbers()) {
			if (i.equals(2)) {
				found2 = true;
			}
			if (i.equals(3)) {
				found3 = true;
			}
			if (i.equals(4)) {
				found4 = true;
			}
		}
		assertTrue(found2 && found3 && found4);
	}
	
	@Test
	public void testStrings() {
		God god = new God();
		god.setName("THEGOD");
		String one = "1";
		String two = "2";
		String three = "3";
		Set<String> strings = new HashSet<String>();
		strings.add(one);
		strings.add(two);
		strings.add(three);
		god.setMultiStrings(strings);
		assertEquals(3, god.getMultiStrings().size());
		god.addToMultiStrings("4");
		assertEquals(4, god.getMultiStrings().size());
		god.removeFromMultiStrings("1");
		assertEquals(3, god.getMultiStrings().size());
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (String i : god.getMultiStrings()) {
			if (i.equals("2")) {
				found2 = true;
			}
			if (i.equals("3")) {
				found3 = true;
			}
			if (i.equals("4")) {
				found4 = true;
			}
		}
		assertTrue(found2 && found3 && found4);
	}	

}
