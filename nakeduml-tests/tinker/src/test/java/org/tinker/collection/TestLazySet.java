package org.tinker.collection;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;
import org.tinker.Universe;

public class TestLazySet extends BaseTest {
	
	int universecount = 10;
	
//	@Test
	public void testNonLazy1() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		God god = new God();
		god.setName("THEGOD");
		for (int i = 0; i < universecount; i++) {
			Universe universe =	new Universe(god);
			universe.setName("universe" + i);
		}
		stopWatch.stop();
		System.out.println("non lazy 1 " + stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		for(Universe universe : god.getUniverse()) {
			universe.getName();
		}
		stopWatch.stop();
		System.out.println("non lazy 2 " + stopWatch.toString());
	}
	
//	@Test
	public void testNonLazy2() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		God god = new God();
		god.setName("THEGOD");
		for (int i = 0; i < universecount; i++) {
			Universe universe =	new Universe(god);
			universe.setName("universe" + i);
		}
		stopWatch.stop();
		System.out.println("non lazy 1 " + stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		for(Universe universe : god.getUniverse()) {
			if (universe.getName().equals("universe1")) {
				break;
			}
		}
		stopWatch.stop();
		System.out.println("non lazy 2 " + stopWatch.toString());
	}	
	
//	@Test
	public void testLazy1() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		org.tinker.collection.God god = new org.tinker.collection.God();
		god.setName("THEGOD");
		for (int i = 0; i < universecount; i++) {
			org.tinker.collection.Universe universe =	new org.tinker.collection.Universe(god);
			universe.setName("universe" + i);
		}
		stopWatch.stop();
		System.out.println("lazy 1 " + stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		for(org.tinker.collection.Universe universe : god.getUniverse()) {
			universe.getName();
		}
		stopWatch.stop();
		System.out.println("lazy 2 " + stopWatch.toString());
	}
	
//	@Test
	public void testLazy2() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		org.tinker.collection.God god = new org.tinker.collection.God();
		god.setName("THEGOD");
		for (int i = 0; i < universecount; i++) {
			org.tinker.collection.Universe universe =	new org.tinker.collection.Universe(god);
			universe.setName("universe" + i);
		}
		stopWatch.stop();
		System.out.println("lazy 1 " + stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		for(org.tinker.collection.Universe universe : god.getUniverse()) {
			if (universe.getName().equals("universe1")) {
				break;
			}
		}
		stopWatch.stop();
		System.out.println("lazy 2 " + stopWatch.toString());
	}		
	
}
